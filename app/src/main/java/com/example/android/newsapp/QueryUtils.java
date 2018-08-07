package com.example.android.newsapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {
    private static final String LOG_TAG = ArticleActivity.class.getName();

    private QueryUtils() {
    }

    private static URL generateUrl(String newsUrl) {
        URL url = null;

        try {
            url = new URL(newsUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "Faulty URL", e);
        }
        return url;
    }

    private static String readInputStream(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line);
                line = bufferedReader.readLine();
            }
        }
        return stringBuilder.toString();
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        //abort operation if null url
        if (url == null) return jsonResponse;

        // prepare for receiving data via JSON query
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;

        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200) {
                inputStream = httpURLConnection.getInputStream();
                jsonResponse = readInputStream(inputStream);
            } else {
                Log.e(LOG_TAG, "http request error: " + httpURLConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "API query issue: ", e);
        } finally {
            if (httpURLConnection != null) httpURLConnection.disconnect();
            if (inputStream != null) inputStream.close();
        }
        return jsonResponse;
    }

    private static List <Article> parseJson(String articleJSON) {
        String date;

        if (TextUtils.isEmpty(articleJSON)) {
            return null;
        }

        List <Article> articles = new ArrayList <Article>();

        try {
            JSONObject jsonParentResponse = new JSONObject(articleJSON);
            JSONObject jsonChildResponse = jsonParentResponse.getJSONObject("response");
            //get array
            JSONArray articleArray = jsonChildResponse.getJSONArray("results");

            for (int i = 0; i < articleArray.length(); i++) {
                JSONObject currentQuery = articleArray.getJSONObject(i);

                String section = currentQuery.getString("sectionName");
                String title = currentQuery.getString("webTitle");
                String url = currentQuery.getString("webUrl");
                JSONObject jsonChild2Response = currentQuery.getJSONObject("fields");
                String author = jsonChild2Response.optString("byline");
                String thumbnail = jsonChild2Response.optString("thumbnail");

                String publishedDate = currentQuery.getString("webPublicationDate");
                String[] fullDate;
                fullDate = publishedDate.split("T");
                date = fullDate[0];

                articles.add(new Article(section, title, date, author, url, decodeBitmap(thumbnail)));
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "Parsing error: ", e);
        }
        return articles;
    }

    public static List <Article> fetchArticleData(String requestUrl) {
        URL url = generateUrl(requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "HTTP request failed: ", e);
        }
        return parseJson(jsonResponse);
    }

    private static Bitmap decodeBitmap(String url) {
        Bitmap bitmap = null;
        try {
            InputStream inputStream = new URL(url).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "error decoding bitmap: ", e);
        }
        return bitmap;
    }
}
