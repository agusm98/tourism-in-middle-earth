package com.unlam.paradigms.datos;

import java.util.List;
import java.util.Objects;

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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		OfferAbsolute other = (OfferAbsolute) obj;
		return Objects.equals(amountToPay, other.amountToPay);
	}
}
