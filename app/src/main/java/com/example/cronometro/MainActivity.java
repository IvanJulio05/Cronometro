package com.example.cronometro;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {

    private TextView pantalla=null;
    private boolean continuar=false;
    private int segundos=0;
    private int minutos=0;
    Thread crono;
    Handler h= new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pantalla = findViewById(R.id.txtPantalla);
    }

    public void mostrarTiempos(View view){
        Retrofit myRetrofit=MiApi.getInstancia();
        Intent tableTimes=new Intent(this,Tiempos.class);
        TimesService myTimes = myRetrofit.create(TimesService.class);
        myTimes.obtenerTodo().enqueue(new Callback<List<Time>>() {
            @Override
            public void onResponse(Call<List<Time>> call, Response<List<Time>> response) {
                List<Time>times=response.body();

                Bundle t =new Bundle();
                t.putSerializable("tiempos", (Serializable) times);

                tableTimes.putExtras(t);

                startActivity(tableTimes);
            }

            @Override
            public void onFailure(Call<List<Time>> call, Throwable t) {

            }
        });
    }

    public void iniciarCronometro(View view) {

        if(continuar){
            detenerCronometro(view);
            String time=minutos+"."+segundos;
            Retrofit myRetrofit=MiApi.getInstancia();
            TimesService myTimes = myRetrofit.create(TimesService.class);
            myTimes.agregar(Float.parseFloat(time)).enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response){
                    System.out.println("se agrego correctamente");
                }
                @Override
                public void onFailure(Call<Object> call,Throwable t){
                    System.out.println(t.getMessage());
                }

            });
        }
        else{
            segundos=0;
            minutos=0;
            continuar = true;
        }


        crono = new Thread(new Runnable() {
            @Override
            public void run() {
                while (continuar) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    segundos++;
                    if (segundos == 60) {
                        segundos = 0;
                        minutos++;
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pantalla.setText(minutos + "." + segundos);
                        }
                    });
                }
            }
        });
        crono.start();
    }

    public void detenerCronometro(View view) {
        continuar = false;
    }
}