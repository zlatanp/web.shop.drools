package com.sctrcd.buspassws.model;

public class BuyerCategory {

	//TODO kategorija korisnika, bronzani, silver, gold
	
	private String code;
	private String name;
	private ConsumptionThreshold consumption;
	
	public BuyerCategory() {
		super();
	}

	public BuyerCategory(String code, String name, ConsumptionThreshold consumption) {
		super();
		this.code = code;
		this.name = name;
		this.consumption = consumption;
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

	public ConsumptionThreshold getConsumption() {
		return consumption;
	}

	public void setConsumption(ConsumptionThreshold consumption) {
		this.consumption = consumption;
	}

	
	
}
