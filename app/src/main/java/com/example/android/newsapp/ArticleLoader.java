package com.example.android.newsapp;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
//import android.content.AsyncTaskLoader;
import android.util.Log;

import java.util.List;

public class ArticleLoader extends AsyncTaskLoader <List <Article>> {
    private String url;

    public ArticleLoader(@NonNull Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        Log.e("start loading ", "starts ok");
        forceLoad();
    }

    @Nullable
    @Override
    public List <Article> loadInBackground() {
        if (url == null) {
            return null;
        }
        Log.e("load in background ", "loads ok");
        List <Article> articles = QueryUtils.fetchArticleData(url);
        return articles;
    }
}
