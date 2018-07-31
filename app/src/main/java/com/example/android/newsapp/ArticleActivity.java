package com.example.android.newsapp;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * resources: Coding in Flow Navigation Drawer with Fragments tutorial
 * I also reverse engineered the template navigation bar that Android Studio provides
 */

public class ArticleActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, LoaderManager.LoaderCallbacks<List<Article>>{
    private DrawerLayout drawer;
    private ArticleAdapter adapter;
    private TextView emtyStateText;

    public static final String LOG_TAG = ArticleActivity.class.getName();

    private static final int NEWS_LOADER_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.layout_drawer);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navbaropen, R.string.navbarclose);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.f_container, new IntroFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_intro);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Bundle bundle = new Bundle();
        switch (item.getItemId()){
            case 0:
                bundle.putString("url", ApiQueryBuilder.apiQuery(ApiQueryBuilder.CULTURE));
                getSupportFragmentManager().beginTransaction().replace(R.id.f_container, new IntroFragment()).commit();
                break;
            case 1:
                bundle.putString("url", ApiQueryBuilder.apiQuery(ApiQueryBuilder.SCIENCE));
                getSupportFragmentManager().beginTransaction().replace(R.id.f_container, new IntroFragment()).commit();
                break;
            case 2:
                bundle.putString("url", ApiQueryBuilder.apiQuery(ApiQueryBuilder.TRAVEL_UK));
                getSupportFragmentManager().beginTransaction().replace(R.id.f_container, new IntroFragment()).commit();
                break;
            case 3:
                bundle.putString("url", ApiQueryBuilder.apiQuery(ApiQueryBuilder.TECH));
                getSupportFragmentManager().beginTransaction().replace(R.id.f_container, new IntroFragment()).commit();
                break;
            case R.id.calendar:
                Toast.makeText(this, "Saved in reading list", Toast.LENGTH_SHORT).show();
                break;
            case R.id.share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public Loader<List <Article>> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader <List <Article>> loader, List <Article> data) {

    }

    @Override
    public void onLoaderReset(Loader <List <Article>> loader) {

    }
}
