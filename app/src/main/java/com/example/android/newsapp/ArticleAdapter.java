package com.example.android.newsapp;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ArticleAdapter extends RecyclerView.Adapter <ArticleAdapter.ArticleViewHolder> {
    private ArrayList <Article> mArticle;
    private OnItemClickListener mListener;


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder {

        public TextView mTitle, mAuthor, mDate, mSection;
        public ImageView mImageUrl;
        public Uri aUrl;

        public ArticleViewHolder(final View itemView, final OnItemClickListener listener) {
            super(itemView);

            mSection = itemView.findViewById(R.id.news_section);
            mImageUrl = itemView.findViewById(R.id.news_image);
            mTitle = itemView.findViewById(R.id.news_title);
            mDate = itemView.findViewById(R.id.news_date);
            mAuthor = itemView.findViewById(R.id.news_author);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) ;
                        {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public ArticleAdapter(ArrayList <Article> newsList) {
        mArticle = newsList;
    }


    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_main, parent, false);
        ArticleViewHolder nvh = new ArticleViewHolder(view, mListener);
        return nvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article currentItem = mArticle.get(position);
        if (currentItem.getImageUrl() != null) {
            holder.mImageUrl.setImageBitmap(currentItem.getImageUrl());
        }
        holder.mSection.setText(currentItem.getSection());
        holder.mTitle.setText(currentItem.getTitle());
        holder.mDate.setText(currentItem.getDate());
        holder.mAuthor.setText(currentItem.getAuthor());
        holder.aUrl = Uri.parse(currentItem.getUrl());
    }

    @Override
    public int getItemCount() {
        return mArticle.size();
    }
}
