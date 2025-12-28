package org.xu.admin.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.xu.admin.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
