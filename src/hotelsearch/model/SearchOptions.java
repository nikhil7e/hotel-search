package hotelsearch.model;

import java.time.LocalDate;

public class SearchOptions {

    private final String city;
    private final String name;
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;
    private final int nrGuests;

    /**
     * Constructs a SearchOptions object that contains requirements that must be fulfilled when searching for and
     * booking hotels
     *
     * @param city         the city that hotels should reside in. The parameter is case-sensitive and uses icelandic
     *                     letters where applicable, i.e. "Reykjavík"
     * @param name         the optional name of the hotels to be searched for. The parameter is case-sensitive and uses
     *                     icelandic letters where applicable
     * @param checkInDate  the guests' checkin date
     * @param checkOutDate the guests' checkout date
     * @param nrGuests     the non-zero positive integer number of guests that require accommodation
     */
    public SearchOptions(String city, String name, LocalDate checkInDate, LocalDate checkOutDate, int nrGuests) {
        this.name = name;
        this.city = city;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.nrGuests = nrGuests;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public int getNrGuests() {
        return nrGuests;
    }

}
