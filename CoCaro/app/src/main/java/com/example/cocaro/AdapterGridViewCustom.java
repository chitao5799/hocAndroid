package com.example.cocaro;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
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
  //  ArrayList<String> arr;
    ArrayList<CustomTextView> arr;
    public AdapterGridViewCustom(Context myContext, int myLayout, ArrayList<CustomTextView> arr)
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

    boolean isX=true;
    private  class ViewHolder{
        CustomTextView customTextView;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater= (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(myLayout,null);
        final CustomTextView customTextView=view.findViewById(R.id.custom_textview);
        customTextView.setBackgroundResource(R.drawable.oo);
        customTextView.setTypeface(null, Typeface.BOLD);
        customTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
         customTextView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if(isX)
                {
                      customTextView.setText("X");
                    customTextView.setTextColor(Color.parseColor("#ff0000"));
                      isX=false;
                }
                 else
                     {
                         customTextView.setText("O");
                         customTextView.setTextColor(Color.parseColor("#00cc00"));
                         isX=true;
                    }
             }
         });
        return view;
    }
    /*@Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if(view == null)
        {
            viewHolder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(myLayout,null);
            viewHolder.customTextView=(CustomTextView) view.findViewById(R.id.custom_textview);
            view.setTag(viewHolder);
        }else
        {
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.customTextView.setBackgroundResource(R.drawable.oo);
        viewHolder.customTextView.setTypeface(null, Typeface.BOLD);
        viewHolder.customTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
        viewHolder.customTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isX)
                {
                    viewHolder.customTextView.setText("X");
                    viewHolder.customTextView.setTextColor(Color.parseColor("#ff0000"));
                    isX=false;
                }
                else
                {
                    viewHolder.customTextView.setText("O");
                    viewHolder.customTextView.setTextColor(Color.parseColor("#00cc00"));
                    isX=true;
                }
            }
        });
        return view;
    }*/
}
