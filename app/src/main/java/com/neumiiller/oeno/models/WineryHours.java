package com.neumiiller.oeno.models;

import java.util.TreeMap;
import com.neumiiller.oeno.models.WineryTime;

/**
 * @author QJN on 2014-10-09.
 */
public class WineryHours extends TreeMap<String, WineryTime> {

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
		String day = "Sunday";

		if (key == "Mon") {
			day = "Monday";
		} else if (key == "Tue") {
			day = "Tuesday";
		} else if (key == "Wed") {
			day = "Wednesday";
		} else if (key == "Thu") {
			day = "Thursday";
		} else if (key == "Fri") {
			day = "Friday";
		} else if (key == "Sat") {
			day = "Saturday";
		}

		return day;
	}
}