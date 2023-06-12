package main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.unlam.paradigms.tp.User;
import com.unlam.paradigms.tp.UserLoader;
import com.unlam.paradigms.tp.Alfred;
import com.unlam.paradigms.tp.Ticket;

public class Main {
	public static void main(String[] args) {
		String fileName = "src/source-data/users.txt";
		File file = new File(fileName);
		String absolutePath = file.getAbsolutePath();

		UserLoader userLoader = new UserLoader(absolutePath);

		Alfred alfred = new Alfred();

		List<User> users = new ArrayList<User>();

		List<Ticket> tickets = new ArrayList<Ticket>();

		List<Ticket> totalTickets = new ArrayList<Ticket>();

		try {
			users = userLoader.processAndParse();
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (User user : users) {
			System.out.println(user.getUserName() + "--" + user.getBudget() + "--" + user.getAvailableHours() + "--"
					+ user.getTouristAttraction());
			tickets = alfred.offerAttractions(user);

			alfred.showSchedule(tickets);

			totalTickets.addAll(tickets);
		}

		//Manager.generateFile(totalTickets);

	}
}