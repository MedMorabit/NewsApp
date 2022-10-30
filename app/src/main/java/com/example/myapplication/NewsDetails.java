package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.models.NewsHeadlines;
import com.squareup.picasso.Picasso;

public class NewsDetails extends AppCompatActivity {
TextView time,title,author,body;
ImageView image_details;
NewsHeadlines headlines;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        time=findViewById(R.id.time_details);
        title=findViewById(R.id.title_details);
        body=findViewById(R.id.body_details);
        author=findViewById(R.id.author_details);
        image_details=findViewById(R.id.image_details);

        headlines= (NewsHeadlines) getIntent().getSerializableExtra("news");
        author.setText(headlines.getAuthor());
        title.setText(headlines.getTitle());
        body.setText("     "+headlines.getDescription());
        time.setText(headlines.getPublishedAt());
        Picasso.get().load(headlines.getUrlToImage()).into(image_details);
    }
}