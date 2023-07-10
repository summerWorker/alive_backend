package com.alive_backend.entity.user_info;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Document(collection = "user_info")
public class UserInfo {

    @Id
    public ObjectId _id;
    private int userId;
    private String nickname;
    private String phone;
    private String gender;
    public UserInfo() {}

    public UserInfo(int _userId,String _nickname,String _phone, String _gender) {
        this.userId = _userId;
        this.nickname = _nickname;
        this.phone  = _phone;
        this.gender = _gender;
    }


    @Override
    public String toString() {
        return String.format(
                "UserInfo[_id=%s, userId='%d', gender='%s', nickname='%s', phone='%s']",
                _id, userId, gender, nickname, phone);

    }

}
