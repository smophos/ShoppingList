package com.example.shoppinglist;

import android.net.Uri;

public class ShoppingCategory {

	private String mCategory_Name;
	private Uri mCategory_Icon;
	private int mCategory_Number;
	
	public void setCategoryName(String name){
		mCategory_Name=name;
	}
	public void setCategoryIcon(int icon){
		Uri uri=Uri.parse("android.resource://com.example.shoppinglist/"+icon);
		mCategory_Icon=uri;
	}
	public void setCategoryNumber(int number){
		mCategory_Number=number;
	}
	
	public String getCategoryName(){
		return mCategory_Name;
	}
	public Uri getCategoryIcon(){
		return mCategory_Icon;
	}
	public int getCategoryNumber(){
		return mCategory_Number;
	}
}
