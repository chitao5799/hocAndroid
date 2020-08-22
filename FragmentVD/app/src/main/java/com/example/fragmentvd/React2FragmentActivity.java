package com.example.fragmentvd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class React2FragmentActivity extends AppCompatActivity {
    TextView txtMain;
    Button btnMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_react2_fragment);

        txtMain=(TextView)findViewById(R.id.txtMain);
        btnMain=(Button)findViewById(R.id.btnMain);

        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //đứng ở activity set text cho fragment one, fragment này ở trong activity này.
                FragmentOne fragmentOne= (FragmentOne) getFragmentManager().findFragmentById(R.id.fragmentOne);
                //fragmentOne.txtOne.setText("thay đổi bởi activity");
                fragmentOne.GanNoiDung("change by activity");
            }
        });
    }
}