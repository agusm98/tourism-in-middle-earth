package com.unlam.paradigms.tp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

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
		final List<OfferDescription> offerDescriptions = Collections.emptyList();
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
		final List<TourismOption> attractions = Collections.emptyList();
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
	
	public Iterator<TourismOption> getOptions(final User user) {
		
		//TODO: resolve user preferences tourism options
		
		return new TourismOptionIterator(user, attractions);
	}
	
	public void createTicket(final User user, final TourismOption option) {
		
	}

}
