package com.alive_backend.serviceimpl.user_info;

import com.alive_backend.dao.user_info.UserInfoDao;
import com.alive_backend.entity.user_info.UserInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserInfoServiceImplTest {

    @Mock
    private UserInfoDao mockUserInfoDao;

    @InjectMocks
    private UserInfoServiceImpl userInfoServiceImplUnderTest;

    @Test
    void testSaveUserInfo() {
        // Setup
        final UserInfo user = new UserInfo();
        user.setUserId(0);
        user.setNickname("nickname");
        user.setPhone("phone");
        user.setGender(0);

        final UserInfo expectedResult = new UserInfo();
        expectedResult.setUserId(0);
        expectedResult.setNickname("nickname");
        expectedResult.setPhone("phone");
        expectedResult.setGender(0);

        // Configure UserInfoDao.saveUserInfo(...).
        final UserInfo userInfo = new UserInfo();
        userInfo.setUserId(0);
        userInfo.setNickname("nickname");
        userInfo.setPhone("phone");
        userInfo.setGender(0);
        final UserInfo user1 = new UserInfo();
        user1.setUserId(0);
        user1.setNickname("nickname");
        user1.setPhone("phone");
        user1.setGender(0);
        when(mockUserInfoDao.saveUserInfo(user1)).thenReturn(userInfo);

        // Run the test
        final UserInfo result = userInfoServiceImplUnderTest.saveUserInfo(user);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
