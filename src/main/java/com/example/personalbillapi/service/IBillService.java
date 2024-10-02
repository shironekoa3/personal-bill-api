package com.example.personalbillapi.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.example.personalbillapi.entity.Bill;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.poi.ss.formula.functions.T;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author shironekoa3
 * @since 2023-10-22
 */
public interface IBillService extends IService<Bill> {

    boolean existByOrderId(String orderId);

}
