package com.example.findschedule;

import java.io.Serializable;

public class Restaurant implements Serializable {
    private String name;
    private String ho;
    private Integer price;
    private Double distance;

    public Restaurant(String Name, String Ho)  {
        this.name= Name;
        this.ho = Ho;
    }

    public Restaurant(String Name, Integer Price, Double Distance)  {
        this.name= Name;
        this.price = Price;
        this.distance= Distance;
    }
    public String getName() {
        return this.name;
    }
    public String getHo() {
        return this.ho;
    }

    public Integer getPrice() {
        return this.price;
    }
    public Double getDistance() {
        return this.distance;
    }
    public void setName(String Name) {
        this.name = Name;
    }
    public void setPrice(Integer Price) {
        this.price = Price;
    }
    public void setDistance(Double Distance) {
        this.distance = Distance;
    }
    public void setHo(String Ho) {
        this.ho = Ho;
    }
}
