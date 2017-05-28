package com.sctrcd.buspassws.model;

import com.sctrcd.buspassws.enumeration.DiscountType;

public class BillDiscount {
	
	private String code;
	private Bill bill;
	private int discount;
	private DiscountType type;
	
	public BillDiscount() {
		super();
	}

	public BillDiscount(String code, Bill bill, int discount, DiscountType type) {
		super();
		this.code = code;
		this.bill = bill;
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
