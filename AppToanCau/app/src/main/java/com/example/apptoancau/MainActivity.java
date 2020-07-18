package com.example.apptoancau;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txtThongTin;
    Button btnXacNhan;
    EditText edtName,edtEmail,edtSDT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hoten=edtName.getText().toString();
                String email=edtEmail.getText().toString();
                String sdt=edtSDT.getText().toString();

                String textChao=getResources().getString(R.string.txtChao);
                String textEmail=getResources().getString(R.string.txtEmail);
                String textSDT=getResources().getString(R.string.txtSDT);
                txtThongTin.setText(textChao+": "+hoten+"\n"+textEmail+": "+email+"\n"+textSDT+": "+sdt);
            }
        });
    }

    private void AnhXa() {
        txtThongTin=(TextView)findViewById(R.id.txtThonTin);
        btnXacNhan=(Button)findViewById(R.id.btnXacNhan);
        edtName=(EditText)findViewById(R.id.edtHoTen);
        edtEmail=(EditText)findViewById(R.id.edtEmail);
        edtSDT=(EditText)findViewById(R.id.edtSDT);
    }
}