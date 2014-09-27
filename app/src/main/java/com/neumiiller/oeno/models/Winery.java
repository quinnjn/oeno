package com.neumiiller.oeno.models;

import android.text.TextUtils;

/**
 * @author QJN on 2014-09-15.
 */
public class Winery {
    private String name;
    private Location location;

    public String getCity() {
        return location.getCity();
    }

    public String getDrivingTime() {
        return location.getDrivingTime();
    }

    public int getDistance() {
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
}
