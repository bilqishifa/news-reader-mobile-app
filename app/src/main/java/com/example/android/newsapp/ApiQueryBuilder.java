package com.example.android.newsapp;


import android.net.Uri;
import android.support.annotation.Nullable;

public class ApiQueryBuilder {

    public static final String CULTURE = "culture", SCIENCE = "science",
            TRAVEL_UK = "travel", TECH = "technology";

    //public static final String ORDERBY = "&order-by=", PAGE_SIZE = "&page-size=";

    // API query base note it is ordered by latest publications w/size limit of 6 articles
    public static final String API_URL = "https://content.guardianapis.com/search";

    // decided against hiding this information for now:
    private static final String API_KEY = "f33fec42-5b38-4790-a4b7-960bdea7b568";

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
