package com.example.android.newsapp;

import android.graphics.Bitmap;

class Article {

    private String mTitle, mSection, mAuthor, mUrl, mDate;
    private Bitmap mImageUrl; // String url mUrl = url;


    public Article(String section, String title, String author, String date, String url, Bitmap image) {
        mSection = section;
        mTitle = title;
        mAuthor = author;
        mDate = date;
        mUrl = url;
        mImageUrl = image;
    }

    public Bitmap getImageUrl() {
        return mImageUrl;
    }

    public String getSection() {
        return mSection;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getDate() {
        return mDate;
    }

    public String getUrl() {
        return mUrl;
    }
}
