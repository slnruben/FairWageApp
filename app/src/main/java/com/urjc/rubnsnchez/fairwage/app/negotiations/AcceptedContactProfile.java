package com.urjc.rubnsnchez.fairwage.app.negotiations;

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
import com.urjc.rubnsnchez.fairwage.app.contacts.Contact;
import com.urjc.rubnsnchez.fairwage.app.contacts.SearchActivity;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.REFUSED;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.SEARCH_NEGOTIATIONS;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.SUCCESS;
import static com.urjc.rubnsnchez.fairwage.app.common.BBDDUtil.tableRowCount;
import static com.urjc.rubnsnchez.fairwage.app.common.Util.isNetworkAvailable;
import static com.urjc.rubnsnchez.fairwage.app.common.Util.sendDataPOST;
import static com.urjc.rubnsnchez.fairwage.app.common.Util.setTexts;
import static com.urjc.rubnsnchez.fairwage.app.common.Util.stripCode;

public class AcceptedContactProfile extends Activity {
    private Contact contact;

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
    public void onBackPressed() {
        final Context context = getApplicationContext();
        final String urlServer = ((MyApplication) context).getUrlServer();
        if (isNetworkAvailable((ConnectivityManager) Objects.requireNonNull(getSystemService(Context.CONNECTIVITY_SERVICE)))) {
            Thread tr = new Thread() {
                @Override
                public void run() {
                    String user = ((MyApplication) context).getUser();
                    final HashMap<String, String> postDataParams = new HashMap<>();
                    postDataParams.put(context.getString(R.string.user), user);
                    postDataParams.put(context.getString(R.string.state), REFUSED);
                    String success = sendDataPOST(context, urlServer, SEARCH_NEGOTIATIONS, postDataParams);
                    if (String.valueOf(SUCCESS).equals(success)) {
                        final int numRows = tableRowCount(context, BBDDHelper.DataSearchNegotation.TABLE_NAME);
                        if (numRows > 0) {
                            Intent sendIntent = new Intent(AcceptedContactProfile.this, NegotationListActivity.class);
                            startActivity(sendIntent);
                        } else {
                            runOnUiThread(new Thread(new Runnable() {
                                public void run() {
                                    Toast.makeText(context, getString(R.string.noNegotiations), Toast.LENGTH_LONG).show();
                                }
                            }));
                            Intent sendIntent = new Intent(AcceptedContactProfile.this, SearchActivity.class);
                            startActivity(sendIntent);
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
            Toast.makeText(context, getString(R.string.internetRequired), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String name, surnames, email, telephone, job, company, wage, university;
        String career, sector1, sector2, experience, langauges, knowledges;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accepted_contact_profile);
        final Context context = getApplicationContext();
        PrivateKey privateKey = ((MyApplication) context).getPrivateKey();
        Intent intent = getIntent();
        Bundle info = intent.getExtras();
        if (info != null) {
            Negotiation negotiation = new Negotiation(info);
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
        setTextViews(context);
        Button exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new AcceptedContactProfile.sendButtonListener());
    }

    /**
     * Comprueba si el usuario introducido en los campos concuerda con los datos de la BBDD del servidor,
     * si es correcto permite el acceso a la aplicacion y guarda el usuario para futuras sesiones.
     */
    private class sendButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            onBackPressed();
        }
    }
}
