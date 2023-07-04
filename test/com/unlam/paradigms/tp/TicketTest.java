package com.unlam.paradigms.tp;

import org.junit.Test;

import com.unlam.paradigms.datos.Attraction;
import com.unlam.paradigms.datos.TourismOption;
import com.unlam.paradigms.datos.TourismOptionType;
import com.unlam.paradigms.datos.User;

import org.junit.Assert;

public class TicketTest {

	@Test
	public void testGetTotalAmount() {

		// Arrange

		final double ERROR_MARGIN = 0.0;

		TourismOption firstAttraction = new Attraction("La Comarca", 3.0, 6.5, 150, TourismOptionType.DEGUSTACION);
		TourismOption secondAttraction = new Attraction("Lothlórien", 35.0, 1.0, 30, TourismOptionType.DEGUSTACION);
		TourismOption thirdAttraction = new Attraction("La Casa", 5.0, 2.0, 20, TourismOptionType.DEGUSTACION);
		TourismOption fourthAttraction = new Attraction("El irlandes", 10.0, 3.0, 4, TourismOptionType.DEGUSTACION);

		User client = new User("Tomi", 300.0, 2.0, TourismOptionType.DEGUSTACION);
		Ticket finalTicket = new Ticket(client);

		// Act

		finalTicket.addTourOption(firstAttraction);
		finalTicket.addTourOption(secondAttraction);
		finalTicket.addTourOption(thirdAttraction);
		finalTicket.addTourOption(fourthAttraction);

		final double totalAmountExpected = 53.0;

		double totalAmountObtained = finalTicket.getTotalAmount();

		// Assert

		Assert.assertEquals(totalAmountExpected, totalAmountObtained, ERROR_MARGIN);

	}

	@Test
	public void testGetTotalDuration() {

		// Arrange

		final double ERROR_MARGIN = 0.0;

		TourismOption firstAttraction = new Attraction("La Comarca", 3.0, 6.5, 150, TourismOptionType.DEGUSTACION);
		TourismOption secondAttraction = new Attraction("Lothlórien", 35.0, 1.0, 30, TourismOptionType.DEGUSTACION);
		TourismOption thirdAttraction = new Attraction("La Casa", 5.0, 2.0, 20, TourismOptionType.DEGUSTACION);
		TourismOption fourthAttraction = new Attraction("El irlandes", 10.0, 3.0, 4, TourismOptionType.DEGUSTACION);

		User client = new User("Tomi", 300.0, 2.0, TourismOptionType.DEGUSTACION);
		Ticket finalTicket = new Ticket(client);

		// Act

		finalTicket.addTourOption(firstAttraction);
		finalTicket.addTourOption(secondAttraction);
		finalTicket.addTourOption(thirdAttraction);
		finalTicket.addTourOption(fourthAttraction);

		final double totalDurationExpected = 12.5;

		double totalDurationObtained = finalTicket.getTotalDuration();

		// Assert

		Assert.assertEquals(totalDurationExpected, totalDurationObtained, ERROR_MARGIN);
	}

	@Test
	public void testGetOptions() {

		// Arrange

		TourismOption expectedAttraction = new Attraction("La Comarca", 3.0, 6.5, 150, TourismOptionType.DEGUSTACION);
		User client = new User("Tomi", 300.0, 2.0, TourismOptionType.DEGUSTACION);
		Ticket finalTicket = new Ticket(client);

		// Act

		finalTicket.addTourOption(expectedAttraction);
		TourismOption obtainedAttraction = finalTicket.getOptions().remove(0);
		
		//Assert
		
		Assert.assertEquals(expectedAttraction, obtainedAttraction);
		
	}

}
