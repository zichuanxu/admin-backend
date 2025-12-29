package org.xu.admin.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.xu.admin.entity.User;

import java.util.Date;

public class JwtUtils {
    private static final String SECRET = "XuAdminSecretKey123!@#";
    // 过期时间 24小时
    private static final long EXPIRATION = 24 * 60 * 60 * 1000;

    /**
     * 生成 Token
     */
    public static String createToken(User user) {
        return JWT.create()
                .withAudience(user.getId().toString()) // 存入 ID
                .withClaim("username", user.getUsername())
                .withClaim("admin", user.getAdmin()) // 存入角色信息
                .withClaim("avatarUrl", user.getAvatarUrl())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION))
                .sign(Algorithm.HMAC256(SECRET));
    }

    /**
     * 校验并解析 Token
     */
    public static DecodedJWT validateToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
        } catch (Exception e) {
            throw new RuntimeException("Token无效或已过期");
        }
    }
}