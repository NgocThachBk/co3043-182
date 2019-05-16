package com.example.findschedule;

import java.io.Serializable;
import java.util.ArrayList;

public class Data2 implements Serializable {
    private ArrayList<DataExtra> PlaceItems;

    public Data2(){}

    public Data2(ArrayList<DataExtra> Items){
        PlaceItems = Items;
    }

    public void setPlaceItems(ArrayList<DataExtra> placeItems) {
        this.PlaceItems = placeItems;
    }

    public ArrayList<DataExtra> getPlaceItems(){return PlaceItems;}

    public static void addItem(ArrayList<DataExtra> item,ArrayList<Data2> listItem){
        listItem.add(new Data2(item));
    }
}
