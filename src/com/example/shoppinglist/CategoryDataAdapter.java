package com.example.shoppinglist;

import java.util.ArrayList;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CategoryDataAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<ItemData> mList; 
	private int mResource;
	
	public CategoryDataAdapter(Context context, ArrayList<ItemData> list){
	
		mList=list;
		mContext=context;
		mResource=R.layout.itemview;
	
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		RelativeLayout layout;
		
		ItemData item= mList.get(position);
		Log.i("saumya","setting data "+ item.getItemName());
		View view=new View(mContext);
		
		if(convertView==null){
			layout=new RelativeLayout(mContext);
			LayoutInflater inflater= (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			view = inflater.inflate(mResource,layout,true);
		}
		else{
			view=convertView;
		}
		
		TextView itemname=(TextView)view.findViewById(R.id.itemnameval);
		itemname.setText(item.getItemName()+"");
		
		TextView itemamount=(TextView)view.findViewById(R.id.itemamount);
		
		itemamount.setText(""+item.getItemAmount()+"");
		
		return view;
		
	}

}
