package com.eph.news.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.eph.news.Datum;
import com.eph.news.Interfacea.NewsApi;
import com.eph.news.Interfacea.NewsClient;
import com.eph.news.NewsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NewsApi newsApi = NewsClient.getClient();
        Call<NewsResponse> call =newsApi.getPoliticsNews("entertainment");
        Log.v("url", String.valueOf(call));
        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful()) {
                    List<Datum> data =response.body().getData();
                    String[] news = new String[data.size()];

                    for (int i = 0; i < news.length; i++){
                        news[i] = data.get(i).getTitle();
                        Log.v("Okkay",news[i] = data.get(i).getTitle());
                    }
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.v("faillure","noo");
            }
        });
    }
}









