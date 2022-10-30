package com.example.myapplication;

import com.example.myapplication.models.NewApiResponse;
import com.example.myapplication.models.NewsHeadlines;

import java.util.ArrayList;

public interface OnFetchDataListener {
    void onFetchData(ArrayList<NewsHeadlines> mList, String message);
    void onError(String message);

}
