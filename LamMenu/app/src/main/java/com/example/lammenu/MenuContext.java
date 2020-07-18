package com.example.lammenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MenuContext extends AppCompatActivity {
    Button btnChonMau,btnXuly;
    ConstraintLayout manhinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_context);

        btnChonMau=(Button)findViewById(R.id.btnChonMau);
        btnXuly=(Button)findViewById(R.id.btnChonXuly);
        manhinh=(ConstraintLayout)findViewById(R.id.manHinh);
        //đăng ký view cho context menu
        registerForContextMenu(btnChonMau);
        registerForContextMenu(btnXuly);

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {

        if(v.getId() == R.id.btnChonMau)
        {
            getMenuInflater().inflate(R.menu.menu_context,menu);
            menu.setHeaderTitle("Xin mời chọn màu");
            menu.setHeaderIcon(R.mipmap.ic_launcher);
        }
        else if(v.getId() == R.id.btnChonXuly)
        {
            getMenuInflater().inflate(R.menu.menu_popup,menu);
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menuMauDo:
                manhinh.setBackgroundColor(Color.RED);
                break;
            case R.id.menuMauVang:
                manhinh.setBackgroundColor(Color.YELLOW);
                break;
            case R.id.menuMauHong:
                manhinh.setBackgroundColor(Color.GREEN);
                break;
        }
        return super.onContextItemSelected(item);
    }
}