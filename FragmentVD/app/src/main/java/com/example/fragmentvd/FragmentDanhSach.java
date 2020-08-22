package com.example.fragmentvd;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class FragmentDanhSach extends ListFragment {
    String[] arrCity={"Hải Phòng","Nha Trang","Khánh Hòa","Hà Nội","Thái Nguyên","Tây Ninh"};
    ArrayAdapter arrayAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        arrayAdapter=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,arrCity);
        //dùng listview sẵn có của android (tức dùng listview có id là android/list), hoặc tự
        // định nghĩa listview như các bài listview là tự đặt id riêng: +id/...
        setListAdapter(arrayAdapter);

        return inflater.inflate(R.layout.fragment_danh_sach,container,false);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(getActivity(),"bạn đã click: "+arrCity[position],Toast.LENGTH_SHORT).show();
        super.onListItemClick(l, v, position, id);
    }
}
