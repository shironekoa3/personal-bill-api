package com.example.personalbillapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.personalbillapi.entity.Bill;
import com.example.personalbillapi.entity.Category;
import com.example.personalbillapi.mapper.BillMapper;
import com.example.personalbillapi.service.IBillService;
import com.example.personalbillapi.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
public class BillServiceImpl extends ServiceImpl<BillMapper, Bill> implements IBillService {

    private final BillMapper billMapper;
    private final ICategoryService categoryService;

    @Override
    public List<Bill> list(Wrapper<Bill> queryWrapper) {
        List<Bill> list = super.list(queryWrapper);

        if (!list.isEmpty()) {
            List<Long> categoryIds = list.stream()
                    .map(Bill::getCategoryId)
                    .distinct()
                    .collect(Collectors.toList());

            LambdaQueryWrapper<Category> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.in(Category::getId, categoryIds);
            List<Category> categoryList = categoryService.list(lambdaQueryWrapper);

            Map<Long, Category> categoryListMap = new HashMap<>();
            categoryList.forEach(i -> {
                categoryListMap.put(i.getId(), i);
            });

            list.forEach(i -> {
                i.setCategory(categoryListMap.get(i.getCategoryId()));
            });
        }
        return list;
    }

    @Override
    public boolean existByOrderId(String orderId) {
        return billMapper.existByOrderId(orderId) > 0;
    }
}
