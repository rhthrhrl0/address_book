package com.example.address;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {UserProfile.class},version = 1)
/*entities는 이 데이터베이스가 관리할 것들.여러개라면{}안에 ,로추가가능하다. */
public abstract class UserProfileDatabase extends RoomDatabase { //룸데이터베이스를 상속받으려면 추상클래스여야한다.
    public abstract UserProfileDao getUserProfileDao();//파라미터가 0개이고 Dao를 리턴하는 추상메서드가 필요하다.
}
//룸을 사용하는 이유는 코드작성이 쉽고 컴파일 시간이 체크 가능해서. 또한 자동으로 데이터 업데이트가 가능하다.
//룸의 구성요소 세가지: Entity,Dao(=Data access object),Database
//Dao는 실질적으로 데이터베이스에 접근해서 insert(),delete()를 실행한다.
//Database에서는 테이블과 버전을 정의한다. 추상클래스여야하며 파라미터가0인 추상메서드가 한개 이상 필요하다.
//Room을 쓰려면 build.gradle의 dependencies에 추가해줘야할 코드가 있다.