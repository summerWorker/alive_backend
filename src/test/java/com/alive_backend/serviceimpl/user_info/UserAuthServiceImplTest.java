package com.alive_backend.serviceimpl.user_info;

import com.alive_backend.dao.user_info.UserAuthDao;
import com.alive_backend.entity.user_info.UserAuth;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserAuthServiceImplTest {

    @Mock
    private UserAuthDao mockUserAuthDao;

    @InjectMocks
    private UserAuthServiceImpl userAuthServiceImplUnderTest;

    @Test
    void testGetUserAuthByName() {
        // Setup
        final UserAuth expectedResult = new UserAuth();
        expectedResult.setUserId(0);
        expectedResult.setUsername("username");
        expectedResult.setPassword("password");
        expectedResult.setEmail("email");
        expectedResult.setCheckCode("checkCode");

        // Configure UserAuthDao.getUserAuthByName(...).
        final UserAuth userAuth = new UserAuth();
        userAuth.setUserId(0);
        userAuth.setUsername("username");
        userAuth.setPassword("password");
        userAuth.setEmail("email");
        userAuth.setCheckCode("checkCode");
        when(mockUserAuthDao.getUserAuthByName("name")).thenReturn(userAuth);

        // Run the test
        final UserAuth result = userAuthServiceImplUnderTest.getUserAuthByName("name");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetUserAuthByEmail() {
        // Setup
        final UserAuth expectedResult = new UserAuth();
        expectedResult.setUserId(0);
        expectedResult.setUsername("username");
        expectedResult.setPassword("password");
        expectedResult.setEmail("email");
        expectedResult.setCheckCode("checkCode");

        // Configure UserAuthDao.getUserAuthByEmail(...).
        final UserAuth userAuth = new UserAuth();
        userAuth.setUserId(0);
        userAuth.setUsername("username");
        userAuth.setPassword("password");
        userAuth.setEmail("email");
        userAuth.setCheckCode("checkCode");
        when(mockUserAuthDao.getUserAuthByEmail("email")).thenReturn(userAuth);

        // Run the test
        final UserAuth result = userAuthServiceImplUnderTest.getUserAuthByEmail("email");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSaveUserAuth() {
        // Setup
        final UserAuth userAuth = new UserAuth();
        userAuth.setUserId(0);
        userAuth.setUsername("username");
        userAuth.setPassword("password");
        userAuth.setEmail("email");
        userAuth.setCheckCode("checkCode");

        final UserAuth expectedResult = new UserAuth();
        expectedResult.setUserId(0);
        expectedResult.setUsername("username");
        expectedResult.setPassword("password");
        expectedResult.setEmail("email");
        expectedResult.setCheckCode("checkCode");

        // Configure UserAuthDao.saveUserAuth(...).
        final UserAuth userAuth1 = new UserAuth();
        userAuth1.setUserId(0);
        userAuth1.setUsername("username");
        userAuth1.setPassword("password");
        userAuth1.setEmail("email");
        userAuth1.setCheckCode("checkCode");
        final UserAuth userAuth2 = new UserAuth();
        userAuth2.setUserId(0);
        userAuth2.setUsername("username");
        userAuth2.setPassword("password");
        userAuth2.setEmail("email");
        userAuth2.setCheckCode("checkCode");
        when(mockUserAuthDao.saveUserAuth(userAuth2)).thenReturn(userAuth1);

        // Run the test
        final UserAuth result = userAuthServiceImplUnderTest.saveUserAuth(userAuth);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindUserById() {
        // Setup
        final UserAuth expectedResult = new UserAuth();
        expectedResult.setUserId(0);
        expectedResult.setUsername("username");
        expectedResult.setPassword("password");
        expectedResult.setEmail("email");
        expectedResult.setCheckCode("checkCode");

        // Configure UserAuthDao.findUserById(...).
        final UserAuth userAuth = new UserAuth();
        userAuth.setUserId(0);
        userAuth.setUsername("username");
        userAuth.setPassword("password");
        userAuth.setEmail("email");
        userAuth.setCheckCode("checkCode");
        when(mockUserAuthDao.findUserById(0)).thenReturn(userAuth);

        // Run the test
        final UserAuth result = userAuthServiceImplUnderTest.findUserById(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
