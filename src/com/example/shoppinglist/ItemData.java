package com.example.shoppinglist;

public class ItemData {
	
	private String mItemName;
	private int mItemAmount;
	private String mCategoryName;
	
	public void setItemName(String name){
		mItemName=name;
		
	}
	public void setItemAmount(int amount){
		mItemAmount=amount;
	}
	
	public void setCategoryName(String name){
		mCategoryName=name;
	}
	
	
	public String getItemName(){
		return mItemName;
	}
	public int getItemAmount(){
		return mItemAmount;
	}
	
	public String getCategoryName(){
		return mCategoryName;
	}
}
