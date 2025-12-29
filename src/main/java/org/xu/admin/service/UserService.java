package org.xu.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.xu.admin.dto.ChangePasswordDTO;
import org.xu.admin.dto.LoginDTO;
import org.xu.admin.dto.RegisterDTO;
import org.xu.admin.dto.UserDTO;
import org.xu.admin.entity.User;

import java.util.List;

public interface UserService extends IService<User> {

    List<UserDTO> getUserList(boolean admin);

    Page<UserDTO> getUserListPage(Integer pageNum, Integer pageSize);

    Page<UserDTO> getUserListPage(Integer pageNum, Integer pageSize, boolean admin);

    boolean save(User user);

    boolean updateById(User user);

    boolean removeById(Integer id);

    boolean removeByIds(List<Integer> ids);

    UserDTO getById(Integer id);

    boolean register(RegisterDTO user);

    String login(LoginDTO loginUser);

    void logout(String token);

     void changePassword(ChangePasswordDTO dto) ;
}
