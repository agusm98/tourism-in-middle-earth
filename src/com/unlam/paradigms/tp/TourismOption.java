package com.unlam.paradigms.tp;

public abstract class TourismOption implements Comparable<TourismOption> {

	public abstract TourismOptionType getType();

	public abstract String getName();

	public abstract Double getDuration();

	public abstract Double getBaseAmount();

	public abstract Double getAmountToPay();

	public abstract void reserve(String userName);

	public abstract Boolean isValid(final User user);

	public abstract Boolean isOffer();
	
	public abstract boolean equals(Object obj);

	@Override
	public int compareTo(TourismOption tO) {

		if (this.isOffer() && !tO.isOffer()) {
			return -1;
		} 
		else if(!this.isOffer() && tO.isOffer() ) {
			return 1;
		}
		else {
			if (this.getAmountToPay() < tO.getAmountToPay()) {
				return 1;
			} else if (this.getAmountToPay() > tO.getAmountToPay()) {
				return -1;
			} else {
				if (this.getDuration() < tO.getDuration()) {
					return 1;
				} else if (this.getDuration() > tO.getDuration()) {
					return -1;
				}
			}
		}

		return 0;
	}
}
