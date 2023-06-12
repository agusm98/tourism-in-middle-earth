package com.unlam.paradigms.tp;

import java.util.List;

public class OfferAB extends Offer {

	public OfferAB(String name, TourismOptionType type, List<TourismOption> attractions) {
		super(name, type, attractions);
	}

	@Override
	public Double getBaseAmount() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Double getLastBaseAmount() {
		return super.getAttractions().get(super.getAttractions().size() - 1).getBaseAmount();
	}

	@Override
	public Double getAmountToPay() {
		double totalPrice = 0;
		
		for (TourismOption attraction : super.getAttractions()) {
			totalPrice += attraction.getBaseAmount();
		}
		
		return totalPrice - getLastBaseAmount();
	}

}
