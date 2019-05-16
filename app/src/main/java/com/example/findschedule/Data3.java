package com.example.findschedule;

import java.util.ArrayList;

public class Data3 {
    private String Name;
    private long Count;
    public Data3(){}

    public Data3(String name, long count){
        this.Name = name;
        this.Count = count;
    }

    public void setName(String name){
        this.Name = name;
    }

    public void setCount(long count){
        this.Count = count;
    }

    public String getName(){return  Name;}
    public long getCount(){return Count;}
}
