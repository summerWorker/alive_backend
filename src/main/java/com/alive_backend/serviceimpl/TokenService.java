package com.alive_backend.serviceimpl;

import com.alive_backend.entity.user_info.UserAuth;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service("TokenService")
public class TokenService {
    private static final long EXPIRATION_TIME = 1000 * 60 * 30;
    private static final String SECRET_KEY = "your_secret_key";
    public String getToken(UserAuth user) {
        Date expiryDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);

        String token = JWT.create()
                .withAudience(String.valueOf(user.getUserId()))
                .withClaim("user_id", user.getUserId())
                .withClaim("username", user.getUsername())
                .withExpiresAt(expiryDate)
                .sign(Algorithm.HMAC256(user.getUsername() + user.getPassword()));

        return token;
//        token= JWT.create().withAudience(String.valueOf(user.getUserId()))// 将 user id 保存到 token 里面
//                .sign(Algorithm.HMAC256(user.getPassword()));// 以 password 作为 token 的密钥
    }

    public int getUserIdFromToken(String token) {
        DecodedJWT jwt = JWT.decode(token);
        int id = jwt.getClaim("user_id").asInt();
        return id;
    }
//
//    public String getUsernameFromToken(String token) {
//        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build().verify(token);
//        String username = decodedJWT.getClaim("username").asString();
//        return username;
//    }
}
