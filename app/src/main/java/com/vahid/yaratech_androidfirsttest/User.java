package com.vahid.yaratech_androidfirsttest;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Vah on 7/30/2018.
 */

public class User implements Parcelable {

    String name;
    String familyName;
    String email;

    public User(String name, String familyName, String email){
        this.name = name;
        this.familyName = familyName;
        this.email = email;
    }

    protected User(Parcel in) {
        name = in.readString();
        familyName = in.readString();
        email = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(familyName);
        dest.writeString(email);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
