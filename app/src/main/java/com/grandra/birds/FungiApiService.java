package com.grandra.birds;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface FungiApiService {
    @GET("birdIlstrInfo")
    Call<FungiResponse> getFungiInfo(
            @Header("Accept") String accept, // Accept 헤더 설정
            @Header("User-Agent") String userAgent, // User-Agent 헤더 설정
            @Header("Cookie") String cookie, // Cookie 헤더 설정
            @Query("serviceKey") String serviceKey,
            @Query("q1") String q1
    );
}
