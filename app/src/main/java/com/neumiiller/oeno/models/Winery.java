package com.neumiiller.oeno.models;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author QJN on 2014-09-15.
 */
public class Winery implements Parcelable {
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

    protected Winery(Parcel in) {
        name = in.readString();
        location = (WineryLocation) in.readValue(WineryLocation.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeValue(location);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Winery> CREATOR = new Parcelable.Creator<Winery>() {
        @Override
        public Winery createFromParcel(Parcel in) {
            return new Winery(in);
        }

        @Override
        public Winery[] newArray(int size) {
            return new Winery[size];
        }
    };
}