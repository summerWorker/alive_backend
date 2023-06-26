package com.alive_backend.daoimpl.user_info;

import com.alive_backend.dao.user_info.UserInfoDao;
import com.alive_backend.entity.user_info.UserInfo;
import com.alive_backend.repository.user_info.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserInfoDaoImpl implements UserInfoDao {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Override
    public UserInfo saveUserInfo(UserInfo user) {
        try {
            return userInfoRepository.save(user);
        } catch (Exception e) {
            return null;
        }
    }
}
