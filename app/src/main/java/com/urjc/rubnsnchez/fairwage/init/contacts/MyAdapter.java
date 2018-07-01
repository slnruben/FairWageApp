package com.urjc.rubnsnchez.fairwage.init.contacts;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.urjc.rubnsnchez.fairwage.R;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    private ArrayList<Contact> fullNameArray;
    private Context context;

    MyAdapter(Context context, ArrayList<Contact> fullNameArray) {
        this.fullNameArray = fullNameArray;
        this.context = context;
    }

    public int getCount() {
        return fullNameArray.size();
    }

    public Object getItem(int arg0) {
        return fullNameArray.get(arg0);
    }

    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(int pos, View v, ViewGroup parent) {
        LayoutInflater inflate = ((Activity) context).getLayoutInflater();
        @SuppressLint({"ViewHolder", "InflateParams"}) View view = inflate.inflate(R.layout.contact_listview_row, null);
        Contact fullName = fullNameArray.get(pos);
        TextView fullNameText = view.findViewById(R.id.fullName);
        fullNameText.setText(fullName.getWage());
        return view;
    }
}
