package com.neumiiller.oeno.models;

/**
 * @author QJN on 2014-10-09.
 */
public class WineryTime {
	private int open;
	private int close;

	public WineryTime(int open, int close){
		this.open = open;
		this.close = close;
	}

	public String getHourString(){
		if(isClosed()){
			return "Closed";
		}

		return null;
	}
	
	private boolean isClosed(){
		return close < open;
	}
}