package org.xu.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xu.admin.annotation.Auth;
import org.xu.admin.common.Result;
import org.xu.admin.dto.EmployeeDTO;
import org.xu.admin.entity.Employee;
import org.xu.admin.service.IEmployeeService;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

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

    @PostMapping("/import")
    @Auth(mustAdmin = true)
    public Result<Boolean> importData(MultipartFile file) {
        try {
            employeeService.importExcel(file);
            return Result.success(true);
        } catch (Exception e) {
            // 返回友好的错误提示，而不是只在控制台报错
            return Result.error("导入失败: " + e.getMessage());
        }
    }

    @PostMapping("/export")
    @Auth(mustAdmin = true)
    public void export(HttpServletResponse response, @RequestBody(required = false) List<Long> ids) throws IOException {
        // 注意：导出接口通常返回 void，通过 response流输出，且不包装 Result
        employeeService.export(response, ids);
    }
}
