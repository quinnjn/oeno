package com.neumiiller.oeno.unit;

import junit.framework.TestCase;

import com.neumiiller.oeno.models.WineryHours;
import com.neumiiller.oeno.models.WineryTime;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class TestWineryHour extends TestCase {

	public void testSmoke(){
		WineryTime time = new WineryTime(0,0);
		WineryHours hours = new WineryHours();
		hours.put("Mon", time);
		assertNotNull(time);
	}
	
	public void testFullWeek(){
		WineryTime time = new WineryTime(900,1000);
		String[] days = {
				"Mon,Tue,Wed,Thu,Fri,Sat",
				"Sun"
		};
		String expected = "" +
				"Monday to Saturday " + time.getHourString() + "\n" +
				"Sunday "+ time.getHourString()
				;
		
		WineryHours hours = new WineryHours();
		for(String day : days){
			hours.put(day, time);
		}
		
		String actual = hours.getHourString();
		assertEquals(expected, actual);
	}
	
	public void testTwoDays(){
		WineryTime time = new WineryTime(900,1000);
		String[] days = {
				"Mon",
				"Sun"
		};
		String expected = "" +
				"Monday " + time.getHourString() + "\n" +
				"Sunday "+ time.getHourString()
				;
		
		WineryHours hours = new WineryHours();
		for(String day : days){
			hours.put(day, time);
		}
		
		String actual = hours.getHourString();
		assertEquals(expected, actual);
	}
	
	public void testTwoConsecutiveDays(){
		WineryTime time = new WineryTime(900,1000);
		String[] days = {
				"Mon, Tues",
				"Sun"
		};
		String expected = "" +
				"Monday and Tuesday" + time.getHourString() + "\n" +
				"Sunday "+ time.getHourString()
				;
		
		WineryHours hours = new WineryHours();
		for(String day : days){
			hours.put(day, time);
		}
		
		String actual = hours.getHourString();
		assertEquals(expected, actual);
	}
	
	public void testOneDay(){
		WineryTime time = new WineryTime(900,1000);
		String[] days = {
				"Sun"
		};
		String expected = "" +
				"Sunday "+ time.getHourString()
				;
		
		WineryHours hours = new WineryHours();
		for(String day : days){
			hours.put(day, time);
		}
		
		String actual = hours.getHourString();
		assertEquals(expected, actual);
	}
}