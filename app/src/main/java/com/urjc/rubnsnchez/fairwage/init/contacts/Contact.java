package com.urjc.rubnsnchez.fairwage.init.contacts;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.urjc.rubnsnchez.fairwage.R;
import static com.urjc.rubnsnchez.fairwage.init.MyApplication.LOGIN;
import static com.urjc.rubnsnchez.fairwage.init.MyApplication.REGISTER;

/**
 * Clase que define un contacto.
 * @author Rubén Sánchez Rivero
 * @version 1
 */
public class Contact {
    private int id;
    private String user;
    private String password;
    private String name;
    private String surnames;
    private String email;
    private String telephone;
    private String job;
    private String company;
    private String wage;
    private String university;
    private String career;
    private String sector1;
    private String sector2;
    private String experience;
    private String languages;
    private String knowledges;
    private int nameVisibility;
    private int surnamesVisibility;
    private int emailVisibility;
    private int telephoneVisibility;
    private int jobVisibility;
    private int companyVisibility;
    private int universityVisibility;
    private int careerVisibility;
    private int sector1Visibility;
    private int sector2Visibility;
    private int experienceVisibility;
    private int languagesVisibility;
    private int knowledgesVisibility;

    public Contact(String user, String wage) {
        this.user = user;
        this.wage = wage;
    }

    /**
     * Contructor.
     * @param c Contiene los datos de un contacto obtenidos de un SELECT en SQLite.
     */
    public Contact(Cursor c) {
        this.id = c.getInt(0);
        this.user = c.getString(1);
        this.password = c.getString(2);
        this.name = c.getString(3);
        this.surnames = c.getString(4);
        this.email = c.getString(5);
        this.telephone = c.getString(6);
        this.job = c.getString(7);
        this.company = c.getString(8);
        if ("".equals(c.getString(9))) {
            this.wage = "0";
        } else {
            this.wage = c.getString(9);
        }
        this.university = c.getString(10);
        this.career = c.getString(11);
        this.sector1 = c.getString(12);
        this.sector2 = c.getString(13);
        this.experience = c.getString(14);
        this.languages = c.getString(15);
        this.knowledges = c.getString(16);
        this.nameVisibility = c.getInt(17);
        this.surnamesVisibility = c.getInt(18);
        this.emailVisibility = c.getInt(19);
        this.telephoneVisibility = c.getInt(20);
        this.jobVisibility = c.getInt(21);
        this.companyVisibility = c.getInt(22);
        this.universityVisibility = c.getInt(23);
        this.careerVisibility = c.getInt(24);
        this.sector1Visibility = c.getInt(25);
        this.sector2Visibility = c.getInt(26);
        this.experienceVisibility = c.getInt(27);
        this.languagesVisibility = c.getInt(28);
        this.knowledgesVisibility = c.getInt(29);
    }

    /**
     * Constructor
     * @param bundle Contiene los datos de un contacto guardados por otra activity en un Bundle
     */
    public Contact(Bundle bundle) {
       // this.id = (int) bundle.get(String.valueOf(R.string.id));
        this.user = (String) bundle.get(String.valueOf(R.string.user));
        this.name = (String) bundle.get(String.valueOf(R.string.name));
        this.surnames = (String) bundle.get(String.valueOf(R.string.surnames));
        this.email = (String) bundle.get(String.valueOf(R.string.email));
        this.telephone = (String) bundle.get(String.valueOf(R.string.telephone));
        this.job = (String) bundle.get(String.valueOf(R.string.job));
        this.company = (String) bundle.get(String.valueOf(R.string.company));
        this.wage = (String) bundle.get(String.valueOf(R.string.wage));
        this.university = (String) bundle.get(String.valueOf(R.string.university));
        this.career = (String) bundle.get(String.valueOf(R.string.career));
        this.sector1 = (String) bundle.get(String.valueOf(R.string.sector1));
        this.sector2 = (String) bundle.get(String.valueOf(R.string.sector2));
        this.experience = (String) bundle.get(String.valueOf(R.string.experience));
        this.languages = (String) bundle.get(String.valueOf(R.string.languages));
        this.knowledges = (String) bundle.get(String.valueOf(R.string.knowledges));
    }

    /**
     * Constructor
     * @param views   Contiene los datos de un contacto obtenidos en TextViews
     * @param state   Diferencia la creacción de un usuario y su inicio de sesión
     */
    public Contact(TextView[] views, int state) {
        switch (state){
            case REGISTER:
                this.user = String.valueOf(views[0].getText());
                this.password = String.valueOf(views[1].getText());
                this.wage = String.valueOf(views[2].getText());
                break;
            case LOGIN:
                this.user = String.valueOf(views[0].getText());
                this.password = String.valueOf(views[1].getText());
                break;
        }
    }

