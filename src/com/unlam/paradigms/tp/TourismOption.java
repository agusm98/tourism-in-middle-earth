package com.unlam.paradigms.tp;

public abstract class TourismOption {

	public abstract TourismOptionType getType();

	public abstract String getName();

	public abstract Double getDuration();

	public abstract Double getBaseAmount();

	public abstract Double getAmountToPay();

	public abstract void reserve(final String userName);

	public abstract Boolean isValid(final User user);
	
	public abstract Boolean isOffer();
}
