package com.sctrcd.buspassws.model;

import java.util.ArrayList;

public class ItemCategory {

	private String code;
	private ArrayList<ItemCategory> subcategory = new ArrayList<ItemCategory>();
	private int maxDiscount;
	
	
	public ItemCategory() {
		super();
	}
	
	public ItemCategory(String code, ArrayList<ItemCategory> subcategory, int maxDiscount) {
		super();
		this.code = code;
		this.subcategory = subcategory;
		this.maxDiscount = maxDiscount;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ArrayList<ItemCategory> getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(ArrayList<ItemCategory> subcategory) {
		this.subcategory = subcategory;
	}

	public int getMaxDiscount() {
		return maxDiscount;
	}

	public void setMaxDiscount(int maxDiscount) {
		this.maxDiscount = maxDiscount;
	}
	
	
}
