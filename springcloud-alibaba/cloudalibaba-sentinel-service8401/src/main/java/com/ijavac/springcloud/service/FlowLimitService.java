package com.ijavac.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.ijavac.springcloud.entities.CommonResult;
import com.ijavac.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName : FlowLimitService
 * @Description : TODO
 * @Author : ijavac
 * @date: 2020-04-04 17:24
 */
@Service
@Slf4j
public class FlowLimitService {

    @SentinelResource("result")
    public CommonResult getCommonResult() {
        return new CommonResult(200, "by url限流测试OK", new Payment(2020L, IdUtil.simpleUUID()));
    }
}
