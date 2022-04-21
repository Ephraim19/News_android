package com.eph.news.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.eph.news.Interfacea.NewsApi;
import com.eph.news.Interfacea.NewsClient;
import com.eph.news.NewsResponse;
import com.eph.news.adapters.NewsAdapter;
import com.eph.news.databinding.ActivityMainBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    String[] news = {"Men tuxedo suits that fit you perfectly - Ksh 6000","Red rubber shoes perfect for workouts - Ksh 5500","Nike Airforce 1 shoes, improve your shoe game - Ksh 1700","Grey men shorts for relaxing on hot days - Ksh 1300","Yellow T-shirts, shine from far - Ksh 3200","Blue men jeans that fir perfectly - Ksh 900"};
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        NewsApi newsApi = NewsClient.getClient();
        Call<NewsResponse> call =newsApi.getPoliticsNews("entertainment");
        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful()) {

//                    List<Datum> data =response.body().getData();
//                    String[] news = new String[data.size()];
//
//                    for (int i = 0; i < news.length; i++){
//                        news[i] = data.get(i).getTitle();
//                        Log.v("Okkay", String.valueOf(news));
//                    }
//                    NewsAdapter adapter = new NewsAdapter(MainActivity.this, news);
//                    binding.eph.setAdapter(adapter);
//                    binding.eph.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.v("faillure","noo");
            }
        });
    }
}





















