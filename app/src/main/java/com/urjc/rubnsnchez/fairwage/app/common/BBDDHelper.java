package com.urjc.rubnsnchez.fairwage.app.common;

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
    private static final int DATABASE_VERSION = 123;
    private static final String DATABASE_NAME = "fairwage.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String BLOB_TYPE = " BLOB";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String DEFAULT_INTEGER = " DEFAULT 0";

    public static class DataUser implements BaseColumns {
        public static final String TABLE_NAME = "usuarios";
        static final String COLUMN_USER_ID_0 ="id";
        public static final String COLUMN_SERVER_USER_ID_1 ="id_Servidor";
        static final String COLUMN_USER_USER_2 = "Usuario";
        static final String COLUMN_USER_PASSWORD_3 = "Contraseña";
        static final String COLUMN_USER_PRIVATE_KEY_4 ="Clave_Privada";
        static final String COLUMN_USER_PUBLIC_KEY_5 ="Clave_Publica";
        static final String COLUMN_USER_PRIVACY_NAME_6 = "PNombre";
        static final String COLUMN_USER_PRIVACY_SURNAMES_7 = "PApellidos";
        static final String COLUMN_USER_PRIVACY_EMAIL_8 = "PEmail";
        static final String COLUMN_USER_PRIVACY_TELEPHONE_9 = "PTelefono";
        static final String COLUMN_USER_PRIVACY_JOB_10 = "PTrabajo";
        static final String COLUMN_USER_PRIVACY_COMPANY_11 = "PEmpresa";
        static final String COLUMN_USER_PRIVACY_UNIVERSITY_12 = "PUniversidad";
        static final String COLUMN_USER_PRIVACY_CAREER_13 = "PCarrera";
        static final String COLUMN_USER_PRIVACY_SECTOR_1_14 = "PSector1";
        static final String COLUMN_USER_PRIVACY_SECTOR_2_15 = "PSector2";
        static final String COLUMN_USER_PRIVACY_EXPERIENCE_16 = "PExperiencia";
        static final String COLUMN_USER_PRIVACY_LANGUAGES_17 = "PLenguajes";
        static final String COLUMN_USER_PRIVACY_KNOWLEDGES_18 = "PConocimientos";

        private static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + DataUser.TABLE_NAME + " (" +
                        DataUser.COLUMN_USER_ID_0 + " INTEGER PRIMARY KEY," +
                        DataUser.COLUMN_SERVER_USER_ID_1 + TEXT_TYPE + COMMA_SEP +
                        DataUser.COLUMN_USER_USER_2 + TEXT_TYPE + COMMA_SEP +
                        DataUser.COLUMN_USER_PASSWORD_3 + TEXT_TYPE + COMMA_SEP +
                        DataUser.COLUMN_USER_PRIVATE_KEY_4 + BLOB_TYPE + COMMA_SEP +
                        DataUser.COLUMN_USER_PUBLIC_KEY_5 + BLOB_TYPE + COMMA_SEP +
                        DataUser.COLUMN_USER_PRIVACY_NAME_6 + INTEGER_TYPE + DEFAULT_INTEGER + COMMA_SEP +
                        DataUser.COLUMN_USER_PRIVACY_SURNAMES_7 + INTEGER_TYPE + DEFAULT_INTEGER + COMMA_SEP +
                        DataUser.COLUMN_USER_PRIVACY_EMAIL_8 + INTEGER_TYPE + DEFAULT_INTEGER + COMMA_SEP +
                        DataUser.COLUMN_USER_PRIVACY_TELEPHONE_9 + INTEGER_TYPE + DEFAULT_INTEGER + COMMA_SEP +
                        DataUser.COLUMN_USER_PRIVACY_JOB_10 + INTEGER_TYPE + DEFAULT_INTEGER + COMMA_SEP +
                        DataUser.COLUMN_USER_PRIVACY_COMPANY_11 + INTEGER_TYPE + DEFAULT_INTEGER + COMMA_SEP +
                        DataUser.COLUMN_USER_PRIVACY_UNIVERSITY_12 + INTEGER_TYPE + DEFAULT_INTEGER + COMMA_SEP +
                        DataUser.COLUMN_USER_PRIVACY_CAREER_13 + INTEGER_TYPE + DEFAULT_INTEGER + COMMA_SEP +
                        DataUser.COLUMN_USER_PRIVACY_SECTOR_1_14 + INTEGER_TYPE + DEFAULT_INTEGER + COMMA_SEP +
                        DataUser.COLUMN_USER_PRIVACY_SECTOR_2_15 + INTEGER_TYPE + DEFAULT_INTEGER + COMMA_SEP +
                        DataUser.COLUMN_USER_PRIVACY_EXPERIENCE_16 + INTEGER_TYPE + DEFAULT_INTEGER + COMMA_SEP +
                        DataUser.COLUMN_USER_PRIVACY_LANGUAGES_17 + INTEGER_TYPE + DEFAULT_INTEGER + COMMA_SEP +
                        DataUser.COLUMN_USER_PRIVACY_KNOWLEDGES_18 + INTEGER_TYPE + DEFAULT_INTEGER + " )";

        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + DataUser.TABLE_NAME;
    }

    public static class DataContact implements BaseColumns {
        public static final String TABLE_NAME = "contactos";
        static final String COLUMN_CONTACT_ID_0 ="id";
        public static final String COLUMN_CONTACT_SERVER_ID_1 ="id_Servidor";
        static final String COLUMN_CONTACT_PUBLIC_KEY_2 ="Clave_Puplica";
        static final String COLUMN_CONTACT_NAME_3 = "Nombre";
        static final String COLUMN_CONTACT_SURNAMES_4 = "Apellidos";
        static final String COLUMN_CONTACT_EMAIL_5 = "Email";
        static final String COLUMN_CONTACT_TELEPHONE_6 = "Telefono";
        static final String COLUMN_CONTACT_JOB_7 = "Trabajo";
        static final String COLUMN_CONTACT_COMPANY_8 = "Empresa";
        static final String COLUMN_CONTACT_WAGE_9 = "Sueldo";
        static final String COLUMN_CONTACT_UNIVERSITY_10 = "Universidad";
        static final String COLUMN_CONTACT_CAREER_11 = "Carrera";
        static final String COLUMN_CONTACT_SECTOR_1_12 = "Sector1";
        static final String COLUMN_CONTACT_SECTOR_2_13 = "Sector2";
        static final String COLUMN_CONTACT_EXPERIENCE_14 = "Experiencia";
        static final String COLUMN_CONTACT_LANGUAGES_15 = "Lenguajes";
        static final String COLUMN_CONTACT_KNOWLEDGES_16 = "Conocimientos";
        static final String COLUMN_CONTACT_STATE_17 = "Estado";

        private static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + DataContact.TABLE_NAME + " (" +
                        DataContact.COLUMN_CONTACT_ID_0 + " INTEGER PRIMARY KEY," +
                        DataContact.COLUMN_CONTACT_SERVER_ID_1 + INTEGER_TYPE + " UNIQUE" + COMMA_SEP +
                        DataContact.COLUMN_CONTACT_PUBLIC_KEY_2 + TEXT_TYPE + COMMA_SEP +
                        DataContact.COLUMN_CONTACT_NAME_3 + BLOB_TYPE + COMMA_SEP +
                        DataContact.COLUMN_CONTACT_SURNAMES_4 + BLOB_TYPE + COMMA_SEP +
                        DataContact.COLUMN_CONTACT_EMAIL_5 + BLOB_TYPE + COMMA_SEP +
                        DataContact.COLUMN_CONTACT_TELEPHONE_6 + BLOB_TYPE + COMMA_SEP +
                        DataContact.COLUMN_CONTACT_JOB_7 + BLOB_TYPE + COMMA_SEP +
                        DataContact.COLUMN_CONTACT_COMPANY_8 + BLOB_TYPE + COMMA_SEP +
                        DataContact.COLUMN_CONTACT_WAGE_9 + TEXT_TYPE + COMMA_SEP +
                        DataContact.COLUMN_CONTACT_UNIVERSITY_10 + BLOB_TYPE + COMMA_SEP +
                        DataContact.COLUMN_CONTACT_CAREER_11 + BLOB_TYPE + COMMA_SEP +
                        DataContact.COLUMN_CONTACT_SECTOR_1_12 + BLOB_TYPE + COMMA_SEP +
                        DataContact.COLUMN_CONTACT_SECTOR_2_13 + BLOB_TYPE + COMMA_SEP +
                        DataContact.COLUMN_CONTACT_EXPERIENCE_14 + BLOB_TYPE + COMMA_SEP +
                        DataContact.COLUMN_CONTACT_LANGUAGES_15 + BLOB_TYPE + COMMA_SEP +
                        DataContact.COLUMN_CONTACT_KNOWLEDGES_16 + BLOB_TYPE + COMMA_SEP +
                        DataContact.COLUMN_CONTACT_STATE_17 + BLOB_TYPE + " )";

        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + DataContact.TABLE_NAME;
    }

    public static class DataSearchContact implements BaseColumns {
        public static final String TABLE_NAME = "contactos_buscados";
        static final String COLUMN_CONTACT_ID_0 ="id";
        static final String COLUMN_CONTACT_USER_1 ="Usuario";
        static final String COLUMN_CONTACT_PUBLIC_KEY_2 ="Clave_Puplica";
        static final String COLUMN_CONTACT_NAME_3 = "Nombre";
        static final String COLUMN_CONTACT_SURNAMES_4 = "Apellidos";
        static final String COLUMN_CONTACT_EMAIL_5 = "Email";
        static final String COLUMN_CONTACT_TELEPHONE_6 = "Telefono";
        static final String COLUMN_CONTACT_JOB_7 = "Trabajo";
        static final String COLUMN_CONTACT_COMPANY_8 = "Empresa";
        static final String COLUMN_CONTACT_WAGE_9 = "Sueldo";
        static final String COLUMN_CONTACT_UNIVERSITY_10 = "Universidad";
        static final String COLUMN_CONTACT_CAREER_11 = "Carrera";
        static final String COLUMN_CONTACT_SECTOR_1_12 = "Sector1";
        static final String COLUMN_CONTACT_SECTOR_2_13 = "Sector2";
        static final String COLUMN_CONTACT_EXPERIENCE_14 = "Experiencia";
        static final String COLUMN_CONTACT_LANGUAGES_15 = "Lenguajes";
        static final String COLUMN_CONTACT_KNOWLEDGES_16 = "Conocimientos";
        static final String COLUMN_CONTACT_STATE_17 = "Estado";

        static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + DataSearchContact.TABLE_NAME + " (" +
                        DataSearchContact.COLUMN_CONTACT_ID_0 + " INTEGER PRIMARY KEY," +
                        DataSearchContact.COLUMN_CONTACT_USER_1 + TEXT_TYPE + COMMA_SEP +
                        DataSearchContact.COLUMN_CONTACT_PUBLIC_KEY_2 + BLOB_TYPE + COMMA_SEP +
                        DataSearchContact.COLUMN_CONTACT_NAME_3 + TEXT_TYPE + COMMA_SEP +
                        DataSearchContact.COLUMN_CONTACT_SURNAMES_4 + TEXT_TYPE + COMMA_SEP +
                        DataSearchContact.COLUMN_CONTACT_EMAIL_5 + TEXT_TYPE + COMMA_SEP +
                        DataSearchContact.COLUMN_CONTACT_TELEPHONE_6 + TEXT_TYPE + COMMA_SEP +
                        DataSearchContact.COLUMN_CONTACT_JOB_7 + TEXT_TYPE + COMMA_SEP +
                        DataSearchContact.COLUMN_CONTACT_COMPANY_8 + TEXT_TYPE + COMMA_SEP +
                        DataSearchContact.COLUMN_CONTACT_WAGE_9 + TEXT_TYPE + COMMA_SEP +
                        DataSearchContact.COLUMN_CONTACT_UNIVERSITY_10 + TEXT_TYPE + COMMA_SEP +
                        DataSearchContact.COLUMN_CONTACT_CAREER_11 + TEXT_TYPE + COMMA_SEP +
                        DataSearchContact.COLUMN_CONTACT_SECTOR_1_12 + TEXT_TYPE + COMMA_SEP +
                        DataSearchContact.COLUMN_CONTACT_SECTOR_2_13 + TEXT_TYPE + COMMA_SEP +
                        DataSearchContact.COLUMN_CONTACT_EXPERIENCE_14 + TEXT_TYPE + COMMA_SEP +
                        DataSearchContact.COLUMN_CONTACT_LANGUAGES_15 + TEXT_TYPE + COMMA_SEP +
                        DataSearchContact.COLUMN_CONTACT_KNOWLEDGES_16 + TEXT_TYPE + COMMA_SEP +
                        DataSearchContact.COLUMN_CONTACT_STATE_17 + TEXT_TYPE + " )";

        static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + DataSearchContact.TABLE_NAME;
    }

    public static class DataSearchNegotation implements BaseColumns {
        public static final String TABLE_NAME = "negociaciones";
        static final String COLUMN_NEGOTATION_ID_0 ="id";
        static final String COLUMN_NEGOTATION_SERVER_ID_1 ="id_Servidor";
        static final String COLUMN_USER_CREATOR_2 ="Usuario_Creador";
        static final String COLUMN_USER_RECEPTOR_3 ="Usuario_Receptor";
        static final String COLUMN_STATE_4 ="Estado";
        static final String COLUMN_OFFER_NAME_5 = "Ofrecido_Nombre";
        static final String COLUMN_OFFER_SURNAMES_6 = "Ofrecido_Apellidos";
        static final String COLUMN_OFFER_EMAIL_7 = "Ofrecido_Email";
        static final String COLUMN_OFFER_TELEPHONE_8 = "Ofrecido_Telefono";
        static final String COLUMN_OFFER_JOB_9 = "Ofrecido_Trabajo";
        static final String COLUMN_OFFER_COMPANY_10 = "Ofrecido_Empresa";
        static final String COLUMN_OFFER_WAGE_11 = "Ofrecido_Sueldo";
        static final String COLUMN_OFFER_UNIVERSITY_12 = "Ofrecido_Universidad";
        static final String COLUMN_OFFER_CAREER_13 = "Ofrecido_Carrera";
        static final String COLUMN_OFFER_SECTOR_1_14 = "Ofrecido_Sector1";
        static final String COLUMN_OFFER_SECTOR_2_15 = "Ofrecido_Sector2";
        static final String COLUMN_OFFER_EXPERIENCE_16 = "Ofrecido_Experiencia";
        static final String COLUMN_OFFER_LANGUAGES_17 = "Ofrecido_Lenguajes";
        static final String COLUMN_OFFER_KNOWLEDGES_18 = "Ofrecido_Conocimientos";
        static final String COLUMN_REQUIRE_NAME_19 = "Requerido_Nombre";
        static final String COLUMN_REQUIRE_SURNAMES_20 = "Requerido_Apellidos";
        static final String COLUMN_REQUIRE_EMAIL_21 = "Requerido_Email";
        static final String COLUMN_REQUIRE_TELEPHONE_22 = "Requerido_Telefono";
        static final String COLUMN_REQUIRE_JOB_23 = "Requerido_Trabajo";
        static final String COLUMN_REQUIRE_COMPANY_24 = "Requerido_Empresa";
        static final String COLUMN_REQUIRE_WAGE_25 = "Requerido_Sueldo";
        static final String COLUMN_REQUIRE_UNIVERSITY_26 = "Requerido_Universidad";
        static final String COLUMN_REQUIRE_CAREER_27 = "Requerido_Carrera";
        static final String COLUMN_REQUIRE_SECTOR_1_28 = "Requerido_Sector1";
        static final String COLUMN_REQUIRE_SECTOR_2_29 = "Requerido_Sector2";
        static final String COLUMN_REQUIRE_EXPERIENCE_30 = "Requerido_Experiencia";
        static final String COLUMN_REQUIRE_LANGUAGES_31 = "Requerido_Lenguajes";
        static final String COLUMN_REQUIRE_KNOWLEDGES_32 = "Requerido_Conocimientos";

        static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + DataSearchNegotation.TABLE_NAME + " (" +
                        DataSearchNegotation.COLUMN_NEGOTATION_ID_0 + " INTEGER PRIMARY KEY," +
                        DataSearchNegotation.COLUMN_NEGOTATION_SERVER_ID_1 + INTEGER_TYPE + " UNIQUE" + COMMA_SEP +
                        DataSearchNegotation.COLUMN_USER_CREATOR_2 + TEXT_TYPE + COMMA_SEP +
                        DataSearchNegotation.COLUMN_USER_RECEPTOR_3 + TEXT_TYPE + COMMA_SEP +
                        DataSearchNegotation.COLUMN_STATE_4 + TEXT_TYPE + COMMA_SEP +
                        DataSearchNegotation.COLUMN_OFFER_NAME_5 + TEXT_TYPE + COMMA_SEP +
                        DataSearchNegotation.COLUMN_OFFER_SURNAMES_6 + TEXT_TYPE + COMMA_SEP +
                        DataSearchNegotation.COLUMN_OFFER_EMAIL_7 + TEXT_TYPE + COMMA_SEP +
                        DataSearchNegotation.COLUMN_OFFER_TELEPHONE_8 + TEXT_TYPE + COMMA_SEP +
                        DataSearchNegotation.COLUMN_OFFER_JOB_9 + TEXT_TYPE + COMMA_SEP +
                        DataSearchNegotation.COLUMN_OFFER_COMPANY_10 + TEXT_TYPE + COMMA_SEP +
                        DataSearchNegotation.COLUMN_OFFER_WAGE_11 + TEXT_TYPE + COMMA_SEP +
                        DataSearchNegotation.COLUMN_OFFER_UNIVERSITY_12 + TEXT_TYPE + COMMA_SEP +
                        DataSearchNegotation.COLUMN_OFFER_CAREER_13 + TEXT_TYPE + COMMA_SEP +
                        DataSearchNegotation.COLUMN_OFFER_SECTOR_1_14 + TEXT_TYPE + COMMA_SEP +
                        DataSearchNegotation.COLUMN_OFFER_SECTOR_2_15 + TEXT_TYPE + COMMA_SEP +
                        DataSearchNegotation.COLUMN_OFFER_EXPERIENCE_16 + TEXT_TYPE + COMMA_SEP +
                        DataSearchNegotation.COLUMN_OFFER_LANGUAGES_17 + TEXT_TYPE + COMMA_SEP +
                        DataSearchNegotation.COLUMN_OFFER_KNOWLEDGES_18 + TEXT_TYPE + COMMA_SEP +
                        DataSearchNegotation.COLUMN_REQUIRE_NAME_19 + TEXT_TYPE + COMMA_SEP +
                        DataSearchNegotation.COLUMN_REQUIRE_SURNAMES_20 + TEXT_TYPE + COMMA_SEP +
                        DataSearchNegotation.COLUMN_REQUIRE_EMAIL_21 + TEXT_TYPE + COMMA_SEP +
                        DataSearchNegotation.COLUMN_REQUIRE_TELEPHONE_22 + TEXT_TYPE + COMMA_SEP +
                        DataSearchNegotation.COLUMN_REQUIRE_JOB_23 + TEXT_TYPE + COMMA_SEP +
                        DataSearchNegotation.COLUMN_REQUIRE_COMPANY_24 + TEXT_TYPE + COMMA_SEP +
                        DataSearchNegotation.COLUMN_REQUIRE_WAGE_25+ TEXT_TYPE + COMMA_SEP +
                        DataSearchNegotation.COLUMN_REQUIRE_UNIVERSITY_26 + TEXT_TYPE + COMMA_SEP +
                        DataSearchNegotation.COLUMN_REQUIRE_CAREER_27 + TEXT_TYPE + COMMA_SEP +
                        DataSearchNegotation.COLUMN_REQUIRE_SECTOR_1_28 + TEXT_TYPE + COMMA_SEP +
                        DataSearchNegotation.COLUMN_REQUIRE_SECTOR_2_29 + TEXT_TYPE + COMMA_SEP +
                        DataSearchNegotation.COLUMN_REQUIRE_EXPERIENCE_30 + TEXT_TYPE + COMMA_SEP +
                        DataSearchNegotation.COLUMN_REQUIRE_LANGUAGES_31 + TEXT_TYPE + COMMA_SEP +
                        DataSearchNegotation.COLUMN_REQUIRE_KNOWLEDGES_32 + TEXT_TYPE + " )";

        static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + DataSearchNegotation.TABLE_NAME;
    }

    public static class DataState implements BaseColumns {
        public static final String TABLE_NAME = "estados";
        static final String COLUMN_STATE_ID_0 ="id";
        static final String COLUMN_NAME_1 ="Nombre";

        private static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + DataState.TABLE_NAME + " (" +
                        DataState.COLUMN_STATE_ID_0 + " INTEGER PRIMARY KEY," +
                        DataState.COLUMN_NAME_1 + " )";

        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + DataState.TABLE_NAME;
    }

    public static class DataRelationContactUser implements BaseColumns {
        public static final String TABLE_NAME = "contacto_usuario";
        static final String COLUMN_ID_0 ="id";
        static final String COLUMN_USER_ID_1 ="id_Usuario";
        static final String COLUMN_CONTACT_ID_1 ="id_Contacto";

        private static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + DataRelationContactUser.TABLE_NAME + " (" +
                        DataRelationContactUser.COLUMN_ID_0 + " INTEGER PRIMARY KEY," +
                        DataRelationContactUser.COLUMN_USER_ID_1 + INTEGER_TYPE + COMMA_SEP +
                        DataRelationContactUser.COLUMN_CONTACT_ID_1 + " )";

        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + DataRelationContactUser.TABLE_NAME;
    }

    public BBDDHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataUser.SQL_CREATE_ENTRIES);
        db.execSQL(DataContact.SQL_CREATE_ENTRIES);
        db.execSQL(DataSearchContact.SQL_CREATE_ENTRIES);
        db.execSQL(DataSearchNegotation.SQL_CREATE_ENTRIES);
        db.execSQL(DataState.SQL_CREATE_ENTRIES);
        db.execSQL(DataRelationContactUser.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion) {
            db.execSQL(DataUser.SQL_DELETE_ENTRIES);
            db.execSQL(DataContact.SQL_DELETE_ENTRIES);
            db.execSQL(DataSearchContact.SQL_DELETE_ENTRIES);
            db.execSQL(DataSearchNegotation.SQL_DELETE_ENTRIES);
            db.execSQL(DataState.SQL_DELETE_ENTRIES);
            db.execSQL(DataRelationContactUser.SQL_DELETE_ENTRIES);
            onCreate(db);
        }
    }
}
