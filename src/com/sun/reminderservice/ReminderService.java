package com.sun.reminderservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class ReminderService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	  public void onCreate() {
		super.onCreate();
		android.os.Debug.waitForDebugger();
			}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		/* now let's wait until the debugger attaches */
        //android.os.Debug.waitForDebugger();
		//showToast("wow");
		
		
		this.stopSelf();
		
		return START_NOT_STICKY;

		
		}
	protected void showToast(String string){
		Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
	}

	
	@Override
	public void onDestroy() {
	
	super.onDestroy();
	

	}

	
	
	
}
