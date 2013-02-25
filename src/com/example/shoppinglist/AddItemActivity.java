package com.example.shoppinglist;

import android.app.Activity;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class AddItemActivity extends Activity {

	private EditText itemname, itemamount;
	private String itemName, itemAmount, mType;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_item);
		
		
		
		mType=getIntent().getExtras().getString("type");
		
		
		
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
			
				getContentResolver().insert(Uri.parse(ShoppingListDatabaseProvider.URIITEMINSERT),insert );
				finish();
				
				return false;
			}
			
		});
		return true;
	}

}
