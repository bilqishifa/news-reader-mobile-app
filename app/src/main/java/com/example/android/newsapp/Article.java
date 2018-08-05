package com.example.android.newsapp;

import android.graphics.Bitmap;

class Article {

    private String mTitle, mSection, mDate, mAuthor, mUrl;
    private Bitmap mImageUrl;

    public Article(String section, String title, String date, String author, String url, Bitmap image) {
        mSection = section;
        mTitle = title;
        mDate = date;
        mAuthor = author;
        mUrl = url;
        mImageUrl = image;
    }

    public String getSection() {
        return mSection;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDate() {
        return mDate;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getUrl() {
        return mUrl;
    }

    public Bitmap getImageUrl() {
        return mImageUrl;
    }
}
