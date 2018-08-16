package com.example.android.newsapp;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import static com.example.android.newsapp.ApiQueryBuilder.*;

/**
 * resources:   Coding in Flow 'Navigation Drawer with Fragments' tutorial
 * https://github.com/codepath/android_guides/wiki/Fragment-Navigation-Drawer
 * I also reverse engineered the template navigation bar that Android Studio provides
 * Special thanks to Iip from Udacity, who was a terrific mentor during this process. And to Chris Addington who guided me during the onClick implementation.
 */

public class ArticleActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, LoaderManager.LoaderCallbacks <List <Article>> {
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private ArticleAdapter adapter;

    public static final String LOG_TAG = ArticleActivity.class.getName();

    private static final int LOADER_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.layout_drawer);
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navbaropen, R.string.navbarclose);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            Bundle bundle = new Bundle();
            bundle.putString("url", apiQuery(null, 2));
            IntroFragment introFragment = IntroFragment.newInstance(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.f_container, introFragment).commit();
            navigationView.setCheckedItem(R.id.nav_intro);
        }
    }

    /**
     *
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        IntroFragment introFragment;
        Bundle bundle = new Bundle();
        switch (item.getItemId()) {
            case R.id.nav_intro:
                bundle.putString("url", apiQuery(null, 2));
                introFragment = IntroFragment.newInstance(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.f_container, introFragment).commit();
                break;
            case R.id.art_design:
                // bundle content according to section
                bundle.putString("url", apiQuery(CULTURE, 6));
                introFragment = IntroFragment.newInstance(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.f_container, introFragment).commit();
                break;
            case R.id.science:
                bundle.putString("url", apiQuery(SCIENCE, 6));
                introFragment = IntroFragment.newInstance(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.f_container, introFragment).commit();
                break;
            case R.id.travel:
                bundle.putString("url", apiQuery(TRAVEL_UK, 6));
                introFragment = IntroFragment.newInstance(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.f_container, introFragment).commit();
                break;
            case R.id.tech:
                bundle.putString("url", apiQuery(TECH, 6));
                introFragment = IntroFragment.newInstance(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.f_container, introFragment).commit();
                break;
            case R.id.settings:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                break;
            case R.id.share:
                Toast.makeText(this, R.string.coming_soon, Toast.LENGTH_SHORT).show();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public Loader <List <Article>> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader <List <Article>> loader, List <Article> data) {

    }

    @Override
    public void onLoaderReset(Loader <List <Article>> loader) {

    }
}
