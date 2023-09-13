package com.grandra.birds;

import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory;

import retrofit2.Retrofit;


public class FungiApiClient {


    private static final String BASE_URL = "http://apis.data.go.kr/1400119/BirdService/";

    public FungiApiService createService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(TikXmlConverterFactory.create()) // TikXmlConverterFactory 추가
                .build();

        return retrofit.create(FungiApiService.class);
    }
}
