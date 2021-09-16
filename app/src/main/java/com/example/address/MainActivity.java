package com.example.address;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    static public UserProfileDatabase db;//static으로 선언해야 다른곳에서 사용가능

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //레리아웃에 배치된 요소들을 객체화시켜서 메모리위에 올려놓음.그러면 접근가능해짐.이걸 inflate라고 함

        db=Room.databaseBuilder(this,UserProfileDatabase.class,"userprofile").allowMainThreadQueries().build();
        //UserProfileDatabase.class에 해당하는 데이터베이스를 만들고 userdatabase라는 이름으로 사용
        //allowMainThreadQueries()로 메인스레드에서 데이터베이스에 접근가능해짐. 기본적으로 못쓰게 막아놓음.

        bottomNavigationView=findViewById(R.id.bottomNavi);
        //main.xml에 있는 bottomNavi를 연결시킴.

        getSupportFragmentManager().beginTransaction().add(R.id.main_frame,new Fragment1()).commit();
        //시작화면을 설정해줌.프래그먼트매니저는 액티비티와 프래그먼트를 이어주며 도움을 주는 역할이다.
        //트랜잭션이란? 어떤 대상에 대해 추가,제거,변경 등 작업들이 발생하는 것을 묶은 것
        // add()함수의 첫번째 인자는 프래그먼트를 띄울 레이아웃이고 두번째 인자가 그 틀에 띄울화면이다.
        // commit()은 트랜잭션 마무리(즉 저장)

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {//item이 선택되면 인자로 넘김.
                switch(item.getItemId()){ //item id 가져오기
                    case R.id.add:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,new Fragment1()).commit();
                        break; //add가 아니라 replace함수를 사용해서 화면을 대체한다.
                    case R.id.list:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,new Fragment2()).commit();
                        break;
                }
                return true;
            }
        });

    }

    static public void addUserProfile(EditText name,EditText phone){ //static으로 선언
      UserProfile userProfile=new UserProfile(); //빈객체 생성
      userProfile.change(name.getText().toString(),phone.getText().toString());
      // getText()는 CharSequence로 리턴하므로 toString()해줘야함야한다.
      db.getUserProfileDao().insert(userProfile);
    }


}