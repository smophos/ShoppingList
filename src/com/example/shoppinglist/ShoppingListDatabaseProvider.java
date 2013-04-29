package com.example.shoppinglist;

import java.util.ArrayList;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

public class ShoppingListDatabaseProvider extends ContentProvider{

	public static final String DATABASE_NAME="ShoppingDatabase";
	public static final int VERSION=8;
	  
	//Table names
	public static final String CATEGORYTYPETABLE="CategoryType";
	public static final String CATEGORYITEMTYPETABLE="CategoryItem";
	
	//Column Names  
	public static final String CATEGORYTYPEID="CategoryTypeId";
	public static final String CATEGORYTYPEICON="CategoryIcon";
	public static final String CATEGORYTYPENAME="CategoryName";
	public static final String CATEGORYTYPEAMOUNT="CategoryAmount";
	
	public static final String CATEGORYITEMID="CategoryItemId";
	public static final String CATEGORYITEMNAME="CategoryItemName";
	public static final String CATEGORYITEMAMOUNT="CategoryAmountName";
	public static final String CATEGORYITEMTYPE="CategoryItemType";
	public static final String CATEGORYITEMTIME="CategoryItemTime";
	
	private MyOpenHelper mHelper;
	private SQLiteDatabase mDatabase;
	
	public static final String URICATEGORYTYPE="content://com.example.shoppinglist.provider.ShoppingListDatabaseProvider/CategoryType";
	public static final String URICATEGORYITEM="content://com.example.shoppinglist.provider.ShoppingListDatabaseProvider/CategoryItem";
	public static final String URIQUERY="content://com.example.shoppinglist.provider.ShoppingListDatabaseProvider/Category-Query/#";
	public static final String URISELECTCHECK="content://com.example.shoppinglist.provider.ShoppingListDatabaseProvider/Category-Query-Select-Check/";
	public static final String URIINSERT="content://com.example.shoppinglist.provider.ShoppingListDatabaseProvider/Category-Query-Insert/";
	public static final String URISELECTITEM="content://com.example.shoppinglist.provider.ShoppingListDatabaseProvider/Category-Item-Select-Query/";
	//public static final String URIADDCATEGORY="content://com.example.shoppinglist.provider.ShoppingListDatabaseProvider/Category-Query/4";
	public static final String URIITEMINSERT = "content://com.example.shoppinglist.provider.ShoppingListDatabaseProvider/Category-Item-Query/";
	public static final String URIDELETE="content://com.example.shoppinglist.provider.ShoppingListDatabaseProvider/Delete-Query/";
	public static final String URIDELETEITEM="content://com.example.shoppinglist.provider.ShoppingListDatabaseProvider/Delete-Item-Query/";
	public static final String URIRENAME="content://com.example.shoppinglist.provider.ShoppingListDatabaseProvider/Rename-Query/";
	private TypedArray mCategory_Names;
	private TypedArray mCategory_Numbers;
	
	class MyOpenHelper extends SQLiteOpenHelper{

		public MyOpenHelper(Context context, String name,
				CursorFactory factory, int version) {
			
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
			Log.i("saumya","constructor sqliteopenhelper");
			
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			Log.i("saumya","Executing the create statements");
			db.execSQL("Create table "+CATEGORYTYPETABLE+" ( "+CATEGORYTYPEID+" Integer primary key autoincrement, " 
					+CATEGORYTYPENAME+" Text not null unique, "
					+CATEGORYTYPEAMOUNT+" Integer not null, "
					+CATEGORYTYPEICON+" Integer not null)");
			db.execSQL("Create table "+CATEGORYITEMTYPETABLE+" ( "+CATEGORYITEMID+" Integer primary key autoincrement, "
					+CATEGORYITEMNAME+" text not null, "
					+CATEGORYITEMAMOUNT+" long not null, "
					+CATEGORYITEMTYPE+" text not null,"
					+CATEGORYITEMTIME+" DATETIME)");
			Log.i("saumya","Tables Created!");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			
			db.execSQL("DROP TABLE IF EXISTS "+CATEGORYTYPETABLE);
			db.execSQL("DROP TABLE IF EXISTS "+CATEGORYITEMTYPETABLE);
			onCreate(db);
		
		}
		
	}


	@Override
	public int delete(Uri uri, String whereClause, String[] whereArgs) {
		// TODO Auto-generated method stub
		if(Uri.parse(URIDELETE).equals(uri)){
			Log.i("saumya","deleting item "+whereClause+ " with values "+whereArgs[0]);
			mDatabase.delete(CATEGORYTYPETABLE, whereClause+"=?", whereArgs);
			
			mDatabase.execSQL("Delete from "+ CATEGORYITEMTYPETABLE + " where "+ CATEGORYITEMTYPE +"='"+whereArgs[0]+"'");
		}
		if(Uri.parse(URIDELETEITEM).equals(uri)){
			
			Log.i("saumya","deleting item "+whereClause+ " with values "+whereArgs[0]);
			
			mDatabase.delete(CATEGORYITEMTYPETABLE, whereClause+"=?", whereArgs);
			//mDatabase.execSQL("Delete from "+ CATEGORYITEMTYPETABLE + " where "+ CATEGORYITEMNAME +"='"+whereArgs[0]+"'");
			
		}
		
		return 0;
	}


	@Override
	public String getType(Uri arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		
		if(Uri.parse(URIINSERT).equals(uri)){
			
			mDatabase.execSQL("Insert into "+ShoppingListDatabaseProvider.CATEGORYTYPETABLE+ " values("+null+",'"+values.getAsString("categoryname")+"',"+values.getAsInteger("categoryamount")+","+values.getAsInteger("categoryicons")+")");
		
		}
		
		if(Uri.parse(URIITEMINSERT).equals(uri)){
			
			
			mDatabase.execSQL("Insert into "+ ShoppingListDatabaseProvider.CATEGORYITEMTYPETABLE +" values("+null+",'"+values.getAsString("itemname")+"',"+values.getAsInteger("itemamount")+",'"+values.getAsString("categorytype")+"','"+values.getAsString("time")+"')");
			mDatabase.execSQL("Update "+ CATEGORYTYPETABLE + " set "+CATEGORYTYPEAMOUNT +"=(Select COUNT(DISTINCT "+ CATEGORYITEMNAME + ") from "+CATEGORYITEMTYPETABLE + " where "+ CATEGORYITEMTYPE+"='"+values.getAsString("categorytype")+"') where "+CATEGORYTYPENAME+ "='"+values.getAsString("categorytype")+"'" );
		
		}
		return null;
	}


	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		mHelper=new MyOpenHelper(getContext(),DATABASE_NAME, null, VERSION);
		mDatabase=mHelper.getWritableDatabase();
		
		
		
		return true;
	}

	
	
	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
			String sortOrder) {
		Cursor cursor = null;
		Log.i("saumya","inside the query funciton");
		// TODO Auto-generated method stub
		if(uri.equals(Uri.parse(URIQUERY))){
	
			cursor=mDatabase.query(CATEGORYTYPETABLE, projection, selection, selectionArgs, null, null, null);
			 
		}
		if(uri.equals(Uri.parse(URISELECTCHECK))){
			
			Log.i("saumya","executing statement with selectionArgs "+selectionArgs[0]);
			cursor=mDatabase.query(CATEGORYTYPETABLE, projection, selection+"=?", selectionArgs, null, null, null);
			if(cursor==null){
				Log.i("saumya","no cursor returned");
			}
		}
		if(uri.equals(Uri.parse(URISELECTITEM))){
			Log.i("saumya","getting data item "+ selectionArgs[0]);	
			cursor=mDatabase.query(CATEGORYITEMTYPETABLE, projection, selection+"=?", selectionArgs, null, null, null);
			
		}
		
		return cursor; 
	}


	@Override
	public int update(Uri uri, ContentValues values, String stringFilter, String[] arg3) {
		// TODO Auto-generated method stub
		if(Uri.parse(URIRENAME).equals(uri)){
			
			mDatabase.update(CATEGORYTYPETABLE, values, stringFilter, null);
		}
		if(Uri.parse(URIDELETEITEM).equals(uri)){
			
			mDatabase.execSQL("Update "+ CATEGORYTYPETABLE + " set "+CATEGORYTYPEAMOUNT +"=(Select COUNT(DISTINCT "+ CATEGORYITEMNAME + ") from "+CATEGORYITEMTYPETABLE + " where "+ CATEGORYITEMTYPE+"='"+values.getAsString("categorytype")+"') where "+CATEGORYTYPENAME+ "='"+values.getAsString("categorytype")+"'" );
			
		}
		
		return 0;
	}
	
}
