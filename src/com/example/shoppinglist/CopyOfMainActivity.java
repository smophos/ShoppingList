/*package com.example.shoppinglist;

import java.util.ArrayList;

import android.R.color;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class CopyOfMainActivity extends Activity {

	private ArrayList<ShoppingCategory> mList;
	private CategoryAdapter mAdapter;
	private ListView mListView;
	private Resources mResources;
	
	private TypedArray mCategory_Names;
	private TypedArray mCategory_Numbers;
	private TypedArray mCategory_Icons;
	ActionMode mActionMode=null;
	private View mSelectedView;
	
	static public View mSelectedViewStack[]=new View[20];
	static public int mSelectedViewTop=0;
	
	ActionMode.Callback mActionModeCallback= new ActionMode.Callback() {
		
		@Override
		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public void onDestroyActionMode(ActionMode mode) {
			// TODO Auto-generated method stub
			//mSelectedView.setSelected(false);
			//mAdapter.notifyDataSetChanged();
		//	mListView.clearFocus();
			
		for(int i=0;i<mSelectedViewTop;i++){
				mSelectedViewStack[i].setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));
		}
			
			
			mSelectedViewTop=0;
			mSelectedViewStack=null;
			mSelectedView=null;
			mAdapter.setStackUp(mSelectedViewStack, mSelectedViewTop);
			
			//fillCategoryList();
			//mAdapter.notifyDataSetChanged();
			//mListView.refreshDrawableState();
			
			mSelectedViewStack= new View[20] ;
			mActionMode=null;
			
		}
		
		
		@Override
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			// TODO Auto-generated method stub
			MenuInflater inflater=getMenuInflater();
			inflater.inflate(R.menu.categorycontext_layout,menu);
			mAdapter.setStackUp(mSelectedViewStack, mSelectedViewTop);
			
			return true;
		}
		
		@Override
		
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
			// TODO Auto-generated method stub
			Log.i("saumya","Inside the ActionItemClicked");
			switch (item.getItemId()) {
			
            	case R.id.deletecategory:   if(mSelectedView!=null && mSelectedViewTop==0){
            								
            									
            									TextView text=(TextView)mSelectedView.findViewById(R.id.cat_name);
            									
            									int flag=0;
            									Log.i("saumya","The data is "+text.getText().toString());
            									String [] data=new String[]{text.getText().toString()};
            									CharSequence[] arr=mResources.getTextArray(R.array.category_list_names);
            							
            									for(CharSequence charitem : arr){
            										if(charitem.toString().equalsIgnoreCase(data[0])){
            											
            											flag++;
            										}
            									}
            								
            									//if(flag==0){
            										getContentResolver().delete(Uri.parse(ShoppingListDatabaseProvider.URIDELETE),ShoppingListDatabaseProvider.CATEGORYTYPENAME,data);
            										fillCategoryList();
            										
            										mode.finish();
            										
            										// Action picked, so close the CAB
            										}
            									else{
            										Toast.makeText(getBaseContext(),"This category is default and cannot be deleted",Toast.LENGTH_LONG).show();
            									}
            								}
            										
            										
            										for(int i=0;i<mSelectedViewTop;i++ ){
            											
            											TextView text=(TextView)mSelectedViewStack[i].findViewById(R.id.cat_name);
            											int flag=0;
                    									Log.i("saumya","The data is "+text.getText().toString());
                    									String [] data=new String[]{text.getText().toString()};
                    									CharSequence[] arr=mResources.getTextArray(R.array.category_list_names);
                    							
                    									for(CharSequence charitem : arr){
                    										if(charitem.toString().equalsIgnoreCase(data[0])){
                    											
                    											flag++;
                    										}
                    									}
                    									
                    									getContentResolver().delete(Uri.parse(ShoppingListDatabaseProvider.URIDELETE),ShoppingListDatabaseProvider.CATEGORYTYPENAME,data);
                    									fillCategoryList();
            										}
            										
            											
            										mode.finish();
            	
            											
            											
            										return true;
            		default:
            										return false;
			}

			
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		mListView=(ListView)findViewById(R.id.listView1);
		mResources=getResources();
		
		mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		//mListView.setSelector(R.drawable.listselector);
		//mListView.setSelected(true);
		mListView.cancelLongPress();
		
		mSelectedViewTop=0;
		mSelectedViewStack=null;
		
		fillDatabase();
		fillCategoryList();
		
		
		
		
	}
	
	private void fillDatabase(){
		
		mCategory_Names=mResources.obtainTypedArray(R.array.category_list_names);
		mCategory_Numbers=mResources.obtainTypedArray(R.array.category_list_numbers);
		mCategory_Icons=mResources.obtainTypedArray(R.array.category_list_icons);
		
		Cursor cursor,cursorinsert;
		
		for(int i=0;i<mCategory_Names.length();i++){
			
			Log.i("saumya","in array with "+ mCategory_Names.getString(i)+" and number "+mCategory_Numbers.getInt(i, 0));
			
			
			Uri selectUri=Uri.parse(ShoppingListDatabaseProvider.URISELECTCHECK);
			//Uri insertUri=Uri.parse(uriString);
			
			String[] projection={ShoppingListDatabaseProvider.CATEGORYTYPENAME};
			String selection=ShoppingListDatabaseProvider.CATEGORYTYPENAME;
			String selectionArgs[]={mCategory_Names.getString(i)};
	
			cursor=getContentResolver().query(selectUri, projection, selection, selectionArgs, null);
			if(cursor!=null){	
				for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
					
					Log.i("saumya","found "+ cursor.getString(cursor.getColumnIndex(ShoppingListDatabaseProvider.CATEGORYTYPENAME)));
					
				}
			}
			 if(!cursor.moveToFirst()){	
				
				 	ContentValues insert=new ContentValues();
				
				 	insert.put("categoryname", mCategory_Names.getString(i));
				 	insert.put("categoryamount",mCategory_Numbers.getInt(i, 0));
				 	insert.put("categoryicons", mCategory_Icons.getInt(i, 0x7f020000));
				 	
				 	Log.i("saumya","inserting values");
				 	getContentResolver().insert(Uri.parse(ShoppingListDatabaseProvider.URIINSERT),insert );
			}
			 
			category.setCategoryName(mCategory_Names.getString(i));
			category.setCategoryNumber(mCategory_Numbers.getInt(i, 0));
			mList.add(category);
			
		}
	}

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
		
		if(cursor!=null){
			
		    for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
			   
		    	//Creating the category item for the list
		    	ShoppingCategory category=new ShoppingCategory();
			    category.setCategoryName(cursor.getString(cursor.getColumnIndex(ShoppingListDatabaseProvider.CATEGORYTYPENAME)));
			    category.setCategoryNumber(cursor.getInt(cursor.getColumnIndex(ShoppingListDatabaseProvider.CATEGORYTYPEAMOUNT)));
			    category.setCategoryIcon(cursor.getInt(cursor.getColumnIndex(ShoppingListDatabaseProvider.CATEGORYTYPEICON)));
			    
			    Log.i("saumya","adding data "+category.getCategoryName()+" "+category.getCategoryNumber());
			    
			    mList.add(category);
		    }
		}  
		
		Log.i("saumya", "finished adding data to the list");
		
		mAdapter=new CategoryAdapter(this,mList);
		
		
		
		mAdapter.setStackUp(mSelectedViewStack, mSelectedViewTop);
		mListView.setAdapter(mAdapter);
		
		mListView.setOnItemClickListener(new OnItemClickListener() {
  
			@Override
			public void onItemClick(AdapterView<?> arg0, View view1, int arg2,
					long arg3) {
				//view1.setPressed(true);
				// TODO Auto-generated method stub
				//mListView.playSoundEffect(android.view.SoundEffectConstants.CLICK);
				
				//mListView.refreshDrawableState();
				  
				if(mActionMode==null){
					
					Intent intent=new Intent(getBaseContext(),CategoryActivity.class);
					TextView view=(TextView)view1.findViewById(R.id.cat_name);
					intent.putExtra("category",view.getText());
					ImageView image=(ImageView)view1.findViewById(R.id.imageView1);
					int i=image.getId();
					intent.putExtra("icon",i );
					mListView.clearFocus();
					startActivity(intent);
				
				}else{
					
					if(mSelectedViewTop==0){
						
						Log.i("saumya","adding to the stack "+ view1.getTag().toString());
						
						mSelectedViewStack[mSelectedViewTop++]=view1;
						mSelectedViewStack[mSelectedViewTop-1].setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));
						
						//mAdapter.setStackUp(mSelectedViewStack, mSelectedViewTop);
						//mAdapter.notifyDataSetChanged();
						
						return;
						
					}
					else{
						
						mSelectedView=view1;
						//mSelectedViewStack[mSelectedViewTop++]= new View(getBaseContext());
						Log.i("saumya","adding to the stack "+ view1.getTag().toString());
						mSelectedViewStack[mSelectedViewTop++]=view1;
						int contains=0;
						
						//String tag=null;
						for(int i=0;i<mSelectedViewTop;i++){
							
							if(mSelectedViewStack[i].getTag().toString().equals(mSelectedView.getTag().toString())){
								contains++;
								if(contains==2){
									break;
								}
							}  
						}  
						
						if(contains==2){
							
							Log.i("saumya","removing same view ");
							mSelectedViewStack[mSelectedViewTop]=null;
							mSelectedViewTop--;
							
						}
						
						mSelectedViewStack[mSelectedViewTop-1].setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));
						 
					}
					
					
					
					//fillCategoryList();
					//mSelectedView.setSelected(true);
					//mSelectedView.setPressed(true);
					//mSelectedView.setBackgroundColor(color.holo_blue_bright);
					
				}
				
			}
			
			
		});
				
		mListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapterview, View view,
					int position, long arg3) {
				// TODO Auto-generated method stub
				
				//mListView.playSoundEffect(android.view.SoundEffectConstants.CLICK);
				
				Log.i("saumya","LONG PRESS");
				 
				if (mActionMode != null) {
			            return false;
			        }
				 	
				 	//view.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));
		
					view.setSelected(true);
				 	
					//view.setSelected(true);
				 	//view.setActivated(true);
				 	//view.setPressed(true);
			        // Start the CAB using the ActionMode.Callback defined above
				 	
				    if(mSelectedViewTop==0){
				    	
						mSelectedViewStack=new View[20];
						Log.i("saumya", "adding to the stack "+ view.getTag().toString());
				    	mSelectedViewStack[mSelectedViewTop++]=view;
				    	
					}
				    
				 	mActionMode = startActionMode(mActionModeCallback);
			        
			        if(view!=null){
			        	Log.i("saumya","The view is not null and is in position "+ position);
			        	mSelectedView=view;
			        }
			        
			        
			        //view.requestFocusFromTouch();
			     
			        return true;

			}
		
		});
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		
		
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
				
				Intent intent=new Intent(getBaseContext(), SettingsActivityOption.class);
				startActivity(intent);
		
					
				 * 	FragmentManager manager=getFragmentManager();
					SettingsFragment fragment= new SettingsFragment();
					FragmentTransaction transaction=manager.beginTransaction();
					
					transaction.add(R.id.fragmentcontainer, fragment);
					Log.i("saumya","added the fragment");
					transaction.addToBackStack(null);
					transaction.commit();
				
				
				
				return false;
			}
		});
		return true;
	}
	
	
	

}
*/