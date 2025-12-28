package org.xu.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * User 实体类
 * 对应数据库表: user
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User {

    /**
     * 主键 ID，自增
     * 对应 SQL 类型: int
     */
    private Integer id;

    /**
     * 用户名，唯一
     * 对应 SQL 类型: varchar(50)
     */
    private String username;

    /**
     * 密码
     * 对应 SQL 类型: varchar(255)
     */
    private String password;

    /**
     * 邮箱，允许为空
     * 对应 SQL 类型: varchar(100)
     */
    private String email;

    /**
     * 状态 (例如: 1-正常, 0-禁用)
     * 对应 SQL 类型: tinyint
     * 使用 Integer 或 Byte 对应 tinyint
     */
    private Integer status;

    /**
     * 创建时间
     * 对应 SQL 类型: datetime
     * 默认值: CURRENT_TIMESTAMP
     */
    private LocalDateTime createdAt;

    /**
     * 是否为管理员 (0-否, 1-是)
     * 对应 SQL 类型: tinyint
     */
    private Integer admin;
}
