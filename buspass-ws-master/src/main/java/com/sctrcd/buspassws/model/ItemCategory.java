package com.sctrcd.buspassws.model;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;

public class ItemCategory {

    @Id
	private String code;
	private String name;
	private String supercategory;
	private int maxDiscount;
	private boolean wholesale;
	
	
	public ItemCategory() {
		super();
	}
	
	public ItemCategory(String code, String name, String supercategory, int maxDiscount, boolean wholesale) {
		super();
		this.code = code;
		this.name = name;
		this.supercategory = supercategory;
		this.maxDiscount = maxDiscount;
		this.wholesale = wholesale;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSuperCategory() {
		return supercategory;
	}

	public void setSuperCategory(String supercategory) {
		this.supercategory = supercategory;
	}

	public int getMaxDiscount() {
		return maxDiscount;
	}

	public void setMaxDiscount(int maxDiscount) {
		this.maxDiscount = maxDiscount;
	}

	public String getName() { return name; }

	public void setName(String name) { this.name = name; }

	public boolean isWholesale() {
		return wholesale;
	}

	public void setWholesale(boolean wholesale) {
		this.wholesale = wholesale;
	}
}
