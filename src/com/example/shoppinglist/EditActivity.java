package com.example.shoppinglist;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends Activity {

	private EditText itemname_1, itemamount_2;
	private String itemName, itemAmount, mItemName;
	private TextView mTimeText;
	private CheckBox mCheckBox;
	Button mTimeButton, mPlusButton, mMinusButton;
	
	final static int PICKED_TIME=0;
	final static int PICKED_TIME_TRUE=1;
	final static int PICKED_TIME_FALSE=2;
	final static int PICKED_DATE =3;
	final static int PICKED_DATE_TRUE=4;
	final static int PICKED_DATE_FALSE=5;
	
	String time; 
	int year,month,date,hour,minute;
	
	int FlagNameChange=0, FlagAmountChange=0, FlagLocationChange=0, FlagReminderChange=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		
		Log.i("saumya","edit activity started");
		
		itemname_1 = (EditText)findViewById(R.id.itemname2);
		itemamount_2= (EditText)findViewById(R.id.itemamount2);
		
		mPlusButton = (Button)findViewById(R.id.plusbutton_edit);
		mPlusButton = (Button)findViewById(R.id.minusbutton_edit);
		
		mTimeText=(TextView)findViewById(R.id.textTime);
		mItemName=getIntent().getExtras().getString("name");
		Log.i("saumya","the item gotten "+ mItemName);
		mTimeButton=(Button)findViewById(R.id.button_time);
		mTimeButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),AddTimeDate.class);
				startActivityForResult(intent,PICKED_TIME);
			}
			
		});
		
		mCheckBox = (CheckBox) findViewById(R.id.checkBox1);
		mCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if(arg1==true){
					mTimeText.setVisibility(View.VISIBLE);
					mTimeButton.setVisibility(View.VISIBLE);
				}
				else {
					mTimeText.setVisibility(View.GONE);
					mTimeButton.setVisibility(View.GONE);
				}
			}
		});
		
		
		
		fillForm();

		getActionBar().setTitle("Edit the Item " + itemname_1.getHint().toString());
	}

	public void fillForm(){
        Cursor cursor;
        Log.i("saumya","filling the form");
		Uri selectUri=Uri.parse(ShoppingListDatabaseProvider.URISELECTITEM);
		
		//Uri insertUri=Uri.parse(uriString);
		
		String[] projection={ShoppingListDatabaseProvider.CATEGORYITEMNAME, ShoppingListDatabaseProvider.CATEGORYITEMAMOUNT, ShoppingListDatabaseProvider.CATEGORYITEMTIME};
		String selection=ShoppingListDatabaseProvider.CATEGORYITEMNAME;
		String selectionArgs[]={mItemName};

		cursor=getContentResolver().query(selectUri, null, selection, selectionArgs, null);
	   
		if(cursor.moveToFirst()){
			//Log.i("saumya","inside the cursor 1" + cursor.getString(cursor.getColumnIndex(ShoppingListDatabaseProvider.CATEGORYITEMNAME)));
			
			itemname_1.setHint(cursor.getString(cursor.getColumnIndex(ShoppingListDatabaseProvider.CATEGORYITEMNAME))+"");
			//Log.i("saumya","inside the cursor " + cursor.getColumnIndex(ShoppingListDatabaseProvider.CATEGORYITEMAMOUNT));
			itemamount_2.setHint(cursor.getInt(cursor.getColumnIndex(ShoppingListDatabaseProvider.CATEGORYITEMAMOUNT))+"");
			//mTimeText.setText(cursor.getString(cursor.getColumnIndex(ShoppingListDatabaseProvider.CATEGORYITEMTIME)));
			
		}
		
		//cursor.close();
	
	
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_edit, menu);
		MenuItem add= (MenuItem)menu.findItem(R.id.save_edit);
		add.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				if(itemname_1.getText().toString().equals("")&itemamount_2.getText().toString().equals("")){
					Toast.makeText(getApplicationContext(), "No Change", Toast.LENGTH_SHORT).show();
					finish();
				}
				
				String[] changed=new String[2];
				changed[0]=itemname_1.getHint().toString();
				changed[1]=itemamount_2.getHint().toString();
				ContentValues value=new ContentValues();
				if(itemname_1.getText().toString().equals(""))
					value.put("itemname", itemname_1.getHint().toString());
				else
					value.put("itemname", itemname_1.getText().toString());
				
				if(itemamount_2.getHint().toString().equals(""))
					
					value.put("itemnumber", itemamount_2.getHint().toString());
				else
					value.put("itemnumber", itemamount_2.getText().toString());
				
				getContentResolver().update(Uri.parse(ShoppingListDatabaseProvider.URIUPDATE), value, " " , changed);
				Toast.makeText(getApplicationContext(), "Updated!", Toast.LENGTH_SHORT).show();
				finish();
				return false;
			}
		});
		
		return true;
	}

}
 