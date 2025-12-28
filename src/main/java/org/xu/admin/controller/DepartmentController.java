package org.xu.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xu.admin.common.Result;
import org.xu.admin.entity.Department;
import org.xu.admin.service.DepartmentService;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // 1. 获取全量列表 (用于搜索缓存或下拉框)
    @GetMapping("/all")
    public Result<List<Department>> getAll(){
        return Result.success(departmentService.list());
    }

    // 2. 分页查询
    @GetMapping("/page")
    public Result<Page<Department>> getPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                            @RequestParam(defaultValue = "10") Integer pageSize) {
        return Result.success(departmentService.page(new Page<>(pageNum, pageSize)));
    }

    // 3. 新增
    @PostMapping
    public Result<Boolean> save(@RequestBody Department department) {
        return Result.success(departmentService.save(department));
    }

    // 4. 更新
    @PutMapping
    public Result<Boolean> update(@RequestBody Department department) {
        return Result.success(departmentService.updateById(department));
    }

    // 5. 单个删除
    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Integer id) {
        return Result.success(departmentService.removeById(id));
    }

    // 6. 批量删除
    @PostMapping("/delete/batch")
    public Result<Boolean> deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(departmentService.removeByIds(ids));
    }

    // 7. 获取单条详情
    @GetMapping("/{id}")
    public Result<Department> getById(@PathVariable Integer id) {
        return Result.success(departmentService.getById(id));
    }
}