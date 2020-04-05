package com.ijavac.springcloud.service;

import java.math.BigDecimal;

/**
 * @ClassName : AccountService
 * @Description : TODO
 * @Author : ijavac
 * @date: 2020-04-05 15:12
 */
public interface AccountService {
   void decrease(Long userId, BigDecimal money);
}
