package com.sctrcd.buspassws.model;

import java.util.ArrayList;

public class ActionEvent {

	private String code;
	private String name;
	private String from;
	private String to;
	private int discount;
	private ArrayList<ItemCategory> category = new ArrayList<ItemCategory>();
	
	
	public ActionEvent() {
		super();
	}
	
	public ActionEvent(String code, String name, String from, String to, int discount,
			ArrayList<ItemCategory> category) {
		super();
		this.code = code;
		this.name = name;
		this.from = from;
		this.to = to;
		this.discount = discount;
		this.category = category;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public ArrayList<ItemCategory> getCategory() {
		return category;
	}

	public void setCategory(ArrayList<ItemCategory> category) {
		this.category = category;
	}
	
}
