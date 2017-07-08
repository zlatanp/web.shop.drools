package com.sctrcd.buspassws.model;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by zlatan on 2.6.17..
 */
public class ItemCountUser {

    @Id
    private String unique;
    private int id;
    private User u;
    private ArrayList<ItemCount> items = new ArrayList<ItemCount>();
    private double cena;
    private double prvaCena;
    private int korisnickiPoeni;
    private Date datum;
    private int popust;
    private String osnovnipopustCele, dodatnipopustCele1,dodatnipopustCele2,dodatnipopustCele3;


    public ItemCountUser() {
    }

    public ItemCountUser(User u, ArrayList<ItemCount> items) {
        this.u = u;
        this.items = items;
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

    public ArrayList<ItemCount> getItems() {
        return items;
    }

    public void setItems(ArrayList<ItemCount> items) {
        this.items = items;
    }

    public double getCena() {
        return cena;
    }

    public Date getDatum() {
        return datum;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getPopust() {
        return popust;
    }

    public void setPopust(int popust) {
        this.popust = popust;
    }

    public double getPrvaCena() {
        return prvaCena;
    }

    public void setPrvaCena(double prvaCena) {
        this.prvaCena = prvaCena;
    }

    public int getKorisnickiPoeni() {
        return korisnickiPoeni;
    }

    public void setKorisnickiPoeni(int korisnickiPoeni) {
        this.korisnickiPoeni = korisnickiPoeni;
    }

    public String getUnique() {
        return unique;
    }

    public void setUnique(String unique) {
        this.unique = unique;
    }

    public String getOsnovnipopustCele() {
        return osnovnipopustCele;
    }

    public void setOsnovnipopustCele(String osnovnipopustCele) {
        this.osnovnipopustCele = osnovnipopustCele;
    }

    public String getDodatnipopustCele1() {
        return dodatnipopustCele1;
    }

    public void setDodatnipopustCele1(String dodatnipopustCele1) {
        this.dodatnipopustCele1 = dodatnipopustCele1;
    }

    public String getDodatnipopustCele2() {
        return dodatnipopustCele2;
    }

    public void setDodatnipopustCele2(String dodatnipopustCele2) {
        this.dodatnipopustCele2 = dodatnipopustCele2;
    }

    public String getDodatnipopustCele3() {
        return dodatnipopustCele3;
    }

    public void setDodatnipopustCele3(String dodatnipopustCele3) {
        this.dodatnipopustCele3 = dodatnipopustCele3;
    }
}
