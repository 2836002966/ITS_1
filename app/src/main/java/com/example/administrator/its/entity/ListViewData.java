package com.example.administrator.its.entity;

/**
 * Created by Administrator on 2018/11/2.
 */

public class ListViewData {
    private String name;
    private int imageId;
    public ListViewData(String name,int imageId){
        this.name = name;
        this.imageId = imageId;
    }
    public String getName(){
        return name;
    }
    public int getImageId(){
        return imageId;
    }
}
