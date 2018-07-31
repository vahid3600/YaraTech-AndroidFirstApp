package com.vahid.yaratech_androidfirsttest.Models;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

/**
 * Created by Vah on 7/30/2018.
 */

@Parcel
public class User{

    String name;
    String familyName;
    String email;

    @ParcelConstructor
    public User(String name, String familyName, String email) {
        this.name = name;
        this.familyName = familyName;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getEmail() {
        return email;
    }
}
