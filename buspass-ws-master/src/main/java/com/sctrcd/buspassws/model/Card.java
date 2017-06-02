package com.sctrcd.buspassws.model;

import org.springframework.data.annotation.Id;

/**
 * Created by zlatan on 2.6.17..
 */
public class Card {

    @Id
    private String id;
    private Item i;
    private int count;

    public Card() {
    }

    public Card(Item i, int count) {
        this.i = i;
        this.count = count;
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
}
