package com.example.oliverabad.countriescatalog.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by oliverabad on 7/7/18.
 */
public class CountryModel implements Parcelable{

    @SerializedName("Name")
    private String name;

    @SerializedName("NativeName")
    private String nativeName;

    @SerializedName("Region")
    private String region;

    @SerializedName("SubRegion")
    private String subRegion;

    @SerializedName("NativeLanguage")
    private String nativeLanguage;

    @SerializedName("Latitude")
    private String latitude;

    @SerializedName("Longitude")
    private String longitude;

    @SerializedName("CurrencyCode")
    private String currencyCode;

    @SerializedName("CurrencyName")
    private String currencyName;

    @SerializedName("CurrencySymbol")
    private String currencySymbol;

    @SerializedName("FlagPng")
    private String flagPng;

    protected CountryModel(Parcel in) {
        name = in.readString();
        nativeName = in.readString();
        region = in.readString();
        subRegion = in.readString();
        nativeLanguage = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        currencyCode = in.readString();
        currencyName = in.readString();
        currencySymbol = in.readString();
        flagPng = in.readString();
    }

    public static final Creator<CountryModel> CREATOR = new Creator<CountryModel>() {
        @Override
        public CountryModel createFromParcel(Parcel in) {
            return new CountryModel(in);
        }

        @Override
        public CountryModel[] newArray(int size) {
            return new CountryModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getNativeName() {
        return nativeName;
    }

    public String getRegion() {
        return region;
    }

    public String getSubRegion() {
        return subRegion;
    }

    public String getNativeLanguage() {
        return nativeLanguage;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public String getFlagPng() {
        return flagPng;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(nativeName);
        dest.writeString(region);
        dest.writeString(subRegion);
        dest.writeString(nativeLanguage);
        dest.writeString(latitude);
        dest.writeString(longitude);
        dest.writeString(currencyCode);
        dest.writeString(currencyName);
        dest.writeString(currencySymbol);
        dest.writeString(flagPng);
    }
}
