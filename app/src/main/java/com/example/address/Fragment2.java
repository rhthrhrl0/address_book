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
    public ListView listview;
    public static Adapter_Address ad;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_2, container, false);

        List<UserProfile> userProfileList=MainActivity.db.getUserProfileDao().getAll();
        // getALL()함수를 사용해 등록한 모든 회원의 정보를 받고 userProfileList라는 이름의 리스트 생성

        listview=(ListView)view.findViewById(R.id.list); //프레그먼트 내 ListView위젯과 연결된 list변수
        ad=new Adapter_Address(); //어댑터 생성
        listview.setAdapter(ad); //리스트뷰에 어댑터 연결
        //ListView는 adapter의 배열에 있는 원소들을 보여준다.

        for(UserProfile userProfile: userProfileList){
            ad.add_Address(userProfile.name,userProfile.phone);
        } //데이터베이스에 있는 객체들을 어댑터에 있는 배열에 넣어줌.
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