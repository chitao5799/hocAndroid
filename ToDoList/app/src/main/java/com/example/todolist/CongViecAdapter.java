package com.example.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.List;
import java.util.MissingFormatArgumentException;

public class CongViecAdapter extends BaseAdapter {
    private MainActivity context; //chỉ định chính xác context là MainActivity để gọi và dùng 1 số hàm của context này trong class này
    private int layout;
    private List<CongViec> listCV;

    public CongViecAdapter(MainActivity context, int layout, List<CongViec> listCV) {
        this.context = context;
        this.layout = layout;
        this.listCV = listCV;
    }

    @Override
    public int getCount() {
        return listCV.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private  class ViewHolder{
        TextView txtTen;
        ImageView imgDelete,imgEdit;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null)
        {
            viewHolder=new ViewHolder();
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(layout,null);
            viewHolder.txtTen=(TextView)view.findViewById(R.id.txtTenCV);
            viewHolder.imgDelete=(ImageView)view.findViewById(R.id.imgDelete);
            viewHolder.imgEdit=(ImageView)view.findViewById(R.id.imgEdit);
            view.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) view.getTag();
        }

        final CongViec congViec=listCV.get(i);
        viewHolder.txtTen.setText(congViec.getTenCV());

        //bắt sự kiện xóa sửa
        viewHolder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogSuaCongViec(congViec.getTenCV(),congViec.getIdCV());
            }
        });
        viewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogXoaCv(congViec.getTenCV(),congViec.getIdCV());
            }
        });
        return view;
    }
}
