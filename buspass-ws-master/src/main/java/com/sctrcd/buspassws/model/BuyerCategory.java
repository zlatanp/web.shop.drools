package com.sctrcd.buspassws.model;

import org.springframework.data.annotation.Id;

public class BuyerCategory {

	//TODO kategorija korisnika, bronzani, silver, gold

	@Id
	private String code;
	private String name;
	private int from;
	private int to;
	private int coefficient;
	
	public BuyerCategory() {
		super();
	}

	public BuyerCategory(String code, String name, int from, int to, int coefficient) {
		this.code = code;
		this.name = name;
		this.from = from;
		this.to = to;
		this.coefficient = coefficient;
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

	public int getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(int coefficient) {
		this.coefficient = coefficient;
	}
}
