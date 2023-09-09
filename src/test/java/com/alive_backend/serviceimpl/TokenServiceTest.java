package com.alive_backend.serviceimpl;

import com.alive_backend.entity.user_info.UserAuth;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TokenServiceTest {

    private TokenService tokenServiceUnderTest;

    @BeforeEach
    void setUp() {
        tokenServiceUnderTest = new TokenService();
    }

    @Test
    void testGetToken() {
        // Setup
        final UserAuth user = new UserAuth();
        user.setUserId(0);
        user.setUsername("username");
        user.setPassword("password");
        user.setEmail("email");
        user.setCheckCode("checkCode");

        // Run the test
        final String result = tokenServiceUnderTest.getToken(user);

        // Verify the results is not null
        assertThat(result).isNotNull();
    }

    @Test
    void testGetUserIdFromToken() {
        assertThat(tokenServiceUnderTest.getUserIdFromToken(
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwIiwidXNlcl9pZCI6MCwiZXhwIjoxNjk0MTYzMzY3LCJ1c2VybmFtZSI6InVzZXJuYW1lIn0.I_fpmrJj-qzrzL80Zrle9wm4CcnxFbQt_YYK4BDjfUU"
        )).isEqualTo(0);
    }

    @Test
    void testGetExpireTime() {
        assertThat(tokenServiceUnderTest.getExpireTime(
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwIiwidXNlcl9pZCI6MCwiZXhwIjoxNjk0MTYzMzY3LCJ1c2VybmFtZSI6InVzZXJuYW1lIn0.I_fpmrJj-qzrzL80Zrle9wm4CcnxFbQt_YYK4BDjfUU"
        )).isGreaterThan(0L);
    }
}
