package com.sctrcd.buspassws.model;

import org.springframework.data.annotation.Id;

public class ConsumptionThreshold {
	//TODO prag potrosnje, videti jos (2)

    @Id
    private String code;
    private int from;
    private int to;
    private int ifClassic;
    private int ifSilver;
    private int ifGold;

    public ConsumptionThreshold() {
    }

    public ConsumptionThreshold(int from, int to, int ifClassic, int ifSilver, int ifGold) {
        this.from = from;
        this.to = to;
        this.ifClassic = ifClassic;
        this.ifSilver = ifSilver;
        this.ifGold = ifGold;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getIfClassic() {
        return ifClassic;
    }

    public void setIfClassic(int ifClassic) {
        this.ifClassic = ifClassic;
    }

    public int getIfSilver() {
        return ifSilver;
    }

    public void setIfSilver(int ifSilver) {
        this.ifSilver = ifSilver;
    }

    public int getIfGold() {
        return ifGold;
    }

    public void setIfGold(int ifGold) {
        this.ifGold = ifGold;
    }
}
