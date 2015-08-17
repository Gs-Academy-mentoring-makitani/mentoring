package com.example.takenogaku.lib;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

//import com.goodluck.R;

public class PersonOpenHelper extends SQLiteOpenHelper {

	final static private int DB_VERSION = 1;
	
	private final static String DB_NAME_ASSET = Environment.getExternalStorageDirectory() + "/gsacademy.db";

//    adb pull /sdcard/gsacademy.db
//    private static String DB_NAME = "gsacademy";
	
	private final Context mContext;
//	private final File mDatabasePath;
	
	public PersonOpenHelper(Context context) {
		super(context, DB_NAME_ASSET, null, DB_VERSION);
		
		mContext = context;  
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO 自動生成されたメソッド・スタブ

		// ユーザーテーブル create
		db.execSQL(
		        "create table profile_table("+
		        "	id INTEGER PRIMARY KEY,"+
                "   icon text null,"+
                "   name text null,"+
		        "   email text null,"+
                "   sex int null,"+
                "   nation text null,"+
                "   address text null,"+
                "   icon_blob blob null,"+
                "   message text null"+
				");"
		    );


	}
	
	

    

	
	@Override
	public void onUpgrade(SQLiteDatabase upDb, int oldVersion, int newVersion) {
		// TODO 自動生成されたメソッド・スタブ
		
		Log.d("TalkBox_upgrade",oldVersion+";"+newVersion);
		if (oldVersion == 1 && newVersion == 2) {

		}
		
	}

}