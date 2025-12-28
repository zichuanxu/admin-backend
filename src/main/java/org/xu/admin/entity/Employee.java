package org.xu.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 员工实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("employee")
public class Employee {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String gender;

    /**
     * 工号
     */
    private String no;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 个人介绍
     * 对应数据库中的 TEXT 类型
     */
    private String description;

    /**
     * 部门
     */
    private Integer departmentId;
    /**
     * 入职/创建时间
     * 对应数据库中的 DATETIME
     */
    private LocalDateTime createdAt;
}