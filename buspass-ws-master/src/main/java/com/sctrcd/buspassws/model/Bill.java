package com.sctrcd.buspassws.model;

import com.sctrcd.buspassws.enumeration.BillStatus;

public class Bill {

	private String code;
	private String date;
	private User buyer;
	private BillStatus status;
	private double price;
	private int discount;
	private double finalPrice;
	private int consumedPoints;
	private int earnedPoints;
	private String appliedPoints;
	
	public Bill() {
		super();
	}

	public Bill(String code, String date, User buyer, BillStatus status, double price, int discount, double finalPrice,
			int consumedPoints, int earnedPoints, String appliedPoints) {
		super();
		this.code = code;
		this.date = date;
		this.buyer = buyer;
		this.status = status;
		this.price = price;
		this.discount = discount;
		this.finalPrice = finalPrice;
		this.consumedPoints = consumedPoints;
		this.earnedPoints = earnedPoints;
		this.appliedPoints = appliedPoints;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public BillStatus getStatus() {
		return status;
	}

	public void setStatus(BillStatus status) {
		this.status = status;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public int getConsumedPoints() {
		return consumedPoints;
	}

	public void setConsumedPoints(int consumedPoints) {
		this.consumedPoints = consumedPoints;
	}

	public int getEarnedPoints() {
		return earnedPoints;
	}

	public void setEarnedPoints(int earnedPoints) {
		this.earnedPoints = earnedPoints;
	}

	public String getAppliedPoints() {
		return appliedPoints;
	}

	public void setAppliedPoints(String appliedPoints) {
		this.appliedPoints = appliedPoints;
	}
	
}
