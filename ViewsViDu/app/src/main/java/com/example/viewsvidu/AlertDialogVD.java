package com.example.viewsvidu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AlertDialogVD extends AppCompatActivity {
    ListView lvMonHoc;
    ArrayList<String> arrMonHoc;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog_v_d);

        lvMonHoc=(ListView)findViewById(R.id.lv_inActDialog);
        arrMonHoc=new ArrayList<>();
        addItemToArray();
        adapter=new ArrayAdapter(AlertDialogVD.this,android.R.layout.simple_list_item_1,arrMonHoc);
        lvMonHoc.setAdapter(adapter);

        lvMonHoc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                XacNhanXoa(i);
                return false;
            }
        });

    }
    private  void XacNhanXoa(final int position)
    {
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(AlertDialogVD.this);
        alertDialog.setTitle("Thông báo");
        alertDialog.setIcon(R.mipmap.ic_launcher);
        alertDialog.setMessage("Bạn có chắc chắn muốn xóa \""+arrMonHoc.get(position)+"\" không");
        alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                arrMonHoc.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
        alertDialog.setNegativeButton("không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertDialog.show();
    }
    private  void addItemToArray()
    {
        arrMonHoc.add("android");
        arrMonHoc.add("nodejs");
        arrMonHoc.add("ios");
        arrMonHoc.add("java");
        arrMonHoc.add("javascript");
    }
}