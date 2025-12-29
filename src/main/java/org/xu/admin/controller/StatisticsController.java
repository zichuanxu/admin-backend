package org.xu.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xu.admin.common.Result;
import org.xu.admin.dto.DashboardDTO;
import org.xu.admin.dto.EmployeeDTO;
import org.xu.admin.service.IArticleService;
import org.xu.admin.service.IDepartmentService;
import org.xu.admin.service.IEmployeeService;
import org.xu.admin.service.IUserService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IArticleService articleService;
    @Autowired
    private IDepartmentService departmentService;

    @GetMapping("/dashboard")
    public Result<DashboardDTO> getDashboardData() {
        DashboardDTO dto = new DashboardDTO();

        // 1. 获取基础计数 (MyBatis Plus 提供的 count 方法)
        dto.setTotalUsers(userService.count());
        dto.setTotalArticles(articleService.count());
        dto.setTotalDepartments(departmentService.count());

        List<EmployeeDTO> employees = employeeService.getAllEmployeesWithDept();
        dto.setTotalEmployees((long) employees.size());

        // 2. 组装图表数据：部门人数分布
        // 逻辑：遍历员工列表，按部门名称分组计数
        // 如果数据量巨大，这部分逻辑应移至 SQL (SELECT dept_name, count(*) GROUP BY dept_name)
        Map<String, Long> deptCountMap = employees.stream()
                .map(EmployeeDTO::getDepartmentName) // 假设 DTO 里有 DepartmentName
                .filter(name -> name != null) // 过滤掉无部门的
                .collect(Collectors.groupingBy(name -> name, Collectors.counting()));

        dto.setEmployeeInDeptCount(deptCountMap);

        return Result.success(dto);
    }
}