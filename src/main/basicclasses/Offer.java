package main.basicclasses;

import java.util.List;

public abstract class Offer {
	
	private List<Attraction> offers;

	public Offer(List<Attraction> offers) {
		this.offers = offers;
	}

	public List<Attraction> getAttractions() {
		return offers;
	}
}
