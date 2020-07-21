package com.example.truyendlimplicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    Button btnSendData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSendData=(Button)findViewById(R.id.btnSendData);
        final String[] arrCource={"android","ios","javascript","php","python"};
        final HocSinh hocSinh=new HocSinh(20,"Nguyễn Linh");
        btnSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SecondActivity.class);

                intent.putExtra("dulieu","nội dung dữ liệu"); //truyền dũ liệu là string
                intent.putExtra("so",6688);  // truyền dữ liệu kiểu int
                intent.putExtra("dlArr",arrCource);  //truyền dữ liệu kiểu array string
                intent.putExtra("hs",hocSinh);  //truyền dữ liệu kiểu object, class HocSinh phải implements Serializable
                //----------------------------------
                //gửi dữ liệu qua Bundle. bundle như là 1 cái thùng lớn có thể chứa nhiều dữ liệu hoặc
                //nhiều kiểu dữ luệ khác nhau thì gửi đi sẽ tiện hơn.
                String[] arrCity={"HCM","cần thơ","hà nội","nha trang"};
                HocSinh hs_2=new HocSinh(19,"Phạm Quỳnh Tiên");

                Bundle bundle=new Bundle();
                bundle.putString("chuoi","dữ liệu là chuỗi - string");
                bundle.putInt("ConSo",44999);
                bundle.putStringArray("mangDL",arrCity);
                bundle.putSerializable("doituong",hs_2);

                intent.putExtra("khoiDL",bundle);


                startActivity(intent);
            }
        });
    }
}