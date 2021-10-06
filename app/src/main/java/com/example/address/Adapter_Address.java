package com.example.address;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.util.Linkify;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Address extends BaseAdapter {
    ArrayList<address> addresses=new ArrayList<>();
    @Override
    public int getCount() {
        return addresses.size();
    }

    @Override
    public Object getItem(int i) {
        return addresses.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) { //i는 뷰그룹의 뷰가 있는 인덱스
        Context c=viewGroup.getContext();
        // viewGroup의 환경정보(어떻게 진행됐는지)를 받음?(아직 컨텍스트가 정확히 무엇인지 머리에 잡히지않음)
        if(view==null) {
            LayoutInflater li = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //getSystemService는 String을 인자로 받고 Object형으로 리턴함.
            view = li.inflate(R.layout.item_view, viewGroup, false);
            //inflate()는 액티비티가 아닌 동적으로 UI생성시 사용함.
        }
        TextView item_name=view.findViewById(R.id.item_name);
        TextView item_phone=view.findViewById(R.id.item_phone);

        Button item_bt=view.findViewById(R.id.item_bt);

        address a= addresses.get(i); //addresses 배열에 있는 i번째 인덱스의 원소(객체)를 받음.

        //받은 객체의 정보를 사용해서 리스트뷰화면에 들어갈 뷰 한개 구성하기
        item_name.setText(a.getName());
        item_phone.setText(a.getPhone());

        List<UserProfile> userProfileList=MainActivity.db.getUserProfileDao().getAll();

        item_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                      addresses.remove(i);
                      int j=0;
                      Fragment2.ad.notifyDataSetChanged();
                      for(UserProfile userProfile: userProfileList){
                          for(;j==i;){
                              MainActivity.db.getUserProfileDao().delete(userProfile);
                              break;
                          }
                          j++;
                      }
            }
        });

        return view;
    }

    public void add_Address(String name, String phone){
        address addr=new address();
        addr.setName(name);
        addr.setPhone(phone);
        addresses.add(addr);
    }
}
