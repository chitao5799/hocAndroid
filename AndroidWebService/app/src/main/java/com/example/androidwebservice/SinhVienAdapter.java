package com.example.androidwebservice;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class SinhVienAdapter extends BaseAdapter {
    private MainActivity context;
    private int layout;
    private List<SinhVien> listSV;

    public SinhVienAdapter(MainActivity context, int layout, List<SinhVien> listSV) {
        this.context = context;
        this.layout = layout;
        this.listSV = listSV;
    }

    @Override
    public int getCount() {
        return listSV.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private class ViewHolder{
        TextView txtName,txtNamSinh,txtDiaChi;
        ImageView btnDelete,btnEdit;
    }
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null)
        {
            holder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);
            holder.txtName=(TextView)view.findViewById(R.id.txtTenSVShow);
            holder.txtNamSinh=(TextView)view.findViewById(R.id.txtNamSinhShow);
            holder.txtDiaChi=(TextView)view.findViewById(R.id.txtDiachiShow);
            holder.btnDelete=(ImageView)view.findViewById(R.id.btnDelete);
            holder.btnEdit=(ImageView)view.findViewById(R.id.btnEdit);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }

        final SinhVien sv=listSV.get(i);
        holder.txtNamSinh.setText("Năm sinh: "+sv.getNamSinh());
        holder.txtName.setText(sv.getHoTen());
        holder.txtDiaChi.setText(sv.getDiaChi());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent=new Intent(context,UpdateSVActivity.class);
                    intent.putExtra("dataSinhVien",sv);
                    context.startActivity(intent);
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogDelete(sv.getHoTen(),sv.getId());
            }
        });

        return view;
    }
    private void DialogDelete(String ten, final int idsv)
    {
        AlertDialog.Builder dialog=new AlertDialog.Builder(context);
        dialog.setMessage("bạn có chắc chắn muốn xóa sv " +ten +" không");
        dialog.setPositiveButton("có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                context.DeleteStudent(idsv);
            }
        });
        dialog.setNegativeButton("không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialog.show();
    }
}
