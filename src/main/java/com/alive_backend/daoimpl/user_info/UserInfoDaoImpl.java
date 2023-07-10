package com.alive_backend.daoimpl.user_info;

import com.alive_backend.dao.user_info.UserInfoDao;
import com.alive_backend.entity.user_info.UserInfo;
import com.alive_backend.repository.user_info.UserInfoRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserInfoDaoImpl implements UserInfoDao {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Override
    public UserInfo saveUserInfo(UserInfo user) {
        try {
            System.out.println("user: " );
            return userInfoRepository.save(user);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    @Override
    public UserInfo getUserById(int id) {
        try {
            UserInfo user =  userInfoRepository.findByUserId(id);
            System.out.println("dao user: " + user);
            return user;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
