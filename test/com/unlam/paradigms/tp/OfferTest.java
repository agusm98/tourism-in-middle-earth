package com.unlam.paradigms.tp;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class OfferTest {

	@Test
	public void testOneAttractionFullIsOfferFull() {

		// Arrange

		User aUser = new User("Usuario", 150.0, 100.0, TourismOptionType.DEGUSTACION);
		User otherUser = new User("Usuaria", 150.0, 100.0, TourismOptionType.DEGUSTACION);
		TourismOption firstAttraction = new Attraction("Lothlórien", 5.0, 1.0, 30, TourismOptionType.DEGUSTACION);
		TourismOption secondAttraction = new Attraction("La Comarca", 5.0, 6.5, 1, TourismOptionType.DEGUSTACION);

		List<TourismOption> listOfAttractions = new ArrayList<TourismOption>();
		listOfAttractions.add(firstAttraction);
		listOfAttractions.add(secondAttraction);

		Offer anOffer = new OfferAbsolute("Oferta", TourismOptionType.DEGUSTACION, listOfAttractions, "8");

		// Act

		anOffer.reserve(aUser.getUserName());

		boolean expected = false;

		boolean obtained = anOffer.isValid(otherUser);

		// Assert

		Assert.assertEquals(expected, obtained);

	}

	@Test
	public void testIsReservedAtEachAttraction() {
		
		// Arrange

		User aUser = new User("Usuario", 150.0, 100.0, TourismOptionType.DEGUSTACION);
		
		TourismOption firstAttraction = new Attraction("Lothlórien", 5.0, 1.0, 30, TourismOptionType.DEGUSTACION);
		TourismOption secondAttraction = new Attraction("La Comarca", 5.0, 6.5, 2, TourismOptionType.DEGUSTACION);

		List<TourismOption> listOfAttractions = new ArrayList<TourismOption>();
		listOfAttractions.add(firstAttraction);
		listOfAttractions.add(secondAttraction);

		Offer anOffer = new OfferAbsolute("Oferta", TourismOptionType.DEGUSTACION, listOfAttractions, "8");

		// Act

		anOffer.reserve(aUser.getUserName());

		boolean expected = false;

		// Assert
		
		for(TourismOption obtained: anOffer.tourOptions) {
			Assert.assertEquals(expected, obtained.isValid(aUser));
		}

	}

	@Test
	public void testOfferNotOfferedTwiceToSameUser() {

		// Arrange
		
		User aUser = new User("Usuario", 150.0, 100.0, TourismOptionType.DEGUSTACION);
		
		TourismOption firstAttraction = new Attraction("Lothlórien", 5.0, 1.0, 30, TourismOptionType.DEGUSTACION);
		TourismOption secondAttraction = new Attraction("La Comarca", 5.0, 6.5, 2, TourismOptionType.DEGUSTACION);

		List<TourismOption> listOfAttractions = new ArrayList<TourismOption>();
		listOfAttractions.add(firstAttraction);
		listOfAttractions.add(secondAttraction);

		Offer anOffer = new OfferAbsolute("Oferta", TourismOptionType.DEGUSTACION, listOfAttractions, "8");

		// Act

		if (anOffer.isValid(aUser)) {
			anOffer.reserve(aUser.getUserName());
		}

		boolean expected = false;
		boolean obtained = anOffer.isValid(aUser);

		// Assert

		Assert.assertEquals(expected, obtained);
	}

}
