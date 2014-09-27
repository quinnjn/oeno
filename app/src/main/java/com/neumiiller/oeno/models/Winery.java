package com.neumiiller.oeno.models;

import android.location.Location;

/**
 * @author QJN on 2014-09-15.
 */
public class Winery {
    private String name;
    private WineryLocation location;

    private WineryLocation getWineryLocation(){
        return location;
    }

    public String getCity() {
        return location.getCity();
    }

    public String getDrivingTime() {
        return location.getDrivingTime();
    }

    public String getDistance() {
        return location.getDistance();
    }

    public String getAddress() {
        return location.getAddress();
    }

    public String getName() {
        if(name == null){
            name = "";
        }
        return name;
    }

    public void updateLocation(Location location) {
        getWineryLocation().updateLocation(location);
    }
}
