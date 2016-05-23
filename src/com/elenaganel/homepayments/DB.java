package com.elenaganel.homepayments;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DB {
	private static final String DB_NAME = "mydb";
	private static final int DB_VERSION = 1;
	private static final String DB_TABLE_TARIF = "tabtarif";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_IMG = "img";
	public static final String COLUMN_COUNTER_NAME = "count_name";
	public static final String PERSONAL_ACCOUNT = "l_schet";
	public static final String OWNER_NAME = "owner_name";
	public static final String HOME_ADRESS = "home_adress";
	public static final String E_MAIL = "e_mail";
	public static final String TARIF_NAME_1 = "tarif_name_1";
	public static final String COLUMN_TARIF_1 = "tarif_1";
	public static final String COLUMN_START_STATEMENT_1 = "startstat_1";
	public static final String TARIF_NAME_2 = "tarif_name_2";
	public static final String COLUMN_TARIF_2 = "tarif_2";
	public static final String COLUMN_START_STATEMENT_2 = "startstat_2";
	public static final String TARIF_NAME_3 = "tarif_name_3";
	public static final String COLUMN_TARIF_3 = "tarif_3";
	public static final String COLUMN_START_STATEMENT_3 = "startstat_3";
	public static final String TARIF_NAME_4 = "tarif_name_4";
	public static final String COLUMN_TARIF_4 = "tarif_4";
	public static final String COLUMN_START_STATEMENT_4 = "startstat_4";
	
	private static final String DB_TABLE_STATEMENT = "tabstatement";
	public static final String COLUMN_PARENT_ID = "parent_id";
	public static final String COLUMN_NUMBER_OF_TARIF = "tarif_number";
	public static final String DATA = "data";
	public static final String NAME_1 = "name_1";
	public static final String COLUMN_LAST_STATEMENT_1 = "laststat_1";
	public static final String COLUMN_STATEMENT_1 = "stat_1";
	public static final String COLUMN_RASHOD_1 = "rashod_1";
	public static final String COLUMN_TARIF_STATEMENT_1 = "tarif_stat1";
	public static final String COLUMN_SUMMA_1 = "summa_1";
	public static final String NAME_2 = "name_2";
	public static final String COLUMN_LAST_STATEMENT_2 = "laststat_2";
	public static final String COLUMN_STATEMENT_2 = "stat_2";
	public static final String COLUMN_RASHOD_2 = "rashod_2";
	public static final String COLUMN_TARIF_STATEMENT_2 = "tarif_stat2";
	public static final String COLUMN_SUMMA_2 = "summa_2";
	public static final String NAME_3 = "name_3";
	public static final String COLUMN_LAST_STATEMENT_3 = "laststat_3";
	public static final String COLUMN_STATEMENT_3 = "stat_3";
	public static final String COLUMN_RASHOD_3 = "rashod_3";
	public static final String COLUMN_TARIF_STATEMENT_3 = "tarif_stat3";
	public static final String COLUMN_SUMMA_3 = "summa_3";
	public static final String NAME_4 = "name_4";
	public static final String COLUMN_LAST_STATEMENT_4 = "laststat_4";
	public static final String COLUMN_STATEMENT_4 = "stat_4";
	public static final String COLUMN_RASHOD_4 = "rashod_4";
	public static final String COLUMN_TARIF_STATEMENT_4 = "tarif_stat4";
	public static final String COLUMN_SUMMA_4 = "summa_4";
	public static final String COLUMN_ITOGO = "itogo";
	
	
	
	private static final String DB_CREATE_1 = 
			"create table " + DB_TABLE_TARIF + "(" +
			COLUMN_ID + " integer primary key autoincrement, " +
			COLUMN_IMG + " integer, " +
			COLUMN_COUNTER_NAME + " text, " +
			PERSONAL_ACCOUNT + " text, " +
			OWNER_NAME + " text, " +
			HOME_ADRESS + " text, " +
			E_MAIL + " text, " +
			TARIF_NAME_1 + " text, " +
			COLUMN_TARIF_1 + " text, " +
			COLUMN_START_STATEMENT_1 + " text, " +
			TARIF_NAME_2 + " text, " +
			COLUMN_TARIF_2 + " text, " +
			COLUMN_START_STATEMENT_2 + " text, " +
			TARIF_NAME_3 + " text, " +
			COLUMN_TARIF_3 + " text, " +
			COLUMN_START_STATEMENT_3 + " text, " +
			TARIF_NAME_4 + " text, " +
			COLUMN_TARIF_4 + " text, " +
			COLUMN_START_STATEMENT_4 + " text" +
			");";
	private static final String DB_CREATE_2 =
			"create table " + DB_TABLE_STATEMENT + "(" +
			COLUMN_ID + " integer primary key autoincrement, " +
			COLUMN_PARENT_ID + " text, " +
			COLUMN_NUMBER_OF_TARIF + " text, " +
			DATA + " text, " +
			NAME_1 + " text, " +
			COLUMN_LAST_STATEMENT_1 + " text, " +
			COLUMN_STATEMENT_1 + " text, " +
			COLUMN_RASHOD_1 + " text, " +
			COLUMN_TARIF_STATEMENT_1 + " text, " +
			COLUMN_SUMMA_1 + " text, " +
			NAME_2 + " text, " +
			COLUMN_LAST_STATEMENT_2 + " text, " +
			COLUMN_STATEMENT_2 + " text, " +
			COLUMN_RASHOD_2 + " text, " +
			COLUMN_TARIF_STATEMENT_2 + " text, " +
			COLUMN_SUMMA_2 + " text, " +
			NAME_3 + " text, " +
			COLUMN_LAST_STATEMENT_3 + " text, " +
			COLUMN_STATEMENT_3 + " text, " +
			COLUMN_RASHOD_3 + " text, " +
			COLUMN_TARIF_STATEMENT_3 + " text, " +
			COLUMN_SUMMA_3 + " text, " +
			NAME_4 + " text, " +
			COLUMN_LAST_STATEMENT_4 + " text, " +
			COLUMN_STATEMENT_4 + " text, " +
			COLUMN_RASHOD_4 + " text, " +
			COLUMN_TARIF_STATEMENT_4 + " text, " +
			COLUMN_SUMMA_4 + " text, " +
			COLUMN_ITOGO + " text" +
			");";
	
	private final Context mCtx;
	
	private DBHelper mDBHelper;
	private SQLiteDatabase mDB;
	
	public DB(Context ctx) {
		mCtx = ctx;
	}
	
	// открыть подключение
	public void open() {
		mDBHelper = new DBHelper (mCtx, DB_NAME, null, DB_VERSION);
		mDB = mDBHelper.getWritableDatabase();
	}
	
	// закрыть подключение
	public void close() {
		if (mDBHelper!=null) mDBHelper.close();
	}
	
	// получить данные из таблицы DB_TABLE_TARIF
	public Cursor getAllData() {
		//new String[] {COLUMN_IMG, COLUMN_TXT}
		return mDB.query(DB_TABLE_TARIF, new String[] { COLUMN_ID, COLUMN_IMG, 
				COLUMN_COUNTER_NAME }, null, null, null, null, null);
	}
	
	public Cursor getDataByColumnName (String rowID, String colName) {
		Cursor mCursor =  mDB.query(DB_TABLE_TARIF, new String[] {colName}, 
				COLUMN_ID + "=" + rowID, null, null, null, null);
		return mCursor;
	}
	
	// получить все данные из таблицы DB_TABLE_STATEMENT
	public Cursor getAllDataTable_2() {
		//new String[] { COLUMN_ID, COLUMN_IMG,	COLUMN_COUNTER_NAME }
		return mDB.query(DB_TABLE_STATEMENT, null, null, null, 
				null, null,	null);
	}
	
	//Получить данные из таблицы DB_TABLE_STATEMENT, сгруппировать по parentID,
	//сортировать по _id и включать строки в которых столбец COLUMN_STATEMENT_1 
	//не равен null
	public Cursor getSortingDataInTableWhitoutNull(String id) {
		return mDB.query(DB_TABLE_STATEMENT, null, 
				COLUMN_PARENT_ID + "=" + id + " AND " + COLUMN_STATEMENT_1 + " IS NOT NULL",
				null, null,	null, COLUMN_ID + " DESC");
	}
	
	//Обновить данные Таблицы № 1
	public int updateTable_1 (String id, String name, String ls, String ownerName,
			String adres, String email, 
			String tarif_name_1, String newTarif_1, String startstat_1,
			String tarif_name_2, String newTarif_2, String startstat_2,
			String tarif_name_3, String newTarif_3, String startstat_3,
			String tarif_name_4, String newTarif_4, String startstat_4) {
		ContentValues cv = new ContentValues();
		cv.put(COLUMN_COUNTER_NAME, name);
		cv.put(PERSONAL_ACCOUNT, ls);
		cv.put(OWNER_NAME, ownerName);
		cv.put(HOME_ADRESS, adres);
		cv.put(E_MAIL, email);
		cv.put(TARIF_NAME_1, tarif_name_1);
		cv.put(COLUMN_TARIF_1, newTarif_1);
		cv.put(COLUMN_START_STATEMENT_1, startstat_1);
		cv.put(TARIF_NAME_2, tarif_name_2);
		cv.put(COLUMN_TARIF_2, newTarif_2);
		cv.put(COLUMN_START_STATEMENT_2, startstat_2);
		cv.put(TARIF_NAME_3, tarif_name_3);
		cv.put(COLUMN_TARIF_3, newTarif_3);
		cv.put(COLUMN_START_STATEMENT_3, startstat_3);
		cv.put(TARIF_NAME_4, tarif_name_4);
		cv.put(COLUMN_TARIF_4, newTarif_4);
		cv.put(COLUMN_START_STATEMENT_4, startstat_4);
		return mDB.update(DB_TABLE_TARIF, cv, COLUMN_ID + "=" + id, null);
	}
	
	// получить все данные из таблицы DB_TABLE_STATEMENT, сгруппировать по parentID,
	//сортировать по _id
	public Cursor getSortingDataByIdInTable_2(String where) {
		return mDB.query(DB_TABLE_STATEMENT, null, 
				COLUMN_ID + "=" + where,
				null, null,	null, COLUMN_ID + " DESC");
	}
	
	public Cursor getSortingDataByParentIdInTable_2(String where) {
		return mDB.query(DB_TABLE_STATEMENT, null, 
				COLUMN_PARENT_ID + "=" + where,
				null, null,	null, COLUMN_ID + " DESC");
	}
	
	// возвращает курсор, спозиционированный на указанной записи в Таблице № 1
	public Cursor getDataOnId(long rowId) {
		Cursor mCursor = mDB.query(DB_TABLE_TARIF, null,
	       		COLUMN_ID + "=" + rowId, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	
	/*new String[] {
			COLUMN_ID, COLUMN_COUNTER_NAME, COLUMN_TARIF_1, COLUMN_START_STATEMENT_1,
       		COLUMN_TARIF_2,
       		COLUMN_START_STATEMENT_2,
       		COLUMN_TARIF_3, COLUMN_TARIF_4}*/
	
	/* возвращает курсор, спозиционированный на указанной записи в Таблице № 2
	public Cursor getDataOnIdTable_2(long rowId) {
		Cursor mCursor = mDB.query(DB_TABLE_STATEMENT, new String[] {
				COLUMN_ID, COLUMN_PARENT_ID, COLUMN_LAST_STATEMENT_1, 
	     		COLUMN_STATEMENT_1, COLUMN_RASHOD_1,
	       		COLUMN_TARIF_STATEMENT_1, COLUMN_SUMMA_1,
	       		COLUMN_LAST_STATEMENT_2, COLUMN_STATEMENT_2, COLUMN_RASHOD_2,
	       		COLUMN_TARIF_STATEMENT_2, COLUMN_SUMMA_2 },
	       		COLUMN_ID + "=" + rowId, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}*/
	
	
	
	// добавить запись в DB_TABLE_TARIF
	public long addRecTable_1(int img, String name, String ls, String ownerName,
			String adres,
			String email, String tarif_name_1, String tarif_1, String startstat_1,
			String tarif_name_2, String tarif_2, String startstat_2,
			String tarif_name_3, String tarif_3, String startstat_3,
			String tarif_name_4, String tarif_4, String startstat_4) {
		ContentValues cv = new ContentValues();
		cv.put(COLUMN_IMG, img);
		cv.put(COLUMN_COUNTER_NAME, name);
		cv.put(PERSONAL_ACCOUNT, ls);
		cv.put(OWNER_NAME, ownerName);
		cv.put(HOME_ADRESS, adres);
		cv.put(E_MAIL, email);
		cv.put(TARIF_NAME_1, tarif_name_1);
		cv.put(COLUMN_TARIF_1, tarif_1);
		cv.put(COLUMN_START_STATEMENT_1, startstat_1);
		cv.put(TARIF_NAME_2, tarif_name_2);
		cv.put(COLUMN_TARIF_2, tarif_2);
		cv.put(COLUMN_START_STATEMENT_2, startstat_2);
		cv.put(TARIF_NAME_3, tarif_name_3);
		cv.put(COLUMN_TARIF_3, tarif_3);
		cv.put(COLUMN_START_STATEMENT_3, startstat_3);
		cv.put(TARIF_NAME_4, tarif_name_4);
		cv.put(COLUMN_TARIF_4, tarif_4);
		cv.put(COLUMN_START_STATEMENT_4, startstat_4);
		return mDB.insert(DB_TABLE_TARIF, null, cv);
	}
	
	// добавить запись в DB_TABLE_STATEMENT
	public void addMainRecTable_2(String parent_id, String tarif_number, String data,
			String name_1, String laststat_1, String stat_1, String rashod_1,
			String tarifstat_1,	String summa_1,
			String name_2, String laststat_2, String stat_2, String rashod_2,
			String tarifstat_2,	String summa_2,
			String name_3, String laststat_3, String stat_3, String rashod_3,
			String tarifstat_3,	String summa_3,
			String name_4, String laststat_4, String stat_4, String rashod_4,
			String tarifstat_4, String summa_4,
			String itogo) {
			
		ContentValues cv = new ContentValues();
		cv.put(COLUMN_PARENT_ID, parent_id);
		cv.put(COLUMN_NUMBER_OF_TARIF, tarif_number);
		cv.put(DATA, data);
		cv.put(NAME_1, name_1);
		cv.put(COLUMN_LAST_STATEMENT_1, laststat_1);
		cv.put(COLUMN_STATEMENT_1, stat_1);
		cv.put(COLUMN_RASHOD_1, rashod_1);
		cv.put(COLUMN_TARIF_STATEMENT_1, tarifstat_1);
		cv.put(COLUMN_SUMMA_1, summa_1);
		cv.put(NAME_2, name_2);
		cv.put(COLUMN_LAST_STATEMENT_2, laststat_2);
		cv.put(COLUMN_STATEMENT_2, stat_2);
		cv.put(COLUMN_RASHOD_2, rashod_2);
		cv.put(COLUMN_TARIF_STATEMENT_2, tarifstat_2);
		cv.put(COLUMN_SUMMA_2, summa_2);
		cv.put(NAME_3, name_3);
		cv.put(COLUMN_LAST_STATEMENT_3, laststat_3);
		cv.put(COLUMN_STATEMENT_3, stat_3);
		cv.put(COLUMN_RASHOD_3, rashod_3);
		cv.put(COLUMN_TARIF_STATEMENT_3, tarifstat_3);
		cv.put(COLUMN_SUMMA_3, summa_3);
		cv.put(NAME_4, name_4);
		cv.put(COLUMN_LAST_STATEMENT_4, laststat_4);
		cv.put(COLUMN_STATEMENT_4, stat_4);
		cv.put(COLUMN_RASHOD_4, rashod_4);
		cv.put(COLUMN_TARIF_STATEMENT_4, tarifstat_4);
		cv.put(COLUMN_SUMMA_4, summa_4);
		cv.put(COLUMN_ITOGO, itogo);
		mDB.insert(DB_TABLE_STATEMENT, null, cv);
	}
	
	// удалить запись из DB_TABLE_TARIF
	public void delRec(long id) {
		mDB.delete(DB_TABLE_TARIF, COLUMN_ID + " = " + id, null);
	}
	
	// удалить запись из DB_TABLE_STATEMENT
	public void delRecTable_2(long id) {
		mDB.delete(DB_TABLE_STATEMENT, COLUMN_ID + " = " + id, null);
	}	
	
	// класс по созданию и управлению БД
	private class DBHelper extends SQLiteOpenHelper {
		
		public DBHelper(Context contex, String name, CursorFactory factory, int version) {
			super(contex, name, factory, version);
		}

		// создаем и заполняем БД
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(DB_CREATE_1);
			db.execSQL(DB_CREATE_2);
			
			/* ContentValues cv = new ContentValues();
			for (int i = 1; i < 5; i++) {
				cv.put(COLUMN_TXT, "some text " + i);
				cv.put(COLUMN_IMG, R.drawable.ic_launcher);
				db.insert(DB_TABLE, null, cv);
			} */
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			
		}		
	}
}