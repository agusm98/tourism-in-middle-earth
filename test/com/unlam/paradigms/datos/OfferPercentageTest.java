package com.unlam.paradigms.datos;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class OfferPercentageTest {

	@Test
	public void testAmountToPayWithPercentage() {

		// Arrange

		final double ERROR_MARGIN = 0.0;

		TourismOption firstAttraction = new Attraction("Moria", 10.0, 2.0, 6, TourismOptionType.AVENTURA);
		TourismOption secondAttraction = new Attraction("Selva Embrujada", 59.0, 1.0, 5, TourismOptionType.AVENTURA);
		TourismOption thirdAttraction = new Attraction("Mordor", 25.0, 3.0, 4, TourismOptionType.AVENTURA);

		List<TourismOption> tourismOptions = new ArrayList<TourismOption>();
		tourismOptions.add(firstAttraction);
		tourismOptions.add(secondAttraction);
		tourismOptions.add(thirdAttraction);

		TourismOption anOffer = new OfferPercentage("Paquete", TourismOptionType.AVENTURA, tourismOptions, "25");

		// Act

		final double totalAmountExpected = 23.5;

		double totalAmoutObtained = anOffer.getAmountToPay();

		// Assert

		Assert.assertEquals(totalAmountExpected, totalAmoutObtained, ERROR_MARGIN);
	}

}
