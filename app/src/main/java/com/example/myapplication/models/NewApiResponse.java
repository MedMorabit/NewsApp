package com.example.myapplication.models;

import java.io.Serializable;
import java.util.ArrayList;

public class NewApiResponse implements Serializable {
    ArrayList<NewsHeadlines> articles;
    int totalResults;
    String status;

    public ArrayList<NewsHeadlines> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<NewsHeadlines> articles) {
        this.articles = articles;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
