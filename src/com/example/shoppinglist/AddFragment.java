package com.example.shoppinglist;

import java.util.ArrayList;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class AddFragment extends DialogFragment{
	
	private ListView mlistview;
	private ArrayList<String> mlist;
	private static DialogFragment reference;
	
	public static DialogFragment getDialogFragment(){
		return reference;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		reference=this;
		
		View view=inflater.inflate(R.layout.add_listview, container);
		
		mlistview=(ListView)view.findViewById(R.id.addList);
		
		mlist = new ArrayList<String>();
		getDialog().setTitle("Select An Option");
		mlist.add("Add Category");
		mlist.add("Add A Location");
		mlist.add("Add A Time");
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, mlist);
		mlistview.setAdapter(dataAdapter);
		
		mlistview.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position,
					long arg3) {
				// TODO Auto-generated method stub
				
				switch(position){
					
					case 0: FragmentManager manager=getFragmentManager();
							AddCategoryFragment fragment= new AddCategoryFragment();
							fragment.show(manager, "add category");
							getDialog().dismiss();
							break;
					case 1: 
							
							break;
					case 2: Toast.makeText(getActivity(), "", Toast.LENGTH_LONG).show();
							break;
				}
				
				
			}
			
		});
		
		return view;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return super.onCreateDialog(savedInstanceState);
	}
	
	
}
