package com.example.zacharybryant.eagleeyeapp; /**
 * Created by jameslarger on 3/5/17.
 */



import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import java.util.List;
import java.util.ArrayList;
import com.example.zacharybryant.eagleeyeapp.Buildings;


public class DatabaseCommunication extends SQLiteOpenHelper
{

    /*
        Still need to initialize database in MainActivity, but my Studio's Gradle is messing up.
        Will finish off tomorrow during meeting.
     */

    private static final String databaseName = "OverallResources.db";
    private static final String buildingColumn_ID = "buildings";
    private static final String resourceColumn_ID = "resources";
    private static final String foodServices_ID = "food services";


/*
    This method creates and sets up the database
 */
    @Override
    public void onCreate(SQLiteDatabase db)
    {

        /*
            databaseName is obv name of database, ID's are the names of the columns, TEXT indicates that it is a text field

         */
        String createTable = "create Table" + databaseName + "(" + buildingColumn_ID + "TEXT," + resourceColumn_ID + "TEXT,"
                + foodServices_ID + "TEXT," + ")";


        //create table
        db.execSQL(createTable);

        // inserting buildings, resources, and food services
        addBuildings(db);
        addResources(db);
        addFoodServices(db);
    }

    /*
        This method is used in case a greater database version number than the previous one used
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("Delete Table" + db);
        onCreate(db);
    }


    // adding buildings to building category in database
    private void addBuildings(SQLiteDatabase centralDatabase)
    {
        ContentValues buildingValues = new ContentValues();
        /*
            The "id's" in adding to the database are the "categories", or columns, of the database.
            Basically one has to specify which column that it belongs to.
         */
        buildingValues.put(buildingColumn_ID, "Corsair Hall");
        buildingValues.put(buildingColumn_ID, "North Chiller");
        buildingValues.put(buildingColumn_ID, "Residence Hall");
        buildingValues.put(buildingColumn_ID, "Campus Safety");
        buildingValues.put(buildingColumn_ID, "Apollo Hall");
        buildingValues.put(buildingColumn_ID, "Doolittle Hall");
        buildingValues.put(buildingColumn_ID, "Bookstore");
        buildingValues.put(buildingColumn_ID, "Mail Services");
        buildingValues.put(buildingColumn_ID, "Undergraduate Research and Disability Services");
        buildingValues.put(buildingColumn_ID, "Tine Davis Fitness Center");
        buildingValues.put(buildingColumn_ID, "Student Financial Services");
        buildingValues.put(buildingColumn_ID, "Center For Faith and Spirituality");
        buildingValues.put(buildingColumn_ID, "Racquetball Court");
        buildingValues.put(buildingColumn_ID, "Student Support Services and Honors Program");
        buildingValues.put(buildingColumn_ID, "Advanced Flight Sim Center");
        buildingValues.put(buildingColumn_ID, "Fleet Maintenance Hangar");
        buildingValues.put(buildingColumn_ID, "Flight Operations");
        buildingValues.put(buildingColumn_ID, "College of Business");
        buildingValues.put(buildingColumn_ID, "Willie Miller Instructional Center");
        buildingValues.put(buildingColumn_ID, "Aviation Maintenance Sciences");
        buildingValues.put(buildingColumn_ID, "College of Aviation");
        buildingValues.put(buildingColumn_ID, "AMS Hangar");
        buildingValues.put(buildingColumn_ID, "Test Cell I");
        buildingValues.put(buildingColumn_ID, "Engine Test Cells");
        buildingValues.put(buildingColumn_ID, "Canaveral Hall");
        buildingValues.put(buildingColumn_ID, "College of Arts and Sciences");
        buildingValues.put(buildingColumn_ID, "Wellness Center - HDS");
        buildingValues.put(buildingColumn_ID, "ROTC Center");
        buildingValues.put(buildingColumn_ID, "Wellness Center - Counseling");
        buildingValues.put(buildingColumn_ID, "Print Shop");
        buildingValues.put(buildingColumn_ID, "Veteran's Affairs");
        buildingValues.put(buildingColumn_ID, "Eagle Alumni Center");
        buildingValues.put(buildingColumn_ID, "Crotty Tennis Complex");
        buildingValues.put(buildingColumn_ID, "Modular Building - Telescope");
        buildingValues.put(buildingColumn_ID, "Track and Field Concession");
        buildingValues.put(buildingColumn_ID, "Track and Field Bleaches");
        buildingValues.put(buildingColumn_ID, "ROTC Obstacle Course");
        buildingValues.put(buildingColumn_ID, "Multipurpose Artificial Turf Field");
        buildingValues.put(buildingColumn_ID, "Baseball/Softball Clubhouse");
        buildingValues.put(buildingColumn_ID, "JPR Student Center & Admissions Visitor Center");
        buildingValues.put(buildingColumn_ID, "ICI Center");
        buildingValues.put(buildingColumn_ID, "Jim W Henderson Administration and Welcome Center");
        buildingValues.put(buildingColumn_ID, "Sliwa Stadium");
        buildingValues.put(buildingColumn_ID, "Soccer - Ticket Concessions");
        buildingValues.put(buildingColumn_ID, "Soccer Field Bleachers");
        buildingValues.put(buildingColumn_ID, "Student Union (Under Construction)");
        buildingValues.put(buildingColumn_ID, "Lehman College of Engineering");
        buildingValues.put(buildingColumn_ID, "South Chiller");
        buildingValues.put(buildingColumn_ID, "Admissions Operations");
        buildingValues.put(buildingColumn_ID, "Engineering Special Projects and Labs");
        buildingValues.put(buildingColumn_ID, "Clyde Morris Multipurpose Field");
        buildingValues.put(buildingColumn_ID, "Student Village (Adams, Wood, Tallman Commons");
        buildingValues.put(buildingColumn_ID, "Student Village (O'Connor, Stimpson)");
        buildingValues.put(buildingColumn_ID, "Richard Petty Multipurpose West Field");
        buildingValues.put(buildingColumn_ID, "Richard Petty Multipurpose East Field");

