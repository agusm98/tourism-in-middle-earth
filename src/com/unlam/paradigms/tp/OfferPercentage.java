package com.unlam.paradigms.tp;

import java.util.List;

public class OfferPercentage extends Offer {

	public OfferPercentage(String name, TourismOptionType type, List<TourismOption> attractions) {
		super(name, type, attractions);
	}

	@Override
	public Double getBaseAmount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getAmountToPay() {		
		double totalPrice = 0;
		
		for (TourismOption attraction : super.getAttractions()) {
			totalPrice += attraction.getBaseAmount();
		}
		
		return totalPrice * 0.8;
	}

}
