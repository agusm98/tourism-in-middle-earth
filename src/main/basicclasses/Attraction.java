package main.basicclasses;

import main.enums.AttractionType;

public class Attraction {
	private String description;
	private double price;
	private int hours;
	private int places;
	private AttractionType attractionType;
	
	public Attraction(String description, double price, int hours, int places, AttractionType attractionType) {
		this.description = description;
		this.price = price;
		this.hours = hours;
		this.places = places;
		this.attractionType = attractionType;
	}

	@Override
	public String toString() {
		return "Attraction [" + description + "]";
	}
	
}
