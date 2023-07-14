package com.alive_backend.entity.user_info;

import javax.persistence.*;
import com.alive_backend.entity.health_data.MainRecord;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Objects;

@Entity
@Data
@Table(name = "user_info", schema = "health", catalog = "")
public class UserInfo {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "nickname")
    private String nickname;
    @Basic
    @Column(name = "phone")
    private String phone;
    @Basic
    @Column(name = "gender")
    private Integer gender;

//    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "user_id")
//    private MainRecord mainRecord;

//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }
//
//    public String getNickname() {
//        return nickname;
//    }
//
//    public void setNickname(String nickname) {
//        this.nickname = nickname;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public Integer getGender() {
//        return gender;
//    }
//
//    public void setGender(Integer gender) {
//        this.gender = gender;
//    }
//    public MainRecord getMainRecord() {
//        return mainRecord;
//    }
//    public void setMainRecord(MainRecord mainRecord) {
//        this.mainRecord = mainRecord;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        UserInfo userInfo = (UserInfo) o;
//        return userId == userInfo.userId && Objects.equals(nickname, userInfo.nickname) && Objects.equals(phone, userInfo.phone) && Objects.equals(gender, userInfo.gender);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(userId, nickname, phone, gender);
//    }
}
