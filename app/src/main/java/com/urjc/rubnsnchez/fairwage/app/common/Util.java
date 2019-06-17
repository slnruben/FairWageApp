package com.urjc.rubnsnchez.fairwage.app.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.urjc.rubnsnchez.fairwage.R;
import com.urjc.rubnsnchez.fairwage.app.contacts.Contact;
import com.urjc.rubnsnchez.fairwage.app.negotiations.Negotiation;
import org.json.JSONArray;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

import static com.urjc.rubnsnchez.fairwage.app.MyApplication.CHARSET_TYPE;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.CODE_PRIVATE;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.CODE_PUBLIC;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.GET_CONTACT_PUBLIC_KEY;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.REGISTER_SERVER;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.SEARCH_CONTACTS;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.SEARCH_NEGOTIATIONS;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.SUCCESS;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.ERROR;
import static com.urjc.rubnsnchez.fairwage.app.common.BBDDUtil.createSearchContactBBDD;
import static com.urjc.rubnsnchez.fairwage.app.common.BBDDUtil.createSearchNegotiationBBDD;
import static com.urjc.rubnsnchez.fairwage.app.common.CipherUtil.decrypt;

public class Util {
    /**
     * Genera un Bundle con los datos de un contacto para pasarselos a otra activity.
     * @param   fullname Contacto con sus datos
     * @return  Un Bundle.
     */
    public static Bundle createFullNameBundle(Contact fullname) {
        Bundle bundle = new Bundle();
        bundle.putString(String.valueOf(R.string.user), fullname.getUser());
        bundle.putString(String.valueOf(R.string.publicKey), Base64.encodeToString(fullname.getPublicKey(), Base64.NO_WRAP));
        bundle.putString(String.valueOf(R.string.name), fullname.getName());
        bundle.putString(String.valueOf(R.string.surnames), fullname.getSurnames());
        bundle.putString(String.valueOf(R.string.email), fullname.getEmail());
        bundle.putString(String.valueOf(R.string.telephone), fullname.getTelephone());
        bundle.putString(String.valueOf(R.string.job), fullname.getJob());
        bundle.putString(String.valueOf(R.string.company), fullname.getCompany());
        bundle.putString(String.valueOf(R.string.wage), fullname.getWage());
        bundle.putString(String.valueOf(R.string.university), fullname.getUniversity());
        bundle.putString(String.valueOf(R.string.career), fullname.getCareer());
        bundle.putString(String.valueOf(R.string.sector1), fullname.getSector1());
        bundle.putString(String.valueOf(R.string.sector2), fullname.getSector2());
        bundle.putString(String.valueOf(R.string.experience), fullname.getExperience());
        bundle.putString(String.valueOf(R.string.languages), fullname.getLanguages());
        bundle.putString(String.valueOf(R.string.knowledges), fullname.getKnowledges());
        return bundle;
    }

