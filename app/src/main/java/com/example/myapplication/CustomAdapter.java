package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.models.NewsHeadlines;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.NewsViewHolder> {
    private Context context;
    private ArrayList<NewsHeadlines> headlines;
    OnNewsClicked listener;

    public CustomAdapter(Context context, ArrayList<NewsHeadlines> headlines,OnNewsClicked listener) {
        this.context = context;
        this.headlines = headlines;
        this.listener=listener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
           holder.news_title.setText(headlines.get(position).getTitle());
        holder.news_source.setText(headlines.get(position).getSource().getName());
        if(headlines.get(position).getUrlToImage()!=null){
            Picasso.get().load(headlines.get(position).getUrlToImage()).into(holder.news_image);
        }
        holder.card_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(headlines.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return headlines.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder{
        TextView news_title,news_source;
        CardView card_main;
        ImageView news_image;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            news_source=itemView.findViewById(R.id.item_source);
            card_main=itemView.findViewById(R.id.card_main);
            news_title=itemView.findViewById(R.id.item_title);
            news_image=itemView.findViewById(R.id.item_image);
        }
    }
}
