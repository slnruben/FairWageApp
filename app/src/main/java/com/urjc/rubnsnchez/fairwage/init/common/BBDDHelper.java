package com.urjc.rubnsnchez.fairwage.init.common;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * A helper class to manage database creation and version management.
 * @author Rubén Sánchez Rivero
 * @version 1
 */
public class BBDDHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 7;
    private static final String DATABASE_NAME = "fairwage.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String DEFAULT_INTEGER = " DEFAULT 0";

    public static class DataUser implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_USER_ID_0 ="id";
        static final String COLUMN_USER_USER_1 = "Usuario";
        static final String COLUMN_USER_PASSWORD_2 = "Contraseña";
        static final String COLUMN_USER_NAME_3 = "Nombre";
        static final String COLUMN_USER_SURNAMES_4 = "Apellidos";
        static final String COLUMN_USER_EMAIL_5 = "Email";
        static final String COLUMN_USER_TELEPHONE_6 = "Teléfono";
        static final String COLUMN_USER_JOB_7 = "Trabajo";
        static final String COLUMN_USER_COMPANY_8 = "Empresa";
        static final String COLUMN_USER_WAGE_9 = "Sueldo";
        static final String COLUMN_USER_UNIVERSITY_10 = "Universidad";
        static final String COLUMN_USER_CAREER_11 = "Carrera";
        static final String COLUMN_USER_SECTOR_1_12 = "Sector1";
        static final String COLUMN_USER_SECTOR_2_13 = "Sector2";
        static final String COLUMN_USER_EXPERIENCE_14 = "Experiencia";
        static final String COLUMN_USER_LANGUAGES_15 = "Lenguajes";
        static final String COLUMN_USER_KNOWLEDGES_16 = "Conocimientos";
        static final String COLUMN_USER_NAME_VISIBILITY_17 = "Visibilidad_Nombre";
        static final String COLUMN_USER_SURNAMES_VISIBILITY_18 = "Visibilidad_Apellidos";
        static final String COLUMN_USER_EMAIL_VISIBILITY_19 = "Visibilidad_Email";
        static final String COLUMN_USER_TELEPHONE_VISIBILITY_20 = "Visibilidad_Teléfono";
        static final String COLUMN_USER_JOB_VISIBILITY_21 = "Visibilidad_Trabajo";
        static final String COLUMN_USER_COMPANY_VISIBILITY_22 = "Visibilidad_Empresa";
        static final String COLUMN_USER_UNIVERSITY_VISIBILITY_23 = "Visibilidad_Universidad";
        static final String COLUMN_USER_CAREER_VISIBILITY_24 = "Visibilidad_Carrera";
        static final String COLUMN_USER_SECTOR_1_VISIBILITY_25 = "Visibilidad_Sector1";
        static final String COLUMN_USER_SECTOR_2_VISIBILITY_26 = "Visibilidad_Sector2";
        static final String COLUMN_USER_EXPERIENCE_VISIBILITY_27 = "Visibilidad_Experiencia";
        static final String COLUMN_USER_LANGUAGES_VISIBILITY_28 = "Visibilidad_Lenguajes";
        static final String COLUMN_USER_KNOWLEDGES_VISIBILITY_29 = "Visibilidad_Conocimientos";

        private static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + DataUser.TABLE_NAME + " (" +
                        DataUser.COLUMN_USER_ID_0 + " INTEGER PRIMARY KEY," +
                        DataUser.COLUMN_USER_USER_1 + TEXT_TYPE + COMMA_SEP +
                        DataUser.COLUMN_USER_PASSWORD_2 + TEXT_TYPE + COMMA_SEP +
                        DataUser.COLUMN_USER_NAME_3 + TEXT_TYPE + COMMA_SEP +
                        DataUser.COLUMN_USER_SURNAMES_4 + TEXT_TYPE + COMMA_SEP +
                        DataUser.COLUMN_USER_EMAIL_5 + TEXT_TYPE + COMMA_SEP +
                        DataUser.COLUMN_USER_TELEPHONE_6 + TEXT_TYPE + COMMA_SEP +
                        DataUser.COLUMN_USER_JOB_7 + TEXT_TYPE + COMMA_SEP +
                        DataUser.COLUMN_USER_COMPANY_8 + TEXT_TYPE + COMMA_SEP +
                        DataUser.COLUMN_USER_WAGE_9 + TEXT_TYPE + COMMA_SEP +
                        DataUser.COLUMN_USER_UNIVERSITY_10 + TEXT_TYPE + COMMA_SEP +
                        DataUser.COLUMN_USER_CAREER_11 + TEXT_TYPE + COMMA_SEP +
                        DataUser.COLUMN_USER_SECTOR_1_12 + TEXT_TYPE + COMMA_SEP +
                        DataUser.COLUMN_USER_SECTOR_2_13 + TEXT_TYPE + COMMA_SEP +
                        DataUser.COLUMN_USER_EXPERIENCE_14 + TEXT_TYPE + COMMA_SEP +
                        DataUser.COLUMN_USER_LANGUAGES_15 + TEXT_TYPE + COMMA_SEP +
                        DataUser.COLUMN_USER_KNOWLEDGES_16 + TEXT_TYPE + COMMA_SEP +
                        DataUser.COLUMN_USER_NAME_VISIBILITY_17 + INTEGER_TYPE + DEFAULT_INTEGER + COMMA_SEP +
                        DataUser.COLUMN_USER_SURNAMES_VISIBILITY_18 + INTEGER_TYPE + DEFAULT_INTEGER + COMMA_SEP +
                        DataUser.COLUMN_USER_EMAIL_VISIBILITY_19 + INTEGER_TYPE + DEFAULT_INTEGER + COMMA_SEP +
                        DataUser.COLUMN_USER_TELEPHONE_VISIBILITY_20 + INTEGER_TYPE + DEFAULT_INTEGER + COMMA_SEP +
                        DataUser.COLUMN_USER_JOB_VISIBILITY_21+ INTEGER_TYPE + DEFAULT_INTEGER + COMMA_SEP +
                        DataUser.COLUMN_USER_COMPANY_VISIBILITY_22 + INTEGER_TYPE + DEFAULT_INTEGER + COMMA_SEP +
                        DataUser.COLUMN_USER_UNIVERSITY_VISIBILITY_23 + INTEGER_TYPE + DEFAULT_INTEGER + COMMA_SEP +
                        DataUser.COLUMN_USER_CAREER_VISIBILITY_24 + INTEGER_TYPE + DEFAULT_INTEGER + COMMA_SEP +
                        DataUser.COLUMN_USER_SECTOR_1_VISIBILITY_25 + INTEGER_TYPE + DEFAULT_INTEGER + COMMA_SEP +
                        DataUser.COLUMN_USER_SECTOR_2_VISIBILITY_26 + INTEGER_TYPE + DEFAULT_INTEGER + COMMA_SEP +
                        DataUser.COLUMN_USER_EXPERIENCE_VISIBILITY_27 + INTEGER_TYPE + DEFAULT_INTEGER + COMMA_SEP +
                        DataUser.COLUMN_USER_LANGUAGES_VISIBILITY_28 + INTEGER_TYPE + DEFAULT_INTEGER + COMMA_SEP +
                        DataUser.COLUMN_USER_KNOWLEDGES_VISIBILITY_29 + INTEGER_TYPE + DEFAULT_INTEGER + " )";

        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + DataUser.TABLE_NAME;
    }

    public BBDDHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataUser.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion) {
            db.execSQL(DataUser.SQL_DELETE_ENTRIES);
            onCreate(db);
        }
    }
}
