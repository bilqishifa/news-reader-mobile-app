package com.example.android.newsapp;


import android.net.Uri;
import android.support.annotation.Nullable;

public class ApiQueryBuilder {

    public static final String CULTURE = "culture", SCIENCE = "science",
            TRAVEL_UK = "travel", TECH = "technology";

    // API query base
    public static final String API_URL = "https://content.guardianapis.com/search";

    // API KEY
    private static final String API_KEY = BuildConfig.API_KEY;

    /**
     * @param section
     * @return
     */
    public static String apiQuery(@Nullable String section, int pageSize) {
        Uri baseUri = Uri.parse(API_URL);
        Uri.Builder queryBuilder = baseUri.buildUpon();

        queryBuilder.appendQueryParameter("show-fields", "thumbnail,byline");

        if (section != null) queryBuilder.appendQueryParameter("section", section);
        queryBuilder.appendQueryParameter("page-size", Integer.toString(pageSize));
        queryBuilder.appendQueryParameter("api-key", API_KEY);

        return queryBuilder.toString();

    }
}
