package com.sctrcd.buspassws.model;

import java.util.ArrayList;

/**
 * Created by zlatan on 2.6.17..
 */
public class ItemCountUser {

    private User u;
    private ArrayList<ItemCount> items = new ArrayList<ItemCount>();

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
}
