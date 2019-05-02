package com.example.weeklyplanner;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ListView shop_list;
    String[] list_items;
    String[] list_items_days;

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
        list_items = res.getStringArray(R.array.list_items);
        list_items_days = res.getStringArray(R.array.list_items_days);
  //      shop_list.setAdapter(new ArrayAdapter<String>(this, R.layout.shop_list_view,list_items));
    }

}
