package com.example.shoppinglist;

import java.sql.SQLException;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCategoryFragment extends DialogFragment{
	
	private Button mbutton;
	private EditText mEditText;
	/**
	 * Add Category Fragment is a fragment which works on adding the 
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		
		View view=inflater.inflate(R.layout.addcategory_layout, container);
		getDialog().setTitle("Add Category");
		mEditText=(EditText)view.findViewById(R.id.categorynameedittext);
		
		
		
		mbutton=(Button)view.findViewById(R.id.addcategorybutton);
		mbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				// TODO Auto-generated method stub
				ContentValues value=new ContentValues();
				Log.i("saumya","The text "+mEditText.getText().toString());
				
				String edittext=mEditText.getText().toString();
				if(edittext.contains("'")){
					edittext=edittext.replace("'", "''");
				}
			
				Log.i("saumya","the edited text"+edittext);
				
				//Adding default icons 
				
				value.put("categoryname", edittext);
				value.put("categoryicons",0x7f020003);
				value.put("categoryamount",0);
				
				if(mEditText.getText().toString().equals("")){
					Toast.makeText(getActivity(), "Please enter a value", Toast.LENGTH_SHORT).show();
					return;
				}
				try{
					
					getActivity().getContentResolver().insert(Uri.parse(ShoppingListDatabaseProvider.URIINSERT), value);
				
				}catch(Exception exception){
					
					Toast.makeText(getActivity()," Cannot enter the same value ",Toast.LENGTH_SHORT).show();
					return;
				
				}
				getDialog().dismiss();
				
				((MainActivity) getActivity()).fillCategoryList();
				(AddFragment.getDialogFragment()).dismiss();
				
			}
		});
		return view;
	}
	
}
