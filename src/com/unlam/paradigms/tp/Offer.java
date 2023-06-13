package com.unlam.paradigms.tp;

import java.util.List;

public abstract class Offer extends TourismOption {

	protected String name;
	protected TourismOptionType type;
	protected List<TourismOption> options;

	public Offer(String name, TourismOptionType type, List<TourismOption> options) {
		this.name = name;
		this.type = type;
		this.options = options;
	}

	public List<TourismOption> getAttractions() {
		return options;
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
		
		for (TourismOption option : options) {
			duration += option.getDuration();
		}
		
		return duration;
	}
	
	@Override
	public Double getBaseAmount() {
		double baseAmount = 0;
		
		for (TourismOption option : options) {
			baseAmount += option.getBaseAmount();
		}
		
		return baseAmount;
	}

	@Override
	public void reserve(String userName) {
		for (TourismOption option : options) {
			option.reserve(userName);
		}
	}
	
	@Override
	public Boolean isValid(User user) {
		for (TourismOption option : options) {
			if(!option.isValid(user)) {
				return false;
			}
		}
		return true;
	}

}
