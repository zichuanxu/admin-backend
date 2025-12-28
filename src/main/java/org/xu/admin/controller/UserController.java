package org.xu.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xu.admin.common.Result;
import org.xu.admin.dto.UserDTO;
import org.xu.admin.entity.User;
import org.xu.admin.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public Result<Page<UserDTO>> getUsersByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                                @RequestParam(defaultValue = "10") Integer pageSize,
                                                @RequestParam(defaultValue = "false") boolean admin){
        return Result.success(userService.getUserListPage(pageNum, pageSize, admin));
    }

    @GetMapping("/all-users")
    public Result<List<UserDTO>> getUsers(@RequestParam(defaultValue = "false") boolean admin){
        return Result.success(userService.getUserList( admin));
    }



    /**
     * 新增用户
     * 对应前端: axios.post("/api/user", userObject)
     */
    @PostMapping
    public Result<Boolean> save(@RequestBody User user) {
        // 可以在此处对密码进行加密处理，例如：user.setPassword(BCrypt.hashpw(user.getPassword()))
        return Result.success(userService.save(user));
    }

    /**
     * 更新用户
     * 对应前端: axios.put("/api/user", userObject)
     */
    @PutMapping
    public Result<Boolean> update(@RequestBody User user) {
        return Result.success(userService.updateById(user));
    }

    /**
     * 根据 ID 删除单个用户
     * 对应前端: axios.delete(`/api/user/${id}`)
     */
    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Integer id) {
        return Result.success(userService.removeById(id));
    }

    /**
     * 批量删除用户
     * 对应前端: axios.post("/api/user/delete/batch", data.selectedIds)
     */
    @PostMapping("/delete/batch")
    public Result<Boolean> deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(userService.removeByIds(ids));
    }

    /**
     * 根据 ID 查询详情（用于编辑页面回显）
     * 对应前端: axios.get(`/api/user/${id}`)
     */
    @GetMapping("/{id}")
    public Result<User> getById(@PathVariable Integer id) {
        return Result.success(userService.getById(id));
    }


}
