package com.eph.news.Interfacea;

import com.eph.news.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {
        @GET("news")
        Call<NewsResponse> getPoliticsNews(
                @Query("category") String category
        );

}
