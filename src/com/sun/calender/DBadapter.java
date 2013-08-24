package com.sun.calender;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class DBadapter {
    //defining columns(fields) in all 3 tables
    public static final String KEY_REMINDERID = "_id";
   // public static final String KEY_TRANSID = "transId";
    public static final String KEY_TYPE = "r_about";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_DATE= "date"; 
    public static final String KEY_TIME= "time";
    
    private static final String TAG = "DBAdapter";

    private static final String DATABASE_NAME = "reminderdetails";
    private static final String DATABASE_REMINDERTable = "clientstable";
 
    private static final int DATABASE_VERSION = 1;
 //Creating the database reminderdetails
    //CREATING REMINDERTable
    private static final String DATABASE_CREATE_REMINDERTABLE =
        "create table clientstable (_id integer primary key autoincrement, "
        + "name text not null, surname text not null, " 
        + "mobile integer not null);";
 
     private final Context context;  
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;


    public DBadapter(Context ctx) 
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    public class DatabaseHelper extends SQLiteOpenHelper 
    {
        DatabaseHelper(Context context) 
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) 
        {
            db.execSQL(DATABASE_CREATE_REMINDERTABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, 
                              int newVersion) 
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion 
                  + " to "
                  + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS clientstable");
            onCreate(db);
        }
    }    

    //methods for opening and closing the database, as well as the methods for adding/editing/deleting rows in the table.



   //---opens the database---
    public DBadapter open() throws SQLException 
    {
        db = DBHelper.getWritableDatabase();
        return this;

    }

    //---closes the database---    
    public void close() 
    {
        DBHelper.close();
    }

    //---insert a client and his info into the database---

    public long insertReminder(String r_about, String description, String date, String time) 
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_TYPE, r_about);
        initialValues.put(KEY_DESCRIPTION, description);
        initialValues.put(KEY_DATE, date);
        initialValues.put(KEY_TIME, time);
        return db.insert(DATABASE_REMINDERTable, null, initialValues);
    }
 
    public boolean deleteReminder(long Id) 
    {
        return db.delete(DATABASE_REMINDERTable,KEY_REMINDERID + "=" + Id, null) > 0;
    }

    //---retrieves all the clients---
    public Cursor getAllReminder() 
    {
        return db.query(DATABASE_REMINDERTable, new String[] {
        		KEY_REMINDERID, 
                KEY_TYPE,
                KEY_DESCRIPTION,
                KEY_DATE,
                KEY_TIME}, 
                null, 
                null, 
                null, 
                null, 
                null);

    }
    
    //---retrieves a particular client---
    public Cursor getReminder(long Id) throws SQLException 
    {
        Cursor mCursor =
                db.query(true, DATABASE_REMINDERTable, new String[] {
                		KEY_REMINDERID,
                        KEY_TYPE, 
                        KEY_DESCRIPTION,
                        KEY_DATE,
                        KEY_TIME
                        }, 
                        KEY_REMINDERID + "=" + Id, 
                        null,
                        null, 
                        null, 
                        null, 
                        null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //---updates a client's details---
    public boolean updateReminder(long Id, String r_about, String description, String date, String time) 
    {
        ContentValues args = new ContentValues();
        args.put(KEY_TYPE, r_about);
        args.put(KEY_DESCRIPTION, description);
        args.put(KEY_DATE, date);
        args.put(KEY_TIME, time);
        
        return db.update(DATABASE_REMINDERTable, args, 
        		KEY_REMINDERID + "=" + Id, null) > 0;
    }
    
    public SQLiteDatabase getWritableDatabase() {
        // TODO Auto-generated method stub
        return null;
    }
}
