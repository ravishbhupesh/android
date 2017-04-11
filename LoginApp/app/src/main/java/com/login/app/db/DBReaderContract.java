package com.login.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by bhupeshr on 4/11/2017.
 */

public final class DBReaderContract extends SQLiteOpenHelper {

    public static final String TAG = "DBReaderContract";

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "LoginAppDB.db";

    public DBReaderContract(Context context) {
        super(context, DATABASE_NAME, new SQLiteCursorFactory(), DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    /* Inner class that defines the table contents */
    public static class DBEntry implements BaseColumns {
        public static final String TABLE_NAME = "USERS";
        public static final String COLUMN_NAME_USERNAME = "USERNAME";
        public static final String COLUMN_NAME_PASSWORD = "PASSWORD";
        public static final String COLUMN_NAME_SOURCE = "SOURCE";
        public static final String COLUMN_NAME_ACTIVE = "ACTIVE";
        public static final String COLUMN_NAME_DATE_OF_REGISTERATION = "DATE_OF_REGISTERATION";
        public static final String COLUMN_NAME_DATE_OF_UNREGISTERATION = "DATE_OF_UNREGISTERATION";
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DBEntry.TABLE_NAME + " (" +
                    DBEntry._ID + " INTEGER PRIMARY KEY," +
                    DBEntry.COLUMN_NAME_USERNAME + " TEXT," +
                    DBEntry.COLUMN_NAME_PASSWORD + " TEXT," +
                    DBEntry.COLUMN_NAME_SOURCE + " TEXT," +
                    DBEntry.COLUMN_NAME_ACTIVE + " TEXT," +
                    DBEntry.COLUMN_NAME_DATE_OF_REGISTERATION + " TEXT," +
                    DBEntry.COLUMN_NAME_DATE_OF_UNREGISTERATION + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DBEntry.TABLE_NAME;
}
