package com.revature.classes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateRange {

	public String getDateRange()
	{
		Date date = new Date();
	    Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
	    
	    c.add(Calendar.DATE, -i + 1);
	    Date start = c.getTime();
	    c.add(Calendar.DATE, 4);
	    Date end = c.getTime();
	    
	    SimpleDateFormat startformat = new SimpleDateFormat("MMM dd");
	    String range1 = startformat.format(start);
	    
	    SimpleDateFormat endformat = new SimpleDateFormat("MMM dd");
	    String range2 = endformat.format(end);
	    
	    String daterange = (range1 + " - " + range2);
	    System.out.println(daterange);
	    
	    return daterange;
	}
}