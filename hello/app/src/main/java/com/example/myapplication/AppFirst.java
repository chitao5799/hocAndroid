package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AppFirst extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_first);


    }

//    view.findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            NavHostFragment.findNavController(AppFirst.this)
//                    .navigate(R.id.action_FirstFragment_to_SecondFragment);
//        }
//    });
//    view.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            Toast myToast = Toast.makeText(getActivity(), "Hello toast!", Toast.LENGTH_SHORT);
//            myToast.show();
//        }
//    });
}