package com.sun.calender;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Text;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.sun.calender.R;

import com.sun.dbhelper.*;


public class AddReminder extends Activity {
	
	SimpleDateFormat  sourceformat = new SimpleDateFormat("dd-MMM-yyyy");  
	 SimpleDateFormat  targetformat = new SimpleDateFormat("yyyy-MM-dd");
	
	public GregorianCalendar month;
	private ReminderDataSource datasource;
	private List<AboutDAO> list_Abouts;
	int i=0;
	private Button timeBtn;
	
	private Button dateBtn;
	DateFormat formatDateTime=DateFormat.getDateTimeInstance();
	Calendar date= Calendar.getInstance(), time=Calendar.getInstance();
	//private TextView timeLabel;
	//private AdapterView<SpinnerAdapter> spinner;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addreminder);
		
		datasource = new ReminderDataSource(this);
		datasource.open();
		retrieveAboutsfromDB();
		

		TextView title =(TextView) findViewById(R.id.title);
		month= (GregorianCalendar) GregorianCalendar.getInstance();
		title.setText(android.text.format.DateFormat.format("dd MMMM yyyy", month));
		
		
		Spinner spinner1 = (Spinner)findViewById(R.id.spinnerRepeat);
		ArrayAdapter<String> spinnerAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
		spinnerAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner1.setAdapter(spinnerAdapter1);
		
		//Integer indexValue = Spinner.getSelectedItemPosition();
		//	String Text = spinner.getSelectedItem().toString();
	/*		if(yy1 =="Events"|| yy1=="Appointment"){
	//		notify.setVisibility(View.VISIBLE);
			spinner1.setVisibility(View.VISIBLE);
		}
		else{
	//		notify.setVisibility(View.GONE);
			spinner1.setVisibility(View.GONE);
		}
	/*	    
	   
	//	Spinner spinnernotify = (Spinner) findViewById(R.id.spinnerRepeat);
	//	Spinner spinnerAbout = (Spinner) findViewById(R.id.spinnerAbout);
	//	TextView notify = (TextView)findViewById(R.id.text);
		
		
	*/
	
		 
		
		
		timeBtn=(Button)findViewById(R.id.time_button);
	     updateTime();
	        
	     dateBtn=(Button)findViewById(R.id.date_button);
	     updateDate();  
		 
         Intent intent = getIntent();
         Bundle b = intent.getExtras();
         if(b!=null)
         {
             String item1 =(String) b.get("gridDate");
             dateBtn.setText(item1);
         }	
		
		
		ImageView eventview = (ImageView) findViewById(R.id.eventsView);
		eventview.setOnClickListener(new ImageView.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent= new Intent(AddReminder.this, ReminderView.class);
				startActivity(intent);
			}
		});
		
        
	}
	
