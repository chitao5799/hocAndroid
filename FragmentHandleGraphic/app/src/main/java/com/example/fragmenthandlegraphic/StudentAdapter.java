package com.example.fragmenthandlegraphic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.fragment.app.ListFragment;

import java.util.List;

public class StudentAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Student> studentsList;

    public StudentAdapter(Context context, int layout, List<Student> studentsList) {
        this.context = context;
        this.layout = layout;
        this.studentsList = studentsList;
    }

    @Override
    public int getCount() {
        return studentsList.size();
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
        TextView txtTensv;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null)
        {
            holder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);
            holder.txtTensv=(TextView)view.findViewById(R.id.txtTenSV);
            view.setTag(holder);
        }
        else holder= (ViewHolder) view.getTag();

        Student sv=studentsList.get(i);
        holder.txtTensv.setText(sv.getHoTen());
        return view;
    }
}
