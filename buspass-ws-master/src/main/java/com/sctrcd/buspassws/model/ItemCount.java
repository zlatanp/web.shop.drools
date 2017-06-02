package com.sctrcd.buspassws.model;

/**
 * Created by zlatan on 2.6.17..
 */
public class ItemCount {

    private Item i;
    private int count;
    private boolean wholesale;

    public ItemCount() { }

    public ItemCount(Item i, int count, boolean wholesale) {
        this.i = i;
        this.count = count;
        this.wholesale = wholesale;
    }

    public Item getI() {
        return i;
    }

    public void setI(Item i) {
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
}
