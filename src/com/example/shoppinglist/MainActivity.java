package com.example.shoppinglist;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	static ArrayList<ShoppingCategory> mList;
	static ArrayList<String> mCategoryList;
	public static  CategoryAdapter mAdapter;
	private ListView mListView;
	private Resources mResources;
	
	private TypedArray mCategory_Names;
	private TypedArray mCategory_Numbers;
	private TypedArray mCategory_Icons;
	
	private TypedArray mItem_Names;
	private TypedArray mItem_Amounts;
	
	private View mSelectedView=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		mListView=(ListView)findViewById(R.id.listView1);
		mResources=getResources();
		
		mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		mListView.setCacheColorHint(Color.TRANSPARENT);
		mListView.setAlwaysDrawnWithCacheEnabled(false);
		
//		mListView.setSelector(R.drawable.listselector);
		
		//mListView.setSelected(true);
		
		mListView.cancelLongPress();
		registerForContextMenu(mListView);
		
		
		
		//fillDatabase();
		fillCategoryList();
		
		
		
	}
	
	public static void refresh(){
		mAdapter.notifyDataSetChanged();
	}
	private void fillDatabase(){
		
		mCategory_Names=mResources.obtainTypedArray(R.array.category_list_names);
		mCategory_Numbers=mResources.obtainTypedArray(R.array.category_list_numbers);
		mCategory_Icons=mResources.obtainTypedArray(R.array.category_list_icons);
		
		Cursor cursor,cursorinsert;
		
		for(int i=0;i<mCategory_Names.length();i++){
			
			
			
			Uri selectUri=Uri.parse(ShoppingListDatabaseProvider.URISELECTCHECK);
			
			//Uri insertUri=Uri.parse(uriString);
			
			String[] projection={ShoppingListDatabaseProvider.CATEGORYTYPENAME};
			String selection=ShoppingListDatabaseProvider.CATEGORYTYPENAME;
			String selectionArgs[]={mCategory_Names.getString(i)};
	
			cursor=getContentResolver().query(selectUri, projection, selection, selectionArgs, null);
			
			
			 if(!cursor.moveToFirst()){	
				
				 	ContentValues insert=new ContentValues();
				
				 	insert.put("categoryname", mCategory_Names.getString(i));
				 	insert.put("categoryamount",mCategory_Numbers.getInt(i, 0));
				 	insert.put("categoryicons", mCategory_Icons.getInt(i, 0x7f020000));
				 	
				 	Log.i("saumya","inserting values");
				 	getContentResolver().insert(Uri.parse(ShoppingListDatabaseProvider.URIINSERT),insert );
			}
			 
			
			 
		/*	category.setCategoryName(mCategory_Names.getString(i));
			category.setCategoryNumber(mCategory_Numbers.getInt(i, 0));
			mList.add(category);
		*/	
			 
			 
		}
		
		
		mItem_Names=getResources().obtainTypedArray(R.array.Books);
		mItem_Amounts=getResources().obtainTypedArray(R.array.Books_amount);
		
		
		
		/*
		for(int i=0;i<mItem_Names.length();i++){
			
			final Uri queryUri=Uri.parse(ShoppingListDatabaseProvider.URISELECTITEM);
			
			String[] projections={ShoppingListDatabaseProvider.CATEGORYITEMNAME,ShoppingListDatabaseProvider.CATEGORYITEMAMOUNT,ShoppingListDatabaseProvider.CATEGORYITEMTYPE};
			String[] selectionArgs={mItem_Names.getString(i)};
			
			Cursor cursor2=getContentResolver().query(queryUri, projections , ShoppingListDatabaseProvider.CATEGORYITEMNAME, selectionArgs ,null);
			
			if(!cursor2.moveToFirst()){
				
				ContentValues insert=new ContentValues();
				
				insert.put("itemname",mItem_Names.getString(i));
				insert.put("itemamount", mItem_Amounts.getInt(i, 0));
				insert.put("categorytype", "Books");
			
				getContentResolver().insert(Uri.parse(ShoppingListDatabaseProvider.URIITEMINSERT),insert );
			
			}
		
		}
		
		mItem_Names=getResources().obtainTypedArray(R.array.Groceries);
		mItem_Amounts=getResources().obtainTypedArray(R.array.Groceries_amount);
		
		for(int i=0;i<mItem_Names.length();i++){
			

			final Uri queryUri=Uri.parse(ShoppingListDatabaseProvider.URISELECTITEM);
			
			String[] projections={ShoppingListDatabaseProvider.CATEGORYITEMNAME,ShoppingListDatabaseProvider.CATEGORYITEMAMOUNT,ShoppingListDatabaseProvider.CATEGORYITEMTYPE};
			String[] selectionArgs={mItem_Names.getString(i)};
			
			Cursor cursor2=getContentResolver().query(queryUri, projections , ShoppingListDatabaseProvider.CATEGORYITEMNAME, selectionArgs ,null);
			
			if(!cursor2.moveToFirst()){
				ContentValues insert=new ContentValues();
			
				insert.put("itemname",mItem_Names.getString(i));
				insert.put("itemamount", mItem_Amounts.getInt(i, 0));
				insert.put("categorytype", "Groceries");
			
				getContentResolver().insert(Uri.parse(ShoppingListDatabaseProvider.URIITEMINSERT),insert );
			}
		
		}
*/	}

	void fillCategoryList() {
		// TODO Auto-generated method stub
		
		Log.i("saumya","filling list");
		
		final Uri queryUri=Uri.parse(ShoppingListDatabaseProvider.URIQUERY);
		String [] projections={ShoppingListDatabaseProvider.CATEGORYTYPEICON,ShoppingListDatabaseProvider.CATEGORYTYPENAME,ShoppingListDatabaseProvider.CATEGORYTYPEAMOUNT};
		
		
		Cursor cursor=getContentResolver().query(queryUri, projections, null, null, null);
		
		if(cursor==null){
			Log.i("saumya","Cursor not got");
		}
		
		mList=new ArrayList<ShoppingCategory>();
		mCategoryList= new ArrayList<String>();
		
		if(cursor!=null){
			
		    for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
			   
		    	//Creating the category item for the list
		    	ShoppingCategory category=new ShoppingCategory();
			    category.setCategoryName(cursor.getString(cursor.getColumnIndex(ShoppingListDatabaseProvider.CATEGORYTYPENAME)));
			    category.setCategoryNumber(cursor.getInt(cursor.getColumnIndex(ShoppingListDatabaseProvider.CATEGORYTYPEAMOUNT)));
			    category.setCategoryIcon(R.drawable.ic_launcher);
			    mCategoryList.add(cursor.getString(cursor.getColumnIndex(ShoppingListDatabaseProvider.CATEGORYTYPENAME)));
			    
			    mList.add(category);
		    }
		}  

		Collections.sort(mList, new CategoryComparator());
		mAdapter=new CategoryAdapter(this,mList);
		mListView.setAdapter(mAdapter);
		
		mListView.setOnItemClickListener(new OnItemClickListener() {
  
			@Override
			public void onItemClick(AdapterView<?> arg0, View view1, int arg2,
					long arg3) {
				//view1.setPressed(true);
				// TODO Auto-generated method stub
				
		
					Intent intent=new Intent(getBaseContext(),CategoryActivity.class);
					
					TextView view=(TextView)view1.findViewById(R.id.cat_name);
					intent.putExtra("category",view.getText());
					
					//ImageView image=(ImageView)view1.findViewById(R.id.imageView1);
					
					//int i=image.getId();
					//intent.putExtra("icon",i );
					
					mListView.clearFocus();
					
					//ActivityOptions options = ActivityOptions.makeScaleUpAnimation(view1, 0,
					// 0, view1.getWidth(), view1.getHeight());
					
					startActivity(intent);
					//overridePendingTransition  (R.animator.right_slide_in,R.animator.do_nothing);
				
			}
			
			
		});
				
		mListView.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				
				mSelectedView=arg1;
				TextView view=(TextView) arg1.findViewById(R.id.cat_name);
				String value = view.getText().toString();
				
				return false;
			}
			
		});
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		 
		 MenuInflater inflater = getMenuInflater();
		 inflater.inflate(R.menu.categorycontext_layout, menu);
		 TextView text=(TextView)mSelectedView.findViewById(R.id.cat_name);
		 String textString=text.getText().toString();
		 CharSequence[] arr=mResources.getTextArray(R.array.category_list_names);
		 
		 /*int flag=0;
		 for(CharSequence charitem : arr){
			 if(charitem.toString().equalsIgnoreCase(textString)){
					
					flag++;
			}
		}
		 if(flag!=0){
			menu.clear(); 
		 }*/
		
		 
	}
	
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
	    AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		switch(item.getItemId()){
		
			case R.id.deletecategory:   TextView text=(TextView)mSelectedView.findViewById(R.id.cat_name);
										String [] data=new String[]{text.getText().toString()};
										getContentResolver().delete(Uri.parse(ShoppingListDatabaseProvider.URIDELETE),ShoppingListDatabaseProvider.CATEGORYTYPENAME,data);
    									fillCategoryList();
    									
    									
    									break;
			
			case R.id.renamecategory: 	
										/*ContentValues content=new ContentValues();
										content.put(ShoppingListDatabaseProvider.CATEGORYTYPENAME,);
										getContentResolver().update(Uri.parse(ShoppingListDatabaseProvider.URIRENAME),);
				*/
				
										break;
		
		}
	    return false;
	    
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		
		getMenuInflater().inflate(R.menu.activity_main, menu);
		MenuItem additem=(MenuItem)menu.findItem(R.id.add);
		additem.setOnMenuItemClickListener(new OnMenuItemClickListener(){

			@Override
			public boolean onMenuItemClick(MenuItem arg0) {
				// TODO Auto-generated method stub
				FragmentManager manager=getFragmentManager();
				AddFragment fragment= new AddFragment();
				fragment.show(manager, "add");
				return false;
			}
			
		});
		
		MenuItem deleteitem=(MenuItem)menu.findItem(R.id.delete);
		deleteitem.setOnMenuItemClickListener(new OnMenuItemClickListener(){

			@Override
			public boolean onMenuItemClick(MenuItem arg0) {
				// TODO Auto-generated method stub
				FragmentManager manager=getFragmentManager();
				DeleteFragment fragment= new DeleteFragment();
				fragment.show(manager, "delete");
				
	
				return false;
			}
		
		});
		
		MenuItem settingsitem=(MenuItem)menu.findItem(R.id.settings);
		settingsitem.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem arg0) {
				
				// TODO Auto-generated method stub
				
				//Intent intent=new Intent(getBaseContext(), SettingsActivityOption.class);
				Intent intent=new Intent(getBaseContext(), SettingsPreference.class);

				startActivity(intent);
		
				
				
				
				return false;
			}
		});
		return true;
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		fillCategoryList();
		mAdapter.notifyDataSetChanged();
	}
	

}
