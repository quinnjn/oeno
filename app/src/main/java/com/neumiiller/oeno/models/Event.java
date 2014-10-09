package com.neumiiller.oeno.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author QJN on 2014-10-09.
 */
public class Event implements Parcelable {
	private String winery;

    protected Event(Parcel in) {
        winery = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(winery);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };
}