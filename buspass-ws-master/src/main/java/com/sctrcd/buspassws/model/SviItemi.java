package com.sctrcd.buspassws.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by zlatan on 7/9/17.
 */
public class SviItemi {

    public Item items;
    public Date datum;

    public SviItemi() {
    }

    public SviItemi(Item items, Date datum) {
        this.items = items;
        this.datum = datum;
    }

    public Item getItems() {
        return items;
    }

    public void setItems(Item items) {
        this.items = items;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }
}
