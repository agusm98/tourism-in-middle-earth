package com.unlam.paradigms.tp;

import java.util.List;

public abstract class Offer extends TourismOption {

	protected String name;
	protected TourismOptionType type;
	private List<TourismOption> options;

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
		// TODO Auto-generated method stub
		return type;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public void reserve(String userName) {
		for (TourismOption tourismOption : options) {
			tourismOption.reserve(userName);
		}
	}
	
	@Override
	public Boolean isValid(User user) {
		for (TourismOption tourismOption : options) {
			if(!tourismOption.isValid(user)) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public int compareTo(TourismOption o) {
		
		for (TourismOption tourismOption : options) {
			
			
		}
		
		return 0;
	}
}
