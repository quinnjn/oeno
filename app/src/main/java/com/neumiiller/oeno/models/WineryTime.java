package com.neumiiller.oeno.models;

/**
 * @author QJN on 2014-10-09.
 */
public class WineryTime {
	private static final char TIME_SEPERATOR = '-';
	
	
	private class TimeHelper{
		private int hour;
		private int min;
		private String frame;
		
		public TimeHelper(int time){
			this.hour = get12Hour(time);
			this.min = getMinute(time);
			this.frame = getFrame(time);
		}
		
		private int getHour(int time){
			int hour = time/100;
			return hour;
		}
		
		private int get12Hour(int time){
			int hour = getHour(time);
			if(isPm(time) && !isNoon(hour)){
				hour -= 12;
			}
			return hour;
		}
		
		private boolean isNoon(int hour){
			return hour == 12;
		}
		
		private int getMinute(int time){
			int min = time % 100;
			return min;
		}
		
		private String getFrame(int time){
			return isPm(time) ? "pm" : "am"; 
		}

		private boolean isPm(int time){
			return getHour(time) >= 12;
		}
		
		public String getTimeStamp(){
			return String.format("%d:%02d %s", hour, min, frame);
		}
	}
	
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

		return generateHourString();
	}

    public String getTodayHours(){
        if(isClosed()){
            return "Closed";
        }
        return "Open till "+new TimeHelper(close).getTimeStamp();
    }
	
	private boolean isClosed(){
		return close < open;
	}
	
	private String generateHourString(){
		TimeHelper openTime = new TimeHelper(open);
		TimeHelper closeTime = new TimeHelper(close);
		
		String openTimeStamp = openTime.getTimeStamp();
		String closeTimeStamp = closeTime.getTimeStamp();
		
		return String.format(
				"%s %s %s", 
				openTimeStamp,
				TIME_SEPERATOR,
				closeTimeStamp
		);
	}
}