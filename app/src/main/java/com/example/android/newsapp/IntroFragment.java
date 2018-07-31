package com.example.android.newsapp;

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

import java.util.ArrayList;
import java.util.List;

public class IntroFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Article>>{

    private RecyclerView mRecyclerView;
    private ArticleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private String mUrl = ApiQueryBuilder.apiQuery(null);

    public IntroFragment(){
        // required
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.rb_recyclerview,container,false);

        // see implementation note in ApiQueryBuilder
        String givenUrl = getArguments().getString("url");
        if (givenUrl != null && !givenUrl.isEmpty()) mUrl = givenUrl;

        mRecyclerView = rootView.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getActivity());

        mAdapter.setOnItemClickListener(new ArticleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //method
            }
        });
        return rootView;
    }

    @NonNull
    @Override
    public Loader<List <Article>> onCreateLoader(int id, @Nullable Bundle args) {
        return new ArticleLoader(getContext(), mUrl);
    }

    @Override
    public void onLoadFinished(@NonNull Loader <List <Article>> loader, List <Article> data) {
        if (data != null && !data.isEmpty()){
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mAdapter = new ArticleAdapter((ArrayList <Article>) data);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader <List <Article>> loader) {

    }
}
