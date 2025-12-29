package org.xu.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xu.admin.annotation.Auth;
import org.xu.admin.common.Result;
import org.xu.admin.entity.Department;
import org.xu.admin.service.IDepartmentService;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    // 1. 获取全量列表 (用于搜索缓存或下拉框)
    @GetMapping("/all")
    @Auth(mustAdmin = true)
    public Result<List<Department>> getAll() {
        return Result.success(departmentService.list());
    }

    // 2. 分页查询
    @GetMapping("/page")
    @Auth(mustAdmin = true)
    public Result<Page<Department>> getPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                            @RequestParam(defaultValue = "10") Integer pageSize) {
        return Result.success(departmentService.page(new Page<>(pageNum, pageSize)));
    }

    // 3. 新增
    @PostMapping
    @Auth(mustAdmin = true)
    public Result<Boolean> save(@RequestBody Department department) {
        return Result.success(departmentService.save(department));
    }

    // 4. 更新
    @PutMapping
    @Auth(mustAdmin = true)
    public Result<Boolean> update(@RequestBody Department department) {
        return Result.success(departmentService.updateById(department));
    }

    // 5. 单个删除
    @DeleteMapping("/{id}")
    @Auth(mustAdmin = true)
    public Result<Boolean> delete(@PathVariable Integer id) {
        return Result.success(departmentService.removeById(id));
    }

    // 6. 批量删除
    @PostMapping("/delete/batch")
    @Auth(mustAdmin = true)
    public Result<Boolean> deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(departmentService.removeByIds(ids));
    }

    // 7. 获取单条详情
    @GetMapping("/{id}")
    @Auth(mustAdmin = true)
    public Result<Department> getById(@PathVariable Integer id) {
        return Result.success(departmentService.getById(id));
    }
}