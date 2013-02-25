package com.example.shoppinglist;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;

public class DeleteItemList extends Activity {

	private ListView mListView;
	private List<String> mDelete_list;
	private String mType;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_delete_item_list);
		
		mListView=(ListView)findViewById(R.id.deleteitemlist);
		
		mDelete_list=new ArrayList<String>();
		
		mType=getIntent().getExtras().getString("type");
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_checked, CategoryActivity.mDelete_list);
		mListView.setAdapter(dataAdapter);
		mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				
				CheckedTextView v=(CheckedTextView)arg1;
				
				mDelete_list.add(position+"");
				Log.i("saumya","The list contains position "+mDelete_list.contains(position+""));
				
				mListView.setItemChecked(position, true);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_delete_item_list, menu);
		MenuItem delete=(MenuItem)menu.findItem(R.id.deleteitem);
		delete.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem arg0) {
				// TODO Auto-generated method stub
				
				for(String string:mDelete_list){
					
					int position= Integer.parseInt(string);
					Log.i("saumya","deleting the item at position "+ position + " with value " + MainActivity.mCategoryList.get(position) );
					
					
					String data[]=new String[]{CategoryActivity.mDelete_list.get(position)};
					Log.i("saumya","the array contains "+ CategoryActivity.mDelete_list.get(position));
					getContentResolver().delete(Uri.parse(ShoppingListDatabaseProvider.URIDELETEITEM),ShoppingListDatabaseProvider.CATEGORYITEMNAME,data);
					ContentValues value=new ContentValues();
					value.put("categorytype",mType);
					getContentResolver().update(Uri.parse(ShoppingListDatabaseProvider.URIDELETEITEM), value, null, null);
				
				}
				
				mDelete_list=null;
				
				finish();
				return false;
			}
		});
		
		return true;
	}

}
