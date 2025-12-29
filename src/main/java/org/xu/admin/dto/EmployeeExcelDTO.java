package org.xu.admin.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 员工实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeExcelDTO {

    @ExcelProperty("name")
    private String name;

    @ExcelProperty("gender")
    private String gender;

    @ExcelProperty("no")
    private String no;

    @ExcelProperty("description")
    private String description;

    @ExcelProperty("departmentName")
    private String departmentName;

}