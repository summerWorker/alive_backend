package com.alive_backend.entity.user_info;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
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

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private UserInfo userInfo;

//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }
//
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
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        UserAuth userAuth = (UserAuth) o;
//
//        if (userId != userAuth.userId) return false;
//        if (username != null ? !username.equals(userAuth.username) : userAuth.username != null) return false;
//        if (password != null ? !password.equals(userAuth.password) : userAuth.password != null) return false;
//        if (email != null ? !email.equals(userAuth.email) : userAuth.email != null) return false;
//        if (checkCode != null ? !checkCode.equals(userAuth.checkCode) : userAuth.checkCode != null) return false;
//        if (status != null ? !status.equals(userAuth.status) : userAuth.status != null) return false;
//        if (codeUpdateTime != null ? !codeUpdateTime.equals(userAuth.codeUpdateTime) : userAuth.codeUpdateTime != null)
//            return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = userId;
//        result = 31 * result + (username != null ? username.hashCode() : 0);
//        result = 31 * result + (password != null ? password.hashCode() : 0);
//        result = 31 * result + (email != null ? email.hashCode() : 0);
//        result = 31 * result + (checkCode != null ? checkCode.hashCode() : 0);
//        result = 31 * result + (status != null ? status.hashCode() : 0);
//        result = 31 * result + (codeUpdateTime != null ? codeUpdateTime.hashCode() : 0);
//        return result;
//    }
}
