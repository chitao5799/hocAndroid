package com.example.docghifile;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvId,tvName,tvPrice,tvPage,tvDes;
    Button btnPrev,btnNext;
    //cac bien lien quan csdl
    SQLiteDatabase db;
    Cursor cs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getViews();
        //tao ra 1 script
        String sql="DROP TABLE IF EXISTS BOOKS;\n"+
                "CREATE TABLE BOOKS(BooKID integer PRIMARY KEY,"+
                " BookName text, Page integer, Price Float, "+
                "Description text);\n"
                +"Insert into BOOKS values (1,'abc',100,40,'dfg');\n"
                +"Insert into BOOKS values (2,'dksl',200,40,'dksfsifg');\n"
                +"Insert into BOOKS values (3,'ksdfoiwe',160,60,'iwer weowir');\n"
                +"Insert into BOOKS values (4,'oiweri',310,90,'dksdffg');\n"
                +"Insert into BOOKS values (5,'poewer',500,10,'polsdif');\n";
        //tao/mo csdl
        db=openOrCreateDatabase("books.db",MODE_PRIVATE,null);
        //thuc thi script sql vua tao
        for(String str: sql.split("\n"))
        {
            db.execSQL(str);
        }
        //thuc hien doc du lieu tu csdl vao con tro cs
        cs=db.rawQuery("Select * from BOOKS",null);
        cs.moveToNext();
        getData();
    }

    private void getViews()
    {
        tvId=(TextView)findViewById(R.id.txtID);
        tvName=(TextView)findViewById(R.id.txtName);
        tvPage=(TextView)findViewById(R.id.txtPages);
        tvPrice=(TextView)findViewById(R.id.txtPrice);
        tvDes=(TextView)findViewById(R.id.txtDes);
        btnNext=(Button)findViewById(R.id.btnNext);
        btnPrev=(Button)findViewById(R.id.btnPrev);
        //dang ky su kien cho cac nut lenh
        btnPrev.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }
    private  void getData()
    {
        //hien thi thong tin len cac textview
       tvId.setText(cs.getString(0));
        tvName.setText(cs.getString(1));
        tvPage.setText(cs.getString(2));
        tvPrice.setText(cs.getString(3));
        tvDes.setText(cs.getString(4));
        btnPrev.setEnabled(!cs.isFirst());//neu la ban ghi dau tien thi disable nut prev
        btnNext.setEnabled(!cs.isLast());//neu la ban ghi cuoi cung thi disable nut next
    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {

            case R.id.btnPrev:
                cs.moveToPrevious();getData();
                break;
            case R.id.btnNext:
                cs.moveToNext();getData();
                break;
        }
    }
    @Override
    public void onBackPressed(){
        //dong cac ket noi
        cs.close();
        db.close();
        super.onBackPressed();
    }
}