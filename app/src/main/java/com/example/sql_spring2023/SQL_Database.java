package com.example.sql_spring2023;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class SQL_Database extends SQLiteOpenHelper {
    int count = 0;

    public SQL_Database(Context appContext){
        super(appContext, "example1.db",null,1);

    }
    @Override
    public void onCreate(SQLiteDatabase database) {
        String query = "CREATE TABLE Table_1 ( First TEXT, Last TEXT, Email TEXT, ID INTEGER PRIMARY KEY, Date TEXT, Phone TEXT, Zip INTEGER)";
        database.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
        String query = "DROP TABLE IF EXISTS Table_1";
        database.execSQL(query);
        onCreate(database);
    }

    public void insertCustomer(HashMap <String, String> queryValues){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("First", queryValues.get("First"));
        values.put("Last", queryValues.get("Last"));
        values.put("Email", queryValues.get("Email"));
        values.put("Date", queryValues.get("Date"));
        values.put("Phone", queryValues.get("Phone"));
        values.put("Zip", queryValues.get("Zip"));

        database.insert("Table_1",null, values);
        database.close();
    }

    public void updateCustomer(HashMap <String, String> queryValues){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("First", queryValues.get("First"));
        values.put("Last", queryValues.get("Last"));
        values.put("Email", queryValues.get("Email"));
        values.put("Date", queryValues.get("Date"));
        values.put("Phone", queryValues.get("Phone"));
        values.put("Zip", queryValues.get("Zip"));

        database.update("Table_1", values, "ID"+"=?",new String[]{queryValues.get("ID")});


    }
    public void deleteCustomer(String ID){
        SQLiteDatabase database = this.getWritableDatabase();
        String deleteQuery = "DELETE FROM Table_1 WHERE ID= " + ID + "";
        database.execSQL(deleteQuery);
    }

    public ArrayList<HashMap<String, String>> getAllCustomers(){
        ArrayList<HashMap<String, String>> customerArrayList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM Table_1 ORDER BY Last";

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                HashMap<String,String> customerMap = new HashMap<>();
                customerMap.put("First", cursor.getString(0));
                customerMap.put("Last", cursor.getString(1));
                customerMap.put("Email", cursor.getString(2));
                customerMap.put("ID", cursor.getString(3));
                customerMap.put("Date", cursor.getString(4));
                customerMap.put("Phone", cursor.getString(5));
                customerMap.put("Zip", cursor.getString(6));

                customerArrayList.add(customerMap);
                count++;
            }while (cursor.moveToNext());

        }
        cursor.close();
        return customerArrayList;
    }

    public HashMap<String,String> getCustomerInfo(String ID){
        HashMap<String,String> customerMap = new HashMap<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM Table_1 WHERE ID =" + ID+ "";
         Cursor cursor = database.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                customerMap.put("First", cursor.getString(0));
                customerMap.put("Last", cursor.getString(1));
                customerMap.put("Email", cursor.getString(2));
                customerMap.put("ID", cursor.getString(3));
                customerMap.put("Date", cursor.getString(4));
                customerMap.put("Phone", cursor.getString(5));
                customerMap.put("Zip", cursor.getString(6));


                count++;
            }while (cursor.moveToNext());

        }
        cursor.close();
        return customerMap;
    }

}


