package org.xu.admin.dto;

import lombok.Data;
import org.xu.admin.entity.Employee;

@Data
public class EmployeeDTO extends Employee {
    /**
     * 额外增加的部门名称字段
     */
    private String departmentName;
}