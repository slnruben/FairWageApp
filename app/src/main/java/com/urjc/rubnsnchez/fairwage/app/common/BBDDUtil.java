package com.urjc.rubnsnchez.fairwage.app.common;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Base64;
import android.util.Log;
import com.urjc.rubnsnchez.fairwage.R;
import com.urjc.rubnsnchez.fairwage.app.MyApplication;
import com.urjc.rubnsnchez.fairwage.app.contacts.Contact;
import org.json.JSONArray;
import org.json.JSONObject;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.ALGORITHM;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.ERROR;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.SUCCESS;

public class BBDDUtil {

    /**
     * Establece los values a partir de un contacto para la BBDD local.
     * @param user  Usuario con los datos.
     * @return      ContentValues para la posterior orden de SQLite.
     */
    public static ContentValues createUserBBDDValues(Contact user) {
        ContentValues values = new ContentValues();
        values.put(BBDDHelper.DataUser.COLUMN_SERVER_USER_ID_1, user.getIdServer());
        values.put(BBDDHelper.DataUser.COLUMN_USER_USER_2, user.getUser());
        values.put(BBDDHelper.DataUser.COLUMN_USER_PASSWORD_3, user.getPassword());
        values.put(BBDDHelper.DataUser.COLUMN_USER_PRIVATE_KEY_4, user.getPrivateKey());
        values.put(BBDDHelper.DataUser.COLUMN_USER_PUBLIC_KEY_5, user.getPublicKey());
        return values;
    }

    public static ContentValues createContactBBDDValues(Contact user) {
        ContentValues values = new ContentValues();
        values.put(BBDDHelper.DataContact.COLUMN_CONTACT_SERVER_ID_1, user.getIdServer());
        values.put(BBDDHelper.DataContact.COLUMN_CONTACT_WAGE_8, user.getWage());
        return values;
    }

    /**
     * Establece los values de privacidad a partir de un contacto para la BBDD local.
     * @param user  Usuario con los datos.
     * @return      ContentValues para la posterior orden de SQLite.
     */
    public static ContentValues createUpdatedUserBBDDValues(Contact user) {
        ContentValues values = new ContentValues();
        values.put(BBDDHelper.DataUser.COLUMN_USER_PRIVACY_NAME_6, user.getNameVisibility());
        values.put(BBDDHelper.DataUser.COLUMN_USER_PRIVACY_SURNAMES_7, user.getSurnamesVisibility());
        values.put(BBDDHelper.DataUser.COLUMN_USER_PRIVACY_EMAIL_8, user.getEmailVisibility());
        values.put(BBDDHelper.DataUser.COLUMN_USER_PRIVACY_TELEPHONE_9, user.getTelephoneVisibility());
        values.put(BBDDHelper.DataUser.COLUMN_USER_PRIVACY_JOB_10, user.getJobVisibility());
        values.put(BBDDHelper.DataUser.COLUMN_USER_PRIVACY_COMPANY_11, user.getCompanyVisibility());
        values.put(BBDDHelper.DataUser.COLUMN_USER_PRIVACY_UNIVERSITY_12, user.getUniversityVisibility());
        values.put(BBDDHelper.DataUser.COLUMN_USER_PRIVACY_CAREER_13, user.getCareerVisibility());
        values.put(BBDDHelper.DataUser.COLUMN_USER_PRIVACY_SECTOR_1_14, user.getSector1Visibility());
        values.put(BBDDHelper.DataUser.COLUMN_USER_PRIVACY_SECTOR_2_15, user.getSector2Visibility());
        values.put(BBDDHelper.DataUser.COLUMN_USER_PRIVACY_EXPERIENCE_16, user.getExperienceVisibility());
        values.put(BBDDHelper.DataUser.COLUMN_USER_PRIVACY_LANGUAGES_17, user.getLanguagesVisibility());
        values.put(BBDDHelper.DataUser.COLUMN_USER_PRIVACY_KNOWLEDGES_18, user.getKnowledgesVisibility());
        return values;
    }

