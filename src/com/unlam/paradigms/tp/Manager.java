package com.unlam.paradigms.tp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class Manager {
	
	private static String ATTRACTIONS_FILE_NAME;
	private static String OFFERS_FILE_NAME;
	private static final String ITINERARY_FILE_NAME = "itinerary.txt";
	private static String SOURCE_PATH;

	private List<TourismOption> tourOptions;
	private static Manager INSTANCE;

	public static Manager getInstance() throws Exception { // Patron singleton
		if (INSTANCE == null) {
			INSTANCE = new Manager();
		}

		return INSTANCE;
	}

	private Manager() throws Exception {
		if(SOURCE_PATH == null) {
			SOURCE_PATH = "src/source-data/"; //Default
		}
		
		if(ATTRACTIONS_FILE_NAME != null) {
			final FileReader attFileReader = new FileReader(SOURCE_PATH + ATTRACTIONS_FILE_NAME);
			final BufferedReader attBufferReader = new BufferedReader(attFileReader);
			this.tourOptions = fetchAttractions(attBufferReader);
			attBufferReader.close();
		}
		
		if(OFFERS_FILE_NAME != null) {
			final FileReader offFileReader = new FileReader(SOURCE_PATH + OFFERS_FILE_NAME);
			final BufferedReader offBufferReader = new BufferedReader(offFileReader);
			List<OfferDescription> offerDescriptions = fetchOffers(offBufferReader);
			this.tourOptions.addAll(buildOffers(offerDescriptions));
			offBufferReader.close();
		}
	}
	
	public static void setAttractionPath(String attractionPath) {
		ATTRACTIONS_FILE_NAME = attractionPath;
	}
	
	public static void setOfferPath(String offerPath) {
		OFFERS_FILE_NAME = offerPath;
	}
	
	public static void setSourcePath(String srcPath) {
		SOURCE_PATH = srcPath;
	}

	private List<OfferDescription> fetchOffers(final BufferedReader bfReader) throws IOException {
		final List<OfferDescription> offerDescriptions = new ArrayList<>();
		String line;

		while ((line = bfReader.readLine()) != null) {
			final String[] parameterPartipant = line.split(",");

			if (parameterPartipant.length > 1) {
				final String offerDescriptionName = parameterPartipant[0];
				final List<String> attractions = List.of(parameterPartipant[1].split("\\|"));
				final OfferType offerType = OfferType.valueOf(parameterPartipant[2]);
				final TourismOptionType tourismOptionType = TourismOptionType.valueOf(parameterPartipant[3]);
				final String offerParameter = parameterPartipant[4];

				offerDescriptions.add(new OfferDescription(offerDescriptionName, attractions, offerType,
						tourismOptionType, offerParameter));
			}
		}

		return offerDescriptions;
	}

	private List<TourismOption> fetchAttractions(final BufferedReader bfReader) throws IOException {
		final List<TourismOption> attractions = new ArrayList<>();
		String line;

		while ((line = bfReader.readLine()) != null) {
			final String[] parameterPartipant = line.split(",");

			if (parameterPartipant.length > 1) {
				String name = parameterPartipant[0];
				Double price = Double.valueOf(parameterPartipant[1]);
				Double hours = Double.valueOf(parameterPartipant[2]);
				Integer places = Integer.valueOf(parameterPartipant[3]);
				TourismOptionType type = TourismOptionType.valueOf(parameterPartipant[4]);

				attractions.add(new Attraction(name, price, hours, places, type));
			}
		}

		return attractions;
	}

	private TourismOption getTourOption(String tourName) {
		for (TourismOption tourOption : this.tourOptions) {
			if (tourName.equals(tourOption.getName())) {
				return tourOption;
			}
		}
		return null;
	}

	private List<TourismOption> buildOffers(final List<OfferDescription> offerDescriptions) throws Exception {
		List<TourismOption> offers = new ArrayList<TourismOption>();
		List<TourismOption> offerOptions;

		for (OfferDescription offerDescription : offerDescriptions) {
			offerOptions = new ArrayList<TourismOption>();

			for (String tourName : offerDescription.getAttractionNames()) {
				TourismOption tourOption = getTourOption(tourName);

				if (tourOption != null) {
					offerOptions.add(tourOption);
				} else {
					throw new Exception("No se encuentra la atraccion: " + tourName);
				}
			}

			offers.add(offerDescription.createOffer(offerOptions));
		}

		return offers;
	}

	public Iterator<TourismOption> getOptions(final User user) {
		return new TourismOptionIterator(user, this.tourOptions);
	}

	public void checkout(Ticket userTicket, TourismOption tourOption) {
		if (userTicket.getUser().updateUser(tourOption)) {
			tourOption.reserve(userTicket.getUser().getUserName());
			userTicket.addTourOption(tourOption);
		}
	}

	public void generateTicketFile(final List<Ticket> tickets) throws IOException {
		final FileWriter fileWriter = new FileWriter(SOURCE_PATH + ITINERARY_FILE_NAME);
		final BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

		bufferedWriter.append("Itinerario de todos los usuarios:\n");
		for (Ticket ticket : tickets) {
			bufferedWriter.append(ticket.toString() + "*---   ---*\n");
		}

		bufferedWriter.close();
		fileWriter.close();
	}
}
