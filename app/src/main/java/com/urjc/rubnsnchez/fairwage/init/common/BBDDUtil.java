package com.urjc.rubnsnchez.fairwage.init.common;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import com.urjc.rubnsnchez.fairwage.init.MyApplication;
import com.urjc.rubnsnchez.fairwage.init.contacts.Contact;

public class BBDDUtil {

    /**
     * Establece los values a partir de un contacto para la BBDD local.
     * @param user  Usuario con los datos.
     * @return      ContentValues para la posterior orden de SQLite.
     */
    public static ContentValues createUserBBDDValues(Contact user) {
        ContentValues values = new ContentValues();
        values.put(BBDDHelper.DataUser.COLUMN_USER_USER_1, user.getUser());
        values.put(BBDDHelper.DataUser.COLUMN_USER_PASSWORD_2, user.getPassword());
        values.put(BBDDHelper.DataUser.COLUMN_USER_WAGE_9, user.getWage());
        return values;
    }

    /**
     * Establece los values a partir de un contacto para la BBDD local.
     * @param user  Usuario con los datos.
     * @return      ContentValues para la posterior orden de SQLite.
     */
    public static ContentValues createUpdatedUserBBDDValues(Contact user) {
        ContentValues values = new ContentValues();
        values.put(BBDDHelper.DataUser.COLUMN_USER_ID_0, user.getId());
        values.put(BBDDHelper.DataUser.COLUMN_USER_NAME_3, user.getName());
        values.put(BBDDHelper.DataUser.COLUMN_USER_SURNAMES_4, user.getSurnames());
        values.put(BBDDHelper.DataUser.COLUMN_USER_EMAIL_5, user.getEmail());
        values.put(BBDDHelper.DataUser.COLUMN_USER_TELEPHONE_6, user.getTelephone());
        values.put(BBDDHelper.DataUser.COLUMN_USER_JOB_7, user.getJob());
        values.put(BBDDHelper.DataUser.COLUMN_USER_COMPANY_8, user.getCompany());
        values.put(BBDDHelper.DataUser.COLUMN_USER_WAGE_9, user.getWage());
        values.put(BBDDHelper.DataUser.COLUMN_USER_UNIVERSITY_10, user.getUniversity());
        values.put(BBDDHelper.DataUser.COLUMN_USER_CAREER_11, user.getCareer());
        values.put(BBDDHelper.DataUser.COLUMN_USER_SECTOR_1_12, user.getSector1());
        values.put(BBDDHelper.DataUser.COLUMN_USER_SECTOR_2_13, user.getSector2());
        values.put(BBDDHelper.DataUser.COLUMN_USER_EXPERIENCE_14, user.getExperience());
        values.put(BBDDHelper.DataUser.COLUMN_USER_LANGUAGES_15, user.getLanguages());
        values.put(BBDDHelper.DataUser.COLUMN_USER_NAME_VISIBILITY_17, user.getNameVisibility());
        values.put(BBDDHelper.DataUser.COLUMN_USER_SURNAMES_VISIBILITY_18, user.getSurnamesVisibility());
        values.put(BBDDHelper.DataUser.COLUMN_USER_EMAIL_VISIBILITY_19, user.getEmailVisibility());
        values.put(BBDDHelper.DataUser.COLUMN_USER_TELEPHONE_VISIBILITY_20, user.getTelephoneVisibility());
        values.put(BBDDHelper.DataUser.COLUMN_USER_JOB_VISIBILITY_21, user.getJobVisibility());
        values.put(BBDDHelper.DataUser.COLUMN_USER_COMPANY_VISIBILITY_22, user.getCompanyVisibility());
        values.put(BBDDHelper.DataUser.COLUMN_USER_UNIVERSITY_VISIBILITY_23, user.getUniversityVisibility());
        values.put(BBDDHelper.DataUser.COLUMN_USER_CAREER_VISIBILITY_24, user.getCareerVisibility());
        values.put(BBDDHelper.DataUser.COLUMN_USER_SECTOR_1_VISIBILITY_25, user.getSector1Visibility());
        values.put(BBDDHelper.DataUser.COLUMN_USER_SECTOR_2_VISIBILITY_26, user.getSector2Visibility());
        values.put(BBDDHelper.DataUser.COLUMN_USER_EXPERIENCE_VISIBILITY_27, user.getExperienceVisibility());
        values.put(BBDDHelper.DataUser.COLUMN_USER_LANGUAGES_VISIBILITY_28, user.getLanguagesVisibility());
        values.put(BBDDHelper.DataUser.COLUMN_USER_KNOWLEDGES_VISIBILITY_29, user.getKnowledgesVisibility());
        return values;
    }

    public static long checkLoginUser(Context context, Contact user) {
        BBDDHelper bbddHelper = ((MyApplication) context).getBbddHelper();
        SQLiteDatabase db = bbddHelper.getWritableDatabase();

        return DatabaseUtils.queryNumEntries(db, BBDDHelper.DataUser.TABLE_NAME,
                                            BBDDHelper.DataUser.COLUMN_USER_USER_1 + "=? AND " +
                                                        BBDDHelper.DataUser.COLUMN_USER_PASSWORD_2 + "=?",
                                                        new String[]{user.getUser(), user.getPassword()});
    }

    public static Contact getUserData(Context context, String user) {
        Contact contact;
        String query;
        Cursor c;
        BBDDHelper bbddHelper = ((MyApplication) context).getBbddHelper();
        SQLiteDatabase db = bbddHelper.getWritableDatabase();
        query = "SELECT * FROM " + BBDDHelper.DataUser.TABLE_NAME + " WHERE " + BBDDHelper.DataUser.COLUMN_USER_USER_1  + "=?";
        c = db.rawQuery(query, new String[]{user});
        c.moveToFirst();
        contact = new Contact(c);
        c.close();
        return contact;
    }
}

