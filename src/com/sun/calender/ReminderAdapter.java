package com.sun.calender;

import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ReminderAdapter extends ArrayAdapter<RowItem> {

	
	//private static List<ReminderDAO> remindString;

	private Context mContext;
	
	public ReminderAdapter(Context reminderView, int reminderItem,	List<RowItem> rowItems) {
		 super(reminderView, reminderItem, rowItems);
	     this.mContext = reminderView;
	}

	private class ViewHolder {
        TextView txtTitle;
        TextView txtDesc;
    }
	
	
	public View getView(int position, View convertView, ViewGroup parent) {
		
		   ViewHolder holder = null;
	        RowItem rowItem =  getItem(position);
	 
	        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
	        if (convertView == null) {
	            convertView = mInflater.inflate(R.layout.reminder_item, null);
	            holder = new ViewHolder();
	            holder.txtDesc = (TextView) convertView.findViewById(R.id.reminder_Description);
	            holder.txtTitle = (TextView) convertView.findViewById(R.id.reminder_subject);
	            
	            convertView.setTag(holder);
	        } else
	            holder = (ViewHolder) convertView.getTag();
	 
	        holder.txtDesc.setText(rowItem.getDesc());
	        holder.txtTitle.setText(rowItem.getTitle());
	        
	        return convertView;
	 
		
	}

	

}
