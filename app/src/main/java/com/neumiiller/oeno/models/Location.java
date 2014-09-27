package com.neumiiller.oeno.models;

/**
 * @author QJN on 2014-09-15.
 */
public class Location {
    private String address;
    private String city;
    private String telephone;
    private double latitude;
    private double longitude;

    public String getCity() {
        if(city == null){
            city = "";
        }
        return city;
    }

    public String getDrivingTime() {
        return "0";
    }

    public int getDistance() {
        return 1;
    }

    public String getAddress() {
        if(address == null){
            address = "";
        }
        return address;
    }
}
