package org.xu.admin.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xu.admin.common.BaseException;
import org.xu.admin.common.Constants;
import org.xu.admin.common.UserContext;
import org.xu.admin.dto.ChangePasswordDTO;
import org.xu.admin.dto.LoginDTO;
import org.xu.admin.dto.RegisterDTO;
import org.xu.admin.dto.UserDTO;
import org.xu.admin.entity.User;
import org.xu.admin.mapper.UserMapper;
import org.xu.admin.service.IUserService;
import org.xu.admin.utils.JwtUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserDTO> getUserList(boolean admin) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getAdmin, admin?1:0)
                .orderByDesc(User::getCreatedAt);
        List<User> users = super.list(wrapper);
        List<UserDTO> dtoList = new ArrayList<>();
        for (User user : users) {
            UserDTO dto = new UserDTO();
            BeanUtils.copyProperties(user, dto);
            dtoList.add(dto);
        }
        return dtoList;
    }

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
    public UserDTO getById(Integer id) {
        User user = super.getById(id);
        UserDTO dto = new UserDTO();
        // 使用 BeanUtils 拷贝属性（前提是字段名一致）
        BeanUtils.copyProperties(user, dto);
        return dto;
    }

    @Override
    public String login(LoginDTO loginUser) {
        // 1. 根据用户名查询
        User dbUser = lambdaQuery().eq(User::getUsername, loginUser.getUsername()).one();
        if (dbUser == null) {
            throw new BaseException("用户不存在");
        }

        // 2. 校验密码 (使用 BCrypt)
        if (!BCrypt.checkpw(loginUser.getPassword(), dbUser.getPassword())) {
            throw new BaseException("密码错误");
        }

        // 3. 校验状态
        if (dbUser.getStatus() == Constants.USER_DISABLED) {
            throw new BaseException("账号已被禁用");
        }

        // 4. 生成 Token
        return JwtUtils.createToken(dbUser);
    }

    @Override
    public boolean register(RegisterDTO dto) {
        User user = new User();
        // 1. 校验用户名唯一
        Long count = lambdaQuery().eq(User::getUsername, dto.getUsername()).count();
        if (count > 0) {
            throw new BaseException("用户名已存在");
        }

        user.setUsername(dto.getUsername());

        // 2. 加密密码
        String rawPassword = dto.getPassword();
        String encodedPassword = BCrypt.hashpw(rawPassword, BCrypt.gensalt());
        user.setPassword(encodedPassword);

        // 3. 设置默认值
        user.setAdmin(Constants.NORMAL_USER); // 默认普通用户
        user.setStatus(Constants.USER_ENABLED); // 默认启用

        return save(user);
    }

    @Override
    public void logout(String token) {
        // TODO
    }

    @Override
    public void changePassword(ChangePasswordDTO dto) {
        // 1. 获取当前登录用户ID
        Integer currentUserId = UserContext.getUserId();
        User user = super.getById(currentUserId);

        if (user == null) {
            throw new BaseException("用户不存在");
        }

        // 2. 校验旧密码是否正确
        if (!BCrypt.checkpw(dto.getOldPassword(), user.getPassword())) {
            throw new BaseException("旧密码错误");
        }

        // 3. 加密新密码
        String newHash = BCrypt.hashpw(dto.getNewPassword(), BCrypt.gensalt());
        user.setPassword(newHash);

        // 4. 更新数据库
        updateById(user);
    }
}
