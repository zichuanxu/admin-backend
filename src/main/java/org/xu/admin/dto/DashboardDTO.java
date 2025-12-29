package org.xu.admin.dto;

import lombok.Data;
import java.util.Map;

@Data
public class DashboardDTO {
    // 核心数字
    private Long totalUsers;
    private Long totalEmployees;
    private Long totalArticles;
    private Long totalDepartments;

    // 图表数据
    // 例如：Key是部门名称，Value是人数
    private Map<String, Long> employeeInDeptCount;
}