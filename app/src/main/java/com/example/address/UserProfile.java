package com.example.address;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity //객체이자 테이블 그자체의 개념이다.
public class UserProfile {
    @PrimaryKey(autoGenerate = true)//PrimaryKey값을 i에 자동생성
    int i; //Primary키 값을 갖는다.
    public String name;
    public String phone;

    public void change(String name,String phone){
        this.name=name;
        this.phone=phone;
    }
}



//만약 데이터베이스의 속성으로 사용되길 원하지않는 멤버변수가 있다면 @Ignore을 위에 적어주면 된다.