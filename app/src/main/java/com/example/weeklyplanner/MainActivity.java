package com.example.weeklyplanner;

import android.content.ClipData;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView shop_list;
    ArrayList<String> list_items;
    ArrayList<String> list_items_days;
    ImageButton delete_item;
    int fred;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener;

    {
        mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.list_nav_back:
                        break;
                    case R.id.list_nav_add:
                        break;
                    case R.id.list_nav_done:
                        break;
                }
                return false;
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.List_Nav);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Resources res = getResources();
        shop_list = findViewById(R.id.shop_list);

            list_items = new ArrayList<String>();
            list_items_days = new ArrayList<String>();

        ItemAdapter itemAdapter = new ItemAdapter(this, list_items,list_items_days,delete_item);
        shop_list.setAdapter(itemAdapter);
  //      shop_list.setAdapter(new ArrayAdapter<String>(this, R.layout.shop_list_view,list_items));
    }


}
