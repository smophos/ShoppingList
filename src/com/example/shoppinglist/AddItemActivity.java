package com.example.shoppinglist;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddItemActivity extends Activity {

	private EditText itemname, itemamount;
	private String itemName, itemAmount, mType;
	Button mButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_item);
		
		
		getActionBar().setTitle("Add an Item");
		mType=getIntent().getExtras().getString("type");
		mButton=(Button)findViewById(R.id.button_time);
		mButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),AddTimeDate.class);
				startActivity(intent);
			}
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_add_item, menu);
		MenuItem add= (MenuItem)menu.findItem(R.id.menu_add_item);
		add.setOnMenuItemClickListener(new OnMenuItemClickListener(){

			@Override
			public boolean onMenuItemClick(MenuItem arg0) {
				// TODO Auto-generated method stub
				
				itemname=(EditText)findViewById(R.id.itemname_edit);
				itemName=itemname.getText().toString();
				itemamount=(EditText)findViewById(R.id.itemamount_edit);
				itemAmount=itemamount.getText().toString();
				
				if(itemName.equals("")||itemAmount.equals("")){
					Toast.makeText(getApplicationContext(), "Text Field Empty", Toast.LENGTH_SHORT).show();
					return false;
				}
				
				ContentValues insert=new ContentValues();
				
				insert.put("itemname",itemName);
				insert.put("itemamount", itemAmount);
				insert.put("categorytype", mType);
				insert.put("time","today");
				getContentResolver().insert(Uri.parse(ShoppingListDatabaseProvider.URIITEMINSERT),insert );
				
				finish();
				
				return false;
			}
			
		});
		return true;
	}

}
