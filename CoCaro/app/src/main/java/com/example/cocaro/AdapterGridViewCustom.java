package com.example.cocaro;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterGridViewCustom extends BaseAdapter
{
    Context myContext;
    int myLayout;
    ArrayList<String> arr;

    public AdapterGridViewCustom(Context myContext, int myLayout, ArrayList<String> arr)
    {
        this.myContext = myContext;
        this.myLayout = myLayout;
        this.arr = arr;
    }

    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object getItem(int i) {
        return arr.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater= (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(myLayout,null);
        final CustomTextView customTextView=view.findViewById(R.id.custom_textview);
        customTextView.setBackgroundResource(R.drawable.oo);
        customTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customTextView.setText("ok");
            }
        });
        return view;
    }
}
