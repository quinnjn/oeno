package com.neumiiller.oeno.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.neumiiller.oeno.models.Event;

/**
 * @author QJN on 2014-10-09.
 */
public class Promo extends Event implements Parcelable {

    protected Promo(Parcel in) {
        super(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Promo> CREATOR = new Parcelable.Creator<Promo>() {
        @Override
        public Promo createFromParcel(Parcel in) {
            return new Promo(in);
        }

        @Override
        public Promo[] newArray(int size) {
            return new Promo[size];
        }
    };
}