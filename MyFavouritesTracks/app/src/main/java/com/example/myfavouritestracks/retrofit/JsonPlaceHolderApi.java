package com.example.myfavouritestracks.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface JsonPlaceHolderApi {

    @GET("v1/me/tracks?market=BR") //
    Call<MainPojo> getUserData(@Header("Authorization") String myToken);




}
