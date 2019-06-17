package com.urjc.rubnsnchez.fairwage.app.negotiations;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.urjc.rubnsnchez.fairwage.R;
import com.urjc.rubnsnchez.fairwage.app.MyApplication;
import com.urjc.rubnsnchez.fairwage.app.common.BBDDHelper;
import com.urjc.rubnsnchez.fairwage.app.contacts.Contact;
import com.urjc.rubnsnchez.fairwage.app.contacts.SearchActivity;

import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.ACCEPTED;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.DELETE_NEGOTIATION;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.REFUSED;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.SEARCH_NEGOTIATIONS;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.SUCCESS;
import static com.urjc.rubnsnchez.fairwage.app.common.BBDDUtil.tableRowCount;
import static com.urjc.rubnsnchez.fairwage.app.common.Util.checkPrivacy;
import static com.urjc.rubnsnchez.fairwage.app.common.Util.createFullNegotiationBundle;
import static com.urjc.rubnsnchez.fairwage.app.common.Util.isNetworkAvailable;
import static com.urjc.rubnsnchez.fairwage.app.common.Util.sendDataPOST;
import static com.urjc.rubnsnchez.fairwage.app.common.Util.setTexts;
import static com.urjc.rubnsnchez.fairwage.app.common.Util.stripCode;

public class ProfileReceivedContactNegotiationActivity extends Activity {
    private Negotiation negotiation;
    private Contact contact;
    private String user;

