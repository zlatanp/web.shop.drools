package com.sctrcd.buspassws.model;

import java.util.ArrayList;

/**
 * Created by zlatan on 7/9/17.
 */
public class SviItemi {

    public ArrayList<Item> allItems;

    public SviItemi() {
        this.allItems = new ArrayList<Item>();
    }

    public ArrayList<Item> getAllItems() {
        return allItems;
    }

    public void setAllItems(ArrayList<Item> allItems) {
        this.allItems = allItems;
    }
}
