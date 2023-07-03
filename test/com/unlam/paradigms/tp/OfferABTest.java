package com.unlam.paradigms.tp;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import org.junit.Test;

public class OfferABTest {

	@Test
	public void testNotAttractionsFree() {
		
		// Arrange

		final double ERROR_MARGIN = 0.0;

		TourismOption firstAttraction = new Attraction("Minas Tirith",5.0,2.5,25,TourismOptionType.PAISAJE);
		TourismOption secondAttraction = new Attraction("Abismo de Helm",5.0,2.0,15,TourismOptionType.PAISAJE);
		TourismOption thirdAttraction = new Attraction("Erebor",12.0,3.0,32,TourismOptionType.PAISAJE);

		List<TourismOption> tourismOptions = new ArrayList<TourismOption>();

		tourismOptions.add(firstAttraction);
		tourismOptions.add(secondAttraction);
		tourismOptions.add(thirdAttraction);

		TourismOption anOffer = new OfferAB("Paquete1", TourismOptionType.PAISAJE, tourismOptions,"");

		// Act

		final double expected = 22.0;
		double obtained = anOffer.getAmountToPay();

		// Assert

		Assert.assertEquals(expected, obtained, ERROR_MARGIN);
	}

	@Test
	public void testEveryAttractionFree() {
		
		// Arrange

		final double ERROR_MARGIN = 0.0;

		TourismOption firstAttraction = new Attraction("Minas Tirith",5.0,2.5,25,TourismOptionType.PAISAJE);
		TourismOption secondAttraction = new Attraction("Abismo de Helm",5.0,2.0,15,TourismOptionType.PAISAJE);
		TourismOption thirdAttraction = new Attraction("Erebor",12.0,3.0,32,TourismOptionType.PAISAJE);

		List<TourismOption> tourismOptions = new ArrayList<TourismOption>();

		tourismOptions.add(firstAttraction);
		tourismOptions.add(secondAttraction);
		tourismOptions.add(thirdAttraction);

		TourismOption anOffer = new OfferAB("Paquete1", TourismOptionType.PAISAJE, tourismOptions,"Minas Tirith|Abismo de Helm|Erebor");

		// Act

		final double expected = 0.0;
		double obtained = anOffer.getAmountToPay();

		// Assert

		Assert.assertEquals(expected, obtained, ERROR_MARGIN);
	}
	
}
