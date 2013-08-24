package com.sun.calender;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootTimeAlarmReciever extends BroadcastReceiver {

	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		//Toast.makeText(context, "DDD", Toast.LENGTH_SHORT).show();
		 AlarmManager manager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		  Intent intent1 = new Intent(context, ReminderBroadcastReceiver.class);
		  PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,intent1, PendingIntent.FLAG_CANCEL_CURRENT);
		  manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
		    (5 * 1000), pendingIntent);
		 }
	


}