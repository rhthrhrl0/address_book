package com.example.address;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Fragment1 extends Fragment {

    private View view;
    private Button add_button;
    public EditText name;
    public EditText phone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_1, container, false);
        add_button=view.findViewById(R.id.add_button);
        name=view.findViewById(R.id.Name);
        phone=view.findViewById(R.id.Phone);

        add_button.setOnClickListener(new View.OnClickListener() { //프래그먼트2로 이동
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
                }



            }
        });
        return view;
    }
}