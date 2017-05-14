package com.khoenguyen.androidbasic.Model.AlertDialogListView;

/**
 * Created by Admin on 5/14/2017.
 */

public class Word {
    private String mEnglish;
    private String mVietNamese;
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    public static final int NO_IMAGE_PROVIDED = -1;
    public Word(String english, String vietnam, int image){
        mEnglish = english;
        mVietNamese = vietnam;
        mImageResourceId = image;
    }

    public String getmEnglish() {
        return mEnglish;
    }

    public String getmVietNamese() {
        return mVietNamese;
    }

    public int getmImageResourceId() {
        return mImageResourceId;
    }
    public boolean hasImage(){
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    public String toString(){
        return "Word{" + mEnglish + ", " +mVietNamese + "}";
    }
}
