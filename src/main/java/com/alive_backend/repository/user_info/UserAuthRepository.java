package com.alive_backend.repository.user_info;

import com.alive_backend.entity.user_info.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth, Integer> {
    UserAuth findByUsername(String name);
    UserAuth findByUserId(int id);
    UserAuth findByEmail(String email);
}
