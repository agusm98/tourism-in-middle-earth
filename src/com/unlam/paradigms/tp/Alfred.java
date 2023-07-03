package com.unlam.paradigms.tp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.List;

public class Alfred {

	private Manager manager;

	public Alfred() throws Exception {
		this.manager = Manager.getInstance();
	}

	public List<Ticket> offerAttractions(List<User> users) throws Exception {
		Scanner inputScan = new Scanner(System.in);
		List<Ticket> tickets = new ArrayList<Ticket>();
		for (User usr : users) {
			Ticket userTicket = new Ticket(usr);

			Iterator<TourismOption> tourOptions = manager.getOptions(usr); // Offer iterator

			System.out.println("Hola " + usr.getUserName());
			while (tourOptions.hasNext()) {
				TourismOption tourOption = tourOptions.next();
				System.out.print(tourOption.toString());

				if (getResponse(inputScan).equals("S")) {
					manager.checkout(userTicket, tourOption);
				}
			}
			tickets.add(userTicket);
			showSchedule(userTicket);
		}

		inputScan.close();
		return tickets;
	}

	public void showSchedule(Ticket userTicket) {
		System.out.println(userTicket.toString());
	}

	private String getResponse(Scanner inputScan) {
		String usrResp = inputScan.nextLine().toUpperCase();
		while (!usrResp.equals("S") && !usrResp.equals("N")) {
			System.out.println("Respuesta invalida, solo se acepta S o N: ");
			usrResp = inputScan.nextLine().toUpperCase();
		}
		return usrResp;
	}

	public void expendFile(List<Ticket> tickets) throws IOException {
		if (tickets.isEmpty() || tickets == null) {
			System.out.println("Ejecucion sin usuarios.");
		} else {
			System.out.print("Finalizando y generando archivo... ");
			manager.generateTicketFile(tickets);
			System.out.println("Hecho. Vuelva prontos!\n");
		}
	}
}
