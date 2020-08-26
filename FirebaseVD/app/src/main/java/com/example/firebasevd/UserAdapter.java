package com.example.firebasevd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {
    ArrayList<User> lsUser;
    Context context;
    public UserAdapter(@NonNull Context context, int resource, ArrayList<User> arrUser) {
        super(context, resource);
        this.context=context;
        this.lsUser=arrUser;
    }

    @Override
    public int getCount() {
        return lsUser.size();
    }

    @Nullable
    @Override
    public User getItem(int position) {
        return lsUser.get(position);
    }

    @Override
    public int getPosition(@Nullable User item) {
        return lsUser.indexOf(item);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if(v==null)
        {
            LayoutInflater layoutInflater=LayoutInflater.from(context);
            v=layoutInflater.inflate(R.layout.item_user,null);
        }
        TextView tvId=(TextView)v.findViewById(R.id.txtID);
        TextView tvName=(TextView)v.findViewById(R.id.txtName);
        TextView tvEmail=(TextView)v.findViewById(R.id.txtEmail);
        ImageButton imgUpdate=(ImageButton)v.findViewById(R.id.imgBtnUpdate);
        ImageButton imgDelete=(ImageButton)v.findViewById(R.id.imgBtnDelete);

        final User user=lsUser.get(position);
        tvId.setText(user.getId());
        tvName.setText(user.getName());
        tvEmail.setText(user.getEmail());

        final String UserId="user"+user.getId();
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference mRef=database.getReference("users");
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRef.child(UserId).removeValue();
                notifyDataSetChanged();
            }
        });
        return v;
    }
}
