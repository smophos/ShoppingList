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

public class AddTimeDate extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_time_date);
		getActionBar().setTitle("Pick a Time and Date");
		
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
				Calendar cal;
				cal = Calendar.getInstance();
				cal = Calendar.getInstance();
				cal.clear();
				int year = cal.get(Calendar.YEAR);
				int month = cal.get(Calendar.MONTH);
				int day = cal.get(Calendar.DATE);
				cal.set(year,month,day,cal.get(Calendar.HOUR),cal.get(Calendar.MINUTE)+1,0);
				
				
				Intent startIntent = new Intent("WorkAlarm");
				PendingIntent startPIntent = PendingIntent.getBroadcast(getBaseContext(), 0, startIntent, 0);
	
				AlarmManager alarm = (AlarmManager) getBaseContext().getSystemService(Context.ALARM_SERVICE);
				alarm.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), startPIntent);
				
				return false;
			}
		});
		
		return true;
	}

}
