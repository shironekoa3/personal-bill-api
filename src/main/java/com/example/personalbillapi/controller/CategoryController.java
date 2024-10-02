package com.example.personalbillapi.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.personalbillapi.entity.Category;
import com.example.personalbillapi.service.ICategoryService;
import com.example.personalbillapi.utils.AjaxResult;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author shironekoa3
 * @since 2023-10-22
 */
@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService categoryService;

    @GetMapping("/list")
    public AjaxResult listAndSearch(@RequestParam(required = false) String searchKey) {
        LambdaQueryWrapper<Category> categoryLambdaQueryWrapper = null;
        if (StringUtils.hasLength(searchKey)) {
            categoryLambdaQueryWrapper = new LambdaQueryWrapper<>();
            categoryLambdaQueryWrapper.like(Category::getName, searchKey);
            categoryLambdaQueryWrapper.or().like(Category::getDescription, searchKey);
        }
        return AjaxResult.success(categoryService.list(categoryLambdaQueryWrapper));
    }

    @PostMapping("/add")
    public AjaxResult add(@RequestBody Category category) {
        if (category.getName().equals("")) {
            return AjaxResult.error("分类名称不能为空！");
        }
        if (categoryService.saveOrUpdate(category)) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }

    @GetMapping("/delete/{id}")
    public AjaxResult delete(@PathVariable Long id) {
        if (categoryService.removeById(id)) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }
}
