package com.unlam.paradigms.tp;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.unlam.paradigms.datos.Attraction;
import com.unlam.paradigms.datos.TourismOptionType;
import com.unlam.paradigms.datos.User;

public class AlfredTest {

	@Test
	public void offerAttractionWithoutBudget() throws Exception {
		Manager.setSourcePath("test/source-data/");
		Manager.setAttractionPath("Case-1/attracctions.txt");
		
		//Manager.setOfferPath();
		Alfred alfred = new Alfred();
		ByteArrayInputStream input = new ByteArrayInputStream("S".getBytes());
		System.setIn(input);
		List<User> lstUser = new ArrayList<User>();
		lstUser.add(new User("Alfred", 0.0, 20.0, TourismOptionType.AVENTURA));
		List<Ticket> usrTicket = alfred.offerAttractions(lstUser);
		Assert.assertEquals(usrTicket.size(), 1);
		Assert.assertEquals(usrTicket.get(0).getOptions().size(), 0);
		Assert.assertEquals(usrTicket.get(0).getUser().getUserName(), lstUser.get(0).getUserName());
	}
	

	@Test
	public void offerAttractionWithoutHours() throws Exception {
		Manager.setSourcePath("test/source-data/");
		Manager.setAttractionPath("Case-1/attracctions.txt");
		
		//Manager.setOfferPath();
		Alfred alfred = new Alfred();
		ByteArrayInputStream input = new ByteArrayInputStream("S".getBytes());
		System.setIn(input);
		List<User> lstUser = new ArrayList<User>();
		lstUser.add(new User("Alfred", 900.0, 0.0, TourismOptionType.AVENTURA));
		List<Ticket> usrTicket = alfred.offerAttractions(lstUser);
		Assert.assertEquals(usrTicket.size(), 1);
		Assert.assertEquals(usrTicket.get(0).getOptions().size(), 0);
		Assert.assertEquals(usrTicket.get(0).getUser().getUserName(), lstUser.get(0).getUserName());
	}
	
	@Test
	public void offerAttractionCheckout() throws Exception {
		Manager.setSourcePath("test/source-data/");
		Manager.setAttractionPath("Case-1/attractions.txt");
		
		//Manager.setOfferPath();
		Alfred alfred = new Alfred();
		ByteArrayInputStream input = new ByteArrayInputStream("S".getBytes());
		System.setIn(input);
		List<User> lstUser = new ArrayList<User>();
		lstUser.add(new User("Alfred", 900.0, 900.0, TourismOptionType.AVENTURA));
		List<Ticket> usrTicket = alfred.offerAttractions(lstUser);
		Assert.assertEquals(usrTicket.size(), 1);
		Assert.assertEquals(usrTicket.get(0).getOptions().size(), 1);
		Assert.assertEquals(usrTicket.get(0).getUser().getUserName(), lstUser.get(0).getUserName());
	}
	
	@Test
	public void alfredShowSchedule_NullTicket() throws Exception {
		Alfred alfred = new Alfred();
		Ticket emptyTicket = new Ticket(null);
		try {
			alfred.showSchedule(emptyTicket);
		} catch(NullPointerException e) {
			//Pass
		} catch(Exception e) {
			throw new Exception("Expected nullPointerException.");
		}
	}
	
	@Test
	public void alfredShowSchedule_EmptyTicket() throws Exception {
		Alfred alfred = new Alfred();
		Ticket emptyTicket = new Ticket(new User("Alfred", 900.0, 900.0, TourismOptionType.AVENTURA));
		alfred.showSchedule(emptyTicket);
	}
	
	@Test
	public void alfredShowSchedule_UserTicketWAttraction() throws Exception {
		Alfred alfred = new Alfred();
		User usr = new User("Alfred", 30.0, 20.0, TourismOptionType.AVENTURA);
		Ticket usrTicket = new Ticket(usr);
		usrTicket.addTourOption(new Attraction("Mordor", 25.0, 3.0, 0, TourismOptionType.AVENTURA));
		alfred.showSchedule(usrTicket);
	}
}
