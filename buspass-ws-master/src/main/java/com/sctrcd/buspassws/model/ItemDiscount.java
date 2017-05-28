package com.sctrcd.buspassws.model;

import com.sctrcd.buspassws.enumeration.DiscountType;

public class ItemDiscount {

	private String code;
	private Bill bill;
	private Item item;
	private int discount;
	private DiscountType type;
	
	public ItemDiscount() {
		super();
	}

	public ItemDiscount(String code, Bill bill, Item item, int discount, DiscountType type) {
		super();
		this.code = code;
		this.bill = bill;
		this.item = item;
		this.discount = discount;
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public DiscountType getType() {
		return type;
	}

	public void setType(DiscountType type) {
		this.type = type;
	}

}
