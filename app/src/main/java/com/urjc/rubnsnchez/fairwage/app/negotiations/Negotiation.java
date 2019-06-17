package com.urjc.rubnsnchez.fairwage.app.negotiations;

import android.database.Cursor;
import android.os.Bundle;
import com.urjc.rubnsnchez.fairwage.R;

public class Negotiation {
    private int id;
    private int idServer;
    private String userCreator;
    private String userReceptor;
    private String state;
    private String oName;
    private String oSurnames;
    private String oEmail;
    private String oTelephone;
    private String oJob;
    private String oCompany;
    private String oWage;
    private String oUniversity;
    private String oCareer;
    private String oSector1;
    private String oSector2;
    private String oExperience;
    private String oLanguages;
    private String oKnowledges;
    private String rName;
    private String rSurnames;
    private String rEmail;
    private String rTelephone;
    private String rJob;
    private String rCompany;
    private String rWage;
    private String rUniversity;
    private String rCareer;
    private String rSector1;
    private String rSector2;
    private String rExperience;
    private String rLanguages;
    private String rKnowledges;

    Negotiation(Cursor c) {
        this.id = c.getInt(0);
        this.idServer = c.getInt(1);
        this.userCreator = c.getString(2);
        this.userReceptor = c.getString(3);
        this.state = c.getString(4);
        this.oName = c.getString(5);
        this.oSurnames = c.getString(6);
        this.oEmail = c.getString(7);
        this.oTelephone = c.getString(8);
        this.oJob = c.getString(9);
        this.oCompany = c.getString(10);
        this.oWage = c.getString(11);
        this.oUniversity = c.getString(12);
        this.oCareer = c.getString(13);
        this.oSector1 = c.getString(14);
        this.oSector2 = c.getString(15);
        this.oExperience = c.getString(16);
        this.oLanguages = c.getString(17);
        this.oKnowledges = c.getString(18);
        this.rName = c.getString(19);
        this.rSurnames = c.getString(20);
        this.rEmail = c.getString(21);
        this.rTelephone = c.getString(22);
        this.rJob = c.getString(23);
        this.rCompany = c.getString(24);
        this.rWage = c.getString(25);
        this.rUniversity = c.getString(26);
        this.rCareer = c.getString(27);
        this.rSector1 = c.getString(28);
        this.rSector2 = c.getString(29);
        this.rExperience = c.getString(30);
        this.rLanguages = c.getString(31);
        this.rKnowledges = c.getString(32);
    }

    Negotiation(Bundle bundle) {
        this.idServer = Integer.parseInt(String.valueOf(bundle.get(String.valueOf(R.string.idLowcase))));
        this.userCreator = (String) bundle.get(String.valueOf(R.string.userCreator));
        this.userReceptor = (String) bundle.get(String.valueOf(R.string.userReceptor));
        this.state = (String) bundle.get(String.valueOf(R.string.state));
        this.oName = (String) bundle.get(String.valueOf(R.string.oName));
        this.oSurnames = (String) bundle.get(String.valueOf(R.string.oSurnames));
        this.oEmail = (String) bundle.get(String.valueOf(R.string.oEmail));
        this.oTelephone = (String) bundle.get(String.valueOf(R.string.oTelephone));
        this.oJob = (String) bundle.get(String.valueOf(R.string.oJob));
        this.oCompany = (String) bundle.get(String.valueOf(R.string.oCompany));
        this.oWage = (String) bundle.get(String.valueOf(R.string.oWage));
        this.oUniversity = (String) bundle.get(String.valueOf(R.string.oUniversity));
        this.oCareer = (String) bundle.get(String.valueOf(R.string.oCareer));
        this.oSector1 = (String) bundle.get(String.valueOf(R.string.oSector1));
        this.oSector2 = (String) bundle.get(String.valueOf(R.string.oSector2));
        this.oExperience = (String) bundle.get(String.valueOf(R.string.oExperience));
        this.oLanguages = (String) bundle.get(String.valueOf(R.string.oLanguages));
        this.oKnowledges = (String) bundle.get(String.valueOf(R.string.oKnowledges));

        this.rName = (String) bundle.get(String.valueOf(R.string.rName));
        this.rSurnames = (String) bundle.get(String.valueOf(R.string.rSurnames));
        this.rEmail = (String) bundle.get(String.valueOf(R.string.rEmail));
        this.rTelephone = (String) bundle.get(String.valueOf(R.string.rTelephone));
        this.rJob = (String) bundle.get(String.valueOf(R.string.rJob));
        this.rCompany = (String) bundle.get(String.valueOf(R.string.rCompany));
        this.rWage = (String) bundle.get(String.valueOf(R.string.rWage));
        this.rUniversity = (String) bundle.get(String.valueOf(R.string.rUniversity));
        this.rCareer = (String) bundle.get(String.valueOf(R.string.rCareer));
        this.rSector1 = (String) bundle.get(String.valueOf(R.string.rSector1));
        this.rSector2 = (String) bundle.get(String.valueOf(R.string.rSector2));
        this.rExperience = (String) bundle.get(String.valueOf(R.string.rExperience));
        this.rLanguages = (String) bundle.get(String.valueOf(R.string.rLanguages));
        this.rKnowledges = (String) bundle.get(String.valueOf(R.string.rKnowledges));
    }

    public int getId() {
        return id;
    }

    public int getIdServer() {
        return idServer;
    }

    public String getUserCreator() {
        return userCreator;
    }

    public String getUserReceptor() {
        return userReceptor;
    }

    public String getState() {
        return state;
    }

    public String getoName() {
        return oName;
    }

    public String getoSurnames() {
        return oSurnames;
    }

    public String getoEmail() {
        return oEmail;
    }

    public String getoTelephone() {
        return oTelephone;
    }

    public String getoJob() {
        return oJob;
    }

    public String getoCompany() {
        return oCompany;
    }

    public String getoWage() {
        return oWage;
    }

    public String getoUniversity() {
        return oUniversity;
    }

    public String getoCareer() {
        return oCareer;
    }

    public String getoSector1() {
        return oSector1;
    }

    public String getoSector2() {
        return oSector2;
    }

    public String getoExperience() {
        return oExperience;
    }

    public String getoLanguages() {
        return oLanguages;
    }

    public String getoKnowledges() {
        return oKnowledges;
    }

    public String getrName() {
        return rName;
    }

    public String getrSurnames() {
        return rSurnames;
    }

    public String getrEmail() {
        return rEmail;
    }

    public String getrTelephone() {
        return rTelephone;
    }

    public String getrJob() {
        return rJob;
    }

    public String getrCompany() {
        return rCompany;
    }

    public String getrWage() {
        return rWage;
    }

    public String getrUniversity() {
        return rUniversity;
    }

    public String getrCareer() {
        return rCareer;
    }

    public String getrSector1() {
        return rSector1;
    }

    public String getrSector2() {
        return rSector2;
    }

    public String getrExperience() {
        return rExperience;
    }

    public String getrLanguages() {
        return rLanguages;
    }

    public String getrKnowledges() {
        return rKnowledges;
    }

    public String toString() {
        return this.id + ": " + this.userCreator + " | " + this.userReceptor;
    }
}
