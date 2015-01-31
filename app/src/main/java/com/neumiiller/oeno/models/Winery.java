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
    private WineryMeta meta;

    public boolean shouldShowLocation() {
        return location.isLocationUpdated();
    }

    private WineryLocation getWineryLocation() {
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

    public String getTelephone() {
        String rawTelephone = location.getTelephone();

        StringBuilder builder = new StringBuilder()
                .append(1)
                .append(" ")
                .append("(")
                .append(rawTelephone, 0, 3)
                .append(")")
                .append(" ")
                .append(rawTelephone, 3, 6)
                .append("-")
                .append(rawTelephone, 6, 10);

        return builder.toString();
    }

    public String getName() {
        if (name == null) {
            name = "";
        }
        return name;
    }

    public void setMeta(WineryMeta meta) {
        this.meta = meta;
    }

    public WineryMeta getMeta() {
        return this.meta;
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