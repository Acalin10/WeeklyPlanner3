package com.example.weeklyplanner;

import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView shop_list;
    ArrayList<String> list_items = new ArrayList<String>();;
    ArrayList<String> list_items_days = new ArrayList<String>();;
    ImageButton delete_item;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.list_nav_back:
                    StringBuilder stringBuilder = new StringBuilder();
                    for(String s : list_items){
                        stringBuilder.append(s);
                        stringBuilder.append(",");
                    }
                    SharedPreferences settings = getSharedPreferences("PREFS",0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("words", stringBuilder.toString());
                    editor.commit();
                    break;
                case R.id.list_nav_add:
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("Add Item");
                    alertDialog.setMessage("Write the item below and click ok");
                    final EditText input;
                    input = new EditText(getApplicationContext());
                    alertDialog.setView(input);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    list_items.add(preferedCase(input.getText().toString()));
                                    list_items_days.add("Extra");
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                    break;
                case R.id.list_nav_done:
                    stringBuilder = new StringBuilder();
                    for(String s : list_items){
                        stringBuilder.append(s);
                        stringBuilder.append(",");
                    }
                    settings = getSharedPreferences("PREFS",0);
                    editor = settings.edit();
                    editor.putString("words", stringBuilder.toString());
                    editor.commit();
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.List_Nav);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Resources res = getResources();
        shop_list = findViewById(R.id.shop_list);

        SharedPreferences settings = getSharedPreferences("PREFS",0);
        String list_itemsString = settings.getString("words","");
        String[] list_itemWords = list_itemsString.split(",");
        ArrayList<String> items = new ArrayList<>();
        for (int i=0; i<list_itemWords.length; i++){
            items.add(list_itemWords[i]);
        }
        ItemAdapter itemAdapter = new ItemAdapter(this, list_items,list_items_days,delete_item);
        shop_list.setAdapter(itemAdapter);
  //      shop_list.setAdapter(new ArrayAdapter<String>(this, R.layout.shop_list_view,list_items));
    }
    public static String preferedCase(String original){
        if(original.isEmpty()){
            return original;
        }
        return original.substring(0,1).toUpperCase()+original.substring(1).toLowerCase();
    }

}
