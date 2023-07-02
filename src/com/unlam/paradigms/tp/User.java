package com.unlam.paradigms.tp;

public class User {
	private String name;
	private Double budget;
	private Double availableHours;
	private TourismOptionType tourType;

	public User(String name, Double budget, Double availableHours, TourismOptionType tourType) {
		this.name = name;
		this.budget = budget;
		this.availableHours = availableHours;
		this.tourType = tourType;
	}

	public String getUserName() {
		return name;
	}

	public Double getAvailableHours() {
		return availableHours;
	}

	public Double getBudget() {
		return budget;
	}

	public TourismOptionType getTourType() {
		return tourType;
	}
	
	public boolean updateUser(TourismOption tourOption) {
		if (this.budget >= tourOption.getAmountToPay() && this.availableHours >= tourOption.getDuration()) {
			this.budget -= tourOption.getAmountToPay();
			this.availableHours -= tourOption.getDuration();
			return true;
		}
		
		return false;
	}
}
