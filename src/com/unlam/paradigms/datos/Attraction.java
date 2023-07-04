package com.unlam.paradigms.datos;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

public class Attraction extends TourismOption {

	private String name;
	private Double price;
	private Double hours;
	private Integer places;
	private TourismOptionType type;
	private List<String> reservations = new ArrayList<String>();

	public Attraction(String name, Double price, Double hours, Integer places, TourismOptionType type) {
		this.name = name;
		this.price = price;
		this.hours = hours;
		this.places = places;
		this.type = type;
	}

	public Integer getPlaces() {
		return places;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public TourismOptionType getType() {
		return type;
	}

	@Override
	public Double getDuration() {
		return hours;
	}

	@Override
	public Double getBaseAmount() {
		return price;
	}

	@Override
	public Double getAmountToPay() {
		return price;
	}

	@Override
	public void reserve(String userName) {
		reservations.add(userName);
		--this.places;
	}

	@Override
	public String toString() {
		StringBuilder detail = new StringBuilder();
		detail.append("Atraccion\n");
		detail.append("Nombre: " + this.name + "\n");
		detail.append("Duracion: " + String.valueOf(this.hours) + " hora(s)\n");
		detail.append("Precio: " + String.valueOf(this.price) + " oro\n");
		return detail.toString();
	}

	@Override
	public Boolean isValid(User user) {
		return this.places > 0 && user.getAvailableHours() >= this.hours && user.getBudget() >= this.price
				&& !this.reservations.contains(user.getUserName());
	}

	@Override
	public Boolean isOffer() {
		return false;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Attraction other = (Attraction) obj;
		return Objects.equals(hours, other.hours) && Objects.equals(name, other.name)
				&& Objects.equals(places, other.places) && Objects.equals(price, other.price)
				&& Objects.equals(reservations, other.reservations) && type == other.type;
	}
}
