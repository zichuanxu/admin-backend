package org.xu.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xu.admin.annotation.Auth;
import org.xu.admin.common.Result;
import org.xu.admin.dto.EmployeeDTO;
import org.xu.admin.entity.Employee;
import org.xu.admin.service.EmployeeService;

import java.util.List;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/page")
    @Auth(mustAdmin = true)
    public Result<IPage<EmployeeDTO>> selectPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                                 @RequestParam(defaultValue = "10") Integer pageSize) {
        // 此时返回的是带有 departmentName 的 DTO 分页对象
        IPage<EmployeeDTO> pageData = employeeService.getEmployeesWithDeptPage(pageNum, pageSize);
        return Result.success(pageData);
    }

    @GetMapping("/all")
    @Auth(mustAdmin = true)
    public Result<List<EmployeeDTO>> selectAll() {
        return Result.success(employeeService.getAllEmployeesWithDept()); // 直接查全表，不分页
    }

    /**
     * 新增员工
     */
    @PostMapping("/add")
    @Auth(mustAdmin = true)
    public Result<Boolean> add(@RequestBody Employee employee) {
        return Result.success(employeeService.saveEmployee(employee));
    }

    /**
     * 更新员工信息
     */
    @PutMapping("/update")
    @Auth(mustAdmin = true)
    public Result<Boolean> update(@RequestBody Employee employee) {
        return Result.success(employeeService.updateEmployee(employee));
    }

    /**
     * 单个删除
     */
    @DeleteMapping("/{id}")
    @Auth(mustAdmin = true)
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.success(employeeService.deleteEmployee(id));
    }

    /**
     * 批量删除
     */
    @PostMapping("/delete/batch")
    @Auth(mustAdmin = true)
    public Result<Boolean> deleteBatch(@RequestBody List<Long> ids) {
        return Result.success(employeeService.deleteBatch(ids));
    }

    @GetMapping("/{id}")
    @Auth(mustAdmin = true)
    public Result<Employee> getById(@PathVariable Long id) {
        return Result.success(employeeService.selectById(id));
    }
}
