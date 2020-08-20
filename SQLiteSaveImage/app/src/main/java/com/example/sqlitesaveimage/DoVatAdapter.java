package com.example.sqlitesaveimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class DoVatAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<DoVat> doVatsList;

    public DoVatAdapter(Context context, int layout, List<DoVat> doVatsList) {
        this.context = context;
        this.layout = layout;
        this.doVatsList = doVatsList;
    }

    @Override
    public int getCount() {
        return doVatsList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private class ViewHolder
    {
        TextView txtTen,txtMota;
        ImageView hinh;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null)
        {
            viewHolder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);
            viewHolder.txtTen=(TextView)view.findViewById(R.id.txtTenDoVatShow);
            viewHolder.txtMota=(TextView)view.findViewById(R.id.txtMoTaShow);
            viewHolder.hinh=(ImageView)view.findViewById(R.id.imgAnhShow);
            view.setTag(viewHolder);
        }
        else {
            viewHolder= (ViewHolder) view.getTag();
        }
        DoVat doVat=doVatsList.get(i);

        viewHolder.txtTen.setText(doVat.getTen());
        viewHolder.txtMota.setText(doVat.getMoTa());
        //chuyển byte[] -> bitmap  . vì ảnh lưu trong sqlite là byte[]
        byte[] hinhAnh =doVat.getHinh();
        Bitmap bitmap= BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
        viewHolder.hinh.setImageBitmap(bitmap);
        return view;
    }
}
