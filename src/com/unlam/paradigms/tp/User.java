package com.unlam.paradigms.tp;

public class User {
	private String name;
	private Double budget;
	private Double availableHours;
	private String touristAttraction;

	public User(String name, Double budget, Double availableHours, String touristAttraction) {
		this.name = name;
		this.budget = budget;
		this.availableHours = availableHours;
		this.touristAttraction = touristAttraction;
	}

	public String getUserName() {
		return name;
	}

	public void setUserName(String userName) {
		this.name = userName;
	}

	public Double getAvailableHours() {
		return availableHours;
	}

	public void setAvailableHours(Double availableHours) {
		this.availableHours = availableHours;
	}

	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

	public String getTouristAttraction() {
		return touristAttraction;
	}

	public void setTouristAttraction(String touristAttraction) {
		this.touristAttraction = touristAttraction;
	}
	
	public boolean updateUser(TourismOption tourOption) {

		if (this.budget - tourOption.getAmountToPay() >= 0 && this.availableHours - tourOption.getDuration() >= 0) {

			this.budget -= tourOption.getAmountToPay();
			this.availableHours -= tourOption.getDuration();
			
			return true;
		}
		
		return false;
	}
}
