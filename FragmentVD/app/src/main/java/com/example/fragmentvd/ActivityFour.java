package com.example.fragmentvd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityFour extends AppCompatActivity {
    FragmentManager fragmentManager=getFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
    }

    public void AddAAA(View view) {
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frameLayoutActiFour,new FragmentAAA(),"TagFragAAA");
        fragmentTransaction.addToBackStack("stack_AAA");
        fragmentTransaction.commit();
    }

    public void AddBBB(View view) {
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frameLayoutActiFour,new FragmentBBB(),"TagFragBBB");
        fragmentTransaction.addToBackStack("stack_BBB");
        fragmentTransaction.commit();
    }

    public void AddCCC(View view) {
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frameLayoutActiFour,new FragmentCCC(),"TagFragCCC");
        fragmentTransaction.addToBackStack("stack_CCC");
        fragmentTransaction.commit();
    }

    public void RemoveAAA(View view) {
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        FragmentAAA fragmentAAA= (FragmentAAA) getFragmentManager().findFragmentByTag("TagFragAAA");
        if(fragmentAAA!=null){
            fragmentTransaction.remove(fragmentAAA);
            fragmentTransaction.commit();
        }else
            Toast.makeText(ActivityFour.this,"Fragment AAA không tồn tại",Toast.LENGTH_SHORT).show();

    }

    public void RemoveBBB(View view) {
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        FragmentBBB fragmentBBB= (FragmentBBB) getFragmentManager().findFragmentByTag("TagFragBBB");
        if(fragmentBBB!=null){
            fragmentTransaction.remove(fragmentBBB);
            fragmentTransaction.commit();
        }else
            Toast.makeText(ActivityFour.this,"Fragment BBB không tồn tại",Toast.LENGTH_SHORT).show();
    }

    public void RemoveCCC(View view) {
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        FragmentCCC fragmentCCC= (FragmentCCC) getFragmentManager().findFragmentByTag("TagFragCCC");
        if(fragmentCCC!=null){
            fragmentTransaction.remove(fragmentCCC);
            fragmentTransaction.commit();
        }else
            Toast.makeText(ActivityFour.this,"Fragment CCC không tồn tại",Toast.LENGTH_SHORT).show();
    }

    public void Back(View view) {
        getFragmentManager().popBackStack();
    }

    public void Pop_AAA(View view) {
       getFragmentManager().popBackStack("stack_AAA",0);
    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() > 0)
             getFragmentManager().popBackStack();
        else
            super.onBackPressed();
    }
}