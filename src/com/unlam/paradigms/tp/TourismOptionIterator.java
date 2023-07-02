package com.unlam.paradigms.tp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class TourismOptionIterator implements Iterator<TourismOption> {
	
	private final User user;
	private List<TourismOption> userTourOptions;
	private Integer currentIndex;
	
	public TourismOptionIterator(final User usr, final List<TourismOption> tourOptions) {
		this.user = usr;
		this.currentIndex = 0;
		setUserTourOptions(tourOptions);
	}
	
	private void setUserTourOptions(final List<TourismOption> tourOptions) {
		List<TourismOption> otherTourOptions = new ArrayList<TourismOption>();
		this.userTourOptions = new ArrayList<TourismOption>();
		
		for(TourismOption tourOption : tourOptions) {
			if(tourOption.isValid(this.user)) {
				if(tourOption.getType() == this.user.getTourType()) {
					this.userTourOptions.add(tourOption);
				} else {
					otherTourOptions.add(tourOption);
				}
				
			}
		}
		
		if(this.userTourOptions.size() > 1) {
			Collections.sort(this.userTourOptions);
		}
		
		if(otherTourOptions.size() > 1) {
			Collections.sort(otherTourOptions);
		}
		this.userTourOptions.addAll(otherTourOptions);
	}

	@Override
	public boolean hasNext() {
		while(currentIndex < userTourOptions.size() && !userTourOptions.get(currentIndex).isValid(user)) {
			++currentIndex;
		}
		
		return currentIndex < userTourOptions.size();
	}

	@Override
	public TourismOption next() {
		return userTourOptions.get(currentIndex++);
	}

}
