package org.xu.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xu.admin.annotation.Auth;
import org.xu.admin.common.Constants;
import org.xu.admin.common.Result;
import org.xu.admin.common.UserContext;
import org.xu.admin.dto.ChangePasswordDTO;
import org.xu.admin.dto.LoginDTO;
import org.xu.admin.dto.RegisterDTO;
import org.xu.admin.dto.UserDTO;
import org.xu.admin.entity.User;
import org.xu.admin.service.IUserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private IUserService userService;

    // === 开放接口 ===

    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginDTO user) {
        String token = userService.login(user);
        return Result.success(token);
    }

    @PostMapping("/register")
    public Result<Boolean> register(@RequestBody RegisterDTO user) {
        return Result.success(userService.register(user));
    }

    // 需要admin权限
    @Auth(mustAdmin = true)
    @GetMapping("/all")
    public Result<Page<UserDTO>> getUsersByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                                @RequestParam(defaultValue = "10") Integer pageSize,
                                                @RequestParam(defaultValue = "false") boolean admin){
        return Result.success(userService.getUserListPage(pageNum, pageSize, admin));
    }

    @Auth(mustAdmin = true)
    @GetMapping("/all-users")
    public Result<List<UserDTO>> getUsers(@RequestParam(defaultValue = "false") boolean admin){
        return Result.success(userService.getUserList( admin));
    }

    /**
     * 更新用户
     * 对应前端: axios.put("/api/user", userObject)
     */
    @PutMapping
    @Auth(mustAdmin = true)
    public Result<Boolean> update(@RequestBody User user) {
        return Result.success(userService.updateById(user));
    }

    /**
     * 根据 ID 删除单个用户
     * 对应前端: axios.delete(`/api/user/${id}`)
     */
    @DeleteMapping("/{id}")
    @Auth(mustAdmin = true)
    public Result<Boolean> delete(@PathVariable Integer id) {
        return Result.success(userService.removeById(id));
    }

    /**
     * 批量删除用户
     * 对应前端: axios.post("/api/user/delete/batch", data.selectedIds)
     */
    @PostMapping("/delete/batch")
    @Auth(mustAdmin = true)
    public Result<Boolean> deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(userService.removeByIds(ids));
    }

    /**
     * 根据 ID 查询详情（用于编辑页面回显）
     * 对应前端: axios.get(`/api/user/${id}`)
     */
    @GetMapping("/{id}")
    @Auth(mustAdmin = true)
    public Result<UserDTO> getById(@PathVariable Integer id) {
        return Result.success(userService.getById(id));
    }

    @Auth
    @GetMapping("/me")
    public Result<UserDTO> getMyInfo() {
        Integer currentUserId = UserContext.getUserId();
        return Result.success(userService.getById(currentUserId));
    }

    @Auth
    @PostMapping("/logout")
    public Result logout(HttpServletRequest request) {
        // 1. 从请求头获取 token
        String token = request.getHeader(Constants.AUTH_HEADER);

        // 2. 如果 token 存在，调用 service 删除
        if (token != null && !token.isEmpty()) {
            userService.logout(token);
        }
        // 3. 返回成功
        return Result.success();
    }

    /**
     * 修改密码
     * 对应前端: axios.post("/api/user/password", data)
     */
    @PostMapping("/password")
    @Auth // 只要登录即可，不需要管理员权限
    public Result<Boolean> changePassword(@RequestBody ChangePasswordDTO dto) {
        userService.changePassword(dto);
        return Result.success(true);
    }

    @PostMapping("/update-avatar")
    @Auth
    public Result<Boolean> updateAvatar(@RequestBody UserDTO userDto) {
        Integer currentUserId = UserContext.getUserId();

        User updateUser = new User();
        updateUser.setId(currentUserId);
        updateUser.setAvatarUrl(userDto.getAvatarUrl());

        return Result.success(userService.updateById(updateUser));
    }
}
