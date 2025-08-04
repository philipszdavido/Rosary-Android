package com.chidumennamdi.rosary.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Prayer implements Parcelable {
    public String title;
    public String content;

    public Prayer(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Prayer(Parcel in) {
        this.title = in.readString();
        this.content = in.readString();
    }

    public static final Parcelable.Creator<Prayer> CREATOR = new Creator<Prayer>() {
        @Override
        public Prayer createFromParcel(Parcel in) {
            return new Prayer(in);
        }

        @Override
        public Prayer[] newArray(int size) {
            return new Prayer[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(content);
    }

}
