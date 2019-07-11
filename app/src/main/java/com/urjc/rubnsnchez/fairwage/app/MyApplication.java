package com.urjc.rubnsnchez.fairwage.app;

import android.app.Application;

import com.urjc.rubnsnchez.fairwage.app.common.BBDDHelper;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Clase común y accesible para todas las activities
 * @author Rubén Sánchez Rivero
 * @version 1
 */
public class MyApplication extends Application {
    public static final String REGISTER_SERVER = "register";
    public static final String UPDATE_SERVER = "update_user";
    public static final String SEARCH_CONTACTS = "search_contacts";
    public static final String CREATE_NEGOTIATION = "create_negotiation";
    public static final String SEARCH_NEGOTIATIONS = "search_negotiations";
    public static final String ACCEPT_NEGOTIATION = "accept_negotiation";
    public static final String REFUSE_NEGOTIATION = "refuse_negotiation";
    public static final String DELETE_NEGOTIATION = "delete_negotiation";
    public static final String UPDATE_NEGOTIATION = "update_negotiation";
    public static final String GET_CONTACT_PUBLIC_KEY = "get_contact_public_key";
    public static final String LOGIN_SERVER = "login";
    public static final int REGISTER = 0;
    public static final int LOGIN = 1;
    public static final int EDIT = 2;
    public static final int SEARCH = 3;
    public static final int PROFILE = 4;
    public static final int NEGOTIATION = 4;
    public static final String SENDED = "Enviada";
    public static final String RECEIVED = "Recibida";
    public static final String REFUSED = "Rechazada";
    public static final String ACCEPTED = "Aceptada";
    public static final String CODE_PRIVATE = "|1|";
    public static final String CODE_PUBLIC = "|2|";
    public static final String ALGORITHM = "RSA";
    public static final String CHARSET_TYPE = "UTF-8";
    public static final int ERROR = -1;
    public static final int SUCCESS = 1;
    private String user;
    private BBDDHelper bbddHelper;
    private PrivateKey privateKey;
    private PublicKey publicKey;


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

    public String getUrlServer() {
        return "https://pruebaspostgresql.herokuapp.com/";
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }
}