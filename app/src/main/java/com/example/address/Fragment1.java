package com.example.address;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Fragment1 extends Fragment {

    private View view;
    private Button add_button;
    public static EditText name;
    public static EditText phone;
    String n1; //프래그먼트 재생성시 번들에서 값 받을 변수
    String p1; //프래그먼트 재생성시 번들에서 값 받을 변수
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){ //프래그먼트1 처음생성이 아닐때
            n1=getArguments().getString("name"); //데이터유지
            p1=getArguments().getString("phone"); //데이터유지
            /* getArguments() 는 Fragment 클래스에 있는 함수로
            만약 setArguments(인자)에서 Bundle을 인자로 이 프래그먼트가 받았다면 사용가능함 */
        }
        else{   //프래그먼트1 처음 생성시
            n1="";
            p1="";
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_1, container, false);
        add_button=view.findViewById(R.id.add_button);
        name=view.findViewById(R.id.Name);
        phone=view.findViewById(R.id.Phone);

        name.setText(n1);
        phone.setText(p1);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(name.getText())||TextUtils.isEmpty(phone.getText())) {
                    //TextUtils클래스의 static함수인 isEmpty()는 해당 문자열이 비었는지 검사해준다.
                Toast.makeText(getActivity(),"등록실패",Toast.LENGTH_SHORT).show();
                /*makeText()는 Static함수다. getActivity()는 Fragment클래스에 있는 함수로
                  현재 프래그먼트의 아래있는 액티비티를 리턴한다.
                  LENGTH_SHORT는 Toast클래스에 있는 satic int형 변수다.*/
                }
                else {
                    Toast.makeText(getActivity(),"등록성공",Toast.LENGTH_SHORT).show();
                    MainActivity.addUserProfile(name, phone);
                    MainActivity.nm="";
                    MainActivity.ph="";
                    name.setText(MainActivity.nm);
                    phone.setText(MainActivity.ph);
                }
            }
        });
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        MainActivity.nm=name.getText().toString();
        MainActivity.ph=phone.getText().toString();
    }
    //프래그먼트의 onSave()먼저 호출되고 그후 액티비티의 onSave()호출되므로 미리 액티비티에게 값을 넘겨줘야함


    public static Fragment1 newInstance(String name, String phone) {
        Bundle args = new Bundle();
        Fragment1 f= new Fragment1();
        args.putString("name",name);
        args.putString("phone",phone);
        f.setArguments(args);/*프래그먼트가 만약 재생성일 걸 대비해서
        프래그먼트의 onCreate()함수에 넘겨줄 Bundle만들기(데이터유지위해)  */
        return f;
    } //newInstance()함수 종료 되어야 프래그먼트의 onCreate()호출함.
}