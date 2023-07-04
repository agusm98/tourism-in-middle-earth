package com.unlam.paradigms.datos;

import org.junit.Assert;
import org.junit.Test;

public class UserTest {

	@Test
	public void testUserWithoutAvailableHours() {
		
		//Arrange
		
		User busyUser = new User("Usuario", 150.0, 0.0, TourismOptionType.AVENTURA);
		Attraction vacantAttraction = new Attraction("Atraccion", 25.0, 2.0, 15, TourismOptionType.AVENTURA);
		
		//Act
		
		boolean expected = false;
		boolean obtained = busyUser.updateUser(vacantAttraction);
		
		//Assert
		
		Assert.assertEquals(expected, obtained);
		
	}
	@Test
	public void testUserWithoutAvailableBudget() {
		
		//Arrange
		
		User bankruptUser = new User("Usuario", 0.0, 1.0, TourismOptionType.AVENTURA);
		Attraction normalAttraction = new Attraction("Atraccion", 25.0, 2.0, 15, TourismOptionType.AVENTURA);
		
		//Act
		
		boolean expected = false;
		boolean obtained = bankruptUser.updateUser(normalAttraction);
		
		//Assert
		
		Assert.assertEquals(expected, obtained);
		
	}

}
