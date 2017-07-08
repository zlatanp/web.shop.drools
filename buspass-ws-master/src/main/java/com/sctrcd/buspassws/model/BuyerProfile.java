package com.sctrcd.buspassws.model;

public class BuyerProfile {
	
	private String deliveryAdress;
	private int rewardPoints;
	private BuyerCategory category;
	private PurchaseHistory history;
	
	public BuyerProfile() {
		super();
	}

	public BuyerProfile(String deliveryAdress, int rewardPoints, BuyerCategory category, PurchaseHistory history) {
		super();
		this.deliveryAdress = deliveryAdress;
		this.rewardPoints = rewardPoints;
		this.category = category;
		this.history = history;
	}

	public String getDeliveryAdress() {
		return deliveryAdress;
	}

	public void setDeliveryAdress(String deliveryAdress) {
		this.deliveryAdress = deliveryAdress;
	}

	public int getRewardPoints() {
		return rewardPoints;
	}

	public void setRewardPoints(int rewardPoints) {
		this.rewardPoints = rewardPoints;
	}

	public BuyerCategory getCategory() {
		return category;
	}

	public void setCategory(BuyerCategory category) {
		this.category = category;
	}

	public PurchaseHistory getHistory() {
		return history;
	}

	public void setHistory(PurchaseHistory history) {
		this.history = history;
	}
	
	
}
