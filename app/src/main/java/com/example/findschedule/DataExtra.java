package com.example.findschedule;

import java.io.Serializable;
import java.util.ArrayList;

public class DataExtra implements Serializable {
    private String name;
    private String Image;
    private String TimeStart;
    private String PeriodOfTime;
    private String AteMoney;
    private String GateMoney;
    private String RestMoney;
    private ArrayList<String> ListImage;
    public DataExtra(){}

    public DataExtra(String name, String image){
        this.name = name;
        this.Image = image;
    }

    public void setObject(Data1 data1){
        this.name = data1.getname();
        this.Image = data1.getImage();
        this.TimeStart = data1.getTimeStart();
        this.PeriodOfTime = data1.getPeriodOfTime();
        this.AteMoney = data1.getAteMoney();
        this.GateMoney = data1.getGateMoney();
        this.RestMoney = data1.getRestMoney();
    }

    public void setname(String name){
        this.name = name;
    }

    public void setImage(String image){
        this.Image = image;
    }

    public void setTimeStart(String timeStart){this.TimeStart = timeStart;}

    public void setPeriodOfTime(String periodOfTime) {this.PeriodOfTime = periodOfTime;}

    public void setAteMoney (String ateMoney) {this.AteMoney = ateMoney;}

    public void setGateMoney (String gateMoney) {this.GateMoney = gateMoney;}

    public void setRestMoney (String restMoney) {this.RestMoney = restMoney;}

    public void setListImage( ArrayList<String> listImage) {this.ListImage = listImage;}

    public String getname(){return  name;}
    public String getImage(){return Image;}
    public String getTimeStart() {return TimeStart;}
    public String getPeriodOfTime() {return PeriodOfTime;}
    public String getAteMoney() {return AteMoney;}
    public String getGateMoney() {return GateMoney;}
    public String getRestMoney() {return RestMoney;}
    public ArrayList<String> getListImage() {return ListImage;}
}
