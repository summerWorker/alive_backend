package com.alive_backend.repository.user_info;

import com.alive_backend.entity.user_info.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends MongoRepository<UserInfo, String> {
    UserInfo findByUserId(int id);
}