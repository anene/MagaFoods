package com.example.ajinkya.magafoods;

import android.os.Parcelable;
import android.os.Parcel;

/**
 * Created by ajinkya on 5/24/15.
 */
public class Business implements Parcelable {

    final String name;
    final String imageUrl;
    final double latitude;
    final double longitude;

    /**
     * Constructs a Business object
     * @param name name
     * @param photoUrl Link to Photo
     * @param latitude latitude coordinate
     * @param longitude longitude coordinate
     */
    public Business(String name, String photoUrl, String latitude, String longitude)
    {
        this.name = name;
        this.imageUrl = photoUrl;
        this.latitude = Double.parseDouble(latitude);
        this.longitude = Double.parseDouble(longitude);
    }

    /**
     * returns the latitutde
     * @return latitude
     */
    public double getLatitude()
    {
        return latitude;
    }

    @Override
    public String toString()
    {
        return name;
    }

    /**
     * returns the longitude
     * @return longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * returns the photo url
     * @return photoUrl
     */
    public String getPhotoUrl()
    {
        return imageUrl;
    }

    protected Business(Parcel in) {
        name = in.readString();
        imageUrl = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

        @Override
        public int describeContents() {
        return 0;
    }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(imageUrl);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }

        @SuppressWarnings("unused")
        public static final Parcelable.Creator<Business> CREATOR = new Parcelable.Creator<Business>() {
            @Override
            public Business createFromParcel(Parcel in) {
                return new Business(in);
            }

            @Override
            public Business[] newArray(int size) {
                return new Business[size];
            }
        };
    }
