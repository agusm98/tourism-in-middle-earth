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

	private static final String ATTRACTIONS_FILE_NAME = "attracctions.txt";
	private static final String OFFERS_FILE_NAME = "offers.txt";
	private static final String ITINERARY_FILE_NAME = "itinerary.txt";
	private static final String SOURCE_PATH = "src/source-data/";

	private List<TourismOption> tourOptions;
	private static Manager INSTANCE;

	public static Manager getInstance() throws Exception { // Patron singleton
		if (INSTANCE == null) {
			INSTANCE = new Manager();
		}

		return INSTANCE;
	}

	private Manager() throws Exception {
		final FileReader attFileReader = new FileReader(SOURCE_PATH + ATTRACTIONS_FILE_NAME);
		final FileReader offFileReader = new FileReader(SOURCE_PATH + OFFERS_FILE_NAME);
		final BufferedReader attBufferReader = new BufferedReader(attFileReader);
		final BufferedReader offBufferReader = new BufferedReader(offFileReader);

		this.tourOptions = fetchAttractions(attBufferReader);

		List<OfferDescription> offerDescriptions = fetchOffers(offBufferReader);

		this.tourOptions.addAll(buildOffers(offerDescriptions));

		attBufferReader.close();
		offBufferReader.close();
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
