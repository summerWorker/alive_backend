package com.alive_backend.dao.user_info;

import com.alive_backend.entity.user_info.UserAuth;

public interface UserAuthDao {
    UserAuth getUserAuthByName(String name);
    UserAuth getUserAuthByEmail(String email);
    Boolean saveUserAuth(UserAuth userAuth);
}
