package com.ijavac.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @ClassName : AccountDao
 * @Description : TODO
 * @Author : ijavac
 * @date: 2020-04-05 15:13
 */
@Mapper
public interface AccountDao {
    /**
     * 扣减账户余额
     *
     * @param userId
     * @param money
     * @return
     */
    int decrease(@Param("userId") Long userId, @Param("money") BigDecimal money);
}
