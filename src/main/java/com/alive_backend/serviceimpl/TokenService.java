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
    }

    public int getUserIdFromToken(String token) {
        DecodedJWT jwt = JWT.decode(token);
        int id = jwt.getClaim("user_id").asInt();
        return id;
    }

    public long getExpireTime(String token) {
        DecodedJWT jwt = JWT.decode(token);
        Date expireTime = jwt.getExpiresAt();
        return expireTime.getTime();
    }
}
