package com.example.shoppinglist;


import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.widget.Toast;

public class SettingsPreference extends PreferenceActivity implements OnSharedPreferenceChangeListener {

	Uri tone;
	ListPreference smo;
	RingtonePreference pref;
	protected void onCreate(Bundle paramBundle)
	  {
	    super.onCreate(paramBundle);
	   addPreferencesFromResource(R.xml.settingspref);
	   pref =(RingtonePreference)findPreference("ringtone");
	   getPreferenceScreen().getSharedPreferences().
       registerOnSharedPreferenceChangeListener(this);
		 
		/* pref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener(){

			@Override
			public boolean onPreferenceChange(Preference arg0, Object arg1) {
				// TODO Auto-generated method stub
				SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
				String strRingtonePreference = preference.getString("smo", "DEFAULT_SOUND");
				tone = Uri.parse(strRingtonePreference);
				Ringtone note = RingtoneManager.getRingtone(getApplicationContext(), tone);
				arg0.setSummary(note.getTitle(getBaseContext())+" is set");
				if(!preference.contains("smo")){
					   Toast.makeText(getBaseContext(), "Its not here why?", Toast.LENGTH_SHORT).show();
				}
				return false;
			}
	    	 
	     });*/
	
	  }
	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		// TODO Auto-generated method stub
		
		if(key.equals("ringtone")){
			//SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
			
			String strRingtonePreference = sharedPreferences.getString("ringtone", "DEFAULT_SOUND");
			tone = Uri.parse(strRingtonePreference);
			Ringtone note = RingtoneManager.getRingtone(getApplicationContext(), tone);
			pref.setSummary(note.getTitle(getBaseContext()));
			
		}
	}
}
