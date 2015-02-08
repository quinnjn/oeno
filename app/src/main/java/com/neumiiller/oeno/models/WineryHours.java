package com.neumiiller.oeno.models;

import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;

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

        String firstDayKey = dayArray[firstIndex].trim();
        String lastDayKey = dayArray[lastIndex].trim();

        String firstDay = getFullDay(firstDayKey);
        String lastDay = getFullDay(lastDayKey);

        String splitGrammar = (dayArray.length > 2) ? "to" : "and";

        if (dayArray.length == 1) {
            return String.format("%s:", firstDay);
        } else {
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

    public String getOpenString() {
        int dayOfWeek = Calendar.getInstance()
                .get(Calendar.DAY_OF_WEEK);
        String dayAbbrev = "";

        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                dayAbbrev = "Sun";
                break;
            case Calendar.MONDAY:
                dayAbbrev = "Mon";
                break;
            case Calendar.TUESDAY:
                dayAbbrev = "Tue";
                break;
            case Calendar.WEDNESDAY:
                dayAbbrev = "Wed";
                break;
            case Calendar.THURSDAY:
                dayAbbrev = "Thu";
                break;
            case Calendar.FRIDAY:
                dayAbbrev = "Fri";
                break;
            case Calendar.SATURDAY:
                dayAbbrev = "Sat";
                break;
        }
        for(Entry<String, WineryTime> entry : entrySet()){
            String key = entry.getKey();
            if(key.contains(dayAbbrev)){
                return entry.getValue().getTodayHours();
            }
        }
        return "";
    }
}