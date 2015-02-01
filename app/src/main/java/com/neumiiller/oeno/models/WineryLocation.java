package com.neumiiller.oeno.models;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import java.text.DecimalFormat;

/**
 * @author QJN on 2014-09-15.
 */
public class WineryLocation implements Parcelable {
    private String address;
    private String city;
    private String telephone;
    private double latitude;
    private double longitude;
    private String distance;
    private String drivingTime;
    private boolean locationUpdated = false;

    public String getCity() {
        if (city == null) {
            city = "";
        }
        return city;
    }

    public String getDrivingTime() {
        if (TextUtils.isEmpty(drivingTime)) {
            return "0 min drive";
        }
        return drivingTime;
    }

    public boolean isLocationUpdated() {
        return locationUpdated;
    }

    public String getDistance() {
        if (TextUtils.isEmpty(distance)) {
            return "0 km";
        }
        return distance;
    }

    public String getAddress() {
        if (address == null) {
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
        this.locationUpdated = km > 0;
    }

    private void setDistance(float distanceKm) {
        this.distance = String.format("%1.1f km", distanceKm);
    }

    private void setDrivingTime(float drivingTime) {
        DecimalFormat drivingTimeFormatter = new DecimalFormat("#");
        double drivingTimeCeil = Math.ceil(drivingTime);
        String stringPlaceholder = " min drive";
        this.drivingTime = drivingTimeFormatter.format(drivingTimeCeil) + stringPlaceholder;
    }

    protected WineryLocation(Parcel in) {
        address = in.readString();
        city = in.readString();
        telephone = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        distance = in.readString();
        drivingTime = in.readString();
        locationUpdated = in.readInt() == 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(address);
        dest.writeString(city);
        dest.writeString(telephone);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(distance);
        dest.writeString(drivingTime);
        dest.writeInt(locationUpdated ? 0 : 1);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<WineryLocation> CREATOR = new Parcelable.Creator<WineryLocation>() {
        @Override
        public WineryLocation createFromParcel(Parcel in) {
            return new WineryLocation(in);
        }

        @Override
        public WineryLocation[] newArray(int size) {
            return new WineryLocation[size];
        }
    };

    public String getTelephone() {
        return telephone;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}