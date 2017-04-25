package com.example.zacharybryant.eagleeyeapp;
/**
 * This class is meant to deal with pre-loading all the buildings, resources, and food services
 * into the database.
 * @author James Larger
 * @version 1.0
 */



import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;



public class DatabaseCommunication extends SQLiteOpenHelper
{

    private static final String databaseName = "OverallResources";
    private static final String buildingColumn_ID = "buildings";
    private static final String resourceColumn_ID = "resources";
    private static final String foodServices_ID = "food_services";

    private static final String CREATE_TABLE_BUILDINGS = "CREATE TABLE BUILDINGS (NAME CHAR(50) PRIMARY KEY, DESCRIPTION CHAR(1500), LAT CHAR(10), LON CHAR(10))";
    private static final String CREATE_TABLE_RESOURCES = "CREATE TABLE RESOURCES (NAME CHAR(50) PRIMARY KEY, DESCRIPTION CHAR(1500), LAT CHAR(10), LON CHAR(10))";
    private static final String CREATE_TABLE_FOOD = "CREATE TABLE FOOD (NAME CHAR(50) PRIMARY KEY, DESCRIPTION CHAR(1500), LAT CHAR(10), LON CHAR(10))";


     /**
     *
     * @param context -- context of the database description
     * @param name -- name of database
     * @param factory -- able to open stream
     * @param version -- version the database is
     */
    public DatabaseCommunication(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


     /**
        This method creates and sets up the database
     */
    @Override
    public void onCreate(SQLiteDatabase db)
    {

        /*
            databaseName is obviously name of database, ID's are the names of the columns, TEXT indicates that it is a text field
            @return nothing
            @param SQLITE Database
         */
//        String createTable = "CREATE TABLE " + databaseName + "(" + buildingColumn_ID + " TEXT," + resourceColumn_ID + " TEXT,"
//                + foodServices_ID + " TEXT);";


        /*
         *  create table
         */
        db.execSQL(CREATE_TABLE_BUILDINGS);
        db.execSQL(CREATE_TABLE_RESOURCES);
        db.execSQL(CREATE_TABLE_FOOD);

        /*
         * inserting buildings, resources, and food services
         */
        addResources(db);
        addBuildings(db);
//        addFoodServices(db);
    }

    /**
        This method is used in case a greater database version number than the previous one used

     */

    /**
     *
     * @param centralDatabase -- database we are checking version
     * @param oldVersion -- old version of database
     * @param newVersion -- next version available
     */
    @Override
    public void onUpgrade(SQLiteDatabase centralDatabase, int oldVersion, int newVersion)
    {


        centralDatabase.execSQL("DROP TABLE IF EXISTS BUILDINGS");
        centralDatabase.execSQL("DROP TABLE IF EXISTS RESOURCES");
        centralDatabase.execSQL("DROP TABLE IF EXISTS FOOD");
        onCreate(centralDatabase);
    }




    public String tableToString(SQLiteDatabase db, String tableName) {
        Log.d("Database","tableToString called");
        String tableString="";
        Cursor allRows  = db.rawQuery("SELECT * FROM " + tableName, null);
        allRows.moveToFirst();
//        tableString += allRows.getString(0);
        //cursorToString(allRows);
        tableString+=allRows.getCount();
        allRows.close();
        return tableString;
    }

    public String cursorToString(Cursor cursor){
        String cursorString = "";
        if (cursor.moveToFirst() ){
            String[] columnNames = cursor.getColumnNames();
            for (String name: columnNames)
                cursorString += String.format("%s ][ ", name);
            cursorString += "\n";
            do {
                for (String name: columnNames) {
                    cursorString += String.format("%s ][ ",
                            cursor.getString(cursor.getColumnIndex(name)));
                }
                cursorString += "\n";
            } while (cursor.moveToNext());
        }
        return cursorString;
    }

    /**
     * @param centralDatabase -- -- central database we are working with to add all buildings on campus.
     *      The "id's" in adding to the database are the "categories", or columns, of the database.
     *      Basically one has to specify which column that it belongs to.
     */
    private void addBuildings(SQLiteDatabase centralDatabase) {
        Log.d("Database", "Adding buildings");

        ContentValues buildingValues = new ContentValues();

        BufferedReader reader = new BufferedReader(new InputStreamReader(MainActivity.context.getResources().openRawResource(R.raw.buildinglist)));
        String buffer;
        try {
            while ((buffer = reader.readLine()) != null) {
                buildingValues.put("NAME", buffer);
                buildingValues.put("DESCRIPTION", reader.readLine());
                buildingValues.put("LAT", reader.readLine());
                buildingValues.put("LON", reader.readLine());
                centralDatabase.insert("BUILDINGS", null, buildingValues);
            }
            reader.close();
        } catch (IOException e) {
            Log.d("Database", "Missing Buildings");
        }
    }


    /**
     *   adding resources to resource category in database
     *   @param centralDatabase -- this is the database that was created above, and passing it
     *                          to add resources
     */
    private void addResources(SQLiteDatabase centralDatabase)
    {
        Log.d("Database","Adding resources");
        //Not adding to database currently
        ContentValues resourcesValues = new ContentValues();

        BufferedReader reader = new BufferedReader(new InputStreamReader(MainActivity.context.getResources().openRawResource(R.raw.resourcelist)));
        String buffer;
        try{
            while ((buffer=reader.readLine())!=null){
                resourcesValues.put("NAME",buffer);
                resourcesValues.put("DESCRIPTION", reader.readLine());
                resourcesValues.put("LAT",reader.readLine());
                resourcesValues.put("LON",reader.readLine());
                centralDatabase.insert("RESOURCES",null,resourcesValues);
            }
            reader.close();
        } catch(IOException e){Log.d("Database","Missing Resources");}
    }



    private void addFoodServices(SQLiteDatabase centralDatabase)
    {

        /**
         *  adding content to food services category for the central database.
         *
         * @param centralDatabase -- passing the database created above that so it can add food services
         * @return nothing
         */

        Log.d("Database","Adding food");

        ContentValues foodValues = new ContentValues();

        BufferedReader reader = new BufferedReader(new InputStreamReader(MainActivity.context.getResources().openRawResource(R.raw.foodlist)));
        String buffer;
        try{
            while ((buffer=reader.readLine())!=null){
                foodValues.put("NAME",buffer);
                foodValues.put("DESCRIPTION", reader.readLine());
                foodValues.put("LAT",reader.readLine());
                foodValues.put("LON",reader.readLine());
                centralDatabase.insert("FOOD",null,foodValues);
            }
            reader.close();
        } catch(IOException e){Log.d("Database","Missing Food");}

    }


}
