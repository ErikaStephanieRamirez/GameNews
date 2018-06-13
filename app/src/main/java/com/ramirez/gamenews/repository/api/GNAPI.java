package com.ramirez.gamenews.repository.api;


import com.ramirez.gamenews.repository.modelos.New;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface GNAPI {

    String BASEURL ="http://gamenewsuca.herokuapp.com";

    @GET("/news")
    Call<List<New>> getNews(@Header("Authorization") String autorizazion);

    @FormUrlEncoded
    @POST("/login")
    Call<String> login(@Field("user") String username, @Field("password") String password);
}
