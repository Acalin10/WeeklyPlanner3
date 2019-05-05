package com.example.weeklyplanner;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenu;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.sql.Array;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Objects;

public class PlannerAdapter extends BaseAdapter  {
    ArrayList<String> dates;
    ImageButton addMeal;
    ImageView meals;
    private LayoutInflater mInflater;

    PlannerAdapter(Context context, ArrayList<String> dates, ImageButton addMeal, ImageView meals){
        mInflater =((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        this.dates=dates;
        this.addMeal=addMeal;
        this.meals=meals;
    }

    @Override
    public int getCount() {
        return dates.size();
    }

    @Override
    public Object getItem(int i) {
        return dates.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = mInflater.inflate(R.layout.main_recycle_view,null);
        TextView datesTextView = v.findViewById(R.id.date);
        ImageButton addMealButton = v.findViewById(R.id.main_addMeal);
        ImageView numberOfMeals = v.findViewById(R.id.main_meals);
        String date = dates.get(i);
        datesTextView.setText(date);
        return v;
    }
}
