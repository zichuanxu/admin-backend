package org.xu.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

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

    private String avatarUrl; // 对应数据库 avatar_url
}
