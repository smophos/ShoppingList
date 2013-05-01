package com.example.shoppinglist;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class CategoryActivity extends Activity {

	
	private Intent mIntent;
	private Resources mResource;
	private ArrayList<ItemData> mList;
	static List<String> mDelete_list;
	private ListView mListView;
	private String mItem;
	private static CategoryDataAdapter mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.activity_category);
		mIntent=getIntent();
		String itemType= mIntent.getStringExtra("category");
		//0x7f020000
		mItem=itemType;
		//Toast.makeText(this, "inside activity " +  mItem, Toast.LENGTH_SHORT).show();
		
		int icon=mIntent.getIntExtra("icon",0);
		Log.i("saumya","icon value "+icon);
		getActionBar().setTitle("Items in "+itemType);
		
		//getActionBar().setIcon(Uri.parse("icon"));
		
		mListView=(ListView)findViewById(R.id.ItemList);
		fillList();
		
		mAdapter=new CategoryDataAdapter(this, mList);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long arg3) {
				// TODO Auto-generated method stub
				TextView text= (TextView) view.findViewById(R.id.itemnameval);
				Intent intent = new Intent(getBaseContext(),EditActivity.class);
				intent.putExtra("name", text.getText().toString());
				startActivity(intent);
			}
		
		
		});
		
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i("saumya","ON START");
		fillList();
		mAdapter=new CategoryDataAdapter(this, mList);
		mListView.setAdapter(mAdapter);
	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.i("saumya","ON RESTART");
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
		mDelete_list=new ArrayList<String>();
		Log.i("saumya","adding to a list "+ cursor.moveToFirst() );
		
		for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
			 
	    	//Creating the category item for the list
	    	
			ItemData data=new ItemData();
		    data.setCategoryName(cursor.getString(cursor.getColumnIndex(ShoppingListDatabaseProvider.CATEGORYITEMTYPE)));
		    data.setItemName(cursor.getString(cursor.getColumnIndex(ShoppingListDatabaseProvider.CATEGORYITEMNAME)));
		    data.setItemAmount(cursor.getInt(cursor.getColumnIndex(ShoppingListDatabaseProvider.CATEGORYITEMAMOUNT)));
		    mDelete_list.add(data.getItemName());
		    Log.i("saumya","item + "+ data.getItemName());
		    mList.add(data);
		    
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_category, menu);
		
		MenuItem additem=(MenuItem)menu.findItem(R.id.additem);
		additem.setOnMenuItemClickListener(new OnMenuItemClickListener(){

			@Override
			public boolean onMenuItemClick(MenuItem arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getBaseContext(),AddItemActivity.class);
				intent.putExtra("type", mItem);
				startActivity(intent);
				
				return false;
			}
			
		});
		
		MenuItem deleteitem=(MenuItem)menu.findItem(R.id.deleteitem);
		deleteitem.setOnMenuItemClickListener( new OnMenuItemClickListener(){

			@Override
			public boolean onMenuItemClick(MenuItem arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getBaseContext(),DeleteItemList.class);
				intent.putExtra("type", mItem);
				startActivity(intent);
				return false;
				
			}
			
		});
		
		
		return true;
	}
	

}
