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
}
