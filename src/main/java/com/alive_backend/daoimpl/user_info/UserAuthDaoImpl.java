package com.alive_backend.daoimpl.user_info;

import com.alive_backend.dao.user_info.UserAuthDao;
import com.alive_backend.entity.user_info.UserAuth;
import com.alive_backend.repository.user_info.UserAuthRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserAuthDaoImpl implements UserAuthDao {
    @Autowired
    private UserAuthRepository userAuthRepository;
    @Override
    public UserAuth getUserAuthByName(String name) {
        return userAuthRepository.findByUsername(name);
    }
    @Override
    public UserAuth getUserAuthByEmail(String email) {
        return userAuthRepository.findByEmail(email);
    }
    @Override
    public UserAuth saveUserAuth(UserAuth userAuth) {
//        try {
            return userAuthRepository.save(userAuth);
//        } catch (Exception e) {
//            return null;
//        }
    }
    @Override
    public UserAuth findUserById(int id) {
        return userAuthRepository.findByUserId(id);
    }
}
