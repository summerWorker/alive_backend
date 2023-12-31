package com.alive_backend.service.user_info;

import com.alive_backend.entity.user_info.UserAuth;

public interface UserAuthService {
    UserAuth getUserAuthByName(String name);
    UserAuth getUserAuthByEmail(String email);
    UserAuth saveUserAuth(UserAuth userAuth);
    UserAuth findUserById(int id);
}
