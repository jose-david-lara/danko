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
                    DANKO_USER.TYPE_USER + " TEXT);";

    public static final String DANKO_TABLE_DANKO_USER_DROP = "DROP TABLE IF EXISTS " + DANKO_USER.TABLE_NAME;

}
