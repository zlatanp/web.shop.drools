package com.sctrcd.buspassws.model;

import org.kie.api.definition.type.PropertyReactive;

import java.util.Date;

/**
 * Created by zlatan on 2.6.17..
 */
@PropertyReactive
public class ItemCount {

    private Item i;
    private int count;
    private boolean wholesale;
    private double price;
    public boolean snizeno;
    public Date datum;
    public int popust = 0;
    public String nadkategorija;
    public int maxDiscount;

    private String stavkaRacunaPopust;
    private String dodatnipopust1, dodatnipopust2, dodatnipopust3;

    public ItemCount() { }

    public ItemCount(Item i, int count, boolean wholesale, double price, boolean snizeno, Date datum, int popust, String supercat, int maxDiscount) {
        this.i = i;
        this.count = count;
        this.wholesale = wholesale;
        this.price = price;
        this.snizeno = snizeno;
        this.datum = datum;
        this.nadkategorija = supercat;
        this.popust = popust;
        this.maxDiscount = maxDiscount;

    }

    public Item getItem() {
        return i;
    }

    public void setItem(Item i) {
        this.i = i;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isWholesale() {
        return wholesale;
    }

    public void setWholesale(boolean wholesale) {
        this.wholesale = wholesale;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isSnizeno() {
        return snizeno;
    }

    public void setSnizeno(boolean snizeno) {
        this.snizeno = snizeno;
    }

    public Date getDatum() {
        return datum;
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

    public String getNadkategorija() {
        return nadkategorija;
    }

    public void setNadkategorija(String nadkategorija) {
        this.nadkategorija = nadkategorija;
    }

    public int getMaxDiscount() {
        return maxDiscount;
    }

    public void setMaxDiscount(int maxDiscount) {
        this.maxDiscount = maxDiscount;
    }

    public String getStavkaRacunaPopust() {
        return stavkaRacunaPopust;
    }

    public void setStavkaRacunaPopust(String stavkaRacunaPopust) {
        this.stavkaRacunaPopust = stavkaRacunaPopust;
    }

    public String getDodatnipopust1() {
        return dodatnipopust1;
    }

    public void setDodatnipopust1(String dodatnipopust1) {
        this.dodatnipopust1 = dodatnipopust1;
    }

    public String getDodatnipopust2() {
        return dodatnipopust2;
    }

    public void setDodatnipopust2(String dodatnipopust2) {
        this.dodatnipopust2 = dodatnipopust2;
    }

    public String getDodatnipopust3() {
        return dodatnipopust3;
    }

    public void setDodatnipopust3(String dodatnipopust3) {
        this.dodatnipopust3 = dodatnipopust3;
    }
}
