package com.example.cronometro;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class MiApi {

    private static Retrofit instacia=null;
    private static final String URL_API="http://192.168.10.101:8080/cronometro/";

    public static Retrofit getInstancia(){
        if(instacia==null){

            instacia=new Retrofit.Builder().baseUrl(URL_API).addConverterFactory(GsonConverterFactory.create()).build();

        }

        return instacia;
    }
}