    /**
     * Establece los values a partir de un contacto para la BBDD local.
     * @param user  Usuario con los datos.
     * @return      ContentValues para la posterior orden de SQLite.
     */
    public static ContentValues createUpdatedContactBBDDValues(PublicKey publicKey, Contact user) {
        ContentValues values = new ContentValues();
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            values.put(BBDDHelper.DataContact.COLUMN_CONTACT_NAME_2, cipher.doFinal(user.getName().getBytes()));
            values.put(BBDDHelper.DataContact.COLUMN_CONTACT_SURNAMES_3, cipher.doFinal(user.getSurnames().getBytes()));
            values.put(BBDDHelper.DataContact.COLUMN_CONTACT_EMAIL_4, cipher.doFinal(user.getEmail().getBytes()));
            values.put(BBDDHelper.DataContact.COLUMN_CONTACT_TELEPHONE_5, cipher.doFinal(user.getTelephone().getBytes()));
            values.put(BBDDHelper.DataContact.COLUMN_CONTACT_JOB_6, cipher.doFinal(user.getJob().getBytes()));
            values.put(BBDDHelper.DataContact.COLUMN_CONTACT_COMPANY_7, cipher.doFinal(user.getCompany().getBytes()));
            values.put(BBDDHelper.DataContact.COLUMN_CONTACT_WAGE_8, user.getWage());
            values.put(BBDDHelper.DataContact.COLUMN_CONTACT_UNIVERSITY_9, cipher.doFinal(user.getUniversity().getBytes()));
            values.put(BBDDHelper.DataContact.COLUMN_CONTACT_CAREER_10, cipher.doFinal(user.getCareer().getBytes()));
            values.put(BBDDHelper.DataContact.COLUMN_CONTACT_SECTOR_1_11, cipher.doFinal(user.getSector1().getBytes()));
            values.put(BBDDHelper.DataContact.COLUMN_CONTACT_SECTOR_2_12, cipher.doFinal(user.getSector2().getBytes()));
            values.put(BBDDHelper.DataContact.COLUMN_CONTACT_EXPERIENCE_13, cipher.doFinal(user.getExperience().getBytes()));
            values.put(BBDDHelper.DataContact.COLUMN_CONTACT_LANGUAGES_14, cipher.doFinal(user.getLanguages().getBytes()));
            values.put(BBDDHelper.DataContact.COLUMN_CONTACT_KNOWLEDGES_15, cipher.doFinal(user.getKnowledges().getBytes()));
        } catch (Exception e) {
            Log.e("error", "ERROR DE CIFRADO " + e);
        }

