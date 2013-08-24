package com.sun.dbhelper;

import java.util.Date;

@SuppressWarnings("unused")
public class ReminderDAO {
	  private long KEY_id;
	  private int VSDAboutId;
	  private String VSDReminderDescription;
	  private String VSDReminderAtDate;
	  private String VSDReminderAtTime;
	  private long VSDCycleId;
	  private int  VSDStatus;
	
	
	  public long getReminderId() {
	    return KEY_id;
	  }

	  public void setReminderId(long id) {
	    this.KEY_id = id;
	  }

	  public int getAboutId() {
	    return VSDAboutId;
	  }

	  public void setAboutId(int aboutid) {
	    this.VSDAboutId = aboutid;
	  }

	  public String getReminderDescription(){
			return VSDReminderDescription;
		}
	  
	  public void setReminderDescription(String ReminderDescription){
		  this.VSDReminderDescription = ReminderDescription;
	  }

	  public String getReminderAtDate(){
		  return this.VSDReminderAtDate;
	  }
	  
	  public void setReminderAtDate(String ReminderFrom)
	  {
		  this.VSDReminderAtDate = ReminderFrom;
	  }
	  
	  public String getReminderAtTime(){
		  return this.VSDReminderAtTime;
	  }
	  
	  public void setReminderAtTime(String ReminderTo)
	  {
		  this.VSDReminderAtTime = ReminderTo;
	  }
	  
	  public long getCycleId() {
		    return VSDCycleId;
		  }

	 public void setCycleId(long CycleId) {
		    this.VSDCycleId = CycleId;
		  }

	 public int getStatus() {
		    return VSDStatus;
		  }

	 public void setStatus(int StatusId) {
		    this.VSDStatus = StatusId;
		  }
	  
	  
	  
	  
	  
	  
//	  // Will be used by the ArrayAdapter in the ListView
//	  @Override
//	  public String toString() {
//	    return comment;
//	  }
}
