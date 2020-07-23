package com.example.truyendlimplicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    TextView txtKq,txtKq2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        txtKq=(TextView)findViewById(R.id.txtKetQua);
        txtKq2=(TextView)findViewById(R.id.txtKQ2);

        //intent nhận dữ liệu
        Intent intent=getIntent();
        String duLieu=intent.getStringExtra("dulieu");//vì bên kia gửi dữ liệu là kiểu string nên dùng getStringExtra()
        int dlSo=intent.getIntExtra("so",22); /* 22 là giá trị mặc định, số này sẽ được dùng khi
                    bên kia gửi giá trị là kiểu float mà bên này dùng getIntExtra , hoặc bên kia gửi kiểu int nhưng tên
                    tham số là "so" mà bên này lại getIntExtra("So", 22) thì name có phân biệt hoa thường thì vì
                    ko có name tên "So" nên cũng sẽ lấy giá trị mặc định.*/
        txtKq.setText(duLieu+" ; "+dlSo);

        String[] arrMonhoc=intent.getStringArrayExtra("dlArr");
        txtKq.append("\n"+arrMonhoc.length+" - "+arrMonhoc[2]);

        HocSinh hocSinh= (HocSinh) intent.getSerializableExtra("hs");
        txtKq.append("\n"+hocSinh.getHoTen()+" - "+hocSinh.getTuoi());

        //intent nhận dữ liệu truyền quan Bundle
        Bundle bundle=intent.getBundleExtra("khoiDL");
        if(bundle!=null)//check xem nếu truyền name: "khoiDL" sai thì bundle sẽ null và khi lấy dữ liệu thì app sẽ crash.
        {
            String chuoi=bundle.getString("chuoi");
            int so=bundle.getInt("ConSo",-1);
            String[] arr=bundle.getStringArray("mangDL");
            HocSinh hocSinh2= (HocSinh) bundle.getSerializable("doituong");

            txtKq2.setText(chuoi+"\n"+so+"\n"+arr[1]+"\n"+hocSinh2.getHoTen());
        }

    }
}