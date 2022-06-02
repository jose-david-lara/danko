package com.wposs.danko.db.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wposs.danko.db.create.DBTables;

import java.util.Map;
import java.util.Objects;

public class DBRepository {

    private DBUtils dbUtils;


    public static final String PASSWORD = "PASSWORD_USER";
    public static final String ACCESS = "ACCESS";
    public static final String NAME = "NAME";
    public static final String LAST_NAME = "LAST_NAME";
    public static final String URL_PHOTO = "URL_PHOTO";
    public static final String JWT_FEED = "JWT_FEED";
    public static final String TYPE_USER = "TYPE_USER";

    public void insertUserInfo(Map<String, Object> userLogin, Context context) throws Exception{
        dbUtils = new DBUtils(context);

        SQLiteDatabase db = dbUtils.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBTables.DANKO_USER.EMAIL, Objects.requireNonNull(userLogin.get("email")).toString());
        values.put(DBTables.DANKO_USER.ACCESS, Objects.requireNonNull(userLogin.get("access")).toString());
        values.put(DBTables.DANKO_USER.NAME, Objects.requireNonNull(userLogin.get("name")).toString());
        values.put(DBTables.DANKO_USER.LAST_NAME, Objects.requireNonNull(userLogin.get("last_name")).toString());
        values.put(DBTables.DANKO_USER.URL_PHOTO, Objects.requireNonNull(userLogin.get("url_photo")).toString());
        values.put(DBTables.DANKO_USER.JWT_FEED, Objects.requireNonNull(userLogin.get("jwt_feed")).toString());
        values.put(DBTables.DANKO_USER.TYPE_USER, Objects.requireNonNull(userLogin.get("type_user")).toString());


        db.insert(DBTables.DANKO_USER.TABLE_NAME, null, values);

        db.close();
    }

}
