package com.alive_backend.daoimpl.user_info;

import com.alive_backend.entity.user_info.UserInfo;
import com.alive_backend.repository.user_info.UserInfoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserInfoDaoImplTest {

    @Mock
    private UserInfoRepository mockUserInfoRepository;

    @InjectMocks
    private UserInfoDaoImpl userInfoDaoImplUnderTest;

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

        // Configure UserInfoRepository.save(...).
        final UserInfo userInfo = new UserInfo();
        userInfo.setUserId(0);
        userInfo.setNickname("nickname");
        userInfo.setPhone("phone");
        userInfo.setGender(0);
        final UserInfo entity = new UserInfo();
        entity.setUserId(0);
        entity.setNickname("nickname");
        entity.setPhone("phone");
        entity.setGender(0);
        when(mockUserInfoRepository.save(entity)).thenReturn(userInfo);

        // Run the test
        final UserInfo result = userInfoDaoImplUnderTest.saveUserInfo(user);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
