package com.sctrcd.buspassws.model;

import com.sctrcd.buspassws.enumeration.RecordStatus;
import org.springframework.data.annotation.Id;

public class Item {

	@Id
	private String code;
	private String name;
	private String category;
	private double price;
	private int quantityInShop;
	private String dateOfRecord;
	private RecordStatus statusOfRecord;
	private boolean needMore;
	private int minQuantityOnStock;
	
	public Item() {
		super();
	}

	public Item(String code, String name,  String category, double price, int quatityInShop, String dateOfRecord,
			RecordStatus statusOfRecord, boolean needMore, int minQuantityOnStock) {
		super();
		this.code = code;
		this.name = name;
		this.category = category;
		this.price = price;
		this.quantityInShop = quatityInShop;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantityInShop() {
		return quantityInShop;
	}

	public void setQuantityInShop(int quantityInShop) {
		this.quantityInShop = quantityInShop;
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

	public String getName() { return name; }

	public void setName(String name) { this.name = name; }
}
