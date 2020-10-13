package com.example.yemekgetir;


public class musteri {

    public String ad;
    public String soyad;
    public String tc;
    public String telefon;
    public String adres;
    public String sifre;
    public String sifreTekrar;
    public String selected;
    public String onay;
    public String current;

    public musteri(String ad, String soyad, String tc, String telefon, String adres, String sifre, String sifreTekrar, String selected, String onay, String current){
        this.ad=ad;
        this.soyad=soyad;
        this.tc=tc;
        this.telefon=telefon;
        this.adres=adres;
        this.sifre=sifre;
        this.sifreTekrar=sifreTekrar;
        this.selected=selected;
        this.onay=onay;
        this.current=current;
    }

    public musteri(){

    }
}
