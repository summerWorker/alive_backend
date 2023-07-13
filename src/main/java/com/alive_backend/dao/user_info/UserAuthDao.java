package com.alive_backend.dao.user_info;

import com.alive_backend.entity.user_info.UserAuth;
import org.apache.catalina.User;

public interface UserAuthDao {
    UserAuth getUserAuthByName(String name);
    UserAuth getUserAuthByEmail(String email);
    UserAuth saveUserAuth(UserAuth userAuth);
    UserAuth findUserById(int id);
}
