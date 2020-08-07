package com.example.orderfood;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class FoodAdapter extends ArrayAdapter<Food>
{
    ArrayList<Food> listFood;
    Context context;
    public FoodAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Food> listFood) {
        super(context, resource, listFood);
        this.listFood=listFood;
        this.context=context;
    }

    @Override
    public int getCount() {
        return listFood.size();
    }

    @Nullable
    @Override
    public Food getItem(int position) {
        return listFood.get(position);
    }

    @Override
    public int getPosition(@Nullable Food item) {
        return listFood.indexOf(item);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,
                        @NonNull ViewGroup parent) {
        View view=convertView;
        if(view==null){
            LayoutInflater layoutInflater=LayoutInflater.from(context);
            view=layoutInflater.inflate(R.layout.item_food_layout,null);

        }
        //lấy ra thông tin của food tại vị trí position
        Food food=getItem(position);
        //thực hiện gán dữ liệu lên item food
        if(food!=null){
            TextView tvName=(TextView)view.findViewById(R.id.txtNameItemListview);
            TextView tvPrice=(TextView)view.findViewById(R.id.txtGiaItemListview);
            TextView tvAmount=(TextView)view.findViewById(R.id.txtSLitemListView);
            ImageView imageView=(ImageView)view.findViewById(R.id.imgItemListview);
            ImageButton imageButton=(ImageButton)view.findViewById(R.id.imgOrder);
            tvName.setText(food.name);
            tvPrice.setText(food.gia);
            tvAmount.setText(food.soluong);
            Bitmap bitmap= BitmapFactory.decodeByteArray(food.picture,0,food.picture.length);
            imageView.setImageBitmap(bitmap);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"thêm food vào giỏ hàng",Toast.LENGTH_LONG).show();
                }
            });
        }

        return view;

    }
}
