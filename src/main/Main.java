package main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.unlam.paradigms.datos.User;
import com.unlam.paradigms.datos.UserLoader;
import com.unlam.paradigms.tp.Alfred;
import com.unlam.paradigms.tp.Manager;
import com.unlam.paradigms.tp.Ticket;

public class Main {
	public static void main(String[] args) throws Exception {
		Manager.setAttractionPath("attracctions.txt");//args[0]
		Manager.setOfferPath("offers.txt");//args[1]
		String fileName = "src/source-data/users.txt";//args[2]
		File file = new File(fileName);
		String absolutePath = file.getAbsolutePath();

		UserLoader userLoader = new UserLoader(absolutePath);

		List<User> users = new ArrayList<User>();

		try {
			users = userLoader.processAndParse();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Alfred alfred = new Alfred();

		List<Ticket> tickets = alfred.offerAttractions(users);

		alfred.expendFile(tickets);
	}
}