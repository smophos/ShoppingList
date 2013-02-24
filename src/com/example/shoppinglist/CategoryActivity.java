package com.example.shoppinglist;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.Toast;

public class CategoryActivity extends Activity {

	private Intent mIntent;
	private Resources mResource;
	private ArrayList<ItemData> mList;
	private ListView mListView;
	private String mItem;
	private CategoryDataAdapter mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.activity_category);
		mIntent=getIntent();
		String itemType= mIntent.getStringExtra("category");
		//0x7f020000
		mItem=itemType;
		Toast.makeText(this, "inside activity " +  mItem, Toast.LENGTH_SHORT).show();
		
		int icon=mIntent.getIntExtra("icon",0);
		Log.i("saumya","icon value "+icon);
		getActionBar().setTitle(itemType);
		
		//getActionBar().setIcon(Uri.parse("icon"));
		
		mListView=(ListView)findViewById(R.id.ItemList);
		
		fillList();
		
		mAdapter=new CategoryDataAdapter(this, mList);
		mListView.setAdapter(mAdapter);
		
		
		
	}

	void fillList(){
		
		final Uri queryUri=Uri.parse(ShoppingListDatabaseProvider.URISELECTITEM);
		
		String[] projections={ShoppingListDatabaseProvider.CATEGORYITEMNAME,ShoppingListDatabaseProvider.CATEGORYITEMAMOUNT,ShoppingListDatabaseProvider.CATEGORYITEMTYPE};
		String[] selectionArgs={mItem};
		Cursor cursor=getContentResolver().query(queryUri, projections , ShoppingListDatabaseProvider.CATEGORYITEMTYPE, selectionArgs ,null);
		
		
		if(cursor==null){
			Log.i("saumya","Cursor not got in item activity");
			
			return;
		}
		
		mList=new ArrayList<ItemData>();
		Log.i("saumya","adding to a list "+ cursor.moveToFirst() );
		
		for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
			 
	    	//Creating the category item for the list
	    	
			ItemData data=new ItemData();
		    data.setCategoryName(cursor.getString(cursor.getColumnIndex(ShoppingListDatabaseProvider.CATEGORYITEMTYPE)));
		    data.setItemName(cursor.getString(cursor.getColumnIndex(ShoppingListDatabaseProvider.CATEGORYITEMNAME)));
		    data.setItemAmount(cursor.getInt(cursor.getColumnIndex(ShoppingListDatabaseProvider.CATEGORYITEMAMOUNT)));
		    
		    //Log.i("saumya","adding to the list "+ data.getItemName());
		    //Toast.makeText(this, "Adding data "+ data.getItemName(), Toast.LENGTH_SHORT).show();  
		   
		    mList.add(data);
		    
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_category, menu);
		return true;
	}

}
