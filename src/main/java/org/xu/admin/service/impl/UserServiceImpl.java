package org.xu.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xu.admin.dto.UserDTO;
import org.xu.admin.entity.User;
import org.xu.admin.mapper.UserMapper;
import org.xu.admin.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Page<UserDTO> getUserListPage(Integer pageNum, Integer pageSize) {
        Page<User> page = new Page<>(pageNum, pageSize);
        userMapper.selectPage(page, null);
        Page<UserDTO> dtoPage = (Page<UserDTO>) page.convert(user -> {
            UserDTO dto = new UserDTO();
            // 使用 BeanUtils 拷贝属性（前提是字段名一致）
            BeanUtils.copyProperties(user, dto);
            return dto;
        });
        return dtoPage;
    }

    @Override
    public Page<UserDTO> getUserListPage(Integer pageNum, Integer pageSize, boolean admin) {
        Page<User> page = new Page<>(pageNum, pageSize);

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getAdmin, admin?1:0)
                .orderByDesc(User::getCreatedAt);
        userMapper.selectPage(page, wrapper);
        Page<UserDTO> dtoPage = (Page<UserDTO>) page.convert(user -> {
            UserDTO dto = new UserDTO();
            // 使用 BeanUtils 拷贝属性（前提是字段名一致）
            BeanUtils.copyProperties(user, dto);
            return dto;
        });
        return dtoPage;
    }

    @Override
    public boolean save(User user) {
        return super.save(user);
    }

    @Override
    public boolean updateById(User user) {
        return super.updateById(user);
    }

    @Override
    public boolean removeById(Integer id) {
        return super.removeById(id);
    }

    @Override
    public boolean removeByIds(List<Integer> ids) {
        return super.removeByIds(ids);
    }

    @Override
    public User getById(Integer id) {
        return super.getById(id);
    }
}
