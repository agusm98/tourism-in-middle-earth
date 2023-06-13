package com.unlam.paradigms.tp;

import java.util.ArrayList;
import java.util.List;

public class OfferAB extends Offer {

	private List<String> freeOptions = new ArrayList<>();

	public OfferAB(String name, TourismOptionType type, List<TourismOption> options, final String parameter) {
		super(name, type, options);
		this.freeOptions = List.of(parameter.split("\\|"));
	}

	@Override
	public Double getAmountToPay() {
		double amountToPay = 0;

		for (TourismOption option : options) {
			if (!freeOptions.contains(option.getName())) {
				amountToPay += option.getAmountToPay();
			}
		}
		return amountToPay;
	}

}
