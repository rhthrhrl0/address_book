package com.example.address;

import android.content.Context;
import android.graphics.drawable.Drawable;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        Context c=viewGroup.getContext();
        if(view==null){
            LayoutInflater li=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=li.inflate(R.layout.item_view,viewGroup,false);
        }
        TextView item_name=view.findViewById(R.id.item_name);
        TextView item_phone=view.findViewById(R.id.item_phone);
        Button item_bt=view.findViewById(R.id.item_bt);

        address a= addresses.get(i);
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
                              MainActivity.deleteUserProfile(userProfile);
                              break;
                          }
                          j++;
                      }
            }
        });



        return view;
    }

    public void add_Address(String name, String phone){
        address people=new address();
        people.setName(name);
        people.setPhone(phone);
        addresses.add(people);
    }
}
