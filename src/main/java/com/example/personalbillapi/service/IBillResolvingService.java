package com.example.personalbillapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.personalbillapi.entity.Bill;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author shironekoa3
 * @since 2023-10-22
 */
public interface IBillResolvingService extends IService<Bill> {

    String resolvingBillOnWeChat(List<List<String>> lists);
    String resolvingBillOnAlipay(List<List<String>> lists);

}
