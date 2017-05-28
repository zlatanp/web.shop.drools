package com.sctrcd.buspassws.model;

import com.sctrcd.buspassws.enumeration.RecordStatus;

public class Item {

	private String code;
	private ItemCategory category;
	private double price;
	private int quatityInShop;
	private String dateOfRecord;
	private RecordStatus statusOfRecord;
	private boolean needMore;
	private int minQuantityOnStock;
	
	public Item() {
		super();
	}

	public Item(String code, ItemCategory category, double price, int quatityInShop, String dateOfRecord,
			RecordStatus statusOfRecord, boolean needMore, int minQuantityOnStock) {
		super();
		this.code = code;
		this.category = category;
		this.price = price;
		this.quatityInShop = quatityInShop;
		this.dateOfRecord = dateOfRecord;
		this.statusOfRecord = statusOfRecord;
		this.needMore = needMore;
		this.minQuantityOnStock = minQuantityOnStock;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ItemCategory getCategory() {
		return category;
	}

	public void setCategory(ItemCategory category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuatityInShop() {
		return quatityInShop;
	}

	public void setQuatityInShop(int quatityInShop) {
		this.quatityInShop = quatityInShop;
	}

	public String getDateOfRecord() {
		return dateOfRecord;
	}

	public void setDateOfRecord(String dateOfRecord) {
		this.dateOfRecord = dateOfRecord;
	}

	public RecordStatus getStatusOfRecord() {
		return statusOfRecord;
	}

	public void setStatusOfRecord(RecordStatus statusOfRecord) {
		this.statusOfRecord = statusOfRecord;
	}

	public boolean isNeedMore() {
		return needMore;
	}

	public void setNeedMore(boolean needMore) {
		this.needMore = needMore;
	}

	public int getMinQuantityOnStock() {
		return minQuantityOnStock;
	}

	public void setMinQuantityOnStock(int minQuantityOnStock) {
		this.minQuantityOnStock = minQuantityOnStock;
	}
	
	
	
	
}
