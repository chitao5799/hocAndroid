package com.example.fragmentvd;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void AddFragment(View view) {
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        Fragment fragment=null;
        switch (view.getId())
        {
            case R.id.btnFragmentA:
                fragment= new FragmentA();
                break;
            case R.id.btnFragmentB:
               fragment=new FragmentB();
                break;
        }
        fragmentTransaction.replace(R.id.frameLayout1,fragment);//replace có thể thay bằng add nếu chỉ có 1 fragment
        fragmentTransaction.commit();
    }
}