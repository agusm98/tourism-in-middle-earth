package com.unlam.paradigms.tp;

import java.util.List;
import java.util.Objects;

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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		OfferPercentage other = (OfferPercentage) obj;
		return Objects.equals(discount, other.discount);
	}
}
