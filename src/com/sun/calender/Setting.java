package com.sun.calender;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class Setting extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_setting);
		Locale.setDefault(Locale.US);
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
		        	Intent intent= new Intent(Setting.this, AddReminder.class);
					startActivity(intent);
		    		return true;
		        case R.id.menu_setting:
		        	Intent intent1= new Intent(Setting.this, Setting.class);
					startActivity(intent1);
		        	return true;
		 
		        case R.id.menu_exit:
		            Toast.makeText(Setting.this, "Exit is Selected", Toast.LENGTH_SHORT).show();
		            return true;
		 
		        default:
		            return super.onOptionsItemSelected(item);
		        }
		    }
		
	
}
