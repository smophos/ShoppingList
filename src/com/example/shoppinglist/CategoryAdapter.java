package com.example.shoppinglist;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CategoryAdapter extends BaseAdapter {

	private ArrayList<ShoppingCategory> mCategoryList;
	private Context mContext;
	private int mResource;
	
	
	
	public CategoryAdapter(Context context, ArrayList<ShoppingCategory> categoryList){
		
		mContext=context;
		mCategoryList= categoryList;
		mResource=R.layout.category_list;
		
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
	public View getView(int position, View convertView, ViewGroup parent) {
		
		// TODO Auto-generated method stub
		RelativeLayout layout;
		ShoppingCategory category= (ShoppingCategory)getItem(position);
		
		String category_name=category.getCategoryName();
		int category_number=category.getCategoryNumber();
		//Uri image_resource=category.getCategoryIcon();
		
		//Uri image_resource=R.drawable.ic_launcher;
		
		View view=new View(mContext);
		Log.i("saumya","at "+position+" getting item with details "+ category_name +" "+ category_number );
	
		if(convertView==null){
			  
			
			layout=new RelativeLayout(mContext);
			LayoutInflater inflater= (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			view = inflater.inflate(mResource,layout,true);
			
		
		}else
		{
			
			view=convertView;
			
		}
		
		//ImageView category_image=(ImageView)view.findViewById(R.id.imageView1);
		//category_image.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_menu_example));
		//category_image.setImageResource(image_resource);
		
		TextView categoryname_view=(TextView)view.findViewById(R.id.cat_name);
		Log.i("saumya","got cat_name layout");
		categoryname_view.setText(category_name);
		
		TextView categorynumber_view=(TextView)view.findViewById(R.id.cat_number);
		Log.i("saumya","got cat_number layout");
		categorynumber_view.setText(category_number+"");
		
		view.setTag(category_name);
		Log.i("saumya","changing data set");
		
		
/*		for(int i=0;i<mSelectedViewTop;i++){
			
			TextView text=(TextView)mSelectedViewStack[i].findViewById(R.id.cat_name);
			TextView compareText=(TextView)convertView.findViewById(R.id.cat_name);
			String textstring = text.getText().toString();
			String compareTextString = compareText.getText().toString();
			
			Log.i("saumya","in stack with adapter "+textstring);
			
			//mSelectedView.setBackgroundColor(color.holo_blue_bright);
			//mSelectedViewStack[i].setSelected(true);  
		
		}  
		
*/		/*if(mSelectedViewTop!=0){
			for(int i=0;i<mSelectedViewTop;i++){
				
				TextView text=(TextView)mSelectedViewStack[i].findViewById(R.id.cat_name);
				TextView compareText=(TextView)convertView.findViewById(R.id.cat_name);
				String textstring = text.getText().toString();
				String compareTextString = compareText.getText().toString();
				
				if(textstring.equalsIgnoreCase(compareTextString)){
					
					Log.i("saumya","Changing color of "+textstring + "as it equals "+ compareTextString );
					convertView.setBackgroundColor(mContext.getResources().getColor(android.R.color.holo_blue_bright));
					//text.setText("deleting "+text.getText().toString());
				}
			}
		}else{
			
			convertView.setBackgroundColor(mContext.getResources().getColor(android.R.color.white));
		}*/
		
		view.refreshDrawableState();
		
		return view;
		
	}
	
	
	
	/*public CategoryAdapter(Context context, int resource,
			int textViewResourceId, List objects) {
		super(context, resource, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
	}*/
	
	
	/*@Override
	public View getView(int position, View convertView,ViewGroup parentView){
	
		return convertView;
	}*/

}
