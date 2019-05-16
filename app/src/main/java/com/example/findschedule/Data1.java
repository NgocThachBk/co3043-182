package com.example.findschedule;

import java.io.Serializable;
import java.util.ArrayList;

public class Data1 implements Serializable {
    private String name;
    private String Image;
    private String TimeStart;
    private String PeriodOfTime;
    private String AteMoney;
    private String GateMoney;
    private String RestMoney;

    public Data1(){}

    public Data1(String name, String image){
        this.name = name;
        this.Image = image;
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



    public String getname(){return  name;}
    public String getImage(){return Image;}
    public String getTimeStart() {return TimeStart;}
    public String getPeriodOfTime() {return PeriodOfTime;}
    public String getAteMoney() {return AteMoney;}
    public String getGateMoney() {return GateMoney;}
    public String getRestMoney() {return RestMoney;}

}
