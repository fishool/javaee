package com.ijavac.springcloud.controller;

import com.ijavac.springcloud.entities.CommonResult;
import com.ijavac.springcloud.service.AccountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author zzyy
 * @date 2020/3/8 14:21
 **/
@RestController
public class AccountController {
    @Resource
    private AccountService accountService;

    @RequestMapping(value = "account/decrease")
    public CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money) {
        accountService.decrease(userId, money);
        return new CommonResult(200, "扣减账户余额成功");
    }
}
