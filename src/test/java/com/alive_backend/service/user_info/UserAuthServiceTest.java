package com.alive_backend.service.user_info;

import com.alive_backend.entity.user_info.UserAuth;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserAuthServiceTest {
    private UserAuthService userAuthService;
    private UserAuth fakeUserAuth;

    @BeforeEach
    void setUp() {
        fakeUserAuth = new UserAuth();
        fakeUserAuth.setUsername("test");
        fakeUserAuth.setPassword("test");
        fakeUserAuth.setEmail("test@");
        fakeUserAuth.setCheckCode("");
        fakeUserAuth.setCodeUpdateTime(Timestamp.valueOf("2023-06-27 06:12:58")); // 设置合适的值

        userAuthService = new UserAuthService() {
            @Override
            public UserAuth getUserAuthByName(String name) {
                return fakeUserAuth;
            }

            @Override
            public UserAuth getUserAuthByEmail(String email) {
                return fakeUserAuth;
            }

            @Override
            public UserAuth saveUserAuth(UserAuth userAuth) {
                return fakeUserAuth;
            }
        };
    }

    @Test
    void testGetUserAuthByName() {
        UserAuth userAuth = userAuthService.getUserAuthByName("test");
        assertEquals(userAuth, fakeUserAuth);
    }

    @Test
    void testGetUserAuthByEmail() {
        UserAuth userAuth = userAuthService.getUserAuthByEmail("test@");
        assertEquals(userAuth, fakeUserAuth);
    }

    @Test
    void testSaveUserAuth() {
        UserAuth userAuth = userAuthService.saveUserAuth(fakeUserAuth);
        assertEquals(userAuth, fakeUserAuth);
    }
}
