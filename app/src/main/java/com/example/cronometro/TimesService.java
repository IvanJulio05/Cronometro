package com.example.cronometro;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface TimesService {

    @GET("showAll")
    Call<List<Time>> obtenerTodo();

    @GET("insert/{time}")
    Call<Object> agregar(@Path("time") float time);

    @GET("delete")
    Call<Object> eliminar();

}
