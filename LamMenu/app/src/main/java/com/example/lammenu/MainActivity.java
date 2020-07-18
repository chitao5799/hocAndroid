package com.example.lammenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //tạo ra một dấu 3 chấm góc trên bên phải của ứng dụng, khi bấm vào sẽ show ra các
        //item menu khai báo trong file memu/menu_demo.xml
        getMenuInflater().inflate(R.menu.menu_demo,menu);
        return super.onCreateOptionsMenu(menu);
    }
    boolean retrunManimenu=false;
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_search:
                Toast.makeText(MainActivity.this,"bạn đã click search",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_setting:
                Toast.makeText(MainActivity.this,"bạn đã click setting",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_share:
                Toast.makeText(MainActivity.this,"bạn đã click share",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuEmail:
                Toast.makeText(MainActivity.this,"bạn đã click email",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuExit:
                Toast.makeText(MainActivity.this,"bạn đã click exit",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuSDT:
                Toast.makeText(MainActivity.this,"bạn đã click sdt",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuback:
                retrunManimenu=true;
                break;
        }
        return super.onOptionsItemSelected(item);
    }
 /*   @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {
        // Clear the previous layout
        menu.clear();

        if(retrunManimenu)
        {
            // Add main menu items..
            menu.add(0, R.id.menu_search, 0, "search");
            menu.add(1, R.id.menu_setting, 1, "setting");
            menu.add(2, R.id.menu_share, 2, "share");
            menu.add(3, R.id.menuContacts, 3, "contacts");
            menu.add(4, R.id.menuExit, 4, "exit");
        }
        else
        {
            // Add sub-menu items
            menu.add(0, R.id.menuEmail, 0, "email");
            menu.add(1, R.id.menuSDT, 1, "sđt");
            menu.add(2, R.id.menuback, 2, "back");
        }
        retrunManimenu=false;
        return super.onPrepareOptionsMenu(menu);
    }*/

}