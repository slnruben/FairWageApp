package com.urjc.rubnsnchez.fairwage.init.contacts;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.urjc.rubnsnchez.fairwage.R;
import com.urjc.rubnsnchez.fairwage.init.MyApplication;
import com.urjc.rubnsnchez.fairwage.init.common.BBDDHelper;

import java.util.HashMap;
import java.util.Map;

import static com.urjc.rubnsnchez.fairwage.init.common.BBDDUtil.createUpdatedUserBBDDValues;
import static com.urjc.rubnsnchez.fairwage.init.common.BBDDUtil.getUserData;
import static com.urjc.rubnsnchez.fairwage.init.common.Util.setEditTexts;
import static com.urjc.rubnsnchez.fairwage.init.common.Util.setVisibilityButtons;

public class EditActivity extends Activity {
    Button nameVisibility, surnamesVisibility, emailVisibility, telephoneVisibility, jobVisibility, companyVisibility;
    Button universityVisibility, careerVisibility, sector1Visibility, sector2Visibility, experienceVisibility;
    Button languagesVisibility, knowledgesVisibility;
    Button saveButton, cancelButton;
    Button[] visibilityButtons;
    Contact contact;

    private void setVisibilityButtonViews(Context context) {
        nameVisibility = findViewById(R.id.nameVisibility);
        surnamesVisibility = findViewById(R.id.surnamesVisibility);
        emailVisibility = findViewById(R.id.emailVisibility);
        telephoneVisibility = findViewById(R.id.telephoneVisibility);
        jobVisibility = findViewById(R.id.jobVisibility);
        companyVisibility = findViewById(R.id.companyVisibility);
        universityVisibility = findViewById(R.id.universityVisibility);
        careerVisibility = findViewById(R.id.careerVisibility);
        sector1Visibility = findViewById(R.id.sector1Visibility);
        sector2Visibility = findViewById(R.id.sector2Visibility);
        experienceVisibility = findViewById(R.id.experienceVisibility);
        languagesVisibility = findViewById(R.id.languagesVisibility);
        knowledgesVisibility = findViewById(R.id.knowledgesVisibility);
        visibilityButtons = new Button[]{nameVisibility, surnamesVisibility, emailVisibility, telephoneVisibility,
                                        jobVisibility, companyVisibility, universityVisibility, careerVisibility,
                                        sector1Visibility, sector2Visibility, experienceVisibility, languagesVisibility,
                                        knowledgesVisibility};
        setVisibilityButtons(visibilityButtons, contact, context);
    }

    /**
     * Inserta los datos de un contacto en los EditText.
     */
    private void setEditTextViews(Context context) {
        EditText name = findViewById(R.id.nameInput);
        EditText surnames = findViewById(R.id.surnamesInput);
        EditText email = findViewById(R.id.emailInput);
        EditText telephone = findViewById(R.id.telephoneInput);
        EditText job = findViewById(R.id.jobInput);
        EditText company = findViewById(R.id.companyInput);
        EditText wage = findViewById(R.id.wageInput);
        EditText university = findViewById(R.id.universityInput);
        EditText career = findViewById(R.id.careerInput);
        EditText sector1 = findViewById(R.id.sector1Input);
        EditText sector2 = findViewById(R.id.sector2Input);
        EditText experience = findViewById(R.id.experienceInput);
        EditText languages = findViewById(R.id.languagesInput);
        EditText knowledges = findViewById(R.id.knowledgesInput);
        Map<String, EditText> views = new HashMap<>();
        views.put(context.getString(R.string.name),name);
        views.put(context.getString(R.string.surnames),surnames);
        views.put(context.getString(R.string.email),email);
        views.put(context.getString(R.string.telephone),telephone);
        views.put(context.getString(R.string.job),job);
        views.put(context.getString(R.string.company),company);
        views.put(context.getString(R.string.wage),wage);
        views.put(context.getString(R.string.university),university);
        views.put(context.getString(R.string.career),career);
        views.put(context.getString(R.string.sector1),sector1);
        views.put(context.getString(R.string.sector2),sector2);
        views.put(context.getString(R.string.experience),experience);
        views.put(context.getString(R.string.languages),languages);
        views.put(context.getString(R.string.knowledges),knowledges);
        setEditTexts(views, context, contact);
    }

