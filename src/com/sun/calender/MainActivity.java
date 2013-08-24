package com.sun.calender;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.sun.calender.SimpleGestureFilter.SimpleGestureListener;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements SimpleGestureListener {
	
	private GregorianCalendar month, itemmonth;
	private CalenderAdapter adapter;
	private Handler handler;
	int i=0;
	
	private ArrayList<String> items;
	private SimpleGestureFilter detector; 


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Locale.setDefault(Locale.US);
		
		GridView gridview=(GridView)findViewById(R.id.calenderView);
		TextView title =(TextView) findViewById(R.id.title);
		
		ImageView eventview = (ImageView) findViewById(R.id.eventsView);
		ImageView refreshCalendar = (ImageView) findViewById(R.id.Refresh_calendar);
		ImageView addReminder = (ImageView) findViewById(R.id.add_reminder);
			
		detector = new SimpleGestureFilter(this,this);
			
		month= (GregorianCalendar) GregorianCalendar.getInstance();
		itemmonth=(GregorianCalendar)month.clone();
		
		items= new ArrayList<String>();
		adapter= new CalenderAdapter(this,month);
		
		gridview.setAdapter(adapter);
		
		handler = new Handler();
		handler.post(calendarUpdater);
		
		title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));
		
		
		refreshCalendar.setOnClickListener(new ImageView.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent= new Intent(MainActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
		eventview.setOnClickListener(new ImageView.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent= new Intent(MainActivity.this, ReminderView.class);
				startActivity(intent);
			}
		});
		
		addReminder.setOnClickListener(new ImageView.OnClickListener(){
			@Override
			public void onClick(View V){
				Intent intent= new Intent(MainActivity.this,AddReminder.class);
				startActivity(intent);
				
		}
		});
		
		
		
		gridview.setOnItemClickListener(new GridView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

				((CalenderAdapter) parent.getAdapter()).setSelected(v);
				String selectedGridDate = CalenderAdapter.dayString.get(position);
				String[] separatedTime = selectedGridDate.split("-");
				String gridvalueString = separatedTime[2].replaceFirst("^0*","");
				int gridvalue = Integer.parseInt(gridvalueString);
				
				if ((gridvalue > 10) && (position < 8)) {
					setPreviousMonth();
					refreshCalendar();
				} else if ((gridvalue < 7) && (position > 28)) {
					setNextMonth();
					refreshCalendar();
				}
				((CalenderAdapter) parent.getAdapter()).setSelected(v);

				showToast(selectedGridDate);
					
			}
		});
		
		gridview.setLongClickable(true);
		
		gridview.setOnItemLongClickListener(new GridView.OnItemLongClickListener(){
			public boolean onItemLongClick(AdapterView<?> parent, View v,	int position, long id) {

				((CalenderAdapter) parent.getAdapter()).setSelected(v);
				String selectedGridDate = CalenderAdapter.dayString.get(position);
				String[] separatedTime = selectedGridDate.split("-");
				String gridvalueString = separatedTime[2].replaceFirst("^0*","");
				int gridvalue = Integer.parseInt(gridvalueString);
				
				if ((gridvalue > 10) && (position < 8)) {
					setPreviousMonth();
					refreshCalendar();
				} else if ((gridvalue < 7) && (position > 28)) {
					setNextMonth();
					refreshCalendar();
				}
				((CalenderAdapter) parent.getAdapter()).setSelected(v);

			//	showToast(selectedGridDate);
				
				Intent intent= new Intent(MainActivity.this, AddReminder.class);
				intent.putExtra("gridDate",selectedGridDate);
				startActivity(intent);
				return true;
				
			}
		});

	// startService(new Intent(getBaseContext(), ReminderService.class));
		
