package com.ijavac.springcloud.service.impl;

import com.ijavac.springcloud.dao.AccountDao;
import com.ijavac.springcloud.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @ClassName : AccountServiceImpl
 * @Description : TODO
 * @Author : ijavac
 * @date: 2020-04-05 15:16
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    AccountDao accountDao;
    @Override
    public void decrease(Long userId, BigDecimal money) {
//        try {
//            TimeUnit.SECONDS.sleep(6);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        accountDao.decrease(userId, money);
    }
}