    /**
     * Primer metodo que se ejecuta, obtiene los diferentes elementos xml de la activity
     * y activa los listener de los botones.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);
        final Context context = getApplicationContext();
        String user = ((MyApplication) context).getUser();
        contact = getUserData(context, user);
        setEditTextViews(context);
        setVisibilityButtonViews(context);
        saveButton= findViewById(R.id.saveButton);
        cancelButton= findViewById(R.id.cancelButton);
        saveButton.setOnClickListener(new saveButtonListener());
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        nameVisibility.setOnClickListener(new visibilityButtonListener());
        surnamesVisibility.setOnClickListener(new visibilityButtonListener());
        emailVisibility.setOnClickListener(new visibilityButtonListener());
        telephoneVisibility.setOnClickListener(new visibilityButtonListener());
        jobVisibility.setOnClickListener(new visibilityButtonListener());
        companyVisibility.setOnClickListener(new visibilityButtonListener());
        universityVisibility.setOnClickListener(new visibilityButtonListener());
        careerVisibility.setOnClickListener(new visibilityButtonListener());
        sector1Visibility.setOnClickListener(new visibilityButtonListener());
        sector2Visibility.setOnClickListener(new visibilityButtonListener());
        experienceVisibility.setOnClickListener(new visibilityButtonListener());
        languagesVisibility.setOnClickListener(new visibilityButtonListener());
        knowledgesVisibility.setOnClickListener(new visibilityButtonListener());
    }

    private class visibilityButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getBackground().getConstantState() == getResources().getDrawable(R.drawable.ic_visibility_off).getConstantState()) {
                view.setBackground(getResources().getDrawable(R.drawable.ic_visibility));
            } else {
                view.setBackground(getResources().getDrawable(R.drawable.ic_visibility_off));
            }

        }
    }

    /**
     * Actualiza el contacto con los datos de los EditText, realiza un UPDATE
     * en la BBDD y cambia a interfaz de visualizacion de contacto.
     */
    private class saveButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            final Context context = getApplicationContext();
            final BBDDHelper bbddHelper = ((MyApplication) context).getBbddHelper();
            EditText name = findViewById(R.id.nameInput);
            EditText surnames = findViewById(R.id.surnamesInput);
            EditText email = findViewById(R.id.emailInput);
            EditText telephone = findViewById(R.id.telephoneInput);
            EditText job = findViewById(R.id.jobInput);
            EditText company = findViewById(R.id.companyInput);
            EditText wage = findViewById(R.id.wageInput);
            EditText university = findViewById(R.id.universityInput);
            EditText career = findViewById(R.id.careerInput);
            EditText sector1 = findViewById(R.id.sector1Input);
            EditText sector2 = findViewById(R.id.sector2Input);
            EditText experience = findViewById(R.id.experienceInput);
            EditText languages = findViewById(R.id.languagesInput);
            EditText knowledges = findViewById(R.id.knowledgesInput);
            final Contact updatedContact = new Contact(new TextView[]{name, surnames, email, telephone, job, company, wage,
                                        university, career, sector1, sector2, experience, languages, knowledges},
                                        visibilityButtons, context);
            updatedContact.setId(contact.getId());
            final SQLiteDatabase db = bbddHelper.getWritableDatabase();
            ContentValues values = createUpdatedUserBBDDValues(updatedContact);
            db.update(BBDDHelper.DataUser.TABLE_NAME, values, BBDDHelper.DataUser.COLUMN_USER_ID_0 + "="+  updatedContact.getId(), null);
            Intent sendIntent = new Intent(EditActivity.this, SearchActivity.class);
            startActivity(sendIntent);
            finish();
        }
    }

}
