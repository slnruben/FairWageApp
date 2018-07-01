package com.urjc.rubnsnchez.fairwage.init.common;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.urjc.rubnsnchez.fairwage.R;
import com.urjc.rubnsnchez.fairwage.init.contacts.Contact;
import java.util.Map;

public class Util {

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
    public static boolean checkEditTexts(TextView textViews[]) {
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

    /**
     * Inserta los datos de un contacto en Views.
     */
    public static void setTexts(Map<String, LinearLayout> layouts, Map<String, TextView> views, Context context, Contact contact) {
        String name = contact.getName();
        if (name != null && !"null".equals(name) && !"".equals(name) && contact.getNameVisibility() == 1) {
            views.get(context.getString(R.string.name)).setText(name);
        } else {
            layouts.get(context.getString(R.string.name)).setVisibility(View.GONE);
        }

        String surnames = contact.getSurnames();
        if (surnames != null && !"null".equals(surnames) && !"".equals(surnames) && contact.getSurnamesVisibility() == 1) {
            views.get(context.getString(R.string.surnames)).setText(surnames);
        } else {
            layouts.get(context.getString(R.string.surnames)).setVisibility(View.GONE);
        }

        String email = contact.getEmail();
        if (email != null && !"null".equals(email) && !"".equals(email) && contact.getEmailVisibility() == 1) {
            views.get(context.getString(R.string.email)).setText(email);
        } else {
            layouts.get(context.getString(R.string.email)).setVisibility(View.GONE);
        }

        String telephone = contact.getTelephone();
        if (telephone != null && !"null".equals(telephone) && !"".equals(telephone) && contact.getTelephoneVisibility() == 1) {
            views.get(context.getString(R.string.telephone)).setText(telephone);
        } else {
            layouts.get(context.getString(R.string.telephone)).setVisibility(View.GONE);
        }

        String job = contact.getJob();
        if (job != null && !"null".equals(job) && !"".equals(job) && contact.getJobVisibility() == 1) {
            views.get(context.getString(R.string.job)).setText(job);
        } else {
            layouts.get(context.getString(R.string.job)).setVisibility(View.GONE);
        }

        String company = contact.getCompany();
        if (company != null && !"null".equals(company) && !"".equals(company) && contact.getCompanyVisibility() == 1) {
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
        if (university != null && !"null".equals(university) && !"".equals(university) && contact.getUniversityVisibility() == 1) {
            views.get(context.getString(R.string.university)).setText(university);
        } else {
            layouts.get(context.getString(R.string.university)).setVisibility(View.GONE);
        }

        String career = contact.getCareer();
        if (career != null && !"null".equals(career) && !"".equals(career) && contact.getCareerVisibility() == 1) {
            views.get(context.getString(R.string.career)).setText(career);
        } else {
            layouts.get(context.getString(R.string.career)).setVisibility(View.GONE);
        }

        String sector1 = contact.getSector1();
        if (sector1 != null && !"null".equals(sector1) && !"".equals(sector1) && contact.getSector1Visibility() == 1) {
            views.get(context.getString(R.string.sector1)).setText(sector1);
        } else {
            layouts.get(context.getString(R.string.sector1)).setVisibility(View.GONE);
        }

        String sector2 = contact.getSector2();
        if (sector2 != null && !"null".equals(sector2) && !"".equals(sector2) && contact.getSector2Visibility() == 1) {
            views.get(context.getString(R.string.sector2)).setText(sector2);
        } else {
            layouts.get(context.getString(R.string.sector2)).setVisibility(View.GONE);
        }

        String experience = contact.getExperience();
        if (experience != null && !"null".equals(experience) && !"".equals(experience) && contact.getExperienceVisibility() == 1) {
            views.get(context.getString(R.string.experience)).setText(experience);
        } else {
            layouts.get(context.getString(R.string.experience)).setVisibility(View.GONE);
        }

        String languages = contact.getLanguages();
        if (languages != null && !"null".equals(languages) && !"".equals(languages) && contact.getLanguagesVisibility() == 1) {
            views.get(context.getString(R.string.languages)).setText(languages);
        } else {
            layouts.get(context.getString(R.string.languages)).setVisibility(View.GONE);
        }
        String knowledges = contact.getKnowledges();
        if (knowledges != null && !"null".equals(knowledges) && !"".equals(knowledges) && contact.getKnowledgesVisibility() == 1) {
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
}