    /**
     * Constructor
     * @param views     Contiene los datos de un contacto obtenidos en TextViews
     * @param buttons   Contiene los datos de visibilidad de un contacto obtenidos en Buttons
     */
    public Contact(TextView[] views, Button[] buttons, Context context) {
        this.name = String.valueOf(views[0].getText());
        this.surnames = String.valueOf(views[1].getText());
        this.email = String.valueOf(views[2].getText());
        this.telephone = String.valueOf(views[3].getText());
        this.job = String.valueOf(views[4].getText());
        this.company = String.valueOf(views[5].getText());
        if ("".equals(String.valueOf(views[6].getText()))) {
            this.wage = "0";
        } else {
            this.wage = String.valueOf(views[6].getText());
        }
        this.university = String.valueOf(views[7].getText());
        this.career = String.valueOf(views[8].getText());
        this.sector1 = String.valueOf(views[9].getText());
        this.sector2 = String.valueOf(views[10].getText());
        this.experience = String.valueOf(views[11].getText());
        this.languages = String.valueOf(views[12].getText());
        this.knowledges = String.valueOf(views[13].getText());

        if (buttons[0].getBackground().getConstantState() == context.getResources().getDrawable(R.drawable.ic_visibility_off).getConstantState()) {
            this.nameVisibility = 0;
        } else {
            this.nameVisibility = 1;
        }

        if (buttons[1].getBackground().getConstantState() == context.getResources().getDrawable(R.drawable.ic_visibility_off).getConstantState()) {
            this.surnamesVisibility = 0;
        } else {
            this.surnamesVisibility = 1;
        }

        if (buttons[2].getBackground().getConstantState() == context.getResources().getDrawable(R.drawable.ic_visibility_off).getConstantState()) {
            this.emailVisibility = 0;
        } else {
            this.emailVisibility = 1;
        }

        if (buttons[3].getBackground().getConstantState() == context.getResources().getDrawable(R.drawable.ic_visibility_off).getConstantState()) {
            this.telephoneVisibility = 0;
        } else {
            this.telephoneVisibility = 1;
        }

        if (buttons[4].getBackground().getConstantState() == context.getResources().getDrawable(R.drawable.ic_visibility_off).getConstantState()) {
            this.jobVisibility = 0;
        } else {
            this.jobVisibility = 1;
        }

        if (buttons[5].getBackground().getConstantState() == context.getResources().getDrawable(R.drawable.ic_visibility_off).getConstantState()) {
            this.companyVisibility = 0;
        } else {
            this.companyVisibility = 1;
        }

        if (buttons[6].getBackground().getConstantState() == context.getResources().getDrawable(R.drawable.ic_visibility_off).getConstantState()) {
            this.universityVisibility = 0;
        } else {
            this.universityVisibility = 1;
        }

        if (buttons[7].getBackground().getConstantState() == context.getResources().getDrawable(R.drawable.ic_visibility_off).getConstantState()) {
            this.careerVisibility = 0;
        } else {
            this.careerVisibility = 1;
        }

        if (buttons[8].getBackground().getConstantState() == context.getResources().getDrawable(R.drawable.ic_visibility_off).getConstantState()) {
            this.sector1Visibility = 0;
        } else {
            this.sector1Visibility = 1;
        }

        if (buttons[9].getBackground().getConstantState() == context.getResources().getDrawable(R.drawable.ic_visibility_off).getConstantState()) {
            this.sector2Visibility = 0;
        } else {
            this.sector2Visibility = 1;
        }

        if (buttons[10].getBackground().getConstantState() == context.getResources().getDrawable(R.drawable.ic_visibility_off).getConstantState()) {
            this.experienceVisibility = 0;
        } else {
            this.experienceVisibility = 1;
        }

        if (buttons[11].getBackground().getConstantState() == context.getResources().getDrawable(R.drawable.ic_visibility_off).getConstantState()) {
            this.languagesVisibility = 0;
        } else {
            this.languagesVisibility = 1;
        }

        if (buttons[12].getBackground().getConstantState() == context.getResources().getDrawable(R.drawable.ic_visibility_off).getConstantState()) {
            this.knowledgesVisibility = 0;
        } else {
            this.knowledgesVisibility = 1;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurnames() {
        return surnames;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getJob() {
        return job;
    }

    public String getCompany() {
        return company;
    }

    public String getWage() {
        return wage;
    }

    public String getUniversity() {
        return university;
    }

    public String getCareer() {
        return career;
    }

    public String getSector1() {
        return sector1;
    }

    public String getSector2() {
        return sector2;
    }

    public String getExperience() {
        return experience;
    }

    public String getLanguages() {
        return languages;
    }

    public String getKnowledges() {
        return knowledges;
    }

    public int getNameVisibility() {
        return nameVisibility;
    }

    public int getSurnamesVisibility() {
        return surnamesVisibility;
    }

    public int getEmailVisibility() {
        return emailVisibility;
    }

    public int getTelephoneVisibility() {
        return telephoneVisibility;
    }

    public int getJobVisibility() {
        return jobVisibility;
    }

    public int getCompanyVisibility() {
        return companyVisibility;
    }

    public int getUniversityVisibility() {
        return universityVisibility;
    }

    public int getCareerVisibility() {
        return careerVisibility;
    }

    public int getSector1Visibility() {
        return sector1Visibility;
    }

    public int getSector2Visibility() {
        return sector2Visibility;
    }

    public int getExperienceVisibility() {
        return experienceVisibility;
    }

    public int getLanguagesVisibility() {
        return languagesVisibility;
    }

    public int getKnowledgesVisibility() {
        return knowledgesVisibility;
    }

    @Override
    public String toString() {
        return name;
    }
}