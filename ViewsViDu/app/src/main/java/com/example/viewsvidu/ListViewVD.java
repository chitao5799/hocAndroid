package com.example.viewsvidu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class ListViewVD extends AppCompatActivity {
    ListView lvMonhoc;
    Button btnThem,btnCapNhat;
    EditText edtMonHoc;
    int index=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_vd);

        lvMonhoc=(ListView)findViewById(R.id.lv_inActDialog);
        btnThem=(Button)findViewById(R.id.btnThem);
        edtMonHoc=(EditText)findViewById(R.id.edtMonhoc);
        btnCapNhat=(Button)findViewById(R.id.btnSua);

        final ArrayList<String> arrScore;
        arrScore=new ArrayList<>();
        arrScore.add("IOS");
        arrScore.add("Android");
        arrScore.add("Csharpe");
        arrScore.add("java");

        final ArrayAdapter arrayAdapter=new ArrayAdapter(ListViewVD.this,android.R.layout.simple_list_item_1,arrScore);
        lvMonhoc.setAdapter(arrayAdapter);


        //sự kiện người dùng nhấn và dữ một tý
        lvMonhoc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //i là index của item
               // Toast.makeText(ListViewVD.this,"long click:"+ arrScore.get(i),Toast.LENGTH_SHORT).show();
                arrScore.remove(i);
                arrayAdapter.notifyDataSetChanged();
                return false;
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String monMoi=edtMonHoc.getText().toString();
                edtMonHoc.setText("");
                arrScore.add(monMoi);
                arrayAdapter.notifyDataSetChanged();//để cập nhật lại lvMonHoc
            }
        });

        lvMonhoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //i là index của các item trong listview, bắt đầu bằng 0
                // Toast.makeText(ListViewVD.this, "index:"+i,Toast.LENGTH_SHORT).show();
              //  Toast.makeText(ListViewVD.this, arrScore.get(i),Toast.LENGTH_SHORT).show();
                edtMonHoc.setText(arrScore.get(i));
                index=i;
            }
        });

        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrScore.set(index,edtMonHoc.getText().toString());
                arrayAdapter.notifyDataSetChanged();
                edtMonHoc.setText("");
            }
        });
    }
}