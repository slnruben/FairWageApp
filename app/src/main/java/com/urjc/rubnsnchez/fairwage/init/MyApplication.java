package com.urjc.rubnsnchez.fairwage.init;

import android.app.Application;

import com.urjc.rubnsnchez.fairwage.init.common.BBDDHelper;

/**
 * Clase común y accesible para todas las activities
 * @author Rubén Sánchez Rivero
 * @version 1
 */
public class MyApplication extends Application {
    public static final String VERSION = "Versión: 0.9.9b";
    public static final String DEVELOPER_EMAIL = "ruben.sanchez@jinp.com";
    public static final String LOGIN_FILE = "/login";
    public static final int REGISTER = 0;
    public static final int LOGIN = 1;
    public static final int EDIT = 2;
    public static final int FATAL = -2;
    public static final int ERROR = -1;
    public static final int SUCCESS = 1;
    public static final int NOT_FOUND = 2;
    public static final int DUPLICATE = 3;
    public static final int LOAD = 5;
    private String user = "ruben";
    private String urlServer = "http://83.48.85.164/contactos/";
    private BBDDHelper bbddHelper;

    public void createBBDDHelper() {
        bbddHelper = new BBDDHelper(getApplicationContext());
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public BBDDHelper getBbddHelper() {
        return bbddHelper;
    }
}