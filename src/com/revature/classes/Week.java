package com.revature.classes;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
import com.revature.classes.*;

public class Week implements java.io.Serializable
{
	private String daterange;
	private int javacount;
	private int dotNetCount;
	private int sdetcount;
	private Date date;
	private Date startdate;
	private Date enddate;
	
	public Date getDate()
	{
		return date;
	}
	public void setDate(Date date)
	{
		this.date = date;
	}
	public Date getStartdate()
	{
		return startdate;
	}
	public void setStartdate(Date startdate)
	{
		this.startdate = startdate;
	}
	public Date getEnddate()
	{
		return enddate;
	}
	public void setEnddate(Date enddate)
	{
		this.enddate = enddate;
	}
	public String getDaterange()
	{
		return daterange;
	}
	public void setDaterange(String daterange)
	{
		this.daterange = daterange;
	}
	public int getJavacount()
	{
		return javacount;
	}
	public void setJavacount(int javacount)
	{
		this.javacount = javacount;
	}
	public int getDotNetCount()
	{
		return dotNetCount;
	}
	public void setDotNetCount(int dotNetCount)
	{
		this.dotNetCount = dotNetCount;
	}
	public int getSdetcount()
	{
		return sdetcount;
	}
	public void setSdetcount(int sdetcount)
	{
		this.sdetcount = sdetcount;
	}
	@Override
	public String toString()
	{
		return "Week [daterange=" + daterange + ", javacount=" + javacount + ", dotNetCount=" + dotNetCount
				+ ", sdetcount=" + sdetcount + "]";
	}
	
	
	
}
