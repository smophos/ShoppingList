package com.example.shoppinglist;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
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

public class AddItemActivity extends Activity {

	
	private EditText itemname, itemamount;
	private String itemName, itemAmount, mType;
	private TextView mTimeText;
	private CheckBox mCheckBox;
	Button mTimeButton,mPlusButton,mMinusButton;
	
	final static int PICKED_TIME=0;
	final static int PICKED_TIME_TRUE=1;
	final static int PICKED_TIME_FALSE=2;
	final static int PICKED_DATE =3;
	final static int PICKED_DATE_TRUE=4;
	final static int PICKED_DATE_FALSE=5;

	String time; 
	int year,month,date,hour,minute;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_add_item);
		
		
		getActionBar().setTitle("Add an Item");
		
		
		
		itemamount=(EditText)findViewById(R.id.itemamount_edit);
		
		mTimeText=(TextView)findViewById(R.id.textTime);
		mType=getIntent().getExtras().getString("type");
		
		mPlusButton=(Button)findViewById(R.id.plusbutton);
		mPlusButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int currentValue = Integer.parseInt(itemamount.getText().toString());
				currentValue++;
				itemamount.setText(currentValue+"");
			}
		});
		
		mMinusButton=(Button)findViewById(R.id.minusbutton);
		mMinusButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int currentValue = Integer.parseInt(itemamount.getText().toString());
				if(currentValue==0){
					return;
				}
				currentValue--;
				itemamount.setText(currentValue+"");
			}
		});
		Log.i("saumya","created till here");
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
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		
		super.onActivityResult(requestCode, resultCode, data);
		
		if(requestCode==PICKED_TIME){
			
			switch(resultCode){
				case  PICKED_TIME_TRUE: 
										year=Integer.parseInt(data.getExtras().getString("dateyear"));
										month=Integer.parseInt(data.getExtras().getString("datemonth"));
										date=Integer.parseInt(data.getExtras().getString("dateday"));
										hour=Integer.parseInt(data.getExtras().getString("timehour"));
										minute=Integer.parseInt(data.getExtras().getString("timeminute"));
										
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
										
										mTimeText.setText(date);
										Toast.makeText(getApplicationContext(), date  , Toast.LENGTH_LONG).show();
													
										
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
				if(itemName.contains("'")){
					itemName=itemName.replace("'", "''");
				}
				insert.put("itemname",itemName);
				insert.put("itemamount", itemAmount);
				
				if(mType.contains("'")){
					mType=mType.replace("'", "''");
				}
				
				insert.put("categorytype", mType);
				if(!mTimeText.getText().toString().equals("Time is not set")){
					
					insert.put("time",mTimeText.getText().toString());
					
					
						Log.i("saumya","initialising calendar");
						Calendar cal;
						cal = Calendar.getInstance();
						SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date date1=null;
						try {
							date1 = dateformat.parse(time);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						cal.setTime(date1);
						Toast.makeText(getBaseContext(),"reminder set for "+ year+ " " + month + " " + date + " " + hour + " " + minute, Toast.LENGTH_LONG).show();
				
						Intent startIntent = new Intent("WorkAlarm");
						
						startIntent.putExtra("year",year);
						startIntent.putExtra("month",month);
						startIntent.putExtra("date",date);
						startIntent.putExtra("hour",hour);
						startIntent.putExtra("minute",minute);
						
						startIntent.putExtra("itemname", itemName);
						startIntent.putExtra("itemamount", itemAmount);
						
						
						//int requestCode=(int) System.currentTimeMillis();
						int requestCode= itemName.hashCode();
						PendingIntent startPIntent = PendingIntent.getBroadcast(getBaseContext(), requestCode, startIntent, PendingIntent.FLAG_UPDATE_CURRENT);
					
						AlarmManager alarm = (AlarmManager) getBaseContext().getSystemService(Context.ALARM_SERVICE);
						alarm.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), startPIntent);
						
			            
						
						
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
