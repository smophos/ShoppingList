package com.example.shoppinglist;

import java.util.ArrayList;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

class CategoryAdapterDelete extends BaseAdapter{

	private ArrayList<ShoppingCategory> mCategoryList;
	private Context mContext;
	private int mResource;
	
	
	
	public CategoryAdapterDelete(Context context, ArrayList<ShoppingCategory> categoryList){
		
		mContext=context;
		mCategoryList=categoryList;
		mResource=R.layout.deleteview;
		
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mCategoryList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return mCategoryList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parentGroup) {
		
		// TODO Auto-generated method stub
		
		RelativeLayout layout;
		ShoppingCategory category= (ShoppingCategory)getItem(position);
		
		String category_name=category.getCategoryName();
		int category_number=category.getCategoryNumber();
		
		View view=new View(mContext);
		
		
		if(convertView==null){
			  
			
			layout=new RelativeLayout(mContext);
			LayoutInflater inflater= (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			view = inflater.inflate(mResource,layout,true);
			
		
		}else
		{
			
			view=convertView;
			
		}

		if(view==null){
			Log.i("saumya","view is null");
		}
		TextView categoryname_view=(TextView)view.findViewById(R.id.delete_cat_name);
		categoryname_view.setText(category_name);
		
		TextView categorynumber_view=(TextView)view.findViewById(R.id.delete_cat_number);
		categorynumber_view.setText(category_number+"");
		
		
		view.refreshDrawableState();
		
		return view;
		
	}

}
