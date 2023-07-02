package com.unlam.paradigms.tp;

import java.util.ArrayList;
import java.util.List;

public abstract class Offer extends TourismOption {

	protected String name;
	protected String attractNames;
	protected TourismOptionType type;
	protected List<TourismOption> tourOptions;

	public Offer(String name, TourismOptionType type, List<TourismOption> tourOptions) {
		//this.name = name;
		this.type = type;
		this.tourOptions = tourOptions;
		setName();
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
		for(TourismOption tourOption : tourOptions) {
			tourOption.reserve(userName);
		}
	}
	
	private void setName() {
		List<String> formatNames = new ArrayList<String>();
		for(TourismOption tourOption : this.tourOptions) {
			formatNames.add(tourOption.getName());
		}
		this.name = "["+String.join(", ", formatNames)+"]";
	}
	

	@Override
	public void showDescription() {
        System.out.println("Promocion");
        System.out.println("Atracciones incluidas: "+this.getName());
        System.out.println("Duracion: "+String.valueOf(this.getDuration()));
        System.out.println("Precio original: "+String.valueOf(this.getBaseAmount()));
        System.out.println("Precio con descuento: "+String.valueOf(this.getAmountToPay()));
	}
	
	@Override
	public Boolean isValid(User user) {
		for(TourismOption tourOption : tourOptions) {
			if(!tourOption.isValid(user)) {
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
