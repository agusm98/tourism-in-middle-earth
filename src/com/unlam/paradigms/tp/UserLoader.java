package com.unlam.paradigms.tp;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileInputStream;


public class UserLoader {
	private String path;

	public UserLoader(String path) {
		this.path = path;
	}

	public List<User> processAndParse() throws Exception  {
		
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));

			String line;

			List<User> users = new ArrayList<User>();

			while ((line = reader.readLine()) != null) {
				// Parse the line and create a User object
				String[] elements = line.trim().split(","); // trim delete "/n" and blank spaces
				// Add more logic here to parse and create User objects

				String name = elements[0];
				Double budget = Double.parseDouble(elements[1]);
				Double availableHours = Double.parseDouble(elements[2]);
				TourismOptionType touristAttraction = TourismOptionType.valueOf(elements[3]);;

				User user = new User(name, budget, availableHours, touristAttraction);

				users.add(user);
				
			}
			reader.close();
			
			return users;
			
		
	}

}