//		
		 
	     //this.startService(serviceIntent);
		
		CreateRepeatingBroadcastAlarm();
		
		
		
	}
	
	 public void CreateRepeatingBroadcastAlarm() {
		 
		 AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		  Intent intent = new Intent(this, ReminderBroadcastReceiver.class);
		  PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,intent, PendingIntent.FLAG_CANCEL_CURRENT);
		  manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
		    (5 * 1000), pendingIntent);
		 }

		
	public void CreateRepeatingServiceAlarm()
	{
		//Calendar cal = Calendar.getInstance();
//		 Intent serviceIntent = new Intent(this,ReminderService.class);
//		 mAlarmIntent = PendingIntent.getService(this, 0, serviceIntent, 0);
//		 AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
//		 long interval = 5*1000;
//		 manager.setRepeating(AlarmManager.RTC_WAKEUP,
//				 cal.getTimeInMillis(),
//				 interval,
//				 mAlarmIntent);
		
		
		
		 
	}

	

	@Override 
	 public boolean dispatchTouchEvent(MotionEvent me){ 
	   this.detector.onTouchEvent(me);
	  return super.dispatchTouchEvent(me); 
	 }

	public void onSwipe(int direction) {
	  @SuppressWarnings("unused")
	String str = "";
	  
	  switch (direction) {
	  
	  case SimpleGestureFilter.SWIPE_RIGHT : str = "Swipe Right";
	  setPreviousMonth();
	  refreshCalendar();
	  break;
	  
	  case SimpleGestureFilter.SWIPE_LEFT :  str = "Swipe Left";
	  setNextMonth();
	  refreshCalendar();
	  break;
	  
	  case SimpleGestureFilter.SWIPE_DOWN :  str = "Swipe Down";
	  break;
	  case SimpleGestureFilter.SWIPE_UP :    str = "Swipe Up";
	  break;
	                                           
	  } 
	  
	 }

	 public void onDoubleTap() {
	   showToast("Double Tap");
		 // Toast.makeText(this, "Double Tap", Toast.LENGTH_SHORT).show(); 
	 }

	
		protected void setNextMonth() {
			if (month.get(GregorianCalendar.MONTH) == month.getActualMaximum(GregorianCalendar.MONTH)) {
				month.set((month.get(GregorianCalendar.YEAR) + 1),month.getActualMinimum(GregorianCalendar.MONTH), 1);
			} 
			else {
				month.set(GregorianCalendar.MONTH,month.get(GregorianCalendar.MONTH) + 1);
			}

		}

		protected void setPreviousMonth() {
			if (month.get(GregorianCalendar.MONTH) == month.getActualMinimum(GregorianCalendar.MONTH)) {
				month.set((month.get(GregorianCalendar.YEAR) - 1),month.getActualMaximum(GregorianCalendar.MONTH), 1);
			} 
			else {
				month.set(GregorianCalendar.MONTH,month.get(GregorianCalendar.MONTH) - 1);
			}

		}

		protected void showToast(String string){
			Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
		}

		
		public void refreshCalendar() {
			TextView title = (TextView) findViewById(R.id.title);

			adapter.refreshDays();
			adapter.notifyDataSetChanged();
			handler.post(calendarUpdater); // generate some calendar items

			title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));
		}

		public Runnable calendarUpdater = new Runnable() {

			@Override
			public void run() {
				items.clear();

				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd",Locale.US);
				@SuppressWarnings("unused")
				String itemvalue;
				for (int i = 0; i < 7; i++) {
					itemvalue = df.format(itemmonth.getTime());
					itemmonth.add(GregorianCalendar.DATE, 1);
					items.add("2012-09-12");
					items.add("2012-10-07");
					items.add("2012-10-15");
					items.add("2012-10-20");
					items.add("2012-11-30");
					items.add("2012-11-28");
				}

				adapter.setItems(items);
				adapter.notifyDataSetChanged();
			}
		};
		
	

		@Override
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
		        	Intent intent= new Intent(MainActivity.this, AddReminder.class);
					startActivity(intent);
		    		return true;
		        case R.id.menu_setting:
		        	Intent intent1= new Intent(MainActivity.this, Setting.class);
					startActivity(intent1);
		        	return true;
		 
		        case R.id.menu_exit:
		            //Toast.makeText(MainActivity.this, "Search is Selected", Toast.LENGTH_SHORT).show();
		            showToast("Search Selected");
		            return true;
		 
		        default:
		            return super.onOptionsItemSelected(item);
		        }
		    }    
		

}