    public static Bundle createFullNegotiationBundle(Negotiation negotiation) {
        Bundle bundle = new Bundle();
        bundle.putString(String.valueOf(R.string.idLowcase), String.valueOf(negotiation.getIdServer()));
        bundle.putString(String.valueOf(R.string.userCreator), negotiation.getUserCreator());
        bundle.putString(String.valueOf(R.string.userReceptor), negotiation.getUserReceptor());
        bundle.putString(String.valueOf(R.string.state), negotiation.getState());

        bundle.putString(String.valueOf(R.string.oName), negotiation.getoName());
        bundle.putString(String.valueOf(R.string.oSurnames), negotiation.getoSurnames());
        bundle.putString(String.valueOf(R.string.oEmail), negotiation.getoEmail());
        bundle.putString(String.valueOf(R.string.oTelephone), negotiation.getoTelephone());
        bundle.putString(String.valueOf(R.string.oJob), negotiation.getoJob());
        bundle.putString(String.valueOf(R.string.oCompany), negotiation.getoCompany());
        bundle.putString(String.valueOf(R.string.oWage), negotiation.getoWage());
        bundle.putString(String.valueOf(R.string.oUniversity), negotiation.getoUniversity());
        bundle.putString(String.valueOf(R.string.oCareer), negotiation.getoCareer());
        bundle.putString(String.valueOf(R.string.oSector1), negotiation.getoSector1());
        bundle.putString(String.valueOf(R.string.oSector2), negotiation.getoSector2());
        bundle.putString(String.valueOf(R.string.oExperience), negotiation.getoExperience());
        bundle.putString(String.valueOf(R.string.oLanguages), negotiation.getoLanguages());
        bundle.putString(String.valueOf(R.string.oKnowledges), negotiation.getoKnowledges());
        bundle.putString(String.valueOf(R.string.rName), negotiation.getrName());
        bundle.putString(String.valueOf(R.string.rSurnames), negotiation.getrSurnames());
        bundle.putString(String.valueOf(R.string.rEmail), negotiation.getrEmail());
        bundle.putString(String.valueOf(R.string.rTelephone), negotiation.getrTelephone());
        bundle.putString(String.valueOf(R.string.rJob), negotiation.getrJob());
        bundle.putString(String.valueOf(R.string.rCompany), negotiation.getrCompany());
        bundle.putString(String.valueOf(R.string.rWage), negotiation.getrWage());
        bundle.putString(String.valueOf(R.string.rUniversity), negotiation.getrUniversity());
        bundle.putString(String.valueOf(R.string.rCareer), negotiation.getrCareer());
        bundle.putString(String.valueOf(R.string.rSector1), negotiation.getrSector1());
        bundle.putString(String.valueOf(R.string.rSector2), negotiation.getrSector2());
        bundle.putString(String.valueOf(R.string.rExperience), negotiation.getrExperience());
        bundle.putString(String.valueOf(R.string.rLanguages), negotiation.getrLanguages());
        bundle.putString(String.valueOf(R.string.rKnowledges), negotiation.getrKnowledges());
        return bundle;
    }

    /**
     * Comprueba si un EditText esta en blanco.
     * @param textView  EditText que se quiere comprobar.
     * @return          True si no esta vacio, false en caso contrario.
     */
    private static boolean isNotEmpty(TextView textView) {
        return (textView.getText().toString().trim().length() > 0);
    }

