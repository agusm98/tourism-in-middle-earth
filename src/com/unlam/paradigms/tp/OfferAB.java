package com.unlam.paradigms.tp;

import java.util.ArrayList;
import java.util.List;

public class OfferAB extends Offer {

	private List<String> freeOptions = new ArrayList<>();

	public OfferAB(String name, TourismOptionType type, List<TourismOption> tourOptions, final String parameter) {
		super(name, type, tourOptions);
		this.freeOptions = List.of(parameter.split("\\|"));
	}

	@Override
	public Double getAmountToPay() {
		double amountToPay = 0;

		for (TourismOption tourOption : tourOptions) {
			if (!freeOptions.contains(tourOption.getName())) {
				amountToPay += tourOption.getAmountToPay();
			}
		}
		return amountToPay;
	}

}
