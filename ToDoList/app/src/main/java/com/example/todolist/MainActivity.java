package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.WeakHashMap;

public class MainActivity extends AppCompatActivity {
    Database database;
    ListView lvCongViec;
    ArrayList<CongViec> arrayCV;
    CongViecAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvCongViec=(ListView)findViewById(R.id.listViewCongViec);
        arrayCV=new ArrayList<>();
        adapter=new CongViecAdapter(MainActivity.this,R.layout.dong_cong_viec,arrayCV);
        lvCongViec.setAdapter(adapter);
        

        database=new Database(this,"ghichu,sqlite",null,1);
        //tạo bảng congviec
        String query="CREATE TABLE IF NOT EXISTS CongViec (Id integer primary key autoincrement,TenCV varchar(200) )";
        database.QueryData(query);
        //insert data
       // database.QueryData("insert into CongViec values(null,'công việc ghi chú')");

        GetDataCongViec();
    }
    private void GetDataCongViec()
    {
        //get data
        Cursor cursorDataCongviec=database.GetData("select * from CongViec");
        arrayCV.clear();
        while(cursorDataCongviec.moveToNext())
        {
            String tenCV=cursorDataCongviec.getString(1);
            int id=cursorDataCongviec.getInt(0);
            arrayCV.add(new CongViec(id,tenCV));
        }
        adapter.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_congviec,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menuAddCV)
            DailogThem();
        return super.onOptionsItemSelected(item);
    }

    private void DailogThem()
    {
        final Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_cong_viec);

        final EditText edtAdd=(EditText)dialog.findViewById(R.id.edtThemCongViec);
        Button btnAdd=(Button)dialog.findViewById(R.id.btnThemCV);
        Button btnHuy=(Button)dialog.findViewById(R.id.btnCancel);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten=edtAdd.getText().toString().trim();
                if(ten.equals(""))
                {
                    Toast.makeText(MainActivity.this,"vui lòng nhập tên công việc",Toast.LENGTH_LONG).show();
                }
                else{
                    database.QueryData("insert into CongViec values(null,'"+ten+"')");
                    Toast.makeText(MainActivity.this,"Thêm thành công",Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                    GetDataCongViec();
                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    public void DialogSuaCongViec(String tenCV, final int idCV)
    {
        final Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_update_congviec);

        final EditText edtSua=(EditText)dialog.findViewById(R.id.editTextUpdate);
        Button btnHuy=(Button)dialog.findViewById(R.id.btnHuyUpdate);
        Button btnSua=(Button)dialog.findViewById(R.id.btnUpdate);

        edtSua.setText(tenCV);
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten=edtSua.getText().toString().trim();
                if(ten.equals(""))
                {
                    Toast.makeText(MainActivity.this,"vui lòng nhập tên công việc",Toast.LENGTH_LONG).show();
                }
                else{
                    database.QueryData("update CongViec set TenCV='"+ten+"' where Id='"+idCV+"'");
                    Toast.makeText(MainActivity.this,"Sửa thành công",Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                    GetDataCongViec();
                }
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    public void DialogXoaCv(String tenCV, final int id)
    {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setMessage("Bạn có chắc chắn muốn xóa công việc \""+tenCV+"\" không?");
        dialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                database.QueryData("delete from CongViec where Id='"+id+"'");
                Toast.makeText(MainActivity.this,"xóa thành công",Toast.LENGTH_LONG).show();
                GetDataCongViec();
            }
        });
        dialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialog.show();
    }
}