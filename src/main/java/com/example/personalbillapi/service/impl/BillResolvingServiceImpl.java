package com.example.personalbillapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.personalbillapi.entity.Bill;
import com.example.personalbillapi.mapper.BillMapper;
import com.example.personalbillapi.service.IBillResolvingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author shironekoa3
 * @since 2023-10-22
 */
@Service
@RequiredArgsConstructor
public class BillResolvingServiceImpl extends ServiceImpl<BillMapper, Bill> implements IBillResolvingService {

    public static final HashMap<String, List<Bill>> _resolvingResult = new HashMap<>();

    @Override
    public String resolvingBillOnWeChat(List<List<String>> lists) {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        List<Bill> billList = new ArrayList<>();

        try {
            int startRowIndex = 17;
            List<String> tempRowData = null;
            while (startRowIndex < lists.size() && !(tempRowData = lists.get(startRowIndex++)).get(0).isEmpty()) {
                if (tempRowData.get(4).equals("/")) {
                    continue;
                }
                Bill b = new Bill();
                b.setOrderId(tempRowData.get(8).replace("\t", ""));
                b.setMethod("微信");
                b.setDate(LocalDateTime.parse(tempRowData.get(0), formatter));
                b.setType(tempRowData.get(4).equals("支出") ? 0 : 1);
                b.setAmount(Double.parseDouble(tempRowData.get(5).replace("¥", "")));
                b.setDescription(tempRowData.get(2) + ": " + tempRowData.get(3));
                billList.add(b);
            }

            String key = UUID.randomUUID().toString();
            _resolvingResult.put(key, billList);
            return key;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "";
    }

    @Override
    public String resolvingBillOnAlipay(List<List<String>> lists) {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        List<Bill> billList = new ArrayList<>();

        try {
            int startRowIndex = 23;
            List<String> tempRowData = null;
            while (startRowIndex < lists.size() && !(tempRowData = lists.get(startRowIndex++)).get(0).isEmpty()) {
                if (tempRowData.get(5).equals("不计收支")) {
                    continue;
                }
                Bill b = new Bill();
                b.setOrderId(tempRowData.get(9).replace("\t", ""));
                b.setMethod("支付宝");
                b.setDate(LocalDateTime.parse(tempRowData.get(0), formatter));
                b.setType(tempRowData.get(5).equals("支出") ? 0 : 1);
                b.setAmount(Double.parseDouble(tempRowData.get(6)));
                b.setDescription(tempRowData.get(4));
                billList.add(b);
            }

            String key = UUID.randomUUID().toString();
            _resolvingResult.put(key, billList);
            return key;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "";
    }
}
