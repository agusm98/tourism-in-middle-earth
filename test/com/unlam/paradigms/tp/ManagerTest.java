package com.unlam.paradigms.tp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.unlam.paradigms.datos.Attraction;
import com.unlam.paradigms.datos.OfferAbsolute;
import com.unlam.paradigms.datos.TourismOption;
import com.unlam.paradigms.datos.TourismOptionType;
import com.unlam.paradigms.datos.User;
import com.unlam.paradigms.datos.UserLoader;

public class ManagerTest {

	@Test
	public void testUserBuyAnythingHeCan() throws Exception {

		// Attractions to sell
		TourismOption firstAttraction = new Attraction("Selva", 20.5, 1.0, 7, TourismOptionType.PAISAJE);
		TourismOption secondAttraction = new Attraction("Templo de Anubis", 30.0, 3.0, 20, TourismOptionType.PAISAJE);
		TourismOption thirdAttraction = new Attraction("Minas Tirith", 5.0, 2.5, 25, TourismOptionType.PAISAJE);
		TourismOption fourthAttraction = new Attraction("Erebor", 12.0, 3.0, 32, TourismOptionType.PAISAJE);
		TourismOption fifthAttraction = new Attraction("La Comarca", 3.0, 6.5, 150, TourismOptionType.DEGUSTACION);

		List<TourismOption> attractions = new ArrayList<TourismOption>();
		attractions.add(firstAttraction);
		attractions.add(secondAttraction);
		attractions.add(thirdAttraction);

		TourismOption firstOffer = new OfferAbsolute("Paquete2", TourismOptionType.PAISAJE, attractions, "15");

		List<TourismOption> optionsExpected = new ArrayList<TourismOption>();

		// order of options expected
		optionsExpected.add(firstOffer);
		optionsExpected.add(fourthAttraction);
		optionsExpected.add(fifthAttraction);

		Manager.setAttractionPath("attracctions.txt");
		Manager.setOfferPath("offers.txt");
		Manager.setSourcePath("test/source-data/Case-3/");

		UserLoader aLoader = new UserLoader("test/source-data/Case-3/users.txt");
		User aUser = aLoader.processAndParse().remove(0);

		Ticket aTicket = new Ticket(aUser);
		Manager aManager = Manager.getInstance();

		Iterator<TourismOption> optionsObtained = aManager.getOptions(aUser);

		// Assert

		for (TourismOption exp : optionsExpected) {
			if (optionsObtained.hasNext()) {
				TourismOption obt = optionsObtained.next();
				if (obt.isValid(aUser)) {
					Assert.assertEquals(exp, obt);
					aManager.checkout(aTicket, obt);
				} else {
					Assert.fail();
				}

			} else {
				Assert.fail();
			}
		}

	}
	
	@Test
	public void testUserCannotBuyAnything() throws Exception {

		List<TourismOption> optionsExpected = new ArrayList<TourismOption>();

		Manager.setAttractionPath("attracctions.txt");
		Manager.setSourcePath("test/source-data/Case-2/");

		UserLoader aLoader = new UserLoader("test/source-data/Case-2/users.txt");
		User aUser = aLoader.processAndParse().remove(0);

		Ticket aTicket = new Ticket(aUser);
		Manager aManager = Manager.getInstance();

		Iterator<TourismOption> optionsObtained = aManager.getOptions(aUser);

		// Assert

		for (TourismOption exp : optionsExpected) {
			if (optionsObtained.hasNext()) {
				TourismOption obt = optionsObtained.next();
				if (obt.isValid(aUser)) {
					Assert.assertEquals(exp, obt);
					aManager.checkout(aTicket, obt);
				} else {
					Assert.fail();
				}

			} else {
				Assert.fail();
			}
		}

	}

}
