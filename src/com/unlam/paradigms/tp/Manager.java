package com.unlam.paradigms.tp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class Manager {

	private static final String ATTRACTIONS_FILE_NAME = "atracctions.txt";
	private static final String OFFERS_FILE_NAME = "offers.txt";

	private List<TourismOption> attractions;
	private List<OfferDescription> offerDescriptions;

	public Manager(final String sourcePath) throws IOException {
		final FileReader attFileReader = new FileReader(sourcePath + ATTRACTIONS_FILE_NAME);
		final FileReader offFileReader = new FileReader(sourcePath + OFFERS_FILE_NAME);
		final BufferedReader attBufferReader = new BufferedReader(attFileReader);
		final BufferedReader offBufferReader = new BufferedReader(offFileReader);

		offerDescriptions = fetchOffers(offBufferReader);
		attractions = fetchAttractions(attBufferReader);

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
				final List<String> attractions = List.of(parameterPartipant[1].split("|"));
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

	private List<TourismOption> buildOffersByUserPreference(final List<OfferDescription> offerDescriptions,
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

	public Iterator<TourismOption> getOptions(final User user) {
		final List<TourismOption> optionsByUserPreference = filterByUserPreferences(user, attractions);
		final List<TourismOption> offers = buildOffersByUserPreference(offerDescriptions, optionsByUserPreference);
		final Set<TourismOption> options = new HashSet<>();
		
		options.addAll(offers);
		options.addAll(optionsByUserPreference);
		options.addAll(attractions);
		
		return new TourismOptionIterator(user, new ArrayList<>(options));
	}

	public Ticket createTicket(final User user, final TourismOption option) {
		return new Ticket(user, option);
	}

}
