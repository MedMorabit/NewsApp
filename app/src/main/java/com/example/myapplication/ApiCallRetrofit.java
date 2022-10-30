package com.example.myapplication;

import com.example.myapplication.models.NewApiResponse;
import com.example.myapplication.models.NewsHeadlines;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiCallRetrofit {
    @GET("top-headlines")
    Call<NewApiResponse> getCallHeadline(
            @Query("apiKey") String apiKey,
            @Query("q") String query,
            @Query("country") String country,
            @Query("category") String category);
}
