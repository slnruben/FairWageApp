package com.urjc.rubnsnchez.fairwage.app.contacts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.urjc.rubnsnchez.fairwage.R;
import com.urjc.rubnsnchez.fairwage.app.negotiations.NegotiationActivity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.urjc.rubnsnchez.fairwage.app.common.Util.createFullNameBundle;
import static com.urjc.rubnsnchez.fairwage.app.common.Util.setTexts;

public class ProfileContactActivity extends Activity {
    private Contact fullName;

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
        setTexts(layouts, views, context, fullName);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_profile);
        final Context context = getApplicationContext();
        Intent intent = getIntent();
        Bundle info = intent.getExtras();
        if (info != null) {
            fullName = new Contact(info);
            setTextViews(context);
        }
        Button sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new sendButtonListener());
    }

    /**
     * Comprueba si el usuario introducido en los campos concuerda con los datos de la BBDD del servidor,
     * si es correcto permite el acceso a la aplicacion y guarda el usuario para futuras sesiones.
     */
    private class sendButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent sendIntent = new Intent(ProfileContactActivity.this, NegotiationActivity.class);
            Bundle bundle = createFullNameBundle(fullName);
            sendIntent.putExtras(bundle);
            startActivity(sendIntent);
        }
    }
}
