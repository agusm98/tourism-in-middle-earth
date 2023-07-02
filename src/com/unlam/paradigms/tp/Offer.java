package com.unlam.paradigms.tp;

import java.util.ArrayList;
import java.util.List;

public abstract class Offer extends TourismOption {

	protected String name;
	protected String attractNames;
	protected TourismOptionType type;
	protected List<TourismOption> tourOptions;

	public Offer(String name, TourismOptionType type, List<TourismOption> tourOptions) {
		this.name = name;
		this.type = type;
		this.tourOptions = tourOptions;
		setAttractionNames();
	}

	public List<TourismOption> getAttractions() {
		return tourOptions;
	}

	@Override
	public TourismOptionType getType() {
		return type;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Double getDuration() {
		double duration = 0;

		for (TourismOption tourOption : tourOptions) {
			duration += tourOption.getDuration();
		}

		return duration;
	}

	@Override
	public Double getBaseAmount() {
		double baseAmount = 0;

		for (TourismOption tourOption : tourOptions) {
			baseAmount += tourOption.getBaseAmount();
		}

		return baseAmount;
	}

	@Override
	public void reserve(String userName) {
		for (TourismOption tourOption : tourOptions) {
			tourOption.reserve(userName);
		}
	}

	private void setAttractionNames() {
		List<String> formatNames = new ArrayList<String>();
		for (TourismOption tourOption : this.tourOptions) {
			formatNames.add(tourOption.getName());
		}
		this.attractNames = "[" + String.join(", ", formatNames) + "]";
	}

	@Override
	public String toString() {
		StringBuilder detail = new StringBuilder();
		detail.append("Promocion\n");
		detail.append("Atracciones incluidas: " + this.attractNames + "\n");
		detail.append("Duracion: " + String.valueOf(this.getDuration()) + " hora(s)\n");
		detail.append("Precio original: " + String.valueOf(this.getBaseAmount()) + " oro\n");
		detail.append("Precio con descuento: " + String.valueOf(this.getAmountToPay()) + " oro\n");
		return detail.toString();
	}

	@Override
	public Boolean isValid(User user) {
		for (TourismOption tourOption : tourOptions) {
			if (!tourOption.isValid(user)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Boolean isOffer() {
		return true;
	}
}
