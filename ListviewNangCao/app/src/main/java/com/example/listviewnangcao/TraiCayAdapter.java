package com.example.listviewnangcao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class TraiCayAdapter extends BaseAdapter {
    private Context context;
    private int layout; //là file layout .xml - nó trả về kiểu int
    private List<TraiCay> listTraiCay;

    public TraiCayAdapter(Context context, int layout, List<TraiCay> listTraiCay) {
        this.context = context;
        this.layout = layout;
        this.listTraiCay = listTraiCay;
    }

    @Override
    public int getCount()//giá trị trả về của hàm này là số dòng (số item) muốn hiện trên listview
    {
        return listTraiCay.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    /*
     //vd có 10 dòng mà trên đt chỉ hiển thị xem được 5 dòng thôi, khi mình kéo xuống -kéo lên để xem các dòng bị ẩn thì hàm này sẽ
        //chạy lại, làm giảm hiểu xuất. cách xử lý dùng viewholder - xem hàm getView thứ 2 ở dưới
     @Override
    public View getView(int i, View view, ViewGroup viewGroup)//trả về mỗi dòng (item) của adapter để hiển thị trên listview
    {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(layout,null);

        TextView txtTen=(TextView) view.findViewById(R.id.txtTen);
        TextView txtMota=(TextView) view.findViewById(R.id.txtMoTa);
        ImageView imgHinh=(ImageView) view.findViewById(R.id.imgViewHinh);


            //gán giá trị
            TraiCay traiCay=listTraiCay.get(i);

                txtTen.setText(traiCay.getTen());
                txtMota.setText(traiCay.getMoTa());
                imgHinh.setImageResource(traiCay.getHinh());

                return view;
        }
        */
    private class ViewHolder{
        ImageView imgHinh;
        TextView txtTen,txtMota;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup)//trả về mỗi dòng (item) của adapter để hiển thị trên listview
    {
        ViewHolder viewHolder;
        if(view==null)
        {
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);

            viewHolder=new ViewHolder();
            viewHolder.txtTen=(TextView) view.findViewById(R.id.txtTen);
            viewHolder.txtMota=(TextView) view.findViewById(R.id.txtMoTa);
            viewHolder.imgHinh=(ImageView) view.findViewById(R.id.imgViewHinh);
            view.setTag(viewHolder);//ánh xạ
        }else {
            viewHolder=(ViewHolder) view.getTag();
        }

        //gán giá trị
        TraiCay traiCay=listTraiCay.get(i);

        viewHolder.txtTen.setText(traiCay.getTen());
        viewHolder.txtMota.setText(traiCay.getMoTa());
        viewHolder.imgHinh.setImageResource(traiCay.getHinh());

        //áp dụng animation cho listview
        Animation animScaleList= AnimationUtils.loadAnimation(context,R.anim.list_scale);
        view.startAnimation(animScaleList);
        return view;
    }
}
