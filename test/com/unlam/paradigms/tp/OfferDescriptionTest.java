package com.unlam.paradigms.tp;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import org.junit.Test;

public class OfferDescriptionTest {

	@Test
	public void testBothMethodsToCreateOfferAB() {

		// Arrange

		TourismOption firstAttraction = new Attraction("Minas Tirith", 5.0, 2.5, 25, TourismOptionType.PAISAJE);
		TourismOption secondAttraction = new Attraction("Abismo de Helm", 5.0, 2.0, 15, TourismOptionType.PAISAJE);
		TourismOption thirdAttraction = new Attraction("Erebor", 12.0, 3.0, 32, TourismOptionType.PAISAJE);

		List<TourismOption> attractions = new ArrayList<TourismOption>();
		attractions.add(firstAttraction);
		attractions.add(secondAttraction);
		attractions.add(thirdAttraction);

		List<String> attractionsNames = new ArrayList<String>();
		attractionsNames.add(firstAttraction.getName());
		attractionsNames.add(secondAttraction.getName());
		attractionsNames.add(thirdAttraction.getName());

		OfferDescription offersCreater = new OfferDescription("Paquete1", attractionsNames, OfferType.AxB,
				TourismOptionType.PAISAJE, "Abismo de Helm|Erebor");

		// Act

		Offer offerObtained = offersCreater.createOffer(attractions);

		Offer offerExpected = new OfferAB("Paquete1", TourismOptionType.PAISAJE, attractions, "Abismo de Helm|Erebor");

		// Assert

		Assert.assertEquals(offerExpected, offerObtained);

	}

	@Test
	public void testBothMethodsToCreateOfferPercentage() {

		// Arrange

		TourismOption firstAttraction = new Attraction("Templo de Anubis", 30.0, 3.0, 20, TourismOptionType.PAISAJE);
		TourismOption secondAttraction = new Attraction("Abismo de Helm", 5.0, 2.0, 15, TourismOptionType.PAISAJE);
		TourismOption thirdAttraction = new Attraction("Erebor", 12.0, 3.0, 32, TourismOptionType.PAISAJE);

		List<TourismOption> attractions = new ArrayList<TourismOption>();
		attractions.add(firstAttraction);
		attractions.add(secondAttraction);
		attractions.add(thirdAttraction);

		List<String> attractionsNames = new ArrayList<String>();
		attractionsNames.add(firstAttraction.getName());
		attractionsNames.add(secondAttraction.getName());
		attractionsNames.add(thirdAttraction.getName());

		OfferDescription offersCreater = new OfferDescription("Paquete3", attractionsNames, OfferType.DISCOUNT,
				TourismOptionType.PAISAJE, "25");

		// Act

		Offer offerObtained = offersCreater.createOffer(attractions);

		Offer offerExpected = new OfferPercentage("Paquete3", TourismOptionType.PAISAJE, attractions, "25");

		// Assert

		Assert.assertEquals(offerExpected, offerObtained);

	}

	@Test
	public void testBothMethodsToCreateOfferAbsolute() {

		// Arrange

		TourismOption firstAttraction = new Attraction("Templo de Anubis", 30.0, 3.0, 20, TourismOptionType.PAISAJE);
		TourismOption secondAttraction = new Attraction("Minas Tirith", 5.0, 2.5, 25, TourismOptionType.PAISAJE);
		TourismOption thirdAttraction = new Attraction("Selva", 20.5, 1.0, 7, TourismOptionType.PAISAJE);

		List<TourismOption> attractions = new ArrayList<TourismOption>();
		attractions.add(firstAttraction);
		attractions.add(secondAttraction);
		attractions.add(thirdAttraction);

		List<String> attractionsNames = new ArrayList<String>();
		attractionsNames.add(firstAttraction.getName());
		attractionsNames.add(secondAttraction.getName());
		attractionsNames.add(thirdAttraction.getName());

		OfferDescription offersCreater = new OfferDescription("Paquete2", attractionsNames, OfferType.ABSOLUTE,
				TourismOptionType.PAISAJE, "15");

		// Act

		Offer offerObtained = offersCreater.createOffer(attractions);

		Offer offerExpected = new OfferAbsolute("Paquete2", TourismOptionType.PAISAJE, attractions, "15");

		// Assert

		Assert.assertEquals(offerExpected, offerObtained);

	}
}
