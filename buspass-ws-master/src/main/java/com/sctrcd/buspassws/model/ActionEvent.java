package com.sctrcd.buspassws.model;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;

public class ActionEvent {

	@Id
	private String code;
	private String name;
	private String from;
	private String to;
	private int discount;
	private ArrayList<String> category = new ArrayList<String>();
	
	
	public ActionEvent() {
		super();
	}
	
	public ActionEvent(String code, String name, String from, String to, int discount,
			ArrayList<String> category) {
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

	public ArrayList<String> getCategory() {
		return category;
	}

	public void setCategory(ArrayList<String> category) {
		this.category = category;
	}
	
}
