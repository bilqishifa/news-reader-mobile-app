package com.example.android.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.example.android.newsapp.ApiQueryBuilder.*;

public class IntroFragment extends Fragment implements LoaderManager.LoaderCallbacks <List <Article>> {

    private RecyclerView mRecyclerView;
    private ArticleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private TextView placeholder;
    private Uri mUrl;

    // an API query that will allow to chose a given theme in News App stage 2
    private String qUrl = apiQuery(null);

    public IntroFragment() {
        // required
    }

    /**
     * Enable fragment display
     *
     * @param bundle
     * @return
     */
    public static IntroFragment newInstance(Bundle bundle) {
        IntroFragment introFragment = new IntroFragment();
        introFragment.setArguments(bundle);
        return introFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        String givenUrl = getArguments().getString("url");
        if (givenUrl != null && !givenUrl.isEmpty()) qUrl = givenUrl;

        View rootView = inflater.inflate(R.layout.rb_recyclerview, container, false);

        mRecyclerView = rootView.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        placeholder = rootView.findViewById(R.id.placeholder);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (networkConnection()) {
            getLoaderManager().initLoader(1, null, this);
        } else {
            placeholder.setText(R.string.noNetwork);
            placeholder.setVisibility(View.VISIBLE);
        }
    }

    @NonNull
    @Override
    public Loader <List <Article>> onCreateLoader(int id, @Nullable Bundle args) {
        return new ArticleLoader(getContext(), qUrl);
    }

    @Override
    public void onLoadFinished(@NonNull Loader <List <Article>> loader, List <Article> data) {

        placeholder.setVisibility(View.GONE);

        if (data != null && !data.isEmpty()) {

            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mAdapter = new ArticleAdapter((ArrayList <Article>) data);
            mRecyclerView.setAdapter(mAdapter);

            mAdapter.setOnItemClickListener(new ArticleAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    mUrl = Uri.parse(mAdapter.getmArticle().get(position).getUrl());
                    Intent i = new Intent(Intent.ACTION_VIEW, mUrl);
                    startActivity(i);
                }
            });
        } else {
            placeholder.setText(R.string.noArticles);
            placeholder.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader <List <Article>> loader) {
    }

    /**
     * verify network connection
     *
     * @return
     */
    private boolean networkConnection() {

        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();

        return ni != null && ni.isConnected();
    }
}