/*	
	@SuppressLint("SimpleDateFormat")
	public void DoSubmit(View v)
	{
		ReminderDAO reminder = new ReminderDAO();
		
		
		
		@SuppressWarnings("unused")
		Spinner spinner = (Spinner)findViewById(R.id.spinnerAbout);
//		AboutDAO dd = (AboutDAO)spinner.getSelectedItem();
		reminder.setAboutId(1);
//		int selectedaboutid = getSelectedAboutId();
//		reminder.setAboutId(selectedaboutid);
		

		
		EditText ed =   (EditText)findViewById(R.id.description);
		reminder.setReminderDescription(ed.getText().toString());
		
		Button b1= (Button)findViewById(R.id.time_button);
		reminder.setReminderAtTime(b1.getText().toString());

		Button b2= (Button)findViewById(R.id.date_button);
		reminder.setReminderAtDate(b2.getText().toString());
		
		
		reminder.setCycleId(3);
		reminder.setStatus(1);
		
		datasource.createReminder(reminder);
		
		Toast.makeText(this, "Reminder Is Set", Toast.LENGTH_SHORT).show();
		this.finish();
	
	}
	*/
	
	@SuppressLint("SimpleDateFormat")
	public void DoSubmit(View v)
	{
		ReminderDAO reminder = new ReminderDAO();
		
		
		@SuppressWarnings("unused")
		Spinner spinner = (Spinner)findViewById(R.id.spinnerAbout);
		
		reminder.setAboutId(1);
		int selectedaboutid = getSelectedAboutId();
		reminder.setAboutId(selectedaboutid);
				
		EditText ed =   (EditText)findViewById(R.id.description);
		reminder.setReminderDescription(ed.getText().toString());
		
		Button btnTime= (Button)findViewById(R.id.time_button);
		reminder.setReminderAtTime(btnTime.getText().toString());

		Button btnDate= (Button)findViewById(R.id.date_button);
		Date tempdate;
		
		try {
			tempdate = sourceformat.parse(btnDate.getText().toString());
			reminder.setReminderAtDate(targetformat.format(tempdate));
			
			
		} catch (ParseException e) {
			Log.e(getPackageName(), e.getMessage());
			e.printStackTrace();
		}  
		    
		setCycle(selectedaboutid, reminder);
		reminder.setStatus(1);
		
		datasource.createReminder(reminder);
		
		Toast.makeText(this, "Reminder Is Set", Toast.LENGTH_SHORT).show();
		this.finish();
		
	}
	
	public void setCycle(int selectedaboutid,ReminderDAO reminder)
	{
		if(selectedaboutid == 1)
		{
			reminder.setCycleId(1);// yearly
			
		}
		
		if(selectedaboutid == 2)
		{
			reminder.setCycleId(1);// yearly
			
		}
		
		if(selectedaboutid == 3)
		{
			reminder.setCycleId(2);// monthly
			
		}
		
		if(selectedaboutid == 4)
		{
			reminder.setCycleId(3);// weekly
			
		}
	}
	
	
	public int getSelectedAboutId()
	 {
	  
	 
	  
	  Spinner spinner = (Spinner)findViewById(R.id.spinnerAbout);
	  return spinner.getSelectedItemPosition() + 1;
	  
	
	 }
	
	public void chooseDate(View v){
    	new DatePickerDialog(AddReminder.this, d, date.get(Calendar.YEAR),date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH)).show();
    }
    public void chooseTime(View v){
    	new TimePickerDialog(AddReminder.this, t, time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), true).show();
    }
    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
			date.set(Calendar.YEAR,year);
			date.set(Calendar.MONTH, monthOfYear);
			date.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			updateDate();
		}
	};
	TimePickerDialog.OnTimeSetListener t=new TimePickerDialog.OnTimeSetListener() {
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			time.set(Calendar.HOUR_OF_DAY, hourOfDay);
			time.set(Calendar.MINUTE,minute);
			updateTime();
		}
	};
	private void updateTime() {
		timeBtn.setText(android.text.format.DateFormat.format("hh:mm aa",time.getTime()));
	}
	private void updateDate() {
		dateBtn.setText(android.text.format.DateFormat.format("dd-MMM-yyyy", date.getTime()));
	}
	
	private void retrieveAboutsfromDB()
	{
		setList_Abouts(datasource.getAbouts());
		if(getList_Abouts().isEmpty())
		{
			datasource.PopulateAboutsTable();
			setList_Abouts(datasource.getAbouts());
		}
			
		Spinner spinner = (Spinner)findViewById(R.id.spinnerAbout);
		ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
		spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(spinnerAdapter);

		
		Iterator<AboutDAO> iterator = list_Abouts.iterator();
		while (iterator.hasNext()) {
			spinnerAdapter.add(iterator.next().getAboutDescription());
		}
		
		spinnerAdapter.notifyDataSetChanged();
		
	}


	public List<AboutDAO> getList_Abouts() {
		return list_Abouts;
	}


	public void setList_Abouts(List<AboutDAO> list_Abouts) {
		this.list_Abouts = list_Abouts;
	}
	
}

	



