package com.sun.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

@SuppressWarnings("unused")
public class ReminderSQLHelper extends SQLiteOpenHelper {

	
	            final static String TABLE_VSDREMINDER = "VSDReminder";
				final static String KEY_ID = "_id";
                final static String COLUMN_ABOUT_ID = "VSDAboutId";
                final static String COLUMN_REMINDER_DSC = "VSDReminderDescription";
                final static String COLUMN_REMINDER_AT_DATE = "VSDReminderAtDate";
                final static String COLUMN_REMINDER_AT_TIME = "VSDReminderAtTime";
                final static String COLUMN_CYCLE_ID="VSDCycleId";
                final static String COLUMN_STATUS="VSDStatus";
	
                final static String TABLE_VSDABOUT = "VSDAbout";
                final static String COLUMN_ABOUT_DSC="VSDAboutDescription";
              
                
                final static String TABLE_VSDCYCLE="VSDCycle";            
                final static String COLUMN_CYCLE__DSC="VSDCycleDescription";
                
                final static String DATABASE_NAME = "VSDReminder.db";
          	    final static int DATABASE_VERSION = 1;
          	    
          	  final static String SPACE=" ";
               
	
	  // Database creation sql statement
	  private static final String TABLE_CREATE_VSDREMINDER = "create table "
	      + TABLE_VSDREMINDER + "(" + KEY_ID + " integer primary key autoincrement, "
		  + COLUMN_ABOUT_ID   + " integer not null," 
		  + COLUMN_REMINDER_DSC + " text not null,"	
		  + COLUMN_REMINDER_AT_DATE  + " text not null,"
		  + COLUMN_REMINDER_AT_TIME   + " text not null," 
		  + COLUMN_CYCLE_ID + " integer not null,"
		  + COLUMN_STATUS + " integer not null);";
		 
	  private static final String TABLE_CREATE_VSDABOUT   = "create table "
		      + TABLE_VSDABOUT + "(" + KEY_ID + " integer primary key autoincrement, "
			  + COLUMN_ABOUT_ID   + " integer not null," 
			  + COLUMN_ABOUT_DSC + " text not null );";
	  
	  private static final String TABLE_CREATE_VSDCYCLE   = "create table "
		      + TABLE_VSDCYCLE + "(" + KEY_ID + " integer primary key autoincrement, "
			  + COLUMN_CYCLE_ID   + " integer not null," 
			  + COLUMN_CYCLE__DSC + "  text not null );";
	  
	    
	  
	public ReminderSQLHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
	}

	 @Override
	  public void onCreate(SQLiteDatabase database) {
	    database.execSQL(TABLE_CREATE_VSDREMINDER);
	   database.execSQL(TABLE_CREATE_VSDABOUT);
	    database.execSQL(TABLE_CREATE_VSDCYCLE);
	    
	  }

	  @Override
	  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w(ReminderSQLHelper.class.getName(),
	        "Upgrading database from version " + oldVersion + " to "
	            + newVersion + ", which will destroy all old data");
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_CREATE_VSDREMINDER);
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_CREATE_VSDABOUT);
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_CREATE_VSDCYCLE);
	    onCreate(db);
	  }


}
