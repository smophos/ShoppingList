package com.example.shoppinglist;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
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
import android.widget.Toast;

public class DeleteList extends Activity {

	private ListView mListView;
	private List<String> mDelete_list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.delete_listview);
		Log.i("saumya","inflated view");
		mListView=(ListView)findViewById(R.id.deleteList);
		
		mDelete_list=new ArrayList<String>();
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_checked, MainActivity.mCategoryList);
		mListView.setAdapter(dataAdapter);
		mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		
		
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				
				if(mListView.isItemChecked(position)){
					
					CheckedTextView v=(CheckedTextView)arg1;
					mDelete_list.add(position+"");
					Log.i("saumya","The list contains position "+mDelete_list.contains(position+""));
					mListView.setItemChecked(position, true);
				}
				else{
					//mDelete_list.remove(position+"");
					mListView.setItemChecked(position, false);
					//Toast.makeText(getBaseContext(), "Not selected", Toast.LENGTH_LONG).show();
				}
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_delete_list, menu);
		MenuItem delete=(MenuItem)menu.findItem(R.id.deletemenu);
		delete.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem arg0) {
				// TODO Auto-generated method stub
				
				for(String string:mDelete_list){
					
					int position= Integer.parseInt(string);
					Log.i("saumya","deleting the item at position "+ position + " with value " + MainActivity.mCategoryList.get(position) );
			
					String deletingItem = MainActivity.mCategoryList.get(position);
					if(deletingItem.contains("'")){
								deletingItem=deletingItem.replace("'", "''");
					}
					
					String data[]=new String[]{deletingItem};
					getContentResolver().delete(Uri.parse(ShoppingListDatabaseProvider.URIDELETE),ShoppingListDatabaseProvider.CATEGORYTYPENAME,data);
					
				
				}
				
				mDelete_list=null;
				
				finish();
				return false;
			}
		});
		return true;
	}

}
