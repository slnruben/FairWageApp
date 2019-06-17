package com.urjc.rubnsnchez.fairwage.app.negotiations;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.urjc.rubnsnchez.fairwage.R;
import java.util.ArrayList;

import static com.urjc.rubnsnchez.fairwage.app.MyApplication.ACCEPTED;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.RECEIVED;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.REFUSED;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.SENDED;
import static com.urjc.rubnsnchez.fairwage.app.common.Util.checkPrivacy;

public class MyNegotationAdapter extends BaseAdapter {
    private ArrayList<Negotiation> negotiationtArray;
    private Context context;
    private String user;

    MyNegotationAdapter(Context context, ArrayList<Negotiation> negotiationtArray, String user) {
        this.negotiationtArray = negotiationtArray;
        this.context = context;
        this.user = user;
    }

    public int getCount() {
        return negotiationtArray.size();
    }

    public Object getItem(int arg0) {
        return negotiationtArray.get(arg0);
    }

    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(int pos, View v, ViewGroup parent) {
        String job, sector1, sector2;

        LayoutInflater inflate = ((Activity) context).getLayoutInflater();
        @SuppressLint({"ViewHolder", "InflateParams"}) View view = inflate.inflate(R.layout.negotation_listview_row, null);
        Negotiation negotiation = negotiationtArray.get(pos);
        LinearLayout layoutJob = view.findViewById(R.id.layoutJob);
        LinearLayout layoutSector1 = view.findViewById(R.id.layoutSector1);
        LinearLayout layoutSector2 = view.findViewById(R.id.layoutSector2);
        TextView jobText = view.findViewById(R.id.job);
        TextView wageText = view.findViewById(R.id.wage);
        TextView sector1Text = view.findViewById(R.id.sector1);
        TextView sector2Text = view.findViewById(R.id.sector2);
        TextView state = view.findViewById(R.id.state);
        if (user.equals(negotiation.getUserCreator())) {
            job = checkPrivacy(negotiation.getrJob());
            sector1 = checkPrivacy(negotiation.getrSector1());
            sector2 = checkPrivacy(negotiation.getrSector2());
            if (!job.isEmpty()) {
                jobText.setText(job);
            } else {
                layoutJob.setVisibility(View.INVISIBLE);
            }
            wageText.setText(checkPrivacy(negotiation.getrWage()));

            if (!sector1.isEmpty()) {
                sector1Text.setText(sector1);
            } else {
                layoutSector1.setVisibility(View.GONE);
            }

            if (!sector2.isEmpty()) {
                sector2Text.setText(sector2);
            } else {
                layoutSector2.setVisibility(View.INVISIBLE);
            }
            if (SENDED.equals(negotiation.getState())) {
                state.setText(SENDED);
                state.setTextColor(ContextCompat.getColor(context, R.color.blue));
            } else if (REFUSED.equals(negotiation.getState())) {
                state.setText(REFUSED);
                state.setTextColor(ContextCompat.getColor(context, R.color.red));
            }
        } else {
            job = checkPrivacy(negotiation.getoJob());
            sector1 = checkPrivacy(negotiation.getoSector1());
            sector2 = checkPrivacy(negotiation.getoSector2());
            if (!job.isEmpty()) {
                jobText.setText(job);
            } else {
                layoutJob.setVisibility(View.INVISIBLE);
            }
            wageText.setText(checkPrivacy(negotiation.getoWage()));

            if (!sector1.isEmpty()) {
                sector1Text.setText(sector1);
            } else {
                layoutSector1.setVisibility(View.GONE);
            }

            if (!sector2.isEmpty()) {
                sector2Text.setText(sector2);
            } else {
                layoutSector2.setVisibility(View.INVISIBLE);
            }
            if (SENDED.equals(negotiation.getState())) {
                state.setText(RECEIVED);
                state.setTextColor(ContextCompat.getColor(context, R.color.yellow));
            }
        }
        if (ACCEPTED.equals(negotiation.getState())) {
            state.setText(ACCEPTED);
            state.setTextColor(ContextCompat.getColor(context, R.color.green));
        }
        return view;
    }
}
