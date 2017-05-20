package com.revature.classes;

public class Month implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	private String month;
	private int javaCount;
	private int dotNetCount;
	private int jtaCount;
	
	public String getMonth()
	{
		return month;
	}
	public void setMonth(String month)
	{
		this.month = month;
	}
	public int getJavacount()
	{
		return javaCount;
	}
	public void setJavacount(int javacount)
	{
		this.javaCount = javacount;
	}
	public int getDotNetCount()
	{
		return dotNetCount;
	}
	public void setDotNetCount(int dotNetCount)
	{
		this.dotNetCount = dotNetCount;
	}
	public int getJtacount()
	{
		return jtaCount;
	}
	public void setJtaCount(int jtaCount)
	{
		this.jtaCount = jtaCount;
	}
	@Override
	public String toString()
	{
		return "Month [month=" + month + ", javacount=" + javaCount + ", dotNetCount=" + dotNetCount + ", jtacount="
				+ jtaCount + "]";
	}
	
}