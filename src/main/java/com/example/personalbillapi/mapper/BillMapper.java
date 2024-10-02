package com.example.personalbillapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.personalbillapi.entity.Bill;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author shironekoa3
 * @since 2023-10-22
 */
public interface BillMapper extends BaseMapper<Bill> {
    int existByOrderId(@Param("orderId") String orderId);
}
