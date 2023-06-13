package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.unlam.paradigms.tp.User;
import com.unlam.paradigms.tp.UserLoader;
import com.unlam.paradigms.tp.Alfred;
import com.unlam.paradigms.tp.Manager;
import com.unlam.paradigms.tp.Ticket;

public class Main {
	public static void main(String[] args) throws IOException {
		String fileName = "src/source-data/users.txt";
		File file = new File(fileName);
		String absolutePath = file.getAbsolutePath();
		Manager manager = Manager.getInstance();
		manager.initialize();

		UserLoader userLoader = new UserLoader(absolutePath);

		Alfred alfred = new Alfred();

		List<User> users = new ArrayList<User>();

		List<Ticket> tickets = new ArrayList<Ticket>();

		try {
			users = userLoader.processAndParse();
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (User user : users) {
		    System.out.println("Hola "+user.getUserName());
			Ticket userTicket = alfred.offerAttractions(user);
			if(userTicket != null) {
			    alfred.showSchedule(userTicket);
			    tickets.add(userTicket);
			}
		}

		manager.generateTicketFile(tickets);

	}
}