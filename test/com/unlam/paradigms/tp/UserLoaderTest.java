package com.unlam.paradigms.tp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

import org.junit.Test;

public class UserLoaderTest {

	@Test
	public void testProcessAndParse_ValidFile() throws Exception {

		List<User> expected = new ArrayList<User>();

		expected.add(new User("jorge", 100.0, 5.0, "Mordor"));
		expected.add(new User("agustin", 200.5, 10.0, "Moria"));
		expected.add(new User("Tomi", 300.0, 2.0, "Mordor"));

		String filePath = "test/source-data/users.txt";
		File file = new File(filePath);

		String absolutePath = file.getAbsolutePath();

		UserLoader userLoader = new UserLoader(absolutePath);

		List<User> users = userLoader.processAndParse();

		// Assert
		assertNotNull(users);
		assertEquals(3, users.size());

		for (int i = 0; i < expected.size(); i++) {
			User actualUser = users.get(i);
			User expectedUser = expected.get(i);

			assertEquals(expectedUser.getUserName(), actualUser.getUserName());
			assertEquals(expectedUser.getBudget(), actualUser.getBudget());
			assertEquals(expectedUser.getAvailableHours(), actualUser.getAvailableHours());
			assertEquals(expectedUser.getTouristAttraction(), actualUser.getTouristAttraction());
		}

	}

	@Test
	public void testProcessAndParse_InvalidData() throws Exception {
		// Arrange
		String filePath = "test/prueba/source-data/users.txt";
		File file = new File(filePath);

		String absolutePath = file.getAbsolutePath();

		UserLoader userLoader = new UserLoader(absolutePath);

		// Act & Assert
		assertThrows(Exception.class, userLoader::processAndParse);
		// Verifies that an exception is thrown when processing invalid data
	}

}
