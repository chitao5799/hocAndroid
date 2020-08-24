package com.example.googleapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.List;

public class VideoAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<VideoYoutube> listVideo;

    public VideoAdapter(Context context, int layout, List<VideoYoutube> listVideo) {
        this.context = context;
        this.layout = layout;
        this.listVideo = listVideo;
    }

    @Override
    public int getCount() {
        return listVideo.size();
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
        ImageView imgThumbnail;
        TextView txtTiTle;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null)
        {
            holder=new ViewHolder();
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(layout,null);
            holder.imgThumbnail=(ImageView)view.findViewById(R.id.imgThumbnail);
            holder.txtTiTle=(TextView)view.findViewById(R.id.txtTitle);
            view.setTag(holder);
        }
        else holder= (ViewHolder) view.getTag();
        VideoYoutube video=listVideo.get(i);
        holder.txtTiTle.setText(video.getTitle());
        //hiển thị ảnh lên imageview từ url dùng: https://square.github.io/picasso/
        Picasso.with(context).load(video.getThumbnail()).into(holder.imgThumbnail);

        return view;
    }
}
