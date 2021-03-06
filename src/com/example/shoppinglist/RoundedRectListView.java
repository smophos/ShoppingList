package com.example.shoppinglist;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.AdapterView;
import android.widget.ListView;

public class RoundedRectListView extends ListView {    
	 
    private Context mContext;

    public RoundedRectListView(Context context) {
            super(context);        
            this.mContext=context;
            init();
    }

    public RoundedRectListView(Context context, AttributeSet attrs) {
            super(context, attrs);
            this.mContext=context;
            init();
    }
public RoundedRectListView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            this.mContext=context;
            init();
    }

    protected void init(){
            setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.cutomview));
            setCacheColorHint(Color.WHITE);
            setFooterDividersEnabled(false);
    }
@Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

            switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                    int x = (int) ev.getX();
                    int y = (int) ev.getY();
                    int itemnum = pointToPosition(x, y);

                    if (itemnum == AdapterView.INVALID_POSITION)
                            break;                 
                    else
                    {  
                            if(itemnum==0){
                                    if(itemnum==(getAdapter().getCount()-1))
                                            setSelector(R.drawable.cutomviewhighlight);
                                    else
                                            setSelector(R.drawable.cutomviewhighlight);
                            }/*
                            else if(itemnum==(getAdapter().getCount()-1))
                                    setSelector(R.drawable.listview_selection_shade_bottom_rounded);
                            else
                                    setSelector(R.drawable.listview_selection_shade); */                     
                    }

                    break;
            case MotionEvent.ACTION_UP:
                    break;
            }
            return true;   
    }      
}
