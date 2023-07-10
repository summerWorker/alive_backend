package com.alive_backend.serviceimpl.user_info;

import com.alive_backend.dao.user_info.UserInfoDao;
import com.alive_backend.entity.user_info.UserInfo;
import com.alive_backend.service.user_info.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoDao userInfoDao;
    @Override
    public UserInfo saveUserInfo(UserInfo user) {
        return userInfoDao.saveUserInfo(user);
    }
    @Override
    public UserInfo getUserById(int id) {
        UserInfo user =  userInfoDao.getUserById(id);
        System.out.println("service user: " + user);
        return user;
    }
}
