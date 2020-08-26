package com.example.firebasevd;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ContentMainActivity extends AppCompatActivity {
    ListView lvUsers;
    ArrayList<User> arrListUsers=new ArrayList<>();
    UserAdapter adapter;
    int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvUsers=(ListView)findViewById(R.id.lvUsers);
        adapter=new UserAdapter(ContentMainActivity.this,R.layout.item_user,arrListUsers);
        lvUsers.setAdapter(adapter);
        GetDataListview();
      //  adapter.notifyDataSetChanged();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(arrListUsers.size()>0)
                    id=Integer.parseInt(arrListUsers.get(arrListUsers.size()-1).getId());
                Intent intent=new Intent(ContentMainActivity.this,InsertUserActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }
    private void GetDataListview()
    {
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference mRef=database.getReference("users");
        //lắng nghe sự kiện có sự thay đổi dữ liệu thì thực hiện đọc
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrListUsers.clear();
                adapter.notifyDataSetChanged();
                //lấy ra 1 mảng dữ liệu trên database
                for(DataSnapshot data: dataSnapshot.getChildren())
                {
                    User user =data.getValue(User.class);
                 //   Toast.makeText(ContentMainActivity.this,user.getName(),Toast.LENGTH_SHORT).show();
                    arrListUsers.add(user);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("user","không đọc được csdl");
            }
        });
    }
}