package com.example.android.newsapp;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

//public class ArticleAdapter extends ArrayAdapter{
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
        public String formattedDate, formattedTime;
        public ImageView mImageUrl;
        public Uri mUrl;

        public ArticleViewHolder(final View itemView, final OnItemClickListener listener) {
            super(itemView);

            mImageUrl = itemView.findViewById(R.id.news_image);
            mTitle = itemView.findViewById(R.id.news_title);
            mAuthor = itemView.findViewById(R.id.news_author);

            // add date formatting here
            mDate = itemView.findViewById(R.id.news_date);
            mSection = itemView.findViewById(R.id.news_section);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) ;
                        {
                            listener.onItemClick(position);
                            Log.i("NA position is", " No. " + position);

                            //Intent will go here
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

    //String section, String title, String content, String author, String date, String ur
    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article currentItem = mArticle.get(position);
        if (currentItem.getImageUrl() != null){
            holder.mImageUrl.setImageBitmap(currentItem.getImageUrl());
        }
        holder.mSection.setText(currentItem.getSection());
        holder.mTitle.setText(currentItem.getTitle());
        holder.mAuthor.setText(currentItem.getAuthor());
        holder.mDate.setText(currentItem.getDate());
        holder.mUrl = Uri.parse(currentItem.getUrl());
    }

    @Override
    public int getItemCount() {
        return mArticle.size();
    }

    // readable day dateTime
    private String mDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        return dateFormat.format(dateObject);
    }
}
