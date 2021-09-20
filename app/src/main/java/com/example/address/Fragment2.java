package com.example.address;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class Fragment2 extends Fragment {

    private View view;
    private String result;
    private TextView text2;
    public ListView list;
    public static Adapter_Address ad;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_2, container, false);

        List<UserProfile> userProfileList=MainActivity.db.getUserProfileDao().getAll();
        // getALL()함수를 사용해 등록한 모든 회원의 정보를 받고 userProfileList라는 이름의 리스트 생성
        Log.d("a","프래그먼트2 실행됨");
        list=(ListView)view.findViewById(R.id.list); //프레그먼트 내 ListView위젯과 연결된 list변수
        ad=new Adapter_Address();
        list.setAdapter(ad);

        //상식: ListView는 adapter에 담긴 내용을 보여준다. 즉 adapter에 연결된 data에 정보를 넣으면된다.

        for(UserProfile userProfile: userProfileList){
            ad.add_Address(userProfile.name,userProfile.phone);
        }
        ad.notifyDataSetChanged();//adapter 갱신

        return view;
    }

    public static Fragment2 newInstance() {
        Bundle args = new Bundle();
        Fragment2 fragment = new Fragment2();
        fragment.setArguments(args);
        return fragment;
    }

}