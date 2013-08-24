package com.sun.calender;

import java.util.List;

import com.sun.dbhelper.AboutDAO;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class AboutDAOAdaptor implements SpinnerAdapter {

	private Context context;
    // Your custom values for the spinner (User)
    private List<AboutDAO>  data;
    
    public AboutDAOAdaptor(Context context ,List<AboutDAO> data){
    	this.context = context;
        this.data = data;
    }
    
	@Override
	public int getCount() {
		return data.size();
		
	}
	
	@Override
	public Object getItem(int position) {
		return data.get(position);
		
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public int getItemViewType(int position) {
		return android.R.layout.simple_spinner_dropdown_item;
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView v = new TextView(context);
        v.setTextColor(Color.BLACK);
        v.setText(data.get(position).getAboutDescription());
        return v;
	}
	
	@Override
	public int getViewTypeCount() {
		return 1;
	}
	
	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return this.getView(position, convertView, parent);
	}
    
	
}
