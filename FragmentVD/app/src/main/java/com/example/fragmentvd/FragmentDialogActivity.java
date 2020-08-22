package com.example.fragmentvd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FragmentDialogActivity extends AppCompatActivity implements DeleteData{
    Button btnXoa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_dialog);

        btnXoa=(Button)findViewById(R.id.btnFragDialogVD);
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentHopThoai fragmentHopThoai=new FragmentHopThoai();
                fragmentHopThoai.show(getFragmentManager(),"dialog_xacnhan");
            }
        });
    }

    @Override
    public void GiaTriXoa(boolean delete) {
        if(delete)
            Toast.makeText(FragmentDialogActivity.this,"đã chọn xóa",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(FragmentDialogActivity.this,"đã chọn không",Toast.LENGTH_SHORT).show();
    }
}