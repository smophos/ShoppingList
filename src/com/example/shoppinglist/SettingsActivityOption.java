package com.example.shoppinglist;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;

public class SettingsActivityOption extends Activity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_layout);
		
		
		
		ArrayList<String> list=new ArrayList<String>();
		
		list.add("1000");
		list.add("500");
		list.add("100");
		list.add("200");
		list.add("50");
		
		Spinner spinner = (Spinner)findViewById(R.id.spinner1);
		ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list);
		spinner.setAdapter(adapter);
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		
		getMenuInflater().inflate(R.menu.activity_settings, menu);
		return true;
	}
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container,
//			Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		
//		View view=inflater.inflate(R.layout.settings_layout, container,false);
//		if(view!=null){	
//			Log.i("saumya","created the view");
//		}
//		ArrayList<String> list=new ArrayList<String>();
//		
//		list.add("1000");
//		list.add("500");
//		list.add("100");
//		list.add("200");
//		list.add("50");
//		
//		Spinner spinner = (Spinner)view.findViewById(R.id.spinner1);
//		ArrayAdapter<String> adapter= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, list);
//		spinner.setAdapter(adapter);
//		
//		return view;
//	}
//	
}
