package com.neumiiller.oeno.models;

import java.util.HashMap;
import java.util.LinkedHashMap;
import com.neumiiller.oeno.models.WineryTime;

/**
 * @author QJN on 2014-10-09.
 */
public class WineryHours extends LinkedHashMap<String, WineryTime> {

	public String getHourString() {
		String hourString = "";
		for (Entry<String, WineryTime> entry : entrySet()) {
			String days = entry.getKey();
			WineryTime wineryTime = entry.getValue();

			String dayString = getDayString(days);
			String timestamp = wineryTime.getHourString();

			hourString += String.format("%s %s\n", dayString, timestamp);
		}
		hourString = hourString.trim();
		return hourString;
	}
	
    public String getDayString(String days) {
        String[] dayArray = days.split(",");

        int firstIndex = 0;
        int lastIndex = dayArray.length - 1;

        String firstDay = getFullDay(dayArray[firstIndex]);
        String lastDay = getFullDay(dayArray[lastIndex]);

        String splitGrammar = (dayArray.length > 2) ? "to" : "and";

        if (dayArray.length == 1) {
            return String.format("%s:", firstDay);
        }
        else {
            return String.format(
                    "%s %s %s:",
                    firstDay,
                    splitGrammar,
                    lastDay
                    );
        }
    }

	public String getFullDay(String key) {
		HashMap<String, String> fullDayMap = new HashMap<String, String>();
		
		fullDayMap.put("Mon", "Monday");
		fullDayMap.put("Tue", "Tuesday");
		fullDayMap.put("Tues", "Tuesday"); // TODO unsure which Tue[s] to show. 
		fullDayMap.put("Wed", "Wednesday");
		fullDayMap.put("Thu", "Thursday");
		fullDayMap.put("Fri", "Friday");
		fullDayMap.put("Sat", "Saturday");
		fullDayMap.put("Sun", "Sunday");
		
		return fullDayMap.get(key);
	}
}