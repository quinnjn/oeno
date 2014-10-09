package com.neumiiller.oeno.unit;

import junit.framework.TestCase;

import com.neumiiller.oeno.models.WineryTime;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class TestWineryTime extends TestCase {

	public void testSmoke(){
		WineryTime time = new WineryTime(100,200);
		assertNotNull(time);
	}
	
	public void testClosed(){
		int open = 1000;
		int close = 900;
		String expected = "Closed";
		
		WineryTime time = new WineryTime(open, close);
		String actual = time.getHourString();
		assertEquals(expected, actual);
	}
	
	public void testOpen(){
		int open = 900;
		int close =1000;
		String expected = "9:00 am - 10:00 am";
		
		WineryTime time = new WineryTime(open, close);
		String actual = time.getHourString();
		assertEquals(expected, actual);
	}
	
	public void testOpen9amTo10pm(){
		int open = 900;
		int close = 2200;
		String expected = "9:00 am - 10:00 pm";
		
		WineryTime time = new WineryTime(open, close);
		String actual = time.getHourString();
		assertEquals(expected, actual);
	}
	
	public void testOpen912amTo1012pm(){
		int open = 912;
		int close = 2212;
		String expected = "9:12 am - 10:12 pm";
		
		WineryTime time = new WineryTime(open, close);
		String actual = time.getHourString();
		assertEquals(expected, actual);
	}
	
	public void testOpen12pmTo1pm(){
		int open = 1200;
		int close = 1300;
		String expected = "12:00 pm - 1:00 pm";
		
		WineryTime time = new WineryTime(open, close);
		String actual = time.getHourString();
		assertEquals(expected, actual);
	}
}