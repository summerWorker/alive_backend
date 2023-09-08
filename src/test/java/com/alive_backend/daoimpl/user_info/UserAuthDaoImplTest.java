package com.alive_backend.daoimpl.user_info;

import com.alive_backend.entity.user_info.UserAuth;
import com.alive_backend.repository.user_info.UserAuthRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserAuthDaoImplTest {

    @Mock
    private UserAuthRepository mockUserAuthRepository;

    @InjectMocks
    private UserAuthDaoImpl userAuthDaoImplUnderTest;

    @Test
    void testGetUserAuthByName() {
        // Setup
        final UserAuth expectedResult = new UserAuth();
        expectedResult.setUserId(0);
        expectedResult.setUsername("username");
        expectedResult.setPassword("password");
        expectedResult.setEmail("email");
        expectedResult.setCheckCode("checkCode");

        // Configure UserAuthRepository.findByUsername(...).
        final UserAuth userAuth = new UserAuth();
        userAuth.setUserId(0);
        userAuth.setUsername("username");
        userAuth.setPassword("password");
        userAuth.setEmail("email");
        userAuth.setCheckCode("checkCode");
        when(mockUserAuthRepository.findByUsername("name")).thenReturn(userAuth);

        // Run the test
        final UserAuth result = userAuthDaoImplUnderTest.getUserAuthByName("name");

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

        // Configure UserAuthRepository.findByEmail(...).
        final UserAuth userAuth = new UserAuth();
        userAuth.setUserId(0);
        userAuth.setUsername("username");
        userAuth.setPassword("password");
        userAuth.setEmail("email");
        userAuth.setCheckCode("checkCode");
        when(mockUserAuthRepository.findByEmail("email")).thenReturn(userAuth);

        // Run the test
        final UserAuth result = userAuthDaoImplUnderTest.getUserAuthByEmail("email");

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

        // Configure UserAuthRepository.save(...).
        final UserAuth userAuth1 = new UserAuth();
        userAuth1.setUserId(0);
        userAuth1.setUsername("username");
        userAuth1.setPassword("password");
        userAuth1.setEmail("email");
        userAuth1.setCheckCode("checkCode");
        final UserAuth entity = new UserAuth();
        entity.setUserId(0);
        entity.setUsername("username");
        entity.setPassword("password");
        entity.setEmail("email");
        entity.setCheckCode("checkCode");
        when(mockUserAuthRepository.save(entity)).thenReturn(userAuth1);

        // Run the test
        final UserAuth result = userAuthDaoImplUnderTest.saveUserAuth(userAuth);

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

        // Configure UserAuthRepository.findByUserId(...).
        final UserAuth userAuth = new UserAuth();
        userAuth.setUserId(0);
        userAuth.setUsername("username");
        userAuth.setPassword("password");
        userAuth.setEmail("email");
        userAuth.setCheckCode("checkCode");
        when(mockUserAuthRepository.findByUserId(0)).thenReturn(userAuth);

        // Run the test
        final UserAuth result = userAuthDaoImplUnderTest.findUserById(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
