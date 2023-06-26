package com.alive_backend.repository.user_info;

import com.alive_backend.entity.user_info.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
    UserInfo findByUserId(int id);
}
