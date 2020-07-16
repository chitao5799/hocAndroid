package com.example.gridviewnangcao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

public class HinhAnhAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<HinhAnh> hinhAnhList;

    public HinhAnhAdapter(Context context, int layout, List<HinhAnh> hinhAnhs) {
        this.context = context;
        this.layout = layout;
        this.hinhAnhList = hinhAnhs;
    }

    @Override
    public int getCount() {
        return hinhAnhList.size();
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
        ImageView hinhAnh;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null)
        {
             viewHolder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);
            viewHolder.hinhAnh=(ImageView)view.findViewById(R.id.imgHinhAnh);
            view.setTag(viewHolder);
        }else
        {
            viewHolder= (ViewHolder) view.getTag();
        }
        HinhAnh image= hinhAnhList.get(i);
        viewHolder.hinhAnh.setImageResource(image.getHinh());

        return view;
    }
}
