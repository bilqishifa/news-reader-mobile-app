package com.example.android.newsapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class IntroFragment extends Fragment implements LoaderManager.LoaderCallbacks <List <Article>> {

    private RecyclerView mRecyclerView;
    private ArticleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private String mUrl = ApiQueryBuilder.apiQuery(null);

    public IntroFragment() {
        // required
    }

    /**
     * will enable fragment instantiation w/HTTP response in UI
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
        View rootView = inflater.inflate(R.layout.rb_recyclerview, container, false);

        if (getArguments() != null && getArguments().containsKey("url")) {
            String givenUrl = getArguments().getString("url");
            if (givenUrl != null && !givenUrl.isEmpty())
                mUrl = givenUrl;
        }

        mRecyclerView = rootView.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setAdapter(mAdapter);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        getLoaderManager().initLoader(1, null, this);
    }

    @NonNull
    @Override
    public Loader <List <Article>> onCreateLoader(int id, @Nullable Bundle args) {
        return new ArticleLoader(getContext(), mUrl);
    }

    @Override
    public void onLoadFinished(@NonNull Loader <List <Article>> loader, List <Article> data) {
        if (data != null && !data.isEmpty()) {
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mAdapter = new ArticleAdapter((ArrayList <Article>) data);

            mAdapter.setOnItemClickListener(new ArticleAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    //method
                    Log.i("item ", "position" + position);
                }
            });
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader <List <Article>> loader) {

    }
}
