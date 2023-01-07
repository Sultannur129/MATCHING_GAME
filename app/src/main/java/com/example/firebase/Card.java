package com.example.firebase;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Card implements Serializable {
    public   int Id;
    public   int Katsayi;
    public   String Name;
    public   String House;
    public   float Point;
    public   Bitmap card;
    public   Bitmap Img;

    public Card(){

    }

    public Card(int Id,String Name,String House,float Point,Bitmap card,Bitmap Img,int Katsayi){
        this.Id=Id;
        this.Name=Name;
        this.House=House;
        this.Point=Point;
        this.card=card;
        this.Img=Img;
        this.Katsayi=Katsayi;
    }



}
