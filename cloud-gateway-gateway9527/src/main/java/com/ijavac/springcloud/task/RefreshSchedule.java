package com.ijavac.springcloud.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 补发定时任务
 */
@Component
public class RefreshSchedule {
    @Autowired
    private ApplicationEventPublisher publisher;

    private final static Logger logger = LoggerFactory.getLogger(RefreshSchedule.class);

    // 每10s一次
    @Scheduled(fixedDelay = 10000)
    public void reissueOrder() {
        logger.info("刷新路由定时任务");
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
    }

}
