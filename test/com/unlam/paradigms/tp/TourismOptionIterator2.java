package com.unlam.paradigms.tp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TourismOptionIterator2 implements Iterator<TourismOption> {
	
	private List<TourismOption> listTourOptions;
	private Integer currentIndex;
	
	public TourismOptionIterator2(final List<TourismOption> tourOptions) {
		this.currentIndex = 0;
		this.listTourOptions = new ArrayList<TourismOption>(tourOptions);
	}
	
	@Override
	public boolean hasNext() {
		if(currentIndex >= listTourOptions.size()) {
			return false;
		}
		return true;
	}

	@Override
	public TourismOption next() {
		return listTourOptions.get(currentIndex++);
	}

}
