package com.unlam.paradigms.tp;

import java.util.Iterator;
import java.util.List;

public class TourismOptionIterator implements Iterator<TourismOption> {
	
	private final User user;
	private List<TourismOption> attractions;
	
	public TourismOptionIterator(final User user, final List<TourismOption> attractions) {
		this.user = user;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	public TourismOption next() {
		// TODO Auto-generated method stub
		return null;
	}

}
