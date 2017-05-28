package com.sctrcd.buspassws.model;

public class BillItem {
	
	private Bill bill;
	private int serialNumber;
	private Item item;
	private double price;
	private int quntity;
	private double totalPrice;
	private int discount;
	private double finalPrice;
	private String appliedDiscounts;
	
	public BillItem() {
		super();
	}

	public BillItem(Bill bill, int serialNumber, Item item, double price, int quntity, double totalPrice, int discount,
			double finalPrice, String appliedDiscounts) {
		super();
		this.bill = bill;
		this.serialNumber = serialNumber;
		this.item = item;
		this.price = price;
		this.quntity = quntity;
		this.totalPrice = totalPrice;
		this.discount = discount;
		this.finalPrice = finalPrice;
		this.appliedDiscounts = appliedDiscounts;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuntity() {
		return quntity;
	}

	public void setQuntity(int quntity) {
		this.quntity = quntity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public String getAppliedDiscounts() {
		return appliedDiscounts;
	}

	public void setAppliedDiscounts(String appliedDiscounts) {
		this.appliedDiscounts = appliedDiscounts;
	}
	
	
}
