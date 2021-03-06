package com.urjc.rubnsnchez.fairwage.app.contacts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.urjc.rubnsnchez.fairwage.R;
import com.urjc.rubnsnchez.fairwage.app.MyApplication;
import com.urjc.rubnsnchez.fairwage.app.common.BBDDHelper;
import com.urjc.rubnsnchez.fairwage.app.negotiations.NegotationListActivity;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.PROFILE;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.REFUSED;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.SEARCH_NEGOTIATIONS;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.SUCCESS;
import static com.urjc.rubnsnchez.fairwage.app.common.BBDDUtil.getUserData;
import static com.urjc.rubnsnchez.fairwage.app.common.BBDDUtil.tableRowCount;
import static com.urjc.rubnsnchez.fairwage.app.common.Util.isNetworkAvailable;
import static com.urjc.rubnsnchez.fairwage.app.common.Util.sendDataPOST;
import static com.urjc.rubnsnchez.fairwage.app.common.Util.setTexts;
import static com.urjc.rubnsnchez.fairwage.app.common.Util.setVisibilityButtons;

public class ProfileActivity extends Activity {
    Contact contact;

    private void setVisibilityButtonViews(Context context) {
        Button nameVisibility = findViewById(R.id.nameVisibility);
        Button surnamesVisibility = findViewById(R.id.surnamesVisibility);
        Button emailVisibility = findViewById(R.id.emailVisibility);
        Button telephoneVisibility = findViewById(R.id.telephoneVisibility);
        Button jobVisibility = findViewById(R.id.jobVisibility);
        Button companyVisibility = findViewById(R.id.companyVisibility);
        Button universityVisibility = findViewById(R.id.universityVisibility);
        Button careerVisibility = findViewById(R.id.careerVisibility);
        Button sector1Visibility = findViewById(R.id.sector1Visibility);
        Button sector2Visibility = findViewById(R.id.sector2Visibility);
        Button experienceVisibility = findViewById(R.id.experienceVisibility);
        Button languagesVisibility = findViewById(R.id.languagesVisibility);
        Button knowledgesVisibility = findViewById(R.id.knowledgesVisibility);
        Button[] visibilityButtons = new Button[]{nameVisibility, surnamesVisibility, emailVisibility, telephoneVisibility,
                jobVisibility, companyVisibility, universityVisibility, careerVisibility,
                sector1Visibility, sector2Visibility, experienceVisibility, languagesVisibility,
                knowledgesVisibility};
        setVisibilityButtons(visibilityButtons, contact, context);
    }

    /**
     * Inserta los datos de un contacto en los TextViews.
     */
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

        TextView name = findViewById(R.id.userName);
        TextView surnames = findViewById(R.id.userSurnames);
        TextView email = findViewById(R.id.userEmail);
        TextView telephone = findViewById(R.id.userTelephone);
        TextView job = findViewById(R.id.userJob);
        TextView company = findViewById(R.id.userCompany);
        TextView wage = findViewById(R.id.userWage);
        TextView university = findViewById(R.id.userUniversity);
        TextView career = findViewById(R.id.userCareer);
        TextView sector1 = findViewById(R.id.userSector1);
        TextView sector2 = findViewById(R.id.userSector2);
        TextView experience = findViewById(R.id.userExperience);
        TextView languages = findViewById(R.id.userLanguages);
        TextView knowledges = findViewById(R.id.userKnowledges);
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

    /**
     * Primer metodo que se ejecuta, obtiene los diferentes elementos xml de la activity
     * y activa los listener de los botones.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        final Context context = getApplicationContext();
        String user = ((MyApplication) context).getUser();
        contact = getUserData(context, user, PROFILE);
        setTextViews(context);
        setVisibilityButtonViews(context);
        Button searchButton = findViewById(R.id.searchButton);
        Button editButton = findViewById(R.id.editButton);
        Button negotationButton = findViewById(R.id.negotationButton);
        searchButton.setOnClickListener(new searchButtonListener());
        editButton.setOnClickListener(new editButtonListener());
        negotationButton.setOnClickListener(new negotationButtonListener());
    }

    private class searchButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent sendIntent = new Intent(ProfileActivity.this, SearchActivity.class);
            startActivity(sendIntent);
        }
    }

    private class editButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent sendIntent = new Intent(ProfileActivity.this, EditActivity.class);
            startActivity(sendIntent);
            finish();
        }
    }

    private class negotationButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (isNetworkAvailable((ConnectivityManager) Objects.requireNonNull(getSystemService(Context.CONNECTIVITY_SERVICE)))) {
                final Context context = getApplicationContext();
                String user = ((MyApplication) context).getUser();
                final String urlServer = ((MyApplication) context).getUrlServer();
                final HashMap<String, String> postDataParams = new HashMap<>();
                postDataParams.put(context.getString(R.string.user), user);
                postDataParams.put(context.getString(R.string.state), REFUSED);
                Thread tr = new Thread() {
                    @Override
                    public void run() {
                        Intent sendIntent = new Intent(ProfileActivity.this, NegotationListActivity.class);
                        String success = sendDataPOST(context, urlServer, SEARCH_NEGOTIATIONS, postDataParams);
                        if (String.valueOf(SUCCESS).equals(success)) {
                            final int numRows = tableRowCount(context, BBDDHelper.DataSearchNegotation.TABLE_NAME);
                            if (numRows > 0) {
                                startActivity(sendIntent);
                            } else {
                                runOnUiThread(new Thread(new Runnable() {
                                    public void run() {
                                        Toast.makeText(context, getString(R.string.noNegotiations), Toast.LENGTH_LONG).show();
                                    }
                                }));
                            }
                        } else {
                            runOnUiThread(new Thread(new Runnable() {
                                public void run() {
                                    Toast.makeText(context, getString(R.string.searchNegotationsError), Toast.LENGTH_LONG).show();
                                }
                            }));
                        }
                    }
                };
                tr.start();
            } else {
                Toast.makeText(ProfileActivity.this, getString(R.string.searchInternetRequired), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
