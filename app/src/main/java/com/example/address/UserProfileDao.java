package com.example.address;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserProfileDao {
    //Dao는 데이터베이스에서 데이터에 엑세스하기 위한 쿼리가 있는 객체를 의미

    @Insert
    void insert(UserProfile userProfile);

    @Update
    void update(UserProfile userProfile);

    @Delete
    void delete(UserProfile userProfile);

    //객체클래스 명인 UserProfile은 테이블 그 자체이기도 하므로 이걸 테이블명으로 사용합니다.
    @Query("SELECT*FROM UserProfile") //*UserProfile이란 데이터베이스테이블에서 모든 정보를 가져와라.
    List<UserProfile> getAll();//데이터베이스의 전체레코드를 얻기위한 함수.
    //getAll()실행하면 내부적으로 sql문인 SELECT*FROM UserProfile가 실행되고 리스트 형태로 반환해준다.
}
