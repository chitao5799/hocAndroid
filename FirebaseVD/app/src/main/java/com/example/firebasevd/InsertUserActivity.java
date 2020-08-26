package com.example.firebasevd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertUserActivity extends AppCompatActivity {
    String idUser;
    int lastID=0;
    EditText edtId,edtName,edtEmail;
    Button btnInsert,btnClose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_user);
        GetControls();
        Intent intent=getIntent();
        lastID=intent.getIntExtra("id",0);
        idUser="user"+(lastID+1);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user=new User();
                user.setId(lastID+1+"");
                user.setName(edtName.getText().toString());
                user.setEmail(edtEmail.getText().toString());

                FirebaseDatabase database=FirebaseDatabase.getInstance();
                DatabaseReference mRef=database.getReference("users");
                mRef.child(idUser).setValue(user);
                finish();
            }
        });

    }
    private void GetControls()
    {
        edtEmail=(EditText)findViewById(R.id.edtEmail);
        edtId=(EditText)findViewById(R.id.edtID);
        edtName=(EditText)findViewById(R.id.edtName);
        btnInsert=(Button)findViewById(R.id.btnInsert);
        btnClose=(Button)findViewById(R.id.btnClose);
    }
}