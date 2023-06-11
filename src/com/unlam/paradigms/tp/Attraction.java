package com.unlam.paradigms.tp;

import java.util.Collections;
import java.util.List;

public class Attraction extends TourismOption {
	private String name;
	private Double price;
	private Double hours;
	private Integer places;
	private TourismOptionType type;
	private final List<String> revervations = Collections.emptyList();

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
		revervations.add(userName);
	}

	@Override
	public Boolean isValid(User user) {
		return places > 0;
	}

}
