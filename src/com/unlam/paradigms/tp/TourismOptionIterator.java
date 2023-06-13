package com.unlam.paradigms.tp;

import java.util.Iterator;
import java.util.List;

public class TourismOptionIterator implements Iterator<TourismOption> {
	
	private final User user;
	private List<TourismOption> options;
	private Integer currentIndex = 0;
	
	public TourismOptionIterator(final User user, final List<TourismOption> options) {
		this.user = user;
		this.options = options;
	}

	@Override
	public boolean hasNext() {
		while(currentIndex < options.size() && !options.get(currentIndex).isValid(user)) {
			++currentIndex;
		}
		
		return currentIndex < options.size();
	}

	@Override
	public TourismOption next() {
		return options.get(currentIndex++);
	}

}
