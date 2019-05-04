package com.example.weeklyplanner;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class ShoppingList extends AppCompatActivity {
    ListView shop_list;
    ArrayList<String> list_items;
    ArrayList<String> list_items_days;
    ImageButton delete_item;
    File shopping_list;
    File shopping_list_days;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.list_nav_back:

                    break;
                case R.id.list_nav_add:
                    AlertDialog alertDialog = new AlertDialog.Builder(ShoppingList.this).create();
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
                                    saveArray(list_items,shopping_list);
                                    saveArray(list_items_days,shopping_list_days);
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                    break;
                case R.id.list_nav_done:

                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shopping_list = new File(getApplicationContext().getFilesDir(),"CurrentList.txt");
        shopping_list_days  = new File(getApplicationContext().getFilesDir(),"CurrentListDays.txt");
        list_items = loadArray(shopping_list);
        list_items_days = loadArray(shopping_list_days);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.List_Nav);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        shop_list = findViewById(R.id.shop_list);
        ListItemAdapter itemAdapter = new ListItemAdapter(this, list_items,list_items_days,delete_item,shopping_list,shopping_list_days);
        shop_list.setAdapter(itemAdapter);

    }
    public static void saveArray(ArrayList<String> arrayList, File file){
        try {
            FileWriter fileWriter = new FileWriter(file);
            Writer output = new BufferedWriter(fileWriter);
            int size = arrayList.size();
            for(int i = 0; i < size ; i++){
                output.write(arrayList.get(i) +"\n");
            }
            output.close();
        }catch (Exception e){

        }
    }
    public void onResume(){
        super.onResume();
        ListItemAdapter itemAdapter = new ListItemAdapter(this,list_items,list_items_days,delete_item,shopping_list,shopping_list_days);
        shop_list.setAdapter(itemAdapter);
    }
    public static ArrayList<String> loadArray(File file){
        ArrayList<String> arrayList = new ArrayList<String>();
        String line;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            if(!bufferedReader.ready()){
                throw new IOException();
            }
            while ((line = bufferedReader.readLine()) != null){
                arrayList.add(line);
            }
            bufferedReader.close();
        }catch (IOException e){
            System.out.println(e);
        }
        return arrayList;
    }

    public static String preferedCase(String original){
        if(original.isEmpty()){
            return original;
        }
        return original.substring(0,1).toUpperCase()+original.substring(1).toLowerCase();
    }

}