        return values;
    }

    public static Contact getUserData(Context context, String user, int state) {
        Contact contact;
        String query;
        Cursor c;
        BBDDHelper bbddHelper = ((MyApplication) context).getBbddHelper();
        SQLiteDatabase db = bbddHelper.getWritableDatabase();
        PrivateKey privateKey = ((MyApplication) context).getPrivateKey();
        query = "SELECT * FROM " + BBDDHelper.DataContact.TABLE_NAME + " INNER JOIN " + BBDDHelper.DataUser.TABLE_NAME +
                " ON " + BBDDHelper.DataUser.TABLE_NAME + "." + BBDDHelper.DataUser.COLUMN_SERVER_USER_ID_1 + "="
                + BBDDHelper.DataContact.TABLE_NAME + "." + BBDDHelper.DataContact.COLUMN_CONTACT_SERVER_ID_1 + " WHERE "
                + BBDDHelper.DataUser.COLUMN_USER_USER_2  + "=?";
        c = db.rawQuery(query, new String[]{user});
        c.moveToFirst();
        contact = new Contact(c, state, privateKey);
        c.close();
        return contact;
    }

    public static int tableRowCount(final Context context, String table) {
        int numRows = 0;
        BBDDHelper bbddHelper = ((MyApplication) context).getBbddHelper();
        SQLiteDatabase db = bbddHelper.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT count(*) FROM " + table, null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            numRows = c.getInt(0);
        }
        c.close();
        return numRows;
    }

    static String createSearchContactBBDD(Context context, JSONArray json) {
        String success = String.valueOf(SUCCESS);
        BBDDHelper localHelper = ((MyApplication) context).getBbddHelper();
        SQLiteDatabase db = localHelper.getWritableDatabase();
        db.execSQL(BBDDHelper.DataSearchContact.SQL_DELETE_ENTRIES);
        db.execSQL(BBDDHelper.DataSearchContact.SQL_CREATE_ENTRIES);
        ContentValues values;
        for (int i = 0; i < json.length(); i++) {
            values = new ContentValues();
            try {
                JSONObject row = json.getJSONObject(i);
                values.put(BBDDHelper.DataSearchContact.COLUMN_CONTACT_USER_1,
                        (row.has(context.getString(R.string.user))) ? row.getString(context.getString(R.string.user)) : "");
                if (row.has(context.getString(R.string.publicKey))) {
                    byte[] byteArray = Base64.decode(row.getString(context.getString(R.string.publicKey)), Base64.NO_WRAP);
                    values.put(BBDDHelper.DataSearchContact.COLUMN_CONTACT_PUBLIC_KEY_2, byteArray);
                }
                values.put(BBDDHelper.DataSearchContact.COLUMN_CONTACT_NAME_3,
                        (row.has(context.getString(R.string.name))) ? row.getString(context.getString(R.string.name)) : "");
                values.put(BBDDHelper.DataSearchContact.COLUMN_CONTACT_SURNAMES_4,
                        (row.has(context.getString(R.string.surnames))) ? row.getString(context.getString(R.string.surnames)) : "");
                values.put(BBDDHelper.DataSearchContact.COLUMN_CONTACT_EMAIL_5,
                        (row.has(context.getString(R.string.email))) ? row.getString(context.getString(R.string.email)) : "");
                values.put(BBDDHelper.DataSearchContact.COLUMN_CONTACT_TELEPHONE_6,
                        (row.has(context.getString(R.string.telephone))) ? row.getString(context.getString(R.string.telephone)) : "");
                values.put(BBDDHelper.DataSearchContact.COLUMN_CONTACT_JOB_7,
                        (row.has(context.getString(R.string.job))) ? row.getString(context.getString(R.string.job)) : "");
                values.put(BBDDHelper.DataSearchContact.COLUMN_CONTACT_COMPANY_8,
                        (row.has(context.getString(R.string.company))) ? row.getString(context.getString(R.string.company)) : "");
                values.put(BBDDHelper.DataSearchContact.COLUMN_CONTACT_WAGE_9, row.getString(context.getString(R.string.wage)));
                values.put(BBDDHelper.DataSearchContact.COLUMN_CONTACT_UNIVERSITY_10,
                        (row.has(context.getString(R.string.university))) ? row.getString(context.getString(R.string.university)) : "");
                values.put(BBDDHelper.DataSearchContact.COLUMN_CONTACT_CAREER_11,
                        (row.has(context.getString(R.string.career))) ? row.getString(context.getString(R.string.career)) : "");
                values.put(BBDDHelper.DataSearchContact.COLUMN_CONTACT_SECTOR_1_12,
                        (row.has(context.getString(R.string.sector1))) ? row.getString(context.getString(R.string.sector1)) : "");
                values.put(BBDDHelper.DataSearchContact.COLUMN_CONTACT_SECTOR_2_13,
                        (row.has(context.getString(R.string.sector2))) ? row.getString(context.getString(R.string.sector2)) : "");
                values.put(BBDDHelper.DataSearchContact.COLUMN_CONTACT_EXPERIENCE_14,
                        (row.has(context.getString(R.string.experience))) ? row.getString(context.getString(R.string.experience)) : "");
                values.put(BBDDHelper.DataSearchContact.COLUMN_CONTACT_LANGUAGES_15,
                        (row.has(context.getString(R.string.languages))) ? row.getString(context.getString(R.string.languages)) : "");
                values.put(BBDDHelper.DataSearchContact.COLUMN_CONTACT_KNOWLEDGES_16,
                        (row.has(context.getString(R.string.knowledges))) ? row.getString(context.getString(R.string.knowledges)) : "");
                db.insertOrThrow(BBDDHelper.DataSearchContact.TABLE_NAME, null, values);
            } catch (Exception e) {
                Log.e("error", "Exception: " + e);
                success = String.valueOf(ERROR);
            }
        }
        return success;
    }

    static String createSearchNegotiationBBDD(Context context, JSONArray json) {
        String success = String.valueOf(SUCCESS);
        BBDDHelper localHelper = ((MyApplication) context).getBbddHelper();
        SQLiteDatabase db = localHelper.getWritableDatabase();
        db.execSQL(BBDDHelper.DataSearchNegotation.SQL_DELETE_ENTRIES);
        db.execSQL(BBDDHelper.DataSearchNegotation.SQL_CREATE_ENTRIES);
        ContentValues values;
        for (int i = 0; i < json.length(); i++) {
            values = new ContentValues();
            try {
                JSONObject row = json.getJSONObject(i);
                values.put(BBDDHelper.DataSearchNegotation.COLUMN_NEGOTATION_SERVER_ID_1,
                                                            row.getString(context.getString(R.string.id)));
                values.put(BBDDHelper.DataSearchNegotation.COLUMN_USER_CREATOR_2,
                                                            row.getString(context.getString(R.string.userCreator)));
                values.put(BBDDHelper.DataSearchNegotation.COLUMN_USER_RECEPTOR_3,
                                                            row.getString(context.getString(R.string.userReceptor)));
                values.put(BBDDHelper.DataSearchNegotation.COLUMN_STATE_4,
                                                            row.getString(context.getString(R.string.state)));
                values.put(BBDDHelper.DataSearchNegotation.COLUMN_OFFER_NAME_5,
                        (row.has(context.getString(R.string.oName))) ? row.getString(context.getString(R.string.oName)) : "");
                values.put(BBDDHelper.DataSearchNegotation.COLUMN_OFFER_SURNAMES_6,
                        (row.has(context.getString(R.string.oSurnames))) ? row.getString(context.getString(R.string.oSurnames)) : "");
                values.put(BBDDHelper.DataSearchNegotation.COLUMN_OFFER_EMAIL_7,
                        (row.has(context.getString(R.string.oEmail))) ? row.getString(context.getString(R.string.oEmail)) : "");
                values.put(BBDDHelper.DataSearchNegotation.COLUMN_OFFER_TELEPHONE_8,
                        (row.has(context.getString(R.string.oTelephone))) ? row.getString(context.getString(R.string.oTelephone)) : "");
                values.put(BBDDHelper.DataSearchNegotation.COLUMN_OFFER_JOB_9,
                        (row.has(context.getString(R.string.oJob))) ? row.getString(context.getString(R.string.oJob)) : "");
                values.put(BBDDHelper.DataSearchNegotation.COLUMN_OFFER_COMPANY_10,
                        (row.has(context.getString(R.string.oCompany))) ? row.getString(context.getString(R.string.oCompany)) : "");
                values.put(BBDDHelper.DataSearchNegotation.COLUMN_OFFER_WAGE_11, row.getString(context.getString(R.string.oWage)));
                values.put(BBDDHelper.DataSearchNegotation.COLUMN_OFFER_UNIVERSITY_12,
                        (row.has(context.getString(R.string.oUniversity))) ? row.getString(context.getString(R.string.oUniversity)) : "");
                values.put(BBDDHelper.DataSearchNegotation.COLUMN_OFFER_CAREER_13,
                        (row.has(context.getString(R.string.oCareer))) ? row.getString(context.getString(R.string.oCareer)) : "");
                values.put(BBDDHelper.DataSearchNegotation.COLUMN_OFFER_SECTOR_1_14,
                        (row.has(context.getString(R.string.oSector1))) ? row.getString(context.getString(R.string.oSector1)) : "");
                values.put(BBDDHelper.DataSearchNegotation.COLUMN_OFFER_SECTOR_2_15,
                        (row.has(context.getString(R.string.oSector2))) ? row.getString(context.getString(R.string.oSector2)) : "");
                values.put(BBDDHelper.DataSearchNegotation.COLUMN_OFFER_EXPERIENCE_16,
                        (row.has(context.getString(R.string.oExperience))) ? row.getString(context.getString(R.string.oExperience)) : "");
                values.put(BBDDHelper.DataSearchNegotation.COLUMN_OFFER_LANGUAGES_17,
                        (row.has(context.getString(R.string.oLanguages))) ? row.getString(context.getString(R.string.oLanguages)) : "");
                values.put(BBDDHelper.DataSearchNegotation.COLUMN_OFFER_KNOWLEDGES_18,
                        (row.has(context.getString(R.string.oKnowledges))) ? row.getString(context.getString(R.string.oKnowledges)) : "");

                values.put(BBDDHelper.DataSearchNegotation.COLUMN_REQUIRE_NAME_19,
                        (row.has(context.getString(R.string.rName))) ? row.getString(context.getString(R.string.rName)) : "");
                values.put(BBDDHelper.DataSearchNegotation.COLUMN_REQUIRE_SURNAMES_20,
                        (row.has(context.getString(R.string.rSurnames))) ? row.getString(context.getString(R.string.rSurnames)) : "");
                values.put(BBDDHelper.DataSearchNegotation.COLUMN_REQUIRE_EMAIL_21,
                        (row.has(context.getString(R.string.rEmail))) ? row.getString(context.getString(R.string.rEmail)) : "");
                values.put(BBDDHelper.DataSearchNegotation.COLUMN_REQUIRE_TELEPHONE_22,
                        (row.has(context.getString(R.string.rTelephone))) ? row.getString(context.getString(R.string.rTelephone)) : "");
                values.put(BBDDHelper.DataSearchNegotation.COLUMN_REQUIRE_JOB_23,
                        (row.has(context.getString(R.string.rJob))) ? row.getString(context.getString(R.string.rJob)) : "");
                values.put(BBDDHelper.DataSearchNegotation.COLUMN_REQUIRE_COMPANY_24,
                        (row.has(context.getString(R.string.rCompany))) ? row.getString(context.getString(R.string.rCompany)) : "");
                values.put(BBDDHelper.DataSearchNegotation.COLUMN_REQUIRE_WAGE_25, row.getString(context.getString(R.string.rWage)));
                values.put(BBDDHelper.DataSearchNegotation.COLUMN_REQUIRE_UNIVERSITY_26,
                        (row.has(context.getString(R.string.rUniversity))) ? row.getString(context.getString(R.string.rUniversity)) : "");
                values.put(BBDDHelper.DataSearchNegotation.COLUMN_REQUIRE_CAREER_27,
                        (row.has(context.getString(R.string.rCareer))) ? row.getString(context.getString(R.string.rCareer)) : "");
                values.put(BBDDHelper.DataSearchNegotation.COLUMN_REQUIRE_SECTOR_1_28,
                        (row.has(context.getString(R.string.rSector1))) ? row.getString(context.getString(R.string.rSector1)) : "");
                values.put(BBDDHelper.DataSearchNegotation.COLUMN_REQUIRE_SECTOR_2_29,
                        (row.has(context.getString(R.string.rSector2))) ? row.getString(context.getString(R.string.rSector2)) : "");
                values.put(BBDDHelper.DataSearchNegotation.COLUMN_REQUIRE_EXPERIENCE_30,
                        (row.has(context.getString(R.string.rExperience))) ? row.getString(context.getString(R.string.rExperience)) : "");
                values.put(BBDDHelper.DataSearchNegotation.COLUMN_REQUIRE_LANGUAGES_31,
                        (row.has(context.getString(R.string.rLanguages))) ? row.getString(context.getString(R.string.rLanguages)) : "");
                values.put(BBDDHelper.DataSearchNegotation.COLUMN_REQUIRE_KNOWLEDGES_32,
                        (row.has(context.getString(R.string.rKnowledges))) ? row.getString(context.getString(R.string.rKnowledges)) : "");
                db.insertOrThrow(BBDDHelper.DataSearchNegotation.TABLE_NAME, null, values);
            } catch (Exception e) {
                Log.e("error", "Exception: " + e);
                success = String.valueOf(ERROR);
            }
        }
        return success;
    }
}

