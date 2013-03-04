package com.example.shoppinglist;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

public class TimeAlarm extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
		MediaPlayer btnSound = MediaPlayer.create(context,R.raw.beep);
		btnSound.start();
		Toast.makeText(context,"this alarm has rung at time "+ intent.getExtras().get("time"),Toast.LENGTH_LONG).show();
		
	}
	
}
