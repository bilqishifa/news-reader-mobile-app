package com.example.android.newsapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ArtNewsFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Article>> {

    private RecyclerView mRecyclerView;
    private ArticleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public ArtNewsFragment(){
        // required
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.rb_recyclerview, container, false);

        mRecyclerView = rootView.findViewById(R.id.recyclerView);

        return rootView;
    }

    @NonNull
    @Override
    public Loader<List <Article>> onCreateLoader(int id, @Nullable Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader <List <Article>> loader, List <Article> data) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader <List <Article>> loader) {

    }
}
