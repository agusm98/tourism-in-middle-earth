package com.unlam.paradigms.tp;

import java.util.List;

public class OfferPercentage extends Offer {
	
	private Integer discount;
	
	public OfferPercentage(final String name, final TourismOptionType type, final List<TourismOption> options, final String parameter) {
		super(name, type, options);
		this.discount = Integer.valueOf(parameter);
	}

	@Override
	public Double getAmountToPay() {
		double amountToPay = 0;
		
		for (TourismOption option : options) {
			amountToPay += option.getAmountToPay();
		}
		return (amountToPay * discount) / 100;
	}

}
