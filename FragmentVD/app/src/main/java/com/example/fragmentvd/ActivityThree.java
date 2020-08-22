package com.example.fragmentvd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityThree extends AppCompatActivity {
    Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        btnAdd=(Button)findViewById(R.id.btnActiThree);
        FragmentManager fragmentManager=getFragmentManager();
        final FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentThree fragThree=new FragmentThree();

                Bundle bundle=new Bundle();
                bundle.putString("DuLieu","đây là dữ liệu gửi từ activity");
                fragThree.setArguments(bundle);

                fragmentTransaction.add(R.id.LinearLayoutActiThree,fragThree);
                fragmentTransaction.commit();
            }
        });
    }
}