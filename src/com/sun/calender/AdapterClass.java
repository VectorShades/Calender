package com.sun.calender;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterClass extends BaseAdapter
	{
	    TextView textView;
		@SuppressWarnings("unused")
		private Context context;
	    public AdapterClass(Context context, TextView view)
	    {
	                this.context = context;
	                this.textView = view;
	    }

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			return null;
		}
	}

