package com.unlam.paradigms.tp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public final class Manager {

	private static final String ATTRACTIONS_FILE_NAME = "attracctions.txt";
	private static final String OFFERS_FILE_NAME = "offers.txt";
	private static final String ITINERARY_FILE_NAME = "itinerary.txt";
	private static final String SOURCE_PATH = "src/source-data/";

	private List<TourismOption> options;
	private List<OfferDescription> offerDescriptions;
	private static Manager INSTANCE; 
	
	
	public static Manager getInstance() throws IOException {
        if(INSTANCE == null) {
            INSTANCE = new Manager();
        }
        
        return INSTANCE;
    }

	private Manager() throws IOException {
		final FileReader attFileReader = new FileReader(SOURCE_PATH + ATTRACTIONS_FILE_NAME);
		final FileReader offFileReader = new FileReader(SOURCE_PATH + OFFERS_FILE_NAME);
		final BufferedReader attBufferReader = new BufferedReader(attFileReader);
		final BufferedReader offBufferReader = new BufferedReader(offFileReader);
		final List<TourismOption> attracctions = new ArrayList<>();

		offerDescriptions = fetchOffers(offBufferReader);
		attracctions.addAll(fetchAttractions(attBufferReader));

		options = buildOffers(offerDescriptions, attracctions);
		options.addAll(attracctions);

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

				offerDescriptions
						.add(new OfferDescription(offerDescriptionName, attractions, offerType, tourismOptionType));
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
				final String name = parameterPartipant[0];
				final Double price = Double.valueOf(parameterPartipant[1]);
				final Double hours = Double.valueOf(parameterPartipant[2]);
				final Integer places = Integer.valueOf(parameterPartipant[3]);
				final TourismOptionType type = TourismOptionType.valueOf(parameterPartipant[4]);

				attractions.add(new Attraction(name, price, hours, places, type));
			}
		}

		return attractions;
	}

	private List<TourismOption> filterByUserPreferences(final User user, final List<TourismOption> options) {
		List<TourismOption> filteredOptions = new ArrayList<>();

		for (TourismOption option : options) {
			if (option.isValid(user)) {
				filteredOptions.add(option);
			}
		}

		return filteredOptions;
	}

	private List<TourismOption> filterByOfferDescription(final OfferDescription offerDescription,
			final List<TourismOption> options) {
		List<TourismOption> filteredOptions = new ArrayList<>();

		for (String name : offerDescription.getAttractionNames()) {
			for (TourismOption option : options) {
				if (option.getName().equals(name)) {
					filteredOptions.add(option);
				}
			}
		}

		return filteredOptions;
	}

	private List<TourismOption> buildOffers(final List<OfferDescription> offerDescriptions,
			final List<TourismOption> options) {
		final List<String> optionNames = new ArrayList<>();
		List<TourismOption> filteredOptions = new ArrayList<>();

		for (TourismOption tourismOption : options) {
			optionNames.add(tourismOption.getName());
		}

		for (OfferDescription offerDescription : offerDescriptions) {
			if (optionNames.containsAll(offerDescription.getAttractionNames())) {
				filteredOptions.add(offerDescription.createOffer(filterByOfferDescription(offerDescription, options)));
			}
		}

		return filteredOptions;
	}

	private List<TourismOption> orderByType(final User user, final List<TourismOption> options) {
		List<TourismOption> sortedOptions = new ArrayList<>(options);

		Collections.sort(sortedOptions, new Comparator<TourismOption>() {
			@Override
			public int compare(TourismOption o1, TourismOption o2) {

				if (o1.getType().equals(TourismOptionType.valueOf(user.getTouristAttraction()))) {

					if (o1.getAmountToPay() == o2.getAmountToPay() && o1.getDuration() == o2.getDuration()) {
						return 0;
					}

					if (o1.getAmountToPay() > o2.getAmountToPay()
							|| (o1.getAmountToPay() == o2.getAmountToPay() && o1.getDuration() > o2.getDuration())) {
						return 1;
					}
				}

				return -1;
			}
		});

		return sortedOptions;
	}

	public Iterator<TourismOption> getOptions(final User user) {
		return new TourismOptionIterator(user, filterByUserPreferences(user, orderByType(user, options)));
	}

	public Ticket createTicket(final User user, final TourismOption option) throws IOException {
		final FileWriter fileWriter = new FileWriter(ITINERARY_FILE_NAME);
		final BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

		return new Ticket(user, option);
	}

}
