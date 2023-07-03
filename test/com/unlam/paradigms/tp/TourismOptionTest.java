package com.unlam.paradigms.tp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.Assert;

public class TourismOptionTest {

	@Test
	public void testCompareToRespectTheOrder() {

		// Arrange
		
		List<TourismOption> offerAxB = new ArrayList<TourismOption>();
		List<TourismOption> offerAbs = new ArrayList<TourismOption>();

		//Same Type of TourismOption for all to not alter the order
		User aUser = new User("Denis", 100.0, 25.0, TourismOptionType.PAISAJE);
		
		//Create the attractions
		TourismOption firstAttraction = new Attraction("Templo de Anubis", 30.0, 3.0, 20, TourismOptionType.PAISAJE);
		TourismOption secondAttraction = new Attraction("Selva", 20.5, 1.0, 7, TourismOptionType.PAISAJE);
		TourismOption thirdAttraction = new Attraction("Erebor", 12.0, 3.0, 32, TourismOptionType.PAISAJE);
		TourismOption fourthAttraction = new Attraction("Minas Tirith", 5.0, 2.5, 25, TourismOptionType.PAISAJE);
		TourismOption fifthAttraction = new Attraction("Abismo de Helm", 5.0, 2.0, 15, TourismOptionType.PAISAJE);

		offerAxB.add(firstAttraction);
		offerAxB.add(secondAttraction);
		offerAxB.add(thirdAttraction);

		offerAbs.add(secondAttraction);
		offerAbs.add(fourthAttraction);
		offerAbs.add(fifthAttraction);

		//Create the offers
		TourismOption firstOffer = new OfferAB("Paquete1", TourismOptionType.PAISAJE, offerAxB, "Abismo de Helm|Erebor");
		TourismOption secondOffer = new OfferAbsolute("Paquete2", TourismOptionType.PAISAJE, offerAbs, "15");

		// Act
		
		List<TourismOption> tourismOptionsObtained = new ArrayList<TourismOption>();
		
		//Add TourismOptions to unsorted list and then sort that
		tourismOptionsObtained.add(secondOffer);
		tourismOptionsObtained.add(fifthAttraction);
		tourismOptionsObtained.add(firstAttraction);
		tourismOptionsObtained.add(secondAttraction);
		tourismOptionsObtained.add(firstOffer);
		tourismOptionsObtained.add(thirdAttraction);
		tourismOptionsObtained.add(fourthAttraction);

		Collections.sort(tourismOptionsObtained);
		
		List<TourismOption> tourismOptionsExpected= new ArrayList<TourismOption>();
		
		//Add TourismOption to sorted list
		tourismOptionsExpected.add(firstOffer);
		tourismOptionsExpected.add(secondOffer);
		tourismOptionsExpected.add(firstAttraction);
		tourismOptionsExpected.add(secondAttraction);
		tourismOptionsExpected.add(thirdAttraction);
		tourismOptionsExpected.add(fourthAttraction);
		tourismOptionsExpected.add(fifthAttraction);
		

		// Assert

		Iterator<TourismOption> obtained = new TourismOptionIterator(aUser, tourismOptionsObtained);
		
		for(TourismOption exp: tourismOptionsExpected) {
			if(obtained.hasNext()) {
				TourismOption obt = obtained.next();				
				Assert.assertEquals(exp, obt);
			}
			else {
				break;
			}
		}

	}

}
