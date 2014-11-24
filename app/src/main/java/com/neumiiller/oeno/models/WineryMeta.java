package com.neumiiller.oeno.models;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

import com.neumiiller.oeno.models.Winery;

/**
 * @author QJN on 2014-10-14.
 */
public class WineryMeta {
	private int priority;
	private boolean participating;
	private WineryDetails details;

	public int getPriority(){
		return priority;
	}

    public boolean isParticipating(){
        return participating;
    }

	public WineryDetails getDetails(){
		return details;
	}
}