package com.sun.calender;


import com.sun.reminderservice.ReminderService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ReminderBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		
		context.startService(new Intent(context, ReminderService.class));
	
	}

	
}
