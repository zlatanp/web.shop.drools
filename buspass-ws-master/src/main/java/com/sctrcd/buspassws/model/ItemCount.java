package com.sctrcd.buspassws.model;

/**
 * Created by zlatan on 2.6.17..
 */
public class ItemCount {

    private Item i;
    private int count;
    private boolean wholesale;
    private double price;

    public ItemCount() { }

    public ItemCount(Item i, int count, boolean wholesale, double price) {
        this.i = i;
        this.count = count;
        this.wholesale = wholesale;
        this.price = price;

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
}
