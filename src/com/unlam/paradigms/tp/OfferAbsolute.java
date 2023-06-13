package com.unlam.paradigms.tp;

import java.util.List;

public class OfferAbsolute extends Offer {
	
	final Double amountToPay;

	public OfferAbsolute(String name, TourismOptionType type, List<TourismOption> options, final String parameter) {
		super(name, type, options);
		this.amountToPay = Double.valueOf(parameter);
	}

	@Override
	public Double getAmountToPay() {
		return amountToPay;
	}

}
