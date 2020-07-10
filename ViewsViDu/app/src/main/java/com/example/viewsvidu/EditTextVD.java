package com.example.viewsvidu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class EditTextVD extends AppCompatActivity {
    EditText edtTxt,edtNum1,edtNum2;
    Button btnClick,btnRandom,btnRandom2;
    TextView txtNumberRandom,txtRandomKq;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_textvd);

        getViews();
       // edtTxt.setText("gán nội dung");
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String noiDung=edtTxt.getText().toString();
                //Toast để hiện 1 string (có thể gồm cả icon nhập từ bàn phím đt) lên màn hình 1 thời gian ngắn tầm vài giây.
                Toast.makeText(EditTextVD.this,noiDung,Toast.LENGTH_LONG).show();
            }
        });


        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //tạo số ngẫu nhiên
                Random random=new Random();
                //random.nextInt(); lấy ra số ngẫu nhiên trong khoảng của kiểu int (cả âm lẫn dương - số lớn nhất của kiểu int khoảng vài tỷ)
                //random.nextInt(100); //lấy số ngẫu nhiên từ 0 -> 99
                int number=random.nextInt();
                txtNumberRandom.setText(number+"");//number+"" : là ép kiểu int sang string
            }
        });

        btnRandom2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String chuoi1=edtNum1.getText().toString().trim();
                String chuoi2=edtNum2.getText().toString().trim();
                if(chuoi1.length()==0)
                {
                    Toast.makeText(EditTextVD.this,"vui lòng nhập số ở ô nhập số min",Toast.LENGTH_LONG).show();
                    return;
                }
                if(chuoi2.isEmpty())
                {
                    Toast.makeText(EditTextVD.this,"vui lòng nhập số ở ô nhập số max",Toast.LENGTH_LONG).show();
                    return;
                }
                int numMin=Integer.parseInt(chuoi1);
                int numMax=Integer.parseInt(chuoi2);
                if(numMax-numMin<=0)
                {
                    Toast.makeText(EditTextVD.this," số max phải lớn hơn số min",Toast.LENGTH_LONG).show();
                    return;
                }
                Random random=new Random();
                int number = numMin + random.nextInt(numMax-numMin+1)  ;
                txtRandomKq.setText(String.valueOf(number));

            }
        });
    }
    private  void getViews()
    {
        edtTxt=(EditText) findViewById(R.id.txtName);
        btnClick=(Button) findViewById(R.id.btnClick);

        txtNumberRandom=(TextView) findViewById(R.id.txtRandom);
        btnRandom=(Button) findViewById(R.id.btnRandom);

        edtNum1=(EditText) findViewById(R.id.edtTextNum1);
        edtNum2=(EditText) findViewById(R.id.edtTextNum2);
        btnRandom2=(Button) findViewById(R.id.btnRandom2);
        txtRandomKq=(TextView) findViewById(R.id.txtResult);
    }
}