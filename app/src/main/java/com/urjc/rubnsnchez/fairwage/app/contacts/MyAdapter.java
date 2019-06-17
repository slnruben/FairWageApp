package com.urjc.rubnsnchez.fairwage.app.contacts;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.urjc.rubnsnchez.fairwage.R;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    private ArrayList<Contact> contactArray;
    private Context context;

    MyAdapter(Context context, ArrayList<Contact> contactArray) {
        this.contactArray = contactArray;
        this.context = context;
    }

    public int getCount() {
        return contactArray.size();
    }

    public Object getItem(int arg0) {
        return contactArray.get(arg0);
    }

    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(int pos, View v, ViewGroup parent) {
        LayoutInflater inflate = ((Activity) context).getLayoutInflater();
        @SuppressLint({"ViewHolder", "InflateParams"}) View view = inflate.inflate(R.layout.contact_listview_row, null);
        Contact contact = contactArray.get(pos);
        LinearLayout layoutJob = view.findViewById(R.id.layoutJob);
        LinearLayout layoutSector1 = view.findViewById(R.id.layoutSector1);
        LinearLayout layoutSector2 = view.findViewById(R.id.layoutSector2);
        TextView jobText = view.findViewById(R.id.job);
        TextView wageText = view.findViewById(R.id.wage);
        TextView sector1 = view.findViewById(R.id.sector1);
        TextView sector2 = view.findViewById(R.id.sector2);
        if (contact.getJob() != null) {
            jobText.setText(contact.getJob());
        } else {
            layoutJob.setVisibility(View.INVISIBLE);
        }
        wageText.setText(contact.getWage());

        if (contact.getSector1() != null) {
            sector1.setText(contact.getSector1());
        } else {
            layoutSector1.setVisibility(View.GONE);
        }

        if (contact.getSector2() != null) {
            sector2.setText(contact.getSector2());
        } else {
            layoutSector2.setVisibility(View.INVISIBLE);
        }
        return view;
    }
}
