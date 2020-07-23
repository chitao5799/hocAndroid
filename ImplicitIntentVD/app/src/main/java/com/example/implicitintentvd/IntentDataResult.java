package com.example.implicitintentvd;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class IntentDataResult extends AppCompatActivity {
    TextView txtName;
    Button btnEdit;
    int REQUEST_CODE_EDIT=6688;//số mình tự đặt
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_data_result);

        txtName=(TextView)findViewById(R.id.txtName);
        btnEdit=(Button)findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(IntentDataResult.this,EditTextShowed.class);
                startActivityForResult(intent,REQUEST_CODE_EDIT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==REQUEST_CODE_EDIT && resultCode == RESULT_OK && data!=null)
        {
            String ten=data.getStringExtra("NewName");
            txtName.setText(ten);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}