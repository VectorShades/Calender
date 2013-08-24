package com.sun.calender;



import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import com.sun.dbhelper.ReminderDAO;
import com.sun.dbhelper.ReminderDataSource;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class ReminderView extends Activity {
	
	GregorianCalendar month;
	private ReminderDataSource datasource;
	int i=0;
	public String[] titles;
	public String[] arr_description ;
	 public String[] arr_about;
	 public String[] arr_date;
	 public String[] arr_time;
	 List<RowItem> rowItems;
	 public ReminderAdapter adpt;
	 final Context context=this;
	protected void showToast(String string){
		Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
	}

	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_reminder);
		getReminderDataFromDB();
		
		for (int a=0; a< arr_about.length; a++){
			titles[a]= arr_about[a]+"  " +arr_date[a];

		}
			
			rowItems = new ArrayList<RowItem>();
	        for (int j = 0; j < titles.length; j++) {
	            RowItem item = new RowItem( titles[j], arr_description[j]);
	            rowItems.add(item);
	        }
					
			ListView remindView= (ListView)findViewById(R.id.addReminderView);
			adpt = new ReminderAdapter(this, R.layout.reminder_item, rowItems);
			remindView.setAdapter(adpt);

			TextView title =(TextView) findViewById(R.id.title);
			month= (GregorianCalendar) GregorianCalendar.getInstance();
			title.setText(android.text.format.DateFormat.format("dd MMMM yyyy", month));
		
		ImageView AddReminder = (ImageView) findViewById(R.id.b_addreminder);
		ImageButton monthView = (ImageButton) findViewById(R.id.b_monthView);
        ImageButton dayView = (ImageButton) findViewById(R.id.b_todayView);
        ImageButton weekView = (ImageButton) findViewById(R.id.b_weekView);
        ImageButton AllView = (ImageButton) findViewById(R.id.b_allView); 
        TextView topText =(TextView)findViewById(R.id.top_text);
        topText.setText("All Reminders");
        
        AllView.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				showToast("this is All View");
			}

			
		});
        
        dayView.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent= new Intent(ReminderView.this, DayView.class);
				startActivity(intent);
				finish();
			}

			
		});

        weekView.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent= new Intent(ReminderView.this, WeekView.class);
				startActivity(intent);
				finish();
			}
		});

		monthView.setOnClickListener(new ImageButton.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent= new Intent(ReminderView.this, MonthView.class);
				startActivity(intent);
			finish();
			}
		});
		
		AddReminder.setOnClickListener(new ImageView.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent= new Intent(ReminderView.this, AddReminder.class);
				startActivity(intent);
				finish();
			}
		});
		
		remindView.setOnItemClickListener(new ListView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg, View v, int position,long id) {
			//	((ReminderAdapter) arg.getAdapter()).setSelected(v);
	//			String selectedValue = (String) selectedValue.
				Intent i = new Intent(ReminderView.this,SingleReminder.class);
				startActivity(i);
			}
			
		});
		remindView.setLongClickable(true);
		remindView.setOnItemLongClickListener(new ListView.OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
				final Dialog dialog = new Dialog(context);
				dialog.setContentView(R.layout.delete_dialog);
				dialog.setTitle("Dialog");
				dialog.show();
				@SuppressWarnings("unused")
				Button b1 = (Button)findViewById(R.id.dialogButtonDelete);
				@SuppressWarnings("unused")
				Button b2 = (Button)findViewById(R.id.dialogButtonCancel);
		/*		b2.setOnClickListener(new Button.OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
			*/
				
				
				return true;
			}
			
		});
	}
		
	private void getReminderDataFromDB()
    {
     datasource = new ReminderDataSource(this);
     datasource.open();
     
     Log.d("Reading: ", "Reading all Remnders.."); 
     List<ReminderDAO> reminders = datasource.getAllActiveReminders();
     
     arr_description = new String[reminders.size()];
     String[] temp = new String[reminders.size()];
     arr_about = new String[reminders.size()];
     arr_date = new String[reminders.size()];
     arr_time = new String[reminders.size()];
     titles = new String[reminders.size()];
     for (i=0;i<reminders.size();i++){
      ReminderDAO objReminderDAO = reminders.get(i);
      temp[i] = objReminderDAO.getReminderDescription();
      
      arr_description[i] = temp[i].substring(0, Math.min(temp[i].length(),16))+"...";
      switch (objReminderDAO.getAboutId())
      {
      case 1:    
       arr_about[i] =  "Birthday";
       break;
      case 2:
       arr_about[i] =  "Anniversary";
       break;
      case 3:
       arr_about[i] =  "Events";
       break;
      case 4:
       arr_about[i] =  "Appointment";
       break;
      }//end switch
      
      arr_date[i] = objReminderDAO.getReminderAtDate();
      arr_time[i] = objReminderDAO.getReminderAtTime();
            
     }//end for
     datasource.close(); 
    }	
		
		
		
		
	
	
	
	
	 public boolean onCreateOptionsMenu(Menu menu)
	    {
	        MenuInflater menuInflater = getMenuInflater();
	        menuInflater.inflate(R.layout.menu, menu);
	        return true;
	    }

		 @Override
		public boolean onOptionsItemSelected(MenuItem item)
		    {
		         
		        switch (item.getItemId())
		        {
		        case R.id.menu_reminder:
		        	Intent intent= new Intent(ReminderView.this, AddReminder.class);
					startActivity(intent);
		    		return true;
		        case R.id.menu_setting:
		        	Intent intent1= new Intent(ReminderView.this, Setting.class);
					startActivity(intent1);
		        	return true;
		 
		        case R.id.menu_exit:
		            Toast.makeText(ReminderView.this, "Exit is Selected", Toast.LENGTH_SHORT).show();
		            return true;
		 
		        default:
		            return super.onOptionsItemSelected(item);
		        }
		    }
		
	
}
