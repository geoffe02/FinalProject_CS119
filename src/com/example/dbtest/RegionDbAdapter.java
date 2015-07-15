package com.example.dbtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class RegionDbAdapter {

	// DO NOT REMOVE THIS ID
	public static final String KEY_ROWID = "_id";
	
	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;

	private static final int DATABASE_VERSION = 1;

	private final Context mCtx;

	// DATABASE NAME
	private static final String DATABASE_NAME = "Classes";
		
	// TABLE NAME GOES HERE
	private static final String SQLITE_TABLE = "Classes";

	// COLUMNS NAMES GO HERE
	public static final String KEY_CODE = "code";
	public static final String KEY_NAME = "name";

	// TABLE CREATION
	private static final String DATABASE_CREATE = 
			
			"CREATE TABLE if not exists "
					+ SQLITE_TABLE + 
					" (" 
					   + KEY_ROWID + " integer PRIMARY KEY autoincrement," 
					   
					   // YOU ADD COLUMNS HERE
								   + KEY_CODE + ","
								   + KEY_NAME +								   
					");";

	
	// UTILITY TABLE HELPER CLASS -- LEAVE THIS ALONE
	
	private static class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		// DATABASE CREATION
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE);
		}

		// DATABASE CHANGE
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE);
			onCreate(db);
		}
	}
	
	
	// LIFE CYCLE
	
	public RegionDbAdapter(Context ctx) {
		this.mCtx = ctx;
	}

	public RegionDbAdapter open() throws SQLException {
		mDbHelper = new DatabaseHelper(mCtx);
		mDb = mDbHelper.getWritableDatabase();
		
		
		return this;
	}

	public void close() {
		if (mDbHelper != null) {
			mDbHelper.close();
		}
	}

	
	
	// ADD NEW STUFF HERE
	
	// ACTIONS
	
	// ADD ROW
	public long createRegion(String code, String name) {

		
		// INSERT
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_CODE, code);
		initialValues.put(KEY_NAME, name);


		// parameters
		// mDb.insert(table, nullColumnHack, values);
		
		return mDb.insert(SQLITE_TABLE, null, initialValues);
	}

	// DELETE ALL
	public boolean deleteAllRegions() {

		// DELETE
		// parameters
		// mDb.delete(table, whereClause, whereArgs)
		
		int doneDelete = 0;
		doneDelete = mDb.delete(SQLITE_TABLE, null, null);
		return doneDelete > 0;

	}

	public boolean deleteRegion(long id) {
		int doneDelete = 0 ;
		doneDelete = mDb.delete(SQLITE_TABLE, KEY_ROWID+" = "+id, null);
		return doneDelete > 0;
	}
	
	public Cursor fetchRegionsByName(String inputText) throws SQLException {
		Cursor mCursor = null;
		if (inputText == null || inputText.length() == 0) {
			
			mCursor = mDb.query(SQLITE_TABLE, 
								new String[] { KEY_ROWID,
					
											   // CHANGE COLUMNS -- May be all or just a few
											   KEY_CODE,
											   KEY_NAME,
											   }, 
											   
								null, null, null, null, null);

		} else {
			mCursor = mDb.query(true, 
								SQLITE_TABLE, 
								new String[] { KEY_ROWID,
					
											   // CHANGE COLUMNS -- May be all or just a few					
											   KEY_CODE,
											   KEY_NAME,
											   },
											   
								// searching similar			   
								KEY_CODE + " like '%" + inputText+ "%'",
								
								// DO NOT CHANGE THIS
								null, null, null, null, null);
		}
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;

	}

	public Cursor fetchAllRegions() {

		// parameter descriptions
		// mDb.query(table, columns, selection, selectionArgs, groupBy, having, orderBy)

		Cursor mCursor = mDb.query(SQLITE_TABLE, new String[] { KEY_ROWID,
				KEY_CODE, KEY_NAME }, null, null, null, null, null);

		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	
	
	// SEEDING
	
	public void seed() {

		// the Context is the Activity where this is currently used
		String[] clientData = mCtx.getResources().getStringArray(R.array.clients);
		
		// get string-array, parse and store
		for (String r : clientData) {
			String[] data = r.split(";");
			createRegion(data[0], data[1]);
		}
	}

}