package com.unlam.paradigms.tp;

import org.junit.Assert;

import org.junit.Test;

public class AttractionTest {

	@Test
	public void testNobodyCanRideFullAttraction() {

		// Arrange

		User aUser = new User("Thomas", 30.0, 20.0, TourismOptionType.AVENTURA);
		Attraction anAttraction = new Attraction("Mordor", 25.0, 3.0, 0, TourismOptionType.AVENTURA);

		// Act

		boolean expected = false;
		boolean obtained = anAttraction.isValid(aUser);

		// Assert

		Assert.assertEquals(expected, obtained);

	}

	@Test
	public void testAttractionNotOfferedTwiceToSameUser() {

		// Arrange

		User aUser = new User("Denis", 15.0, 7.0, TourismOptionType.AVENTURA);
		Attraction anAttraction = new Attraction("Mordor", 25.0, 3.0, 10, TourismOptionType.AVENTURA);

		// Act

		if (anAttraction.isValid(aUser)) {
			anAttraction.reserve(aUser.getUserName());
		}

		boolean expected = false;
		boolean obtained = anAttraction.isValid(aUser);

		// Assert

		Assert.assertEquals(expected, obtained);
	}

	@Test
	public void testGettersNotChangeTheAtributes() {

		// Arrange

		Attraction anAttraction = new Attraction("Mordor", 25.0, 3.0, 10, TourismOptionType.AVENTURA);

		Attraction otherAttraction = new Attraction(anAttraction.getName(), anAttraction.getBaseAmount(),
				anAttraction.getDuration(), anAttraction.getPlaces(), anAttraction.getType());

		// Act

		boolean expected = true;

		boolean obtained = anAttraction.equals(otherAttraction);

		// Assert
		
		Assert.assertEquals(expected, obtained);

	}
}
