package com.example.lammenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MenuPopup extends AppCompatActivity {
    Button btnMenuPopup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_popup);

        btnMenuPopup=(Button)findViewById(R.id.btnMenuPopup);
        btnMenuPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu();
            }
        });
    }

    private  void showPopupMenu()
    {
        PopupMenu myPopupMenu=new PopupMenu(MenuPopup.this,btnMenuPopup);
        myPopupMenu.getMenuInflater().inflate(R.menu.menu_popup,myPopupMenu.getMenu());
        /*R.menu.menu_popup là file menu_popup.xml trong forder menu.
         * tạo forder menu: chuột phải vào res ->new->Directory đặt tên menu (tên khác là ko được, vd mymenu)
         *chuột phải vào forder menu: new->Menu Resource File để tạo file menu */
        myPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.menuThem:
                        Toast.makeText(MenuPopup.this,"bạn đã click thêm",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menuSua:
                        Toast.makeText(MenuPopup.this,"bạn đã click sửa",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menuXoa:
                        Toast.makeText(MenuPopup.this,"bạn đã click xóa",Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
        myPopupMenu.show();
    }
}