package com.example.weeklyplanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemAdapter extends BaseAdapter {
    LayoutInflater mInflater;
    ArrayList<String> list_items;
    ArrayList<String> list_items_days;
    ImageButton delete_item;
    public ItemAdapter(Context context, ArrayList<String> i, ArrayList<String> j, ImageButton b){
        list_items=i;
        list_items_days=j;
        delete_item=b;
        mInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override

    public int getCount() {
        return list_items.size();
    }

    @Override
    public Object getItem(int i) {
        return list_items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View v = mInflater.inflate(R.layout.shop_list_view,null);
        TextView nameTextView = (TextView) v.findViewById(R.id.item_name);
        TextView dayTextView = (TextView) v.findViewById(R.id.item_day);
        ImageButton deleteButton = (ImageButton) v.findViewById(R.id.item_delete);
        String name = list_items.get(position);
        String day = list_items_days.get(position);
        nameTextView.setText(name);
        dayTextView.setText(day);
        return v;
    }
}
