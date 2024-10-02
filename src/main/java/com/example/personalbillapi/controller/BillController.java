package com.example.personalbillapi.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.personalbillapi.entity.Bill;
import com.example.personalbillapi.service.IBillResolvingService;
import com.example.personalbillapi.service.IBillService;
import com.example.personalbillapi.service.impl.BillResolvingServiceImpl;
import com.example.personalbillapi.utils.AjaxResult;
import com.example.personalbillapi.utils.ExcelUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author shironekoa3
 * @since 2023-10-22
 */
@RestController
@RequestMapping("/bill")
@RequiredArgsConstructor
@Slf4j
public class BillController {

    private final IBillService billService;
    private final IBillResolvingService billResolvingService;

    @PostMapping("/list")
    public AjaxResult listAndSearch(@RequestBody(required = false) List<String> date) {
        LambdaQueryWrapper<Bill> billLambdaQueryWrapper = null;
        if (date != null && date.size() == 2) {
            billLambdaQueryWrapper = new LambdaQueryWrapper<>();
            billLambdaQueryWrapper.between(Bill::getDate, date.get(0), date.get(1));
        }
        return AjaxResult.success(billService.list(billLambdaQueryWrapper));
    }

    @GetMapping("/delete/{id}")
    public AjaxResult delete(@PathVariable Long id) {
        if (billService.removeById(id)) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }

    @PostMapping("/add")
    public AjaxResult add(@RequestBody Bill bill) {
        if (bill.getMethod().equals("")) {
            return AjaxResult.error("支付方式不能为空！");
        }
        if (billService.saveOrUpdate(bill)) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }


    @PostMapping("/upload")
    public AjaxResult uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return AjaxResult.error("文件为空！");
        }

        List<List<String>> lists = new ArrayList<>();

        try {
            String[] fileNameArray = file.getOriginalFilename().split("\\.");
            String fileType = fileNameArray[fileNameArray.length - 1];
            if (fileType.equals("csv")) {
                lists = ExcelUtils.parseCSVToArrayList(file.getBytes());
            } else if (fileType.equals(".xlsx") || fileType.equals(".xls")) {
                lists = ExcelUtils.readExcelFromStream(file.getBytes());
            }
        } catch (Exception e) {
            return AjaxResult.error("文件读取失败！");
        }

        String key = "";
        if (!lists.isEmpty() && lists.get(0).get(0).contains("微信支付账单明细")) {
            key = billResolvingService.resolvingBillOnWeChat(lists);
        } else if (lists.size() > 21 && lists.get(21).get(0).contains("支付宝")) {
            key = billResolvingService.resolvingBillOnAlipay(lists);
        } else {
            return AjaxResult.error("文件解析失败，目前只支持支付宝和微信的账单导入！");
        }

        if (!key.isEmpty()) {
            Map<String, String> result = new HashMap<>();
            result.put("key", key);
            result.put("length", String.valueOf(BillResolvingServiceImpl._resolvingResult.get(key).size()));
            return AjaxResult.success("", result);
        } else {
            return AjaxResult.error("文件解析失败！");
        }
    }

    @PostMapping("/importByKey")
    public AjaxResult importByKey(@RequestParam("key") String key) {
        if (!BillResolvingServiceImpl._resolvingResult.containsKey(key)) {
            return AjaxResult.error("参数异常！");
        }

        AtomicInteger count = new AtomicInteger();
        AtomicInteger duplicationCount = new AtomicInteger();
        BillResolvingServiceImpl._resolvingResult.get(key).forEach(i -> {
            if (!billService.existByOrderId(i.getOrderId())) {
                billService.save(i);
                count.getAndIncrement();
            } else {
                duplicationCount.getAndIncrement();
            }
        });
        BillResolvingServiceImpl._resolvingResult.remove(key);


        return AjaxResult.success(
                String.format("重复账单 %d 条，已导入账单 %d 条！", duplicationCount.get(), count.get())
        );
    }

    @PostMapping("/cancelImportByKey")
    public AjaxResult cancelImportByKey(@RequestParam("key") String key) {
        if (!BillResolvingServiceImpl._resolvingResult.containsKey(key)) {
            return AjaxResult.error("参数异常！");
        }
        BillResolvingServiceImpl._resolvingResult.remove(key);
        return AjaxResult.success();
    }

}
