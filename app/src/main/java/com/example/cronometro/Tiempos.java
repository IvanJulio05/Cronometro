package com.example.cronometro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Tiempos extends AppCompatActivity {

    TableLayout tabla=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiempos);
        tabla=(TableLayout) findViewById(R.id.tbTiempos);
        Bundle bundle=getIntent().getExtras();

        if(bundle != null){
            List<Time> tiempos = (List<Time>) bundle.getSerializable("tiempos");

            for(Time t:tiempos){
                TableRow row = new TableRow(this);
                row.setGravity(Gravity.CENTER);
                TextView time = new TextView(this);
                time.setText(t.getTime()+"");
                time.setTextSize(20);
                time.setTextColor(Color.BLACK);
                row.addView(time);
                tabla.addView(row);
            }

        }



    }

    public void eliminar(View view){

        Retrofit myRetrofit=MiApi.getInstancia();
        TimesService myTimes = myRetrofit.create(TimesService.class);

        myTimes.eliminar().enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Toast.makeText(getApplicationContext(),"SE ELIMINO TODO",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });

    }



}