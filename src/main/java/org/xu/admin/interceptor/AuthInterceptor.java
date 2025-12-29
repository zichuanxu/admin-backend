package org.xu.admin.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.xu.admin.annotation.Auth;
import org.xu.admin.common.Constants;
import org.xu.admin.common.UserContext;
import org.xu.admin.utils.JwtUtils;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 如果是 OPTIONS 请求，直接放行
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            return true;
        }

        // 如果不是映射到方法直接通过 (比如静态资源)
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Auth auth = handlerMethod.getMethodAnnotation(Auth.class);

        // 如果方法上没有注解，检查类上是否有
        if (auth == null) {
            auth = handlerMethod.getBeanType().getAnnotation(Auth.class);
        }

        // 如果没有 @Auth 注解，或者 required=false，直接放行
        if (auth == null || !auth.required()) {
            return true;
        }

        // 2. 获取 Token
        String token = request.getHeader(Constants.AUTH_HEADER);

        if (StrUtil.isBlank(token)) {
            // 这里建议改为 401 状态码，配合前端拦截器处理
            response.setStatus(401);
            throw new RuntimeException("无Token，请重新登录");
        }

        // 3. 验证 Token
        DecodedJWT jwt = JwtUtils.validateToken(token);
        Integer userId = Integer.valueOf(jwt.getAudience().get(0));
        Integer isAdmin = jwt.getClaim("admin").asInt();

        // 4. 存入 ThreadLocal
        UserContext.setUserId(userId);
        UserContext.setUserRole(isAdmin);

        // 5. 权限校验
        if (auth.mustAdmin() && isAdmin == 0) {
            throw new RuntimeException("权限不足，需要管理员权限");
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        UserContext.clear();
    }
}