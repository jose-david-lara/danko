package com.wposs.danko.db.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.wposs.danko.db.create.DBTables;

public class DBUtils extends SQLiteOpenHelper {


    public DBUtils(Context context) {
        super(context, DBTables.DATABASE_NAME, null, DBTables.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DBTables.DANKO_TABLE_CREATE_DANKO_USER);
        sqLiteDatabase.execSQL(DBTables.DANKO_TABLE_CREATE_DANKO_COUNTRY);
        sqLiteDatabase.execSQL(DBTables.DANKO_TABLE_CREATE_DANKO_CITY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DBTables.DANKO_TABLE_DANKO_USER_DROP);
        sqLiteDatabase.execSQL(DBTables.DANKO_TABLE_DANKO_COUNTRY_DROP);
        sqLiteDatabase.execSQL(DBTables.DANKO_TABLE_DANKO_CITY_DROP);
    }

    public SQLiteDatabase checkDataBase(String Database_path) {
        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase(Database_path, null, SQLiteDatabase.OPEN_READONLY);
            checkDB.close();
            return checkDB;
        } catch (SQLiteException e) {
            Log.e("Error", "No existe la base de datos " + e.getMessage());
            return checkDB;
        }
    }

    public boolean isTableExists(String nameTable) {
        boolean isExist = false;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + nameTable + "'", null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                isExist = true;
            } else {
            }
            cursor.close();
        }
        return isExist;
    }


}
