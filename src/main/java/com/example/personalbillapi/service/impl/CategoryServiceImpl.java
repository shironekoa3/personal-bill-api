package com.example.personalbillapi.service.impl;

import com.example.personalbillapi.entity.Category;
import com.example.personalbillapi.mapper.CategoryMapper;
import com.example.personalbillapi.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shironekoa3
 * @since 2023-10-22
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}
