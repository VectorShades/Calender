package com.sun.dbhelper;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ReminderDataSource {

  // Database fields
  private SQLiteDatabase database;
  private ReminderSQLHelper dbHelper;
  private String[] allColumns = { ReminderSQLHelper.KEY_ID,
		  ReminderSQLHelper.COLUMN_ABOUT_ID ,
		  ReminderSQLHelper.COLUMN_REMINDER_DSC,
		  ReminderSQLHelper.COLUMN_REMINDER_AT_DATE,
		  ReminderSQLHelper.COLUMN_REMINDER_AT_TIME,
		  ReminderSQLHelper.COLUMN_CYCLE_ID,
		  ReminderSQLHelper.COLUMN_STATUS};
  
  private String[] allColumns_vsdAbout = { ReminderSQLHelper.KEY_ID,
		  ReminderSQLHelper.COLUMN_ABOUT_ID ,
		  ReminderSQLHelper.COLUMN_ABOUT_DSC};

  public ReminderDataSource(Context context) {
    dbHelper = new ReminderSQLHelper(context);
  }

  public void open() throws SQLException {
    database = dbHelper.getReadableDatabase();
  }

  public void close() {
    dbHelper.close();
  }

  public void createReminder(ReminderDAO reminder) {
	  
    ContentValues values = new ContentValues();
    values.put(ReminderSQLHelper.COLUMN_ABOUT_ID, reminder.getAboutId());
    values.put(ReminderSQLHelper.COLUMN_REMINDER_DSC, reminder.getReminderDescription());
    values.put(ReminderSQLHelper.COLUMN_REMINDER_AT_DATE, reminder.getReminderAtDate());
    values.put(ReminderSQLHelper.COLUMN_REMINDER_AT_TIME, reminder.getReminderAtTime());
    values.put(ReminderSQLHelper.COLUMN_CYCLE_ID, reminder.getCycleId());
    values.put(ReminderSQLHelper.COLUMN_STATUS, reminder.getStatus());
    
    @SuppressWarnings("unused")
	long insertId = database.insert(ReminderSQLHelper.TABLE_VSDREMINDER, null, values);
  }

  public List<ReminderDAO> getAllActiveReminders() {
    List<ReminderDAO> list_reminders = new ArrayList<ReminderDAO>();

 // Specify the where clause that will limit our results.
    String where = ReminderSQLHelper.COLUMN_STATUS + "=" + 1;
    
    Cursor cursor = database.query(ReminderSQLHelper.TABLE_VSDREMINDER,
        allColumns, where, null, null, null, null);

    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      ReminderDAO reminder = cursorToReminder(cursor);
      
      list_reminders.add(reminder);
      cursor.moveToNext();
    }
    // Make sure to close the cursor
    cursor.close();
    return list_reminders;
  }

  private ReminderDAO cursorToReminder(Cursor cursor) {
    ReminderDAO reminder = new ReminderDAO();
    reminder.setReminderDescription(cursor.getString(0));
    reminder.setReminderId(cursor.getLong(0));
    reminder.setAboutId(cursor.getInt(1));
    reminder.setReminderDescription(cursor.getString(2));
    reminder.setReminderAtDate(cursor.getString(3));
    reminder.setReminderAtTime(cursor.getString(4));
    reminder.setCycleId(cursor.getLong(5));
    reminder.setStatus(cursor.getInt(6));
    
    return reminder;
  }
  
  public List<AboutDAO> getAbouts() {
	    List<AboutDAO> list_Abouts = new ArrayList<AboutDAO>();

	 // Specify the where clause that will limit our results.
	    //String where = ReminderSQLHelper.COLUMN_STATUS + "=" + 1;
	    
	    Cursor cursor = database.query(ReminderSQLHelper.TABLE_VSDABOUT,
	        allColumns_vsdAbout, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	    	AboutDAO about = cursorToAbouts(cursor);
	    	list_Abouts.add(about);
	      cursor.moveToNext();
	    }
	    // Make sure to close the cursor
	    cursor.close();
	    return list_Abouts;
	  }

  private AboutDAO cursorToAbouts(Cursor cursor) {
		  AboutDAO about = new AboutDAO();
		  
		  about.setAboutId(cursor.getInt(0));
		  about.setAboutDescription(cursor.getString(2));
		   
		  return about;
	  }
  
  public void PopulateAboutsTable()
  {
	    ContentValues values = new ContentValues();
	    values.put(ReminderSQLHelper.COLUMN_ABOUT_ID, 1);
	    values.put(ReminderSQLHelper.COLUMN_ABOUT_DSC, "Birthday");
	    database.insert(ReminderSQLHelper.TABLE_VSDABOUT, null, values);
	    values.put(ReminderSQLHelper.COLUMN_ABOUT_ID, 2);
	    values.put(ReminderSQLHelper.COLUMN_ABOUT_DSC, "Anniversary");
	    database.insert(ReminderSQLHelper.TABLE_VSDABOUT, null, values);
	    values.put(ReminderSQLHelper.COLUMN_ABOUT_ID, 3);
	    values.put(ReminderSQLHelper.COLUMN_ABOUT_DSC, "Events");
	    database.insert(ReminderSQLHelper.TABLE_VSDABOUT, null, values);
	    values.put(ReminderSQLHelper.COLUMN_ABOUT_ID, 4);
	    values.put(ReminderSQLHelper.COLUMN_ABOUT_DSC, "Appointment");
	  database.insert(ReminderSQLHelper.TABLE_VSDABOUT, null, values);
	   
  
  }
  
  public void PopulateCycleTable()
  {
	    ContentValues values = new ContentValues();
	    values.put(ReminderSQLHelper.COLUMN_CYCLE_ID, 1);
	    values.put(ReminderSQLHelper.COLUMN_CYCLE__DSC, "Daily");
	    database.insert(ReminderSQLHelper.TABLE_VSDCYCLE, null, values);
	    values.put(ReminderSQLHelper.COLUMN_CYCLE_ID, 2);
	    values.put(ReminderSQLHelper.COLUMN_CYCLE__DSC, "Weekly");
	    database.insert(ReminderSQLHelper.TABLE_VSDCYCLE, null, values);
	    values.put(ReminderSQLHelper.COLUMN_CYCLE_ID, 3);
	    values.put(ReminderSQLHelper.COLUMN_CYCLE__DSC, "MonthLy");
	    database.insert(ReminderSQLHelper.TABLE_VSDCYCLE, null, values);
	    values.put(ReminderSQLHelper.COLUMN_CYCLE_ID, 4);
	    values.put(ReminderSQLHelper.COLUMN_CYCLE__DSC, "Yearly");
	    database.insert(ReminderSQLHelper.TABLE_VSDCYCLE, null, values);
	   
  }

  public  List<ReminderDAO> getWeeksReminder()
  {
	  List<ReminderDAO> list_Weekreminder = new  ArrayList<ReminderDAO>();
	  
	  //sString sql1 = "select * from" +  ReminderSQLHelper.SPACE +  ReminderSQLHelper.TABLE_VSDREMINDER + "where" + ReminderSQLHelper.SPACE + ReminderSQLHelper.COLUMN_STATUS  ;
	  String sql1 = "select * from VSDReminder WHERE VSDReminderAtDate BETWEEN date('now', 'Weekday 1', '-7 days') AND date('now')"; 
	  				
	  Cursor cursor = database.rawQuery(sql1, null);
	 
		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		    	ReminderDAO todayreminder = cursorToReminder(cursor);
		    	list_Weekreminder.add(todayreminder);
		      cursor.moveToNext();
		    }
		    // Make sure to close the cursor
		    cursor.close();
		    return list_Weekreminder;
  }
  
  
  public  List<ReminderDAO> getTodayReminder()

    {
	  List<ReminderDAO> list_todayreminder = new  ArrayList<ReminderDAO>();
	  	 
	  String sql1 = "select * from VSDReminder where VSDStatus = 1  and VSDReminderAtDATE = date('now')"; 
	  	
	  String sql11 = "SELECT date('now')";
	  Cursor cursor = database.rawQuery(sql1, null);
	 
		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		    	ReminderDAO todayreminder = cursorToReminder(cursor);
		    	list_todayreminder.add(todayreminder);
		      cursor.moveToNext();
		    }
		    // Make sure to close the cursor
		    cursor.close();
		    return list_todayreminder;
  }
  
  public  List<ReminderDAO> getMonthReminder()
  {
   List<ReminderDAO> list_monthreminder = new  ArrayList<ReminderDAO>();
     
   String sql1 = "select * from VSDReminder WHERE VSDReminderAtDate BETWEEN date('now','start of month') AND date('now','start of month','+1 month','-1 day')"; 
    
   
   Cursor cursor = database.rawQuery(sql1, null);
  
      cursor.moveToFirst();
      while (!cursor.isAfterLast()) {
       ReminderDAO todayreminder = cursorToReminder(cursor);
       list_monthreminder.add(todayreminder);
        cursor.moveToNext();
      }
      // Make sure to close the cursor
      cursor.close();
      return list_monthreminder;
}
} 
