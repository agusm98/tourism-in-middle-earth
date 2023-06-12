package com.unlam.paradigms.tp;

import java.util.List;

public class OfferDescription {
	private String name;
	private List<String> attractionNames;
	private OfferType offerType;
	private TourismOptionType tourismOptionType;

	public OfferDescription(String name, List<String> attractionNames, OfferType offerType,
			TourismOptionType tourismOptionType) {
		this.name = name;
		this.attractionNames = attractionNames;
		this.offerType = offerType;
		this.tourismOptionType = tourismOptionType;
	}

	public String getName() {
		return name;
	}

	public List<String> getAttractionNames() {
		return attractionNames;
	}

	public OfferType getOfferType() {
		return offerType;
	}

	public TourismOptionType getTourismOptionType() {
		return tourismOptionType;
	}
	
	public Offer createOffer(final List<TourismOption> attractions) {
		switch (offerType) {
		case DISCOUNT: {
			return new OfferPercentage(name, tourismOptionType, attractions);
		}
		case AxB: {
			return new OfferAB(name, tourismOptionType, attractions);
		}
		case ABSOLUTE: {
			return new OfferAbsolute(name, tourismOptionType, attractions);
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + offerType);
		}
	}

}
