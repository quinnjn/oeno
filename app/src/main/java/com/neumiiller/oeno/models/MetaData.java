package com.neumiiller.oeno.models;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author QJN on 2014-10-09.
 */
public class MetaData {
	@SerializedName("bg_color")
	private String backgroundColor = "#ffffff";

	@SerializedName("text_color")
	private String textColor = "#222222";

	public MetaData() {
	}

	public void setBackgroundColor(String backgroundColor){
		this.backgroundColor = backgroundColor;
	}
	
	public void setTextColor(String textColor){
		this.textColor = textColor;
	}
	
	public String getBackgroundColor() {
		return backgroundColor;
	}

	public String getTextColor() {
		return textColor;
	}

}