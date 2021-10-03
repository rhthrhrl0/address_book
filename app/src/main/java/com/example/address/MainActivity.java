package com.example.address;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    static public UserProfileDatabase db;//static으로 선언해야 다른곳에서 사용가능
    Fragment1 f1;
    Fragment2 f2;
    public static String nm; //프래그먼트 재생성 시 데이터 유지 위해 프래그먼트1의 Name텍스트뷰의 값 실시간 가져옴
    public static String ph; //프래그먼트 재생성시 데이터 유지 위해 프래그먼트1의 Phone텍스트뷰의 값 실시간으로 가져옴
    public static int fragment_state; //마지막으로 보고있던 프래그먼트가 뭔지 1과2로 저장
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //레이아웃에 배치된 요소들을 객체화시켜서 메모리위에 올려놓음.그러면 접근가능해짐.이걸 inflate라고 함
        if(savedInstanceState!=null&&savedInstanceState.containsKey("fragment_state")){
            //앱 화면회전 등 다시 생명주기 시작될때
            if((savedInstanceState.getInt("fragment_state")==1)) {
                //마지막으로 보던 화면이 프래그먼트1일때
                f1 = Fragment1.newInstance(savedInstanceState.getString("name"), savedInstanceState.getString("phone"));
                f2= Fragment2.newInstance();
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, f1).commit();
                fragment_state = 1;
            }
            else if((savedInstanceState.getInt("fragment_state")==2)) {
                //마지막으로 보고있던 화면이 프래그먼트2일때
                f1 = Fragment1.newInstance(savedInstanceState.getString("name"), savedInstanceState.getString("phone"));
                f2 = Fragment2.newInstance();
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, f2).commit();
                fragment_state = 2;
            }
        }
        else{ //앱을 완전 처음켰을때
            f1=Fragment1.newInstance("","");
            f2= Fragment2.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,f1).commit();
            fragment_state=1;
        }
        //시작화면을 설정해줌.프래그먼트매니저는 액티비티와 프래그먼트를 이어주며 도움을 주는 역할이다.
        //트랜잭션이란? 어떤 대상에 대해 추가,제거,변경 등 작업들이 발생하는 것을 묶은 것
        // replace()의 첫번째 인자는 프래그먼트를 띄울 레이아웃이고 두번째 인자가 그 틀에 띄울화면이다
        // commit()은 트랜잭션 마무리(즉 저장)

        db=Room.databaseBuilder(this,UserProfileDatabase.class,"userprofile").allowMainThreadQueries().build();
        //UserProfileDatabase.class에 해당하는 데이터베이스를 만들고 userdatabase라는 이름으로 사용
        //allowMainThreadQueries()로 메인스레드에서 데이터베이스에 접근가능해짐. 기본적으로 못쓰게 막아놓음.

        bottomNavigationView=findViewById(R.id.bottomNavi);
        //main.xml에 있는 bottomNavi를 연결시킴.



        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {//item이 선택되면 인자로 넘김.
                switch(item.getItemId()){ //item id 가져오기
                    case R.id.add:
                        fragment_state=1;
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,f1).commit();
                        break; //add가 아니라 replace함수를 사용해서 화면을 대체한다.
                    case R.id.list:
                        fragment_state=2;
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,f2).commit();
                        break;
                }
                return true;
            }
        });

    }

    static public void addUserProfile(EditText name,EditText phone){//데이터베이스에 유저등록
      UserProfile userProfile=new UserProfile(); //빈객체 생성
      userProfile.change(name.getText().toString(),phone.getText().toString());
      // getText()는 CharSequence로 리턴하므로 toString()해줘야한다.
      db.getUserProfileDao().insert(userProfile);
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("name",nm);
        outState.putString("phone",ph);
        outState.putInt("fragment_state",fragment_state);
    }

}