package com.example.shoppinglist;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class AddTimeDate extends Activity {

	
	TimePicker time;
	DatePicker date;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_time_date);
		getActionBar().setTitle("Pick a Time and Date");
		time=(TimePicker)findViewById(R.id.timePicker1);
		date=(DatePicker)findViewById(R.id.datePicker1);
		
	
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_add_time_date, menu);
		MenuItem save=(MenuItem)menu.findItem(R.id.menu_save);
		save.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem arg0) {
				// TODO Auto-generated method stub
				
				/*Calendar cal;
				cal = Calendar.getInstance();
				int year = cal.get(Calendar.YEAR);
				int month = cal.get(Calendar.MONTH);
				int day = cal.get(Calendar.DATE);
				cal.set(year,month,day,time.getCurrentHour(),time.getCurrentMinute(),0);
				
				
				Intent startIntent = new Intent("WorkAlarm");
				
				startIntent.putExtra("time", time.getCurrentHour()+":"+time.getCurrentMinute());
				//int requestCode=(int) System.currentTimeMillis();
				int requestCode= 1;
				PendingIntent startPIntent = PendingIntent.getBroadcast(getBaseContext(), requestCode, startIntent, PendingIntent.FLAG_CANCEL_CURRENT);
			
				AlarmManager alarm = (AlarmManager) getBaseContext().getSystemService(Context.ALARM_SERVICE);
				alarm.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), startPIntent);*/
				
				
				Intent intent= new Intent(getBaseContext(),AddItemActivity.class);
				intent.putExtra("timehour", time.getCurrentHour()+"");
				intent.putExtra("timeminute", time.getCurrentMinute()+"");
				intent.putExtra("dateday",date.getDayOfMonth()+"");
				intent.putExtra("datemonth", (date.getMonth()+1)+"");
				intent.putExtra("dateyear", date.getYear()+"");
				
				
				setResult(AddItemActivity.PICKED_TIME_TRUE,intent);
				finish();
				
				return false;
			}
		});
		
		return true;
	}

}
