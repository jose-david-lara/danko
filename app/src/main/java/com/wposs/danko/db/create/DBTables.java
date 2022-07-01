package com.wposs.danko.db.create;

public class DBTables {

    public static final String DATABASE_NAME = "danko";
    public static final int DATABASE_VERSION = 1;


    public static class DANKO_USER {

        public static final String TABLE_NAME = "DANKO_USER";

        public static final String EMAIL = "EMAIL";
        public static final String PASSWORD = "PASSWORD_USER";
        public static final String ACCESS = "ACCESS";
        public static final String NAME = "NAME";
        public static final String LAST_NAME = "LAST_NAME";
        public static final String URL_PHOTO = "URL_PHOTO";
        public static final String JWT_FEED = "JWT_FEED";
        public static final String TYPE_USER = "TYPE_USER";
        public static final String STATUS = "STATUS";
    }

    public static final String DANKO_TABLE_CREATE_DANKO_USER =
            "CREATE TABLE " + DANKO_USER.TABLE_NAME + " (" +
                    DANKO_USER.EMAIL + " TEXT , " +
                    DANKO_USER.PASSWORD + " TEXT, " +
                    DANKO_USER.ACCESS + " TEXT, " +
                    DANKO_USER.NAME + " TEXT," +
                    DANKO_USER.LAST_NAME + " TEXT," +
                    DANKO_USER.URL_PHOTO + " TEXT," +
                    DANKO_USER.JWT_FEED + " TEXT," +
                    DANKO_USER.TYPE_USER + " TEXT," +
                    DANKO_USER.STATUS + " TEXT);";

    public static final String DANKO_TABLE_DANKO_USER_DROP = "DROP TABLE IF EXISTS " + DANKO_USER.TABLE_NAME;



    public static class DANKO_COUNTRY {
        public static final String TABLE_NAME = "DANKO_COUNTRY";

        public static final String ID = "ID";
        public static final String NAME = "NAME";
        public static final String DESCRIPTION = "DESCRIPTION";
        public static final String LATITUD = "LATITUD";
        public static final String LONGITUD = "LONGITUD";
        public static final String IMAGE = "IMAGE";
    }

    public static final String DANKO_TABLE_CREATE_DANKO_COUNTRY =
            "CREATE TABLE " + DANKO_COUNTRY.TABLE_NAME + " (" +
                    DANKO_COUNTRY.ID + " TEXT , " +
                    DANKO_COUNTRY.NAME + " TEXT, " +
                    DANKO_COUNTRY.DESCRIPTION + " TEXT, " +
                    DANKO_COUNTRY.LATITUD + " TEXT," +
                    DANKO_COUNTRY.LONGITUD + " TEXT," +
                    DANKO_COUNTRY.IMAGE + " TEXT);";

    public static final String DANKO_TABLE_DANKO_COUNTRY_DROP = "DROP TABLE IF EXISTS " + DANKO_COUNTRY.TABLE_NAME;


    public static class DANKO_CITY {
        public static final String TABLE_NAME = "DANKO_CITY";

        public static final String ID = "ID";
        public static final String ID_COUNTRY = "ID_COUNTRY";
        public static final String NAME = "NAME";
        public static final String DESCRIPTION = "DESCRIPTION";
        public static final String LATITUD = "LATITUD";
        public static final String LONGITUD = "LONGITUD";
        public static final String IMAGE = "IMAGE";
        public static final String TYPE = "TYPE";
    }

    public static final String DANKO_TABLE_CREATE_DANKO_CITY =
            "CREATE TABLE " + DANKO_CITY.TABLE_NAME + " (" +
                    DANKO_CITY.ID + " TEXT , " +
                    DANKO_CITY.ID_COUNTRY + " TEXT , " +
                    DANKO_CITY.NAME + " TEXT, " +
                    DANKO_CITY.DESCRIPTION + " TEXT, " +
                    DANKO_CITY.LATITUD + " TEXT," +
                    DANKO_CITY.LONGITUD + " TEXT," +
                    DANKO_CITY.IMAGE + " TEXT," +
                    DANKO_CITY.TYPE + " TEXT);";

    public static final String DANKO_TABLE_DANKO_CITY_DROP = "DROP TABLE IF EXISTS " + DANKO_CITY.TABLE_NAME;

}
