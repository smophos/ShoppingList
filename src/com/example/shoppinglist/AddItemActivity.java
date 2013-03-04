package com.example.shoppinglist;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import android.widget.TextView;
import android.widget.Toast;

public class AddItemActivity extends Activity {

	
	private EditText itemname, itemamount;
	private String itemName, itemAmount, mType;
	private TextView timeText;
	Button mButton;
	
	final static int PICKED_TIME=0;
	final static int PICKED_TIME_TRUE=1;
	final static int PICKED_TIME_FALSE=2;
	final static int PICKED_DATE =3;
	final static int PICKED_DATE_TRUE=4;
	final static int PICKED_DATE_FALSE=5;

	String time; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_add_item);
		
		
		getActionBar().setTitle("Add an Item");
		timeText=(TextView)findViewById(R.id.textTime);
		mType=getIntent().getExtras().getString("type");
		mButton=(Button)findViewById(R.id.button_time);
		mButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),AddTimeDate.class);
				startActivityForResult(intent,PICKED_TIME);
			}
			
		});
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		
		super.onActivityResult(requestCode, resultCode, data);
		
		if(requestCode==PICKED_TIME){
			
			switch(resultCode){
				case  PICKED_TIME_TRUE: 
										
										time=""+data.getExtras().getString("dateyear")+"-"+ data.getExtras().getString("datemonth")+"-"+data.getExtras().getString("dateday") + " " +data.getExtras().getString("timehour") + ":"+data.getExtras().getString("timeminute")  + ":00";
										//timeText.setText("Time chosen is "+ data.getExtras().getString("timehour") + " : "+data.getExtras().getString("timeminute")+ " at date "+ data.getExtras().getString("dateday")+ "/" + data.getExtras().getString("datemonth") +"/" +data.getExtras().getString("dateyear"));
										
										SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
										
										Date enddate=null;
										
										try {
											enddate= dateformat.parse(time);
										} catch (ParseException e) {
											// TODO Auto-generated catch block
											Toast.makeText(getApplicationContext(), "Error in parsing ", Toast.LENGTH_SHORT).show();
											e.printStackTrace();
											
										}
										String date =dateformat.format(enddate);
										
										timeText.setText(date);
										Toast.makeText(getApplicationContext(), "The time  "+ date  , Toast.LENGTH_LONG).show();
										
										
										
										
									    break;
				
				
				case PICKED_TIME_FALSE:
										break;
										
			}
			
			
			
		}
		
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
				if(!timeText.getText().toString().equals("Time is not set")){
					insert.put("time",timeText.getText().toString());
				}
				else 
					insert.put("time","");
					
				getContentResolver().insert(Uri.parse(ShoppingListDatabaseProvider.URIITEMINSERT),insert );
				
				finish();
				
				return false;
			}
			
		});
		return true;
	}

}
