package com.unlam.paradigms.tp;

import java.util.List;

public class OfferPercentage extends Offer {
	
	private Integer discount;
	
	public OfferPercentage(final String name, final TourismOptionType type, final List<TourismOption> tourOptions, final String parameter) {
		super(name, type, tourOptions);
		this.discount = Integer.valueOf(parameter);
	}

	@Override
	public Double getAmountToPay() {
		double amountToPay = 0;
		
		for (TourismOption tourOption : tourOptions) {
			amountToPay += tourOption.getAmountToPay();
		}
		return (amountToPay * discount) / 100;
	}

}
