package com.alive_backend.entity.user_info;


import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Data
@Entity
@Table(name = "user_auth", schema = "health", catalog = "")
public class UserAuth {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "check_code")
    private String checkCode;
    @Basic
    @Column(name = "status")
    private Integer status;
    @Basic
    @Column(name = "code_update_time")
    private Timestamp codeUpdateTime;

//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private UserInfo userInfo;

//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getCheckCode() {
//        return checkCode;
//    }
//
//    public void setCheckCode(String checkCode) {
//        this.checkCode = checkCode;
//    }
//
//    public Integer getStatus() {
//        return status;
//    }
//
//    public void setStatus(Integer status) {
//        this.status = status;
//    }
//
//    public Timestamp getCodeUpdateTime() {
//        return codeUpdateTime;
//    }
//
//    public void setCodeUpdateTime(Timestamp codeUpdateTime) {
//        this.codeUpdateTime = codeUpdateTime;
//    }
//    public UserInfo getUserInfo() {
//        return userInfo;
//    }
//    public void setUserInfo(UserInfo userInfo) {
//        this.userInfo = userInfo;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        UserAuth userAuth = (UserAuth) o;
//        return userId == userAuth.userId && Objects.equals(username, userAuth.username) && Objects.equals(password, userAuth.password) && Objects.equals(email, userAuth.email) && Objects.equals(checkCode, userAuth.checkCode) && Objects.equals(status, userAuth.status) && Objects.equals(codeUpdateTime, userAuth.codeUpdateTime);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(userId, username, password, email, checkCode, status, codeUpdateTime);
//    }
}
