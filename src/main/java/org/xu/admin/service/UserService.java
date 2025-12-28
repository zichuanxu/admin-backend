package org.xu.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.xu.admin.dto.UserDTO;
import org.xu.admin.entity.User;

import java.util.List;

public interface UserService {


    Page<UserDTO> getUserListPage(Integer pageNum, Integer pageSize);

    Page<UserDTO> getUserListPage(Integer pageNum, Integer pageSize, boolean admin);

    boolean save(User user);

    boolean updateById(User user);

    boolean removeById(Integer id);

    boolean removeByIds(List<Integer> ids);

    User getById(Integer id);
}
