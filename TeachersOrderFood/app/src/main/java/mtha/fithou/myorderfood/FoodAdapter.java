package mtha.fithou.myorderfood;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class FoodAdapter extends ArrayAdapter<Food> {
    ArrayList<Food> lsFood;
    Context context;
    public FoodAdapter(@NonNull Context context, int resource,
                       @NonNull ArrayList<Food> lsFood) {
        super(context, resource, lsFood);
        this.lsFood = lsFood;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lsFood.size();
    }

    @Nullable
    @Override
    public Food getItem(int position) {
        return lsFood.get(position);
    }

    @Override
    public int getPosition(@Nullable Food item) {
        return lsFood.indexOf(item);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,
                        @NonNull ViewGroup parent) {
        View view = convertView;
        if(view ==null){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.item_food,null);
        }
        //lay ra thong tin cua food tai vi tri position
        Food food = getItem(position);
        //thuc hien gan du lieu len item_food
        if(food!=null){
            TextView tvName = (TextView) view.findViewById(R.id.tvNameItem);
            TextView tvPrice = (TextView)view.findViewById(R.id.tvPriceItem);
            TextView tvAmount = (TextView)view.findViewById(R.id.tvAmountItem);
            ImageView imageView = (ImageView)view.findViewById(R.id.imgFoodItem);
            ImageButton imageButton = (ImageButton)view.findViewById(R.id.btnAdd);
            tvName.setText(food.name);
            tvPrice.setText(food.price);
            tvAmount.setText(food.amount);
            Bitmap bitmap = BitmapFactory.decodeByteArray(food.picture,
                    0,food.picture.length);
            //set cho imageView
            imageView.setImageBitmap(bitmap);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //xu ly su kien o day
                    Toast.makeText(context,"Them food vao gio hang ",
                            Toast.LENGTH_LONG).show();
                }
            });

        }
        return view;
    }
}
