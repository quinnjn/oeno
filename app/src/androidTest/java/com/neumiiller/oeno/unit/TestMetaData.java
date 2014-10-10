package com.neumiiller.oeno.unit;

import junit.framework.TestCase;

import com.neumiiller.oeno.models.MetaData;

public class TestMetaData extends TestCase {

	public void testSmoke(){
		MetaData meta = new MetaData();
		assertNotNull(meta);
	}

	public void testGetDefaultBgColor(){
		String expected = "#ffffff";
		MetaData meta = new MetaData();
		String actual = meta.getBackgroundColor();

		assertEquals(expected, actual);
	}

	public void testGetSetBackgroundColor(){
		String expected = "#cccccc";
		MetaData meta = new MetaData();
		meta.setBackgroundColor(expected);
		String actual = meta.getBackgroundColor();

		assertEquals(expected, actual);
	}

	public void testGetDefaultTextColor(){
		String expected = "#222222";
		MetaData meta = new MetaData();
		String actual = meta.getTextColor();

		assertEquals(expected, actual);
	}

	public void testGetSetTextColor(){
		String expected = "#cccccc";
		MetaData meta = new MetaData();
		meta.setBackgroundColor(expected);
		String actual = meta.getBackgroundColor();

		assertEquals(expected, actual);
	}
}