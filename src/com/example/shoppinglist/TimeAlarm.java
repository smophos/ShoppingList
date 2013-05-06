package com.example.shoppinglist;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class TimeAlarm extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
		
		if(intent.getAction().equals("WorkAlarm")){
			/*MediaPlayer btnSound = MediaPlayer.create(context,R.raw.beep);
			btnSound.start();
		 	*/
			//Playing notifcation sound
			//Manuall play a loud beep
			try {
		        	
					
					SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
					String strRingtonePreference = preference.getString("ring_tone_pref", "DEFAULT_SOUND");        
				
					Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		        	
					Ringtone r = RingtoneManager.getRingtone(context, notification);
		        	r.play();
		 	} catch (Exception e) {}
		 
		 	Toast.makeText(context,"this alarm has rung at time "+ intent.getExtras().get("time"),Toast.LENGTH_LONG).show();
			NotificationManager manager= (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
			Notification note=new Notification.Builder(context).setAutoCancel(true).setContentIntent(null).setContentTitle(intent.getExtras().getString("itemname")).setTicker("Reminder: Buy item "+intent.getExtras().getString("itemname"))
							.setContentText("The amount required "+intent.getExtras().getString("itemamount")).setSmallIcon(R.drawable.ic_stat_tickicon).setWhen(System.currentTimeMillis()).build();
			
			SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
			String strRingtonePreference = preference.getString("ring_tone_pref", "DEFAULT_SOUND");        
			//note.sound=Uri.parse(strRingtonePreference);
			
			//.sound(strRingtonePreference);
			manager.notify(1,note);
		}
		
		
	}
	
}
