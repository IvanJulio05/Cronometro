package com.example.cronometro;

import java.io.Serializable;

public class Time implements Serializable{
    private long id;
    private float time;

    public Time(long id,float time){
        this.id=id;
        this.time=time;
    }

    public Time(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getTime() {
        return this.time;
    }

    public void setTime(float time) {
        this.time= time;
    }


}
