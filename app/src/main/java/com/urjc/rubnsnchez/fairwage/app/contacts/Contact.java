package com.urjc.rubnsnchez.fairwage.app.contacts;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Button;
import android.widget.TextView;
import com.urjc.rubnsnchez.fairwage.R;

import java.security.PrivateKey;

import static com.urjc.rubnsnchez.fairwage.app.MyApplication.LOGIN;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.REGISTER;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.SEARCH;
import static com.urjc.rubnsnchez.fairwage.app.common.CipherUtil.decrypt;

/**
 * Clase que define un contacto.
 * @author Rubén Sánchez Rivero
 * @version 1
 */
public class Contact {
    private int id;
    private int idServer;
    private byte[] privateKey;
    private byte[] publicKey;
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

    public Contact(String wage, String job, String sector1, String sector2) {
        this.job = job;
        this.wage = wage;
        this.sector1 = sector1;
        this.sector2 = sector2;
    }

    public Contact(String name, String surnames, String email, String telephone, String job, String company, String wage,
                   String university, String career, String sector1, String sector2, String experience, String languages,
                   String knowledges) {
        this.name = name;
        this.surnames = surnames;
        this.email = email;
        this.telephone = telephone;
        this.job = job;
        this.company = company;
        this.wage = wage;
        this.university = university;
        this.career = career;
        this.sector1 = sector1;
        this.sector2 = sector2;
        this.experience = experience;
        this.languages = languages;
        this.knowledges = knowledges;
    }

    /**
     * Contructor.
     * @param c Contiene los datos de un contacto obtenidos de un SELECT en SQLite.
     */
    public Contact(Cursor c, int state) {
        if (state == SEARCH) {
            this.id = c.getInt(0);
            this.user = c.getString(1);
            this.publicKey = c.getBlob(2);
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
        } else {
            this.id = c.getInt(0);
            this.idServer = c.getInt(1);
            //this.publicKey = c.getBlob(2);
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
            //this.state = c.getString(17);
            this.user = c.getString(20);
            this.password = c.getString(21);
            this.privateKey = c.getBlob(22);
            this.publicKey = c.getBlob(23);
            this.nameVisibility = c.getInt(24);
            this.surnamesVisibility = c.getInt(25);
            this.emailVisibility = c.getInt(26);
            this.telephoneVisibility = c.getInt(27);
            this.jobVisibility = c.getInt(28);
            this.companyVisibility = c.getInt(29);
            this.universityVisibility = c.getInt(30);
            this.careerVisibility = c.getInt(31);
            this.sector1Visibility = c.getInt(32);
            this.sector2Visibility = c.getInt(33);
            this.experienceVisibility = c.getInt(34);
            this.languagesVisibility = c.getInt(35);
            this.knowledgesVisibility = c.getInt(36);
        }
    }

    public Contact(Cursor c, int state, PrivateKey privateKey) {
        if (state == SEARCH) {
            this.id = c.getInt(0);
            this.user = c.getString(1);
            this.name = decrypt(privateKey, c.getBlob(3));
            this.surnames = decrypt(privateKey, c.getBlob(4));
            this.email = decrypt(privateKey, c.getBlob(5));
            this.telephone = decrypt(privateKey, c.getBlob(6));
            this.job = decrypt(privateKey, c.getBlob(7));
            this.company = decrypt(privateKey, c.getBlob(8));
            if ("".equals(c.getString(9))) {
                this.wage = "0";
            } else {
                this.wage = c.getString(9);
            }
            this.university = decrypt(privateKey, c.getBlob(10));
            this.career = decrypt(privateKey, c.getBlob(11));
            this.sector1 = decrypt(privateKey, c.getBlob(12));
            this.sector2 = decrypt(privateKey, c.getBlob(13));
            this.experience = decrypt(privateKey, c.getBlob(14));
            this.languages = decrypt(privateKey, c.getBlob(15));
            this.knowledges = decrypt(privateKey, c.getBlob(16));
        } else {
            this.id = c.getInt(0);
            this.idServer = c.getInt(1);
            this.name = decrypt(privateKey, c.getBlob(2));
            this.surnames = decrypt(privateKey, c.getBlob(3));
            this.email = decrypt(privateKey, c.getBlob(4));
            this.telephone = decrypt(privateKey, c.getBlob(5));
            this.job = decrypt(privateKey, c.getBlob(6));
            this.company = decrypt(privateKey, c.getBlob(7));
            if ("".equals(c.getString(8))) {
                this.wage = "0";
            } else {
                this.wage = c.getString(8);
            }
            this.university = decrypt(privateKey, c.getBlob(9));
            this.career = decrypt(privateKey, c.getBlob(10));
            this.sector1 = decrypt(privateKey, c.getBlob(11));
            this.sector2 = decrypt(privateKey, c.getBlob(12));
            this.experience = decrypt(privateKey, c.getBlob(13));
            this.languages = decrypt(privateKey, c.getBlob(14));
            this.knowledges = decrypt(privateKey, c.getBlob(15));
            //this.state = c.getString(17);
            this.user = c.getString(18);
            this.password = c.getString(19);
            this.privateKey = c.getBlob(20);
            this.publicKey = c.getBlob(21);
            this.nameVisibility = c.getInt(22);
            this.surnamesVisibility = c.getInt(23);
            this.emailVisibility = c.getInt(24);
            this.telephoneVisibility = c.getInt(25);
            this.jobVisibility = c.getInt(26);
            this.companyVisibility = c.getInt(27);
            this.universityVisibility = c.getInt(28);
            this.careerVisibility = c.getInt(29);
            this.sector1Visibility = c.getInt(30);
            this.sector2Visibility = c.getInt(31);
            this.experienceVisibility = c.getInt(32);
            this.languagesVisibility = c.getInt(33);
            this.knowledgesVisibility = c.getInt(34);
        }
    }

    /**
     * Constructor
     * @param bundle Contiene los datos de un contacto guardados por otra activity en un Bundle
     */
    public Contact(Bundle bundle) {
       // this.id = (int) bundle.get(String.valueOf(R.string.id));
        this.user = (String) bundle.get(String.valueOf(R.string.user));
        this.publicKey = Base64.decode((String) bundle.get(String.valueOf(R.string.publicKey)), Base64.NO_WRAP);
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

    public int getIdServer() {
        return idServer;
    }

    public void setIdServer(int idServer) {
        this.idServer = idServer;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(byte[] privateKey) {
        this.privateKey = privateKey;
    }

    public byte[] getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(byte[] publicKey) {
        this.publicKey = publicKey;
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