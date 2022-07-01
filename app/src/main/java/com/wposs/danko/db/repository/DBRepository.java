package com.wposs.danko.db.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wposs.danko.db.create.DBTables;
import com.wposs.danko.parameters.dto.CityDTO;
import com.wposs.danko.parameters.dto.CountryDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DBRepository {

    private DBUtils dbUtils;

    public int isDeleteTable(String nameTable, Context context) {
        dbUtils =  new DBUtils(context);
        SQLiteDatabase db = dbUtils.getWritableDatabase();

        db.delete(nameTable, null, null);
        db.close();

        return 0;
    }


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
        values.put(DBTables.DANKO_USER.STATUS, Objects.requireNonNull(userLogin.get("status")).toString());


        db.insert(DBTables.DANKO_USER.TABLE_NAME, null, values);

        db.close();
    }

    public Map<String, Object> getUserInfo(String email, Context context) {

        Map<String, Object> userInfo = new HashMap<>();
        dbUtils = new DBUtils(context);


        SQLiteDatabase db = dbUtils.getReadableDatabase();

        String[] COLUMNS = {
                DBTables.DANKO_USER.EMAIL,
                DBTables.DANKO_USER.ACCESS,
                DBTables.DANKO_USER.NAME,
                DBTables.DANKO_USER.LAST_NAME,
                DBTables.DANKO_USER.URL_PHOTO,
                DBTables.DANKO_USER.JWT_FEED,
                DBTables.DANKO_USER.TYPE_USER,
                DBTables.DANKO_USER.STATUS

        };


        Cursor cursor =
                db.query(DBTables.DANKO_USER.TABLE_NAME,
                        COLUMNS,
                        " EMAIL = ? and STATUS = ?",
                        new String[]{email,"1"},
                        null,
                        null,
                        null,
                        null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            userInfo.put(DBTables.DANKO_USER.EMAIL,cursor.getString(0));
            userInfo.put(DBTables.DANKO_USER.ACCESS,cursor.getString(1));
            userInfo.put(DBTables.DANKO_USER.NAME,cursor.getString(2));
            userInfo.put(DBTables.DANKO_USER.LAST_NAME,cursor.getString(3));
            userInfo.put(DBTables.DANKO_USER.URL_PHOTO,cursor.getString(4));
            userInfo.put(DBTables.DANKO_USER.JWT_FEED,cursor.getString(5));
            userInfo.put(DBTables.DANKO_USER.TYPE_USER,cursor.getString(6));
            userInfo.put(DBTables.DANKO_USER.STATUS,cursor.getString(7));

            cursor.close();
        }
        db.close();
        return userInfo;
    }

    public int updateUserInfo(Map<String, Object> userLogin, Context context) {

        dbUtils = new DBUtils(context);
        SQLiteDatabase db = dbUtils.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBTables.DANKO_USER.ACCESS, Objects.requireNonNull(userLogin.get("access")).toString());

        int i = db.update(DBTables.DANKO_USER.TABLE_NAME, //table
                values,
                DBTables.DANKO_USER.EMAIL + " = ?",
                new String[]{Objects.requireNonNull(userLogin.get("email")).toString()});

        db.close();
        return i;
    }

    public long insertLocationsInfo(CountryDTO countryDTO, Context context) throws Exception{
        dbUtils = new DBUtils(context);

        SQLiteDatabase db = dbUtils.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBTables.DANKO_COUNTRY.ID, countryDTO.getIdCountry());
        values.put(DBTables.DANKO_COUNTRY.NAME, countryDTO.getName());
        values.put(DBTables.DANKO_COUNTRY.DESCRIPTION, countryDTO.getDescription());
        values.put(DBTables.DANKO_COUNTRY.LATITUD, countryDTO.getLatitud());
        values.put(DBTables.DANKO_COUNTRY.LONGITUD, countryDTO.getLongitud());
        values.put(DBTables.DANKO_COUNTRY.IMAGE, countryDTO.getImage());


        long response = db.insert(DBTables.DANKO_COUNTRY.TABLE_NAME, null, values);

        db.close();

        return response;
    }

    public List<CountryDTO> getLocationsDefault(Context context) {

        List<CountryDTO> countryDTOList = new ArrayList<>();
        dbUtils = new DBUtils(context);

        SQLiteDatabase db = dbUtils.getReadableDatabase();

        String[] COLUMNS = {
                DBTables.DANKO_COUNTRY.ID,
                DBTables.DANKO_COUNTRY.NAME,
                DBTables.DANKO_COUNTRY.DESCRIPTION,
                DBTables.DANKO_COUNTRY.LATITUD,
                DBTables.DANKO_COUNTRY.LONGITUD,
                DBTables.DANKO_COUNTRY.IMAGE

        };


        Cursor cursor =
                db.query(DBTables.DANKO_COUNTRY.TABLE_NAME,
                        COLUMNS,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);

        if (cursor.moveToFirst()) {
            do {
                CountryDTO countryDTO = new CountryDTO();

                countryDTO.setIdCountry(cursor.getString(0));
                countryDTO.setName(cursor.getString(1));
                countryDTO.setDescription(cursor.getString(2));
                countryDTO.setLatitud(cursor.getString(3));
                countryDTO.setLongitud(cursor.getString(4));
                countryDTO.setImage(cursor.getString(5));

                countryDTOList.add(countryDTO);
            }while(cursor.moveToNext());

        }
        cursor.close();
        db.close();
        return countryDTOList;
    }



    public long insertLocationsCitiesInfo(CityDTO cityDTO, Context context) throws Exception{
        dbUtils = new DBUtils(context);

        SQLiteDatabase db = dbUtils.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBTables.DANKO_CITY.ID, cityDTO.getIdCity());
        values.put(DBTables.DANKO_CITY.ID_COUNTRY, cityDTO.getIdCountry());
        values.put(DBTables.DANKO_CITY.NAME, cityDTO.getName());
        values.put(DBTables.DANKO_CITY.DESCRIPTION, cityDTO.getDescription());
        values.put(DBTables.DANKO_CITY.LATITUD, cityDTO.getLatitud());
        values.put(DBTables.DANKO_CITY.LONGITUD, cityDTO.getLongitud());
        values.put(DBTables.DANKO_CITY.IMAGE, cityDTO.getImage());
        values.put(DBTables.DANKO_CITY.TYPE, cityDTO.getType());


        long response = db.insert(DBTables.DANKO_CITY.TABLE_NAME, null, values);

        db.close();

        return response;
    }

    public List<CityDTO> getLocationsCitiesDefault(Context context) {

        List<CityDTO> cityDTOList = new ArrayList<>();
        dbUtils = new DBUtils(context);

        SQLiteDatabase db = dbUtils.getReadableDatabase();

        String[] COLUMNS = {
                DBTables.DANKO_CITY.ID,
                DBTables.DANKO_CITY.ID_COUNTRY,
                DBTables.DANKO_CITY.NAME,
                DBTables.DANKO_CITY.DESCRIPTION,
                DBTables.DANKO_CITY.LATITUD,
                DBTables.DANKO_CITY.LONGITUD,
                DBTables.DANKO_CITY.IMAGE,
                DBTables.DANKO_CITY.TYPE

        };


        Cursor cursor =
                db.query(DBTables.DANKO_CITY.TABLE_NAME,
                        COLUMNS,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);

        if (cursor.moveToFirst()) {
            do {
                CityDTO cityDTO = new CityDTO();

                cityDTO.setIdCity(cursor.getString(0));
                cityDTO.setIdCountry(cursor.getString(1));
                cityDTO.setName(cursor.getString(2));
                cityDTO.setDescription(cursor.getString(3));
                cityDTO.setLatitud(cursor.getString(4));
                cityDTO.setLongitud(cursor.getString(5));
                cityDTO.setImage(cursor.getString(6));

                cityDTOList.add(cityDTO);
            }while(cursor.moveToNext());

        }
        cursor.close();
        db.close();
        return cityDTOList;
    }


}
