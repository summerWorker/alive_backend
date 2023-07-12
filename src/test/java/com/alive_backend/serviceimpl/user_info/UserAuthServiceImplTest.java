package com.alive_backend.serviceimpl.user_info;

import com.alive_backend.dao.user_info.UserAuthDao;
import com.alive_backend.entity.user_info.UserAuth;
import com.alive_backend.service.user_info.UserAuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class UserAuthServiceImplTest {
    @Mock
    private UserAuthDao userAuthDao;
    @InjectMocks
    private UserAuthServiceImpl userAuthService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserAuthByName() {
        // 创建一个假的UserAuth对象
        UserAuth fakeUserAuth = new UserAuth();
        fakeUserAuth.setUsername("test");
        fakeUserAuth.setPassword("test");
        fakeUserAuth.setEmail("test@");
        fakeUserAuth.setCheckCode("");
        fakeUserAuth.setCodeUpdateTime(Timestamp.valueOf("2023-06-27 06:12:58")); // 设置合适的值

        // 指定Mock对象的行为
        when(userAuthDao.getUserAuthByName("test")).thenReturn(fakeUserAuth);
        when(userAuthDao.getUserAuthByName("test1")).thenReturn(null);
        // 执行被测试的方法
        UserAuth userAuth = userAuthService.getUserAuthByName("test");

        // 验证结果是否符合预期
        assertEquals(userAuth, fakeUserAuth);

        UserAuth userAuth1 = userAuthService.getUserAuthByName("test1");
        assertNull(userAuth1);

        // 验证Mock对象的方法是否被调用
        verify(userAuthDao, times(1)).getUserAuthByName("test");
        verify(userAuthDao, times(1)).getUserAuthByName("test1");
    }

    @Test
    void testGetUserAuthByEmail() {
        // 创建一个假的UserAuth对象
        UserAuth fakeUserAuth = new UserAuth();
        fakeUserAuth.setUsername("test");
        fakeUserAuth.setPassword("test");
        fakeUserAuth.setEmail("test@");
        fakeUserAuth.setCheckCode("");
        fakeUserAuth.setCodeUpdateTime(Timestamp.valueOf("2023-06-27 06:12:58")); // 设置合适的值

        // 指定Mock对象的行为
        when(userAuthDao.getUserAuthByEmail("test@")).thenReturn(fakeUserAuth);
        when(userAuthDao.getUserAuthByEmail("test1@")).thenReturn(null);

        // 执行被测试的方法
        UserAuth userAuth = userAuthService.getUserAuthByEmail("test@");

        // 验证结果是否符合预期
        assertEquals(userAuth, fakeUserAuth);
        UserAuth userAuth1 = userAuthService.getUserAuthByEmail("test1@");
        assertNull(userAuth1);

        // 验证Mock对象的方法是否被调用
        verify(userAuthDao, times(1)).getUserAuthByEmail("test@");
        verify(userAuthDao, times(1)).getUserAuthByEmail("test1@");
    }

    @Test
    void testSaveUserAuth() {
        // 创建一个待保存的UserAuth对象
        UserAuth userAuth = new UserAuth();
        userAuth.setUsername("test_save");
        userAuth.setPassword("test_save");
        userAuth.setEmail("test_save@");
        userAuth.setCheckCode("");
        userAuth.setCodeUpdateTime(Timestamp.valueOf("2023-06-27 06:12:58"));

        // 指定Mock对象的行为
        when(userAuthDao.saveUserAuth(userAuth)).thenReturn(userAuth);

        // 执行被测试的方法
        UserAuth savedUserAuth = userAuthService.saveUserAuth(userAuth);

        // 验证结果是否符合预期
        assertEquals(userAuth, savedUserAuth);

        // 验证Mock对象的方法是否被调用
        verify(userAuthDao, times(1)).saveUserAuth(userAuth);
    }
}
