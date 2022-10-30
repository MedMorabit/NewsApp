package com.example.myapplication;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.myapplication.models.NewApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestManager {
    Context context;
    Retrofit retrofit=new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create()).build();

    public RequestManager(Context context) {
        this.context = context;

    }
    public void getNewsHeadline(OnFetchDataListener listener,String category,String query){
        ApiCallRetrofit callApi=retrofit.create(ApiCallRetrofit.class);
        Call<NewApiResponse> call=callApi.getCallHeadline(context.getString(R.string.api_key)
                ,query,"us",category);
        try {
            call.enqueue(new Callback<NewApiResponse>() {
                @Override
                public void onResponse(@NonNull Call<NewApiResponse> call, @NonNull Response<NewApiResponse> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                    }

                    listener.onFetchData(response.body().getArticles(),response.message());
                }

                @Override
                public void onFailure(Call<NewApiResponse> call, Throwable t) {
                    listener.onError("request failed");
                }
            });
        }catch (Exception e){
            e.printStackTrace();

        }



    }
}
