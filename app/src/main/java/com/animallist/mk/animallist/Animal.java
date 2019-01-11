package com.animallist.mk.animallist;

import android.net.Uri;

public class Animal {

    private String mName;
    private Uri mImage;

    public Animal(String name, Uri image) {
        mName = name;
        mImage = image;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Uri getImage() {
        return mImage;
    }

    public void setImage(Uri image) {
        mImage = image;
    }
}
