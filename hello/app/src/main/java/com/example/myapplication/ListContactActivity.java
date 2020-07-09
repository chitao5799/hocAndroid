package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ListContactActivity extends AppCompatActivity {
    ListView lvContacts;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contact);

        getViews();
        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(ListContactActivity.this,
                android.R.layout.simple_dropdown_item_1line,contacts);
        lvContacts.setAdapter(adapter);

        lvContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String contact=(String) adapterView.getItemAtPosition(position);
                Toast.makeText(ListContactActivity.this,
                        "Contact: "+contact
                        ,Toast.LENGTH_LONG).show();
            }
        });

        lvContacts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                final String contact=(String) adapterView.getItemAtPosition(position);
                AlertDialog.Builder builder=new AlertDialog.Builder(ListContactActivity.this);
                builder.setMessage("ban co muon xoa contact :"+contact+ " khong");
                builder.setCancelable(false);
                builder.setPositiveButton(
                        "Yes", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i)
                                {
                                    adapter.remove(contact);
                                    adapter.notifyDataSetChanged();
                                    Toast.makeText(ListContactActivity.this,
                                            contact+" da xoa",Toast.LENGTH_LONG).show();
                                }
                            }
                );
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //không thực hiện xóa item đã chọn
                        dialogInterface.dismiss();//ẩn dialog này đi
                    }
                });
                //hiện dialog lên
                builder.show();
                return true;
            }

        });
    }
    final String [] contacts={"an 02929823","minh 0239203823","long 23209392","hoa 02349209329"};
    private  void getViews(){
        lvContacts=(ListView) findViewById(R.id.lvContact);
    }
}