package com.example.orderfood;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvFood;
    FoodAdapter foodAdapter;
    FoodModel foodModel;
    ArrayList<Food> lsFood;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lvFood=(ListView)findViewById(R.id.lvFood);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, InsertFood.class);
                startActivity(intent);
            }
        });
    }
    public void getListData()
    {
        foodModel=new FoodModel(getApplicationContext());
        lsFood=foodModel.getAllFood();
        foodAdapter=new FoodAdapter(getApplicationContext(),R.layout.item_food_layout,lsFood);
        lvFood.setAdapter(foodAdapter);
    }
    @Override
    protected void onPostResume()
    {
        super.onPostResume();
        getListData();
    }
    protected void onPostRestart(){
        super.onRestart();
        getListData();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}