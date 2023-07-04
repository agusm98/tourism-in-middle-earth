package com.unlam.paradigms.datos;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class OfferAbsoluteTest {

	@Test
	public void testOfferWithoutAttractions() {

		// Arrange

		final double ERROR_MARGIN = 0.0;
		TourismOption anOffer = new OfferAbsolute("Paquete", TourismOptionType.BANQUETE, new ArrayList<TourismOption>(),
				"15");

		// Act

		final double expected = 15.0;
		double obtained = anOffer.getAmountToPay();

		// Assert

		Assert.assertEquals(expected, obtained, ERROR_MARGIN);

	}

	@Test
	public void testAttractionsFreeButOfferNot() {

		// Arrange

		final double ERROR_MARGIN = 0.0;
		
		TourismOption firstAttraction = new Attraction("Lothlórien", 0.0, 1.0, 30, TourismOptionType.DEGUSTACION);
		TourismOption secondAttraction = new Attraction("La Comarca", 0.0, 6.5, 150, TourismOptionType.DEGUSTACION);
		
		List<TourismOption> tourismOptions = new ArrayList<TourismOption>();
		
		tourismOptions.add(firstAttraction);
		tourismOptions.add(secondAttraction);
		
		TourismOption anOffer = new OfferAbsolute("Paquete", TourismOptionType.DEGUSTACION, tourismOptions, "15");

		// Act

		final double expected = 15.0;
		double obtained = anOffer.getAmountToPay();

		// Assert

		Assert.assertEquals(expected, obtained, ERROR_MARGIN);

	}

}
