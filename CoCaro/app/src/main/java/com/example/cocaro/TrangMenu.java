package com.example.cocaro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class TrangMenu extends AppCompatActivity {
    Button btnStartGame;
    EditText edtTimePlay;
    int minutePerPlay;
    CheckBox cbTinhDiem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_menu);

        btnStartGame=(Button)findViewById(R.id.btnStartGame);
        edtTimePlay=(EditText)findViewById(R.id.edtTimePerPlay);
        cbTinhDiem=(CheckBox)findViewById(R.id.cbTinhDiem);

        btnStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!CheckInput())
                    return;
                if(cbTinhDiem.isChecked())
                {
                    Intent intent=new Intent(TrangMenu.this,TinhDiem2Nguoi.class);
                    intent.putExtra("timePerPlay",minutePerPlay);
                    startActivity(intent);
                }
                else{
                    Intent intent=new Intent(TrangMenu.this,MainActivity.class);
                    intent.putExtra("timePerPlay",minutePerPlay);
                    startActivity(intent);
                }

            }
        });
    }
    private boolean CheckInput()
    {
        if(edtTimePlay.getText().toString().equals("") || edtTimePlay.getText()==null)
        {
            Toast.makeText(TrangMenu.this,"bạn cần nhập thời gian mỗi lượt chơi",Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            minutePerPlay=Integer.parseInt(edtTimePlay.getText().toString());
            if(minutePerPlay>99||minutePerPlay<0)
            {
                Toast.makeText(TrangMenu.this,"thời gian mỗi lượt chơi cần nhỏ hơn 100 phút và lớn hơn 0",Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        return true;
    }
}