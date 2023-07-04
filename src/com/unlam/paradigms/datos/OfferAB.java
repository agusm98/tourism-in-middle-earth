package com.unlam.paradigms.datos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		OfferAB other = (OfferAB) obj;
		return Objects.equals(freeOptions, other.freeOptions);
	}
}
