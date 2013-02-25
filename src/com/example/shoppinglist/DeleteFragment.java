package com.example.shoppinglist;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DeleteFragment extends DialogFragment {

	private ListView mlistview;
	private static DialogFragment reference;
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return super.onCreateDialog(savedInstanceState);
	}

	public static DialogFragment getDialogFragment(){
		return reference;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		reference=this;
		View view=inflater.inflate(R.layout.delete_listview, container);
		
		mlistview=(ListView)view.findViewById(R.id.deleteList);
		
		List<String> list = new ArrayList<String>();
		getDialog().setTitle("Select An Option");
		list.add("Delete Category");
		list.add("Delete Location");
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, list);
		mlistview.setAdapter(dataAdapter);
		mlistview.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position,
					long arg3) {
				// TODO Auto-generated method stub
				switch(position){
				case 0:  Intent intent=new Intent(getActivity(),DeleteList.class);
						 startActivity(intent);
						 getDialog().dismiss();
						 
						 break;
				}
				
				
			}
			
		});
		
		
		return view;
	}

}