        centralDatabase = this.getWritableDatabase();

        centralDatabase.insert(buildingColumn_ID, null, buildingValues);
        centralDatabase.close();
    }
    // adding resources to resource category in database
    private void addResources(SQLiteDatabase centralDatabase)
    {
        ContentValues resourceValues = new ContentValues();

        resourceValues.put(resourceColumn_ID, "Honors Program");
        resourceValues.put(resourceColumn_ID, "Office of Global Engagement");
        resourceValues.put(resourceColumn_ID, "Fraternity and Sorority Life Office");
        resourceValues.put(resourceColumn_ID, "WIKD");
        resourceValues.put(resourceColumn_ID, "The Avion");
        resourceValues.put(resourceColumn_ID, "Orienation and Family Relations");
        resourceValues.put(resourceColumn_ID, "Touch N GO Production");
        resourceValues.put(resourceColumn_ID, "Student Engagement");
        resourceValues.put(resourceColumn_ID, "IGNITE (Office of Undergraduate Research");
        resourceValues.put(resourceColumn_ID, "Campus Saftey and Security");
        resourceValues.put(resourceColumn_ID, "Student Government Association");
        resourceValues.put(resourceColumn_ID, "Student Engagement/Student Union");
        resourceValues.put(resourceColumn_ID, "Financial Aid");
        resourceValues.put(resourceColumn_ID, "Bursar's Office");
        resourceValues.put(resourceColumn_ID, "Student Employment");
        resourceValues.put(resourceColumn_ID, "Mail Center");
        resourceValues.put(resourceColumn_ID, "Housing and Residence Life");
        resourceValues.put(resourceColumn_ID, "Bookstore");
        resourceValues.put(resourceColumn_ID, "Hunt Library");
        resourceValues.put(resourceColumn_ID, "Counseling Center");
        resourceValues.put(resourceColumn_ID, "Dean of Students");
        resourceValues.put(resourceColumn_ID, "Orientation and Family Relations");
        resourceValues.put(resourceColumn_ID, "Office of Records and Registration");
        resourceValues.put(resourceColumn_ID, "EagleCard");
        resourceValues.put(resourceColumn_ID, "Informtation Techonology (IT)");
        resourceValues.put(resourceColumn_ID, "Office of the Registrar");
        resourceValues.put(resourceColumn_ID, "Veteran's Affairs");
        resourceValues.put(resourceColumn_ID, "Admissions");
        resourceValues.put(resourceColumn_ID, "Career Services");
        resourceValues.put(resourceColumn_ID, "Chaplain's Office");
        resourceValues.put(resourceColumn_ID, "Dining Services");
        resourceValues.put(resourceColumn_ID, "ERLI (Embry-Riddle Language Institute");
        resourceValues.put(resourceColumn_ID, "First Year Programs");

        centralDatabase = this.getWritableDatabase();

        centralDatabase.insert(resourceColumn_ID, null, resourceValues);
        centralDatabase.close();
    }
    // adding content to food services category
    private void addFoodServices(SQLiteDatabase centralDatabase)
    {
        ContentValues foodServiceValues = new ContentValues();

        foodServiceValues.put(foodServices_ID, "Einstein Bros Bagels");
        foodServiceValues.put(foodServices_ID, "Freshens Smoothie Company");
        foodServiceValues.put(foodServices_ID, "Chik-fil-A");
        foodServiceValues.put(foodServices_ID, "Pacific Traders");
        foodServiceValues.put(foodServices_ID, "Salsa at the Landing Strip");
        foodServiceValues.put(foodServices_ID, "Flight Cafe");
        foodServiceValues.put(foodServices_ID, "Propeller's Food Truck");
        foodServiceValues.put(foodServices_ID, "UC Food Court");
        foodServiceValues.put(foodServices_ID, "Starbucks");
        foodServiceValues.put(foodServices_ID, "Salad Toss");

        centralDatabase = this.getWritableDatabase();

        centralDatabase.insert(foodServices_ID, null, foodServiceValues);
        centralDatabase.close();

    }


}