    private void setTextViews(Context context) {
        LinearLayout layoutName = findViewById(R.id.layoutName);
        LinearLayout layoutSurnames = findViewById(R.id.layoutSurnames);
        LinearLayout layoutEmail = findViewById(R.id.layoutEmail);
        LinearLayout layoutTelephone = findViewById(R.id.layoutTelephone);
        LinearLayout layoutJob = findViewById(R.id.layoutJob);
        LinearLayout layoutCompany = findViewById(R.id.layoutCompany);
        LinearLayout layoutWage = findViewById(R.id.layoutWage);
        LinearLayout layoutUniversity = findViewById(R.id.layoutUniversity);
        LinearLayout layoutCareer = findViewById(R.id.layoutCareer);
        LinearLayout layoutSector1 = findViewById(R.id.layoutSector1);
        LinearLayout layoutSector2 = findViewById(R.id.layoutSector2);
        LinearLayout layoutExperience = findViewById(R.id.layoutExperience);
        LinearLayout layoutLanguages = findViewById(R.id.layoutLanguages);
        LinearLayout layoutKnowledges = findViewById(R.id.layoutKnowledges);
        Map<String, LinearLayout> layouts = new HashMap<>();
        layouts.put(context.getString(R.string.name), layoutName);
        layouts.put(context.getString(R.string.surnames), layoutSurnames);
        layouts.put(context.getString(R.string.email), layoutEmail);
        layouts.put(context.getString(R.string.telephone), layoutTelephone);
        layouts.put(context.getString(R.string.job), layoutJob);
        layouts.put(context.getString(R.string.company), layoutCompany);
        layouts.put(context.getString(R.string.wage), layoutWage);
        layouts.put(context.getString(R.string.university), layoutUniversity);
        layouts.put(context.getString(R.string.career), layoutCareer);
        layouts.put(context.getString(R.string.sector1), layoutSector1);
        layouts.put(context.getString(R.string.sector2), layoutSector2);
        layouts.put(context.getString(R.string.experience), layoutExperience);
        layouts.put(context.getString(R.string.languages), layoutLanguages);
        layouts.put(context.getString(R.string.knowledges), layoutKnowledges);

        TextView name = findViewById(R.id.name);
        TextView surnames = findViewById(R.id.surnames);
        TextView email = findViewById(R.id.email);
        TextView telephone = findViewById(R.id.telephone);
        TextView job = findViewById(R.id.job);
        TextView company = findViewById(R.id.company);
        TextView wage = findViewById(R.id.wage);
        TextView university = findViewById(R.id.university);
        TextView career = findViewById(R.id.career);
        TextView sector1 = findViewById(R.id.sector1);
        TextView sector2 = findViewById(R.id.sector2);
        TextView experience = findViewById(R.id.experience);
        TextView languages = findViewById(R.id.languages);
        TextView knowledges = findViewById(R.id.knowledges);
        Map<String, TextView> views = new HashMap<>();
        views.put(context.getString(R.string.name), name);
        views.put(context.getString(R.string.surnames), surnames);
        views.put(context.getString(R.string.email), email);
        views.put(context.getString(R.string.telephone), telephone);
        views.put(context.getString(R.string.job), job);
        views.put(context.getString(R.string.company), company);
        views.put(context.getString(R.string.wage), wage);
        views.put(context.getString(R.string.university), university);
        views.put(context.getString(R.string.career), career);
        views.put(context.getString(R.string.sector1), sector1);
        views.put(context.getString(R.string.sector2), sector2);
        views.put(context.getString(R.string.experience), experience);
        views.put(context.getString(R.string.languages), languages);
        views.put(context.getString(R.string.knowledges), knowledges);
        setTexts(layouts, views, context, contact);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String name, surnames, email, telephone, job, company, wage, university;
        String career, sector1, sector2, experience, langauges, knowledges;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_received_contact_negotiation);
        final Context context = getApplicationContext();
        user = ((MyApplication) context).getUser();
        PrivateKey privateKey = ((MyApplication) context).getPrivateKey();
        Intent intent = getIntent();
        Bundle info = intent.getExtras();
        if (info != null) {
            negotiation = new Negotiation(info);
            if (ACCEPTED.equals(negotiation.getState())) {
                if (user.equals(negotiation.getUserCreator())) {
                    name = stripCode(privateKey, negotiation.getrName());
                    surnames = stripCode(privateKey, negotiation.getrSurnames());
                    email = stripCode(privateKey, negotiation.getrEmail());
                    telephone = stripCode(privateKey, negotiation.getrTelephone());
                    job = stripCode(privateKey, negotiation.getrJob());
                    company = stripCode(privateKey, negotiation.getrCompany());
                    wage = stripCode(privateKey, negotiation.getrWage());
                    university = stripCode(privateKey, negotiation.getrUniversity());
                    career = stripCode(privateKey, negotiation.getrCareer());
                    sector1 = stripCode(privateKey, negotiation.getrSector1());
                    sector2 = stripCode(privateKey, negotiation.getrSector2());
                    experience = stripCode(privateKey, negotiation.getrExperience());
                    langauges = stripCode(privateKey, negotiation.getrLanguages());
                    knowledges = stripCode(privateKey, negotiation.getrKnowledges());
                    contact = new Contact(name, surnames, email, telephone, job, company, wage, university, career,
                            sector1, sector2, experience, langauges, knowledges);
                } else {
                    name = stripCode(privateKey, negotiation.getoName());
                    surnames = stripCode(privateKey, negotiation.getoSurnames());
                    email = stripCode(privateKey, negotiation.getoEmail());
                    telephone = stripCode(privateKey, negotiation.getoTelephone());
                    job = stripCode(privateKey, negotiation.getoJob());
                    company = stripCode(privateKey, negotiation.getoCompany());
                    wage = stripCode(privateKey, negotiation.getoWage());
                    university = stripCode(privateKey, negotiation.getoUniversity());
                    career = stripCode(privateKey, negotiation.getoCareer());
                    sector1 = stripCode(privateKey, negotiation.getoSector1());
                    sector2 = stripCode(privateKey, negotiation.getoSector2());
                    experience = stripCode(privateKey, negotiation.getoExperience());
                    langauges = stripCode(privateKey, negotiation.getoLanguages());
                    knowledges = stripCode(privateKey, negotiation.getoKnowledges());
                    contact = new Contact(name, surnames, email, telephone, job, company, wage, university, career,
                            sector1, sector2, experience, langauges, knowledges);
                }
            } else if (user.equals(negotiation.getUserCreator())) {
                name = checkPrivacy(negotiation.getrName());
                surnames = checkPrivacy(negotiation.getrSurnames());
                email = checkPrivacy(negotiation.getrEmail());
                telephone = checkPrivacy(negotiation.getrTelephone());
                job = checkPrivacy(negotiation.getrJob());
                company = checkPrivacy(negotiation.getrCompany());
                wage = checkPrivacy(negotiation.getrWage());
                university = checkPrivacy(negotiation.getrUniversity());
                career = checkPrivacy(negotiation.getrCareer());
                sector1 = checkPrivacy(negotiation.getrSector1());
                sector2 = checkPrivacy(negotiation.getrSector2());
                experience = checkPrivacy(negotiation.getrExperience());
                langauges = checkPrivacy(negotiation.getrLanguages());
                knowledges = checkPrivacy(negotiation.getrKnowledges());
                contact = new Contact(name, surnames, email, telephone, job, company, wage, university, career,
                        sector1, sector2, experience, langauges, knowledges);
            } else {
                name = checkPrivacy(negotiation.getoName());
                surnames = checkPrivacy(negotiation.getoSurnames());
                email = checkPrivacy(negotiation.getoEmail());
                telephone = checkPrivacy(negotiation.getoTelephone());
                job = checkPrivacy(negotiation.getoJob());
                company = checkPrivacy(negotiation.getoCompany());
                wage = checkPrivacy(negotiation.getoWage());
                university = checkPrivacy(negotiation.getoUniversity());
                career = checkPrivacy(negotiation.getoCareer());
                sector1 = checkPrivacy(negotiation.getoSector1());
                sector2 = checkPrivacy(negotiation.getoSector2());
                experience = checkPrivacy(negotiation.getoExperience());
                langauges = checkPrivacy(negotiation.getoLanguages());
                knowledges = checkPrivacy(negotiation.getoKnowledges());
                contact = new Contact(name, surnames, email, telephone, job, company, wage, university, career,
                        sector1, sector2, experience, langauges, knowledges);
            }
            setTextViews(context);
        }
        Button viewButton = findViewById(R.id.sendButton);
        Button deleteButton = findViewById(R.id.deleteButton);
        Button cancelButton = findViewById(R.id.cancelButton);
        Button spaceStart = findViewById(R.id.spaceStart);
        if (REFUSED.equals(negotiation.getState())) {
            viewButton.setVisibility(View.GONE);
            deleteButton.setVisibility(View.VISIBLE);
        } else if (ACCEPTED.equals(negotiation.getState())) {
            viewButton.setVisibility(View.GONE);
            deleteButton.setVisibility(View.GONE);
            spaceStart.setVisibility(View.INVISIBLE);
        }
        viewButton.setOnClickListener(new ProfileReceivedContactNegotiationActivity.viewButtonListener());
        deleteButton.setOnClickListener(new ProfileReceivedContactNegotiationActivity.deleteButtonListener());
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private class viewButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ProfileReceivedContactNegotiationActivity.this, ReceivedNegotiationActivity.class);
            Bundle bundle = createFullNegotiationBundle(negotiation);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    private class deleteButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            final Context context = getApplicationContext();
            final String urlServer = ((MyApplication) context).getUrlServer();
            final HashMap<String, String> postDataParams = new HashMap<>();
            postDataParams.put(context.getString(R.string.id), String.valueOf(negotiation.getIdServer()));
            if (isNetworkAvailable((ConnectivityManager) Objects.requireNonNull(getSystemService(Context.CONNECTIVITY_SERVICE)))) {
                Thread tr = new Thread() {
                    @Override
                    public void run() {
                        String success = sendDataPOST(context, urlServer, DELETE_NEGOTIATION, postDataParams);
                        if (String.valueOf(SUCCESS).equals(success)) {
                            final HashMap<String, String> postDataParams = new HashMap<>();
                            postDataParams.put(context.getString(R.string.user), user);
                            postDataParams.put(context.getString(R.string.state), REFUSED);
                            success = sendDataPOST(context, urlServer, SEARCH_NEGOTIATIONS, postDataParams);
                            if (String.valueOf(SUCCESS).equals(success)) {
                                final int numRows = tableRowCount(context, BBDDHelper.DataSearchNegotation.TABLE_NAME);
                                if (numRows > 0) {
                                    Intent sendIntent = new Intent(ProfileReceivedContactNegotiationActivity.this, NegotationListActivity.class);
                                    startActivity(sendIntent);
                                } else {
                                    runOnUiThread(new Thread(new Runnable() {
                                        public void run() {
                                            Toast.makeText(context, getString(R.string.noNegotiations), Toast.LENGTH_LONG).show();
                                        }
                                    }));
                                    Intent sendIntent = new Intent(ProfileReceivedContactNegotiationActivity.this, SearchActivity.class);
                                    startActivity(sendIntent);
                                }
                            } else {
                                runOnUiThread(new Thread(new Runnable() {
                                    public void run() {
                                        Toast.makeText(context, getString(R.string.searchNegotationsError), Toast.LENGTH_LONG).show();
                                    }
                                }));
                            }
                        } else {
                            runOnUiThread(new Thread(new Runnable() {
                                public void run() {
                                    Toast.makeText(context, getString(R.string.deleteNegotiationError), Toast.LENGTH_LONG).show();
                                }
                            }));
                        }
                    }
                };
                tr.start();
                Toast.makeText(context, getString(R.string.negotiationDeleted), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, getString(R.string.internetRequired), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
