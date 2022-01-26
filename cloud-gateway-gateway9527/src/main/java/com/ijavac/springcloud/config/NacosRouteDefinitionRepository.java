package com.ijavac.springcloud.config;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.*;

/**
 * nacos 路由数据源
 */
@Configuration
public class NacosRouteDefinitionRepository implements RouteDefinitionRepository {
    @Value("${spring.cloud.nacos.discovery.server-addr}")
    private String nacosAddress;
    private String loginUrl = "http://{}/nacos/v1/auth/users/login";
    String serviceListUrl = "http://{}/nacos/v1/ns/catalog/services?hasIpCount=true&withInstances=false&pageNo=1&pageSize=10000&serviceNameParam=&groupNameParam=&accessToken={}&namespaceId=";
    private Map logParam ;
    @PostConstruct
    public void init() {
        logParam = new HashMap<>();
        logParam.put("username","nacos");
        logParam.put("password", "851158220");
    }


    private static final Logger logger = LoggerFactory.getLogger(NacosRouteDefinitionRepository.class);

    private ApplicationEventPublisher publisher;

    public NacosRouteDefinitionRepository(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        logger.info("开始刷新动态路由");
        List<RouteDefinition> routeDefinitions = getListByNacos();
        return Flux.fromIterable(routeDefinitions);
    }


    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return null;
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return null;
    }

    private List<RouteDefinition> getListByNacos() {
        ArrayList<RouteDefinition> resultList = new ArrayList<>(16);
        String logAddress = StrUtil.format(loginUrl, nacosAddress);
        String loginResult = HttpUtil.post(logAddress, logParam);
        String accessToken = JSONUtil.parseObj(loginResult).getStr("accessToken");
        String serviceResult = HttpUtil.get(StrUtil.format(serviceListUrl, nacosAddress, accessToken));

        JSONArray serviceList = JSONUtil.parseObj(serviceResult).getJSONArray("serviceList");
        Set<String> serverNameSet = new HashSet<>();
        for (int i = 0; i < serviceList.size(); i++) {
            serverNameSet.add(serviceList.getJSONObject(i).getStr("name"));
        }

        for (String serverName : serverNameSet) {
            RouteDefinition routeDefinition = new RouteDefinition();
            routeDefinition.setId(serverName);
            routeDefinition.setUri(URI.create("lb://"+serverName));
            List<PredicateDefinition> predicateDefinitions = new ArrayList<>();
            PredicateDefinition predicateDefinition = new PredicateDefinition();
            predicateDefinition.setName("Path");
            predicateDefinition.addArg("Path", "/"+serverName+"/**");
            predicateDefinitions.add(predicateDefinition);
            routeDefinition.setPredicates(predicateDefinitions);

            List<FilterDefinition> filters = new ArrayList<>();
            FilterDefinition filterDefinition = new FilterDefinition();
            filterDefinition.setName("StripPrefix");
            filterDefinition.addArg("StripPrefix", "1");
            filters.add(filterDefinition);
            routeDefinition.setFilters(filters);

            resultList.add(routeDefinition);
        }

        return resultList;
    }
}
