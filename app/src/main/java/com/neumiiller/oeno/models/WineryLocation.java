package com.neumiiller.oeno.models;

import android.location.Location;
import android.text.TextUtils;

import java.text.DecimalFormat;

/**
 * @author QJN on 2014-09-15.
 */
public class WineryLocation {
    private String address;
    private String city;
    private String telephone;
    private double latitude;
    private double longitude;
    private String distance;
    private String drivingTime;

    public String getCity() {
        if(city == null){
            city = "";
        }
        return city;
    }

    public String getDrivingTime() {
        if(TextUtils.isEmpty(drivingTime)){
            return "0 min drive";
        }
        return drivingTime;
    }

    public String getDistance() {
        if(TextUtils.isEmpty(distance)){
            return "0 km";
        }
        return distance;
    }

    public String getAddress() {
        if(address == null){
            address = "";
        }
        return address;
    }

    private static final int KM_TO_MIN = 2;

    public void updateLocation(Location incomingLocation) {
        Location currentLocation = new Location("current");
        currentLocation.setLatitude(latitude);
        currentLocation.setLongitude(longitude);

        float km = (incomingLocation.distanceTo(currentLocation) / 1000);
        setDistance(km);

        float drivingTime = km * KM_TO_MIN;
        setDrivingTime(drivingTime);
    }

    private void setDistance(float distanceKm){
        this.distance = String.format("%1.1f km", distanceKm);
    }

    private void setDrivingTime(float drivingTime){
        DecimalFormat drivingTimeFormatter = new DecimalFormat("#");
        double drivingTimeCeil = Math.ceil(drivingTime);
        String stringPlaceholder = " min drive";
        this.drivingTime = drivingTimeFormatter.format(drivingTimeCeil) + stringPlaceholder;
    }
}
