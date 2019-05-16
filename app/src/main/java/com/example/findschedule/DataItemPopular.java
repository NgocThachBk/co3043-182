package com.example.findschedule;

import java.util.ArrayList;

public class DataItemPopular {
    private String LinkImage;
    private String Name;

    public DataItemPopular(){}

    public DataItemPopular(String linkimage,String name){
        this.LinkImage = linkimage;
        this.Name = name;
    }

    public void setLinkImage(String linkImage) {
        this.LinkImage = linkImage;
    }

    public void setName(String name) {this.Name = name;}

    public String getLinkImage(){
        return LinkImage;
    }

    public String getName() {return  Name;}

    public void addItem(String link,String name, ArrayList<DataItemPopular> listPopular){
        listPopular.add(new DataItemPopular(link,name));
    }
}