    /**
     * Comprueba que no hay ningun EditText vacio en un array, los que esten
     * vacios se marcan con una exclamacion.
     * @param textViews Array de EditText que se quieren comprobar.
     * @return          False si hay alguno vacio, true en caso contrario.
     */
    public static boolean checkEditTexts(TextView[] textViews) {
        boolean result = true;
        for (TextView textView: textViews) {
            if (!isNotEmpty(textView)) {
                textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_error, 0, 0, 0);
                result = false;
            } else {
                textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }
        }
        return result;
    }

    public static String checkPrivacy(String str) {
        String code;
        String result = "";

        if (str != null && !str.trim().isEmpty()) {
            code = str.substring(0, 3);
            if (CODE_PUBLIC.equals(code)) {
                result = str.substring(3);
            }
        }
        return result;
    }

    public static String stripCode(PrivateKey privateKey, String str) {
        String code;
        String result = "";

        if (str != null && !str.trim().isEmpty()) {
            code = str.substring(0, 3);
            if (CODE_PRIVATE.equals(code)) {
                result = decrypt(privateKey, Base64.decode(str.substring(3), Base64.NO_WRAP));
            } else {
                result = str.substring(3);
            }
        }
        return result;
    }

    /**
     * Inserta los datos de un contacto en Views.
     */
    public static void setTexts(Map<String, LinearLayout> layouts, Map<String, TextView> views, Context context, Contact contact) {
        String name = contact.getName();
        if (name != null && !"null".equals(name) && !"".equals(name)) {
            views.get(context.getString(R.string.name)).setText(name);
        } else {
            layouts.get(context.getString(R.string.name)).setVisibility(View.GONE);
        }

        String surnames = contact.getSurnames();
        if (surnames != null && !"null".equals(surnames) && !"".equals(surnames)) {
            views.get(context.getString(R.string.surnames)).setText(surnames);
        } else {
            layouts.get(context.getString(R.string.surnames)).setVisibility(View.GONE);
        }

        String email = contact.getEmail();
        if (email != null && !"null".equals(email) && !"".equals(email)) {
            views.get(context.getString(R.string.email)).setText(email);
        } else {
            layouts.get(context.getString(R.string.email)).setVisibility(View.GONE);
        }

        String telephone = contact.getTelephone();
        if (telephone != null && !"null".equals(telephone) && !"".equals(telephone)) {
            views.get(context.getString(R.string.telephone)).setText(telephone);
        } else {
            layouts.get(context.getString(R.string.telephone)).setVisibility(View.GONE);
        }

        String job = contact.getJob();
        if (job != null && !"null".equals(job) && !"".equals(job)) {
            views.get(context.getString(R.string.job)).setText(job);
        } else {
            layouts.get(context.getString(R.string.job)).setVisibility(View.GONE);
        }

        String company = contact.getCompany();
        if (company != null && !"null".equals(company) && !"".equals(company)) {
            views.get(context.getString(R.string.company)).setText(company);
        } else {
            layouts.get(context.getString(R.string.company)).setVisibility(View.GONE);
        }

        String wage = contact.getWage();
        String str;
        if (wage != null && !"null".equals(wage) && !"".equals(wage)) {
            str = wage + " " + context.getString(R.string.currency);
            views.get(context.getString(R.string.wage)).setText(str);
        } else {
            str = "0 " + context.getString(R.string.currency);
            views.get(context.getString(R.string.wage)).setText(str);
        }

        String university = contact.getUniversity();
        if (university != null && !"null".equals(university) && !"".equals(university)) {
            views.get(context.getString(R.string.university)).setText(university);
        } else {
            layouts.get(context.getString(R.string.university)).setVisibility(View.GONE);
        }

        String career = contact.getCareer();
        if (career != null && !"null".equals(career) && !"".equals(career)) {
            views.get(context.getString(R.string.career)).setText(career);
        } else {
            layouts.get(context.getString(R.string.career)).setVisibility(View.GONE);
        }

        String sector1 = contact.getSector1();
        if (sector1 != null && !"null".equals(sector1) && !"".equals(sector1)) {
            views.get(context.getString(R.string.sector1)).setText(sector1);
        } else {
            layouts.get(context.getString(R.string.sector1)).setVisibility(View.GONE);
        }

        String sector2 = contact.getSector2();
        if (sector2 != null && !"null".equals(sector2) && !"".equals(sector2)) {
            views.get(context.getString(R.string.sector2)).setText(sector2);
        } else {
            layouts.get(context.getString(R.string.sector2)).setVisibility(View.GONE);
        }

        String experience = contact.getExperience();
        if (experience != null && !"null".equals(experience) && !"".equals(experience)) {
            views.get(context.getString(R.string.experience)).setText(experience);
        } else {
            layouts.get(context.getString(R.string.experience)).setVisibility(View.GONE);
        }

        String languages = contact.getLanguages();
        if (languages != null && !"null".equals(languages) && !"".equals(languages)) {
            views.get(context.getString(R.string.languages)).setText(languages);
        } else {
            layouts.get(context.getString(R.string.languages)).setVisibility(View.GONE);
        }
        String knowledges = contact.getKnowledges();
        if (knowledges != null && !"null".equals(knowledges) && !"".equals(knowledges)) {
            views.get(context.getString(R.string.knowledges)).setText(knowledges);
        } else {
            layouts.get(context.getString(R.string.knowledges)).setVisibility(View.GONE);
        }
    }

    /**
     * Inserta los datos de un contacto en Views.
     */
    public static void setEditTexts(Map<String, EditText> views, Context context, Contact contact) {
        String name = contact.getName();
        if (name != null && !"null".equals(name) && !"".equals(name)) {
            views.get(context.getString(R.string.name)).setText(name);
        } else {
            views.get(context.getString(R.string.name)).setText("");
        }

        String surnames = contact.getSurnames();
        if (surnames != null && !"null".equals(surnames) && !"".equals(surnames)) {
            views.get(context.getString(R.string.surnames)).setText(surnames);
        } else {
            views.get(context.getString(R.string.surnames)).setText("");
        }

        String email = contact.getEmail();
        if (email != null && !"null".equals(email) && !"".equals(email)) {
            views.get(context.getString(R.string.email)).setText(email);
        } else {
            views.get(context.getString(R.string.email)).setText("");
        }

        String telephone = contact.getTelephone();
        if (telephone != null && !"null".equals(telephone) && !"".equals(telephone)) {
            views.get(context.getString(R.string.telephone)).setText(telephone);
        } else {
            views.get(context.getString(R.string.telephone)).setText("");
        }

        String job = contact.getJob();
        if (job != null && !"null".equals(job) && !"".equals(job)) {
            views.get(context.getString(R.string.job)).setText(job);
        } else {
            views.get(context.getString(R.string.job)).setText("");
        }

        String company = contact.getCompany();
        if (company != null && !"null".equals(company) && !"".equals(company)) {
            views.get(context.getString(R.string.company)).setText(company);
        } else {
            views.get(context.getString(R.string.company)).setText("");
        }

        String wage = contact.getWage();
        if (wage != null && !"null".equals(wage) && !"".equals(wage)) {
            views.get(context.getString(R.string.wage)).setText(wage);
        } else {
            views.get(context.getString(R.string.wage)).setText("");
        }

        String university = contact.getUniversity();
        if (university != null && !"null".equals(university) && !"".equals(university)) {
            views.get(context.getString(R.string.university)).setText(university);
        } else {
            views.get(context.getString(R.string.university)).setText("");
        }

        String career = contact.getCareer();
        if (career != null && !"null".equals(career) && !"".equals(career)) {
            views.get(context.getString(R.string.career)).setText(career);
        } else {
            views.get(context.getString(R.string.career)).setText("");
        }

        String sector1 = contact.getSector1();
        if (sector1 != null && !"null".equals(sector1) && !"".equals(sector1)) {
            views.get(context.getString(R.string.sector1)).setText(sector1);
        } else {
            views.get(context.getString(R.string.sector1)).setText("");
        }

        String sector2 = contact.getSector2();
        if (sector2 != null && !"null".equals(sector2) && !"".equals(sector2)) {
            views.get(context.getString(R.string.sector2)).setText(sector2);
        } else {
            views.get(context.getString(R.string.sector2)).setText("");
        }

        String experience = contact.getExperience();
        if (experience != null && !"null".equals(experience) && !"".equals(experience)) {
            views.get(context.getString(R.string.experience)).setText(experience);
        } else {
            views.get(context.getString(R.string.experience)).setText("");
        }

        String languages = contact.getLanguages();
        if (languages != null && !"null".equals(languages) && !"".equals(languages)) {
            views.get(context.getString(R.string.languages)).setText(languages);
        } else {
            views.get(context.getString(R.string.languages)).setText("");
        }
        String knowledges = contact.getKnowledges();
        if (knowledges != null && !"null".equals(knowledges) && !"".equals(knowledges)) {
            views.get(context.getString(R.string.knowledges)).setText(knowledges);
        } else {
            views.get(context.getString(R.string.knowledges)).setText("");
        }
    }

    public static void setVisibilityButtons(Button[] buttons, Contact contact, Context context) {
        if (contact.getNameVisibility() == 0) {
            buttons[0].setBackground(context.getResources().getDrawable(R.drawable.ic_visibility_off));
        } else {
            buttons[0].setBackground(context.getResources().getDrawable(R.drawable.ic_visibility));
        }

        if (contact.getSurnamesVisibility() == 0) {
            buttons[1].setBackground(context.getResources().getDrawable(R.drawable.ic_visibility_off));
        } else {
            buttons[1].setBackground(context.getResources().getDrawable(R.drawable.ic_visibility));
        }

        if (contact.getEmailVisibility() == 0) {
            buttons[2].setBackground(context.getResources().getDrawable(R.drawable.ic_visibility_off));
        } else {
            buttons[2].setBackground(context.getResources().getDrawable(R.drawable.ic_visibility));
        }

        if (contact.getTelephoneVisibility() == 0) {
            buttons[3].setBackground(context.getResources().getDrawable(R.drawable.ic_visibility_off));
        } else {
            buttons[3].setBackground(context.getResources().getDrawable(R.drawable.ic_visibility));
        }

        if (contact.getJobVisibility() == 0) {
            buttons[4].setBackground(context.getResources().getDrawable(R.drawable.ic_visibility_off));
        } else {
            buttons[4].setBackground(context.getResources().getDrawable(R.drawable.ic_visibility));
        }

        if (contact.getCompanyVisibility() == 0) {
            buttons[5].setBackground(context.getResources().getDrawable(R.drawable.ic_visibility_off));
        } else {
            buttons[5].setBackground(context.getResources().getDrawable(R.drawable.ic_visibility));
        }

        if (contact.getUniversityVisibility() == 0) {
            buttons[6].setBackground(context.getResources().getDrawable(R.drawable.ic_visibility_off));
        } else {
            buttons[6].setBackground(context.getResources().getDrawable(R.drawable.ic_visibility));
        }

        if (contact.getCareerVisibility() == 0) {
            buttons[7].setBackground(context.getResources().getDrawable(R.drawable.ic_visibility_off));
        } else {
            buttons[7].setBackground(context.getResources().getDrawable(R.drawable.ic_visibility));
        }

        if (contact.getSector1Visibility() == 0) {
            buttons[8].setBackground(context.getResources().getDrawable(R.drawable.ic_visibility_off));
        } else {
            buttons[8].setBackground(context.getResources().getDrawable(R.drawable.ic_visibility));
        }

        if (contact.getSector2Visibility() == 0) {
            buttons[9].setBackground(context.getResources().getDrawable(R.drawable.ic_visibility_off));
        } else {
            buttons[9].setBackground(context.getResources().getDrawable(R.drawable.ic_visibility));
        }

        if (contact.getExperienceVisibility() == 0) {
            buttons[10].setBackground(context.getResources().getDrawable(R.drawable.ic_visibility_off));
        } else {
            buttons[10].setBackground(context.getResources().getDrawable(R.drawable.ic_visibility));
        }

        if (contact.getLanguagesVisibility() == 0) {
            buttons[11].setBackground(context.getResources().getDrawable(R.drawable.ic_visibility_off));
        } else {
            buttons[11].setBackground(context.getResources().getDrawable(R.drawable.ic_visibility));
        }

        if (contact.getKnowledgesVisibility() == 0) {
            buttons[12].setBackground(context.getResources().getDrawable(R.drawable.ic_visibility_off));
        } else {
            buttons[12].setBackground(context.getResources().getDrawable(R.drawable.ic_visibility));
        }
    }

    private static JSONArray obtDatosJSON(String response) {
        JSONArray json = null;
        try {
            json = new JSONArray(response);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    private static String getDataPOST(Context context, HttpURLConnection con, String nombre) {
        String result = String.valueOf(ERROR);
        InputStream in;
        String linea;
        StringBuilder resultBuilder = new StringBuilder();
        try {
            in = new BufferedInputStream(con.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while ((linea = reader.readLine()) != null) {
                resultBuilder.append(linea);
            }
            if (REGISTER_SERVER.equals(nombre)) {
                result = resultBuilder.toString();
            } else if (SEARCH_CONTACTS.equals(nombre)) {
                final JSONArray json = obtDatosJSON(resultBuilder.toString());
                result = createSearchContactBBDD(context, json);
            } else if (SEARCH_NEGOTIATIONS.equals(nombre)) {
                final JSONArray json = obtDatosJSON(resultBuilder.toString());
                result = createSearchNegotiationBBDD(context, json);
            } else if (GET_CONTACT_PUBLIC_KEY.equals(nombre)) {
                result = resultBuilder.toString();
            } else if (String.valueOf(SUCCESS).equals(resultBuilder.toString())) {
                result = String.valueOf(SUCCESS);
            } else {
                Log.e("error", "ERROR getDataPOST");
            }
            //final JSONArray json = obtDatosJSON(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String getPostDataString(HashMap<String, String> params) {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first) {
                first = false;
            } else {
                result.append("&");
            }
            try {
                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return result.toString();
    }

    public static String sendDataPOST(Context context, String urlServer, String nombre, HashMap<String, String> postDataParams) {
        String result;
        try {
            URL url = new URL(urlServer + nombre);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setReadTimeout(15000);
            con.setConnectTimeout(15000);
            con.setRequestMethod("POST");
            con.setDoInput(true);
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, CHARSET_TYPE));
            writer.write(getPostDataString(postDataParams));
            writer.flush();
            writer.close();
            os.close();
            int respuesta = con.getResponseCode();
            if (respuesta == HttpURLConnection.HTTP_OK) {
                result = getDataPOST(context, con, nombre);
            } else {
                result = "-2";
            }
        } catch (SocketTimeoutException e) {
            Log.e("error", "TIMEOUT: " + e);
            result = "0";
        } catch (Exception e) {
            Log.e("error", "ERROR: " + e);
            result = "-2";
        }
        return result;
    }

    public static boolean isNetworkAvailable(ConnectivityManager cm) {
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        // if no network is available networkInfo will be null
        // otherwise check if we are connected
        return (networkInfo != null && networkInfo.isConnected());
    }
}
