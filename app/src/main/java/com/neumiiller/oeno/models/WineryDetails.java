package com.neumiiller.oeno.models;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

import com.neumiiller.oeno.models.Winery;
import com.neumiiller.oeno.models.WineryHours;

/**
 * @author QJN on 2014-10-14.
 */
public class WineryDetails {
	private String overview;
	private String tours;
	private WineryHours hours;
	private String contact;

	public String getTourString(){
		return tours;
	}
}