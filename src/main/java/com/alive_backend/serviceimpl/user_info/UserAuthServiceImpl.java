package com.alive_backend.serviceimpl.user_info;

import com.alive_backend.dao.user_info.UserAuthDao;
import com.alive_backend.entity.user_info.UserAuth;
import com.alive_backend.service.user_info.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthServiceImpl implements UserAuthService {
    @Autowired
    private UserAuthDao userAuthDao;

    @Override
    public UserAuth getUserAuthByName(String name) {
        return userAuthDao.getUserAuthByName(name);
    }
    @Override
    public UserAuth getUserAuthByEmail(String email) {
        return userAuthDao.getUserAuthByEmail(email);
    }
    @Override
    public UserAuth saveUserAuth(UserAuth userAuth) {
        return userAuthDao.saveUserAuth(userAuth);
    }
}
