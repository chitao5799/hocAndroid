package mtha.fithou.mycontacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InsContactActivity extends AppCompatActivity {

    Button btnCallPhone;
    TextView tvPhone;
    EditText etName, etPhone;
    Button btnSave;
    final int MY_REQUEST_CODE =1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ins_contact);
        getViews();

    }


    private void getViews(){
        btnCallPhone = (Button) findViewById(R.id.btnCallPhone);
        tvPhone = (TextView) findViewById(R.id.tvInfo);
        etName = (EditText) findViewById(R.id.etName);
        etPhone = (EditText) findViewById(R.id.etPhone);
        btnSave = (Button) findViewById(R.id.btnSave);
    }
    public void onBack(View view) {
        //xu ly su kien thuc hien cuoc goi
        Intent dialPhone = new Intent(Intent.ACTION_CALL);
        //setData cho doi tuong intent
        dialPhone.setData(Uri.parse("tel:" + etPhone.getText().toString()));
        //thuc hien startActivity de thuc hien quay so
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            ActivityCompat.requestPermissions(InsContactActivity.this,
                    new String[]{Manifest.permission.CALL_PHONE}
            ,MY_REQUEST_CODE);
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

        }else {
            try {
                startActivity(dialPhone);
            }catch (SecurityException e){
                e.printStackTrace();
            }
        }
        startActivity(dialPhone);


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode){
            case MY_REQUEST_CODE:{
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //duoc cap cap quyen thuc hien cuoc goi
                }else{
                    //quyen nay chua duoc cap
                }
            }
            return;
        }
     //   super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void onSaveContact(View view) {
            //lay du lieu
        String name = etName.getText().toString();
        String phone = etPhone.getText().toString();
        //tao doi tuong intent de put du lieu tra ve
        Intent intent = new Intent();
        intent.putExtra("name", name);
        intent.putExtra("phone",phone);
        //set ket qua tra ve cho activity main
        setResult(RESULT_OK,intent);

        //dong activity hien tai
        finish();
    }
}