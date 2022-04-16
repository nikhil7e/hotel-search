package hotelsearch.model;

import java.time.LocalDate;

public class SearchOptions {

    private final String city;
    private final String name;
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;
    private final int nrGuests;

    public SearchOptions(String city, String name, LocalDate checkInDate, LocalDate checkOutDate, int nrGuests) {
        this.name = name;
        this.city = city;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.nrGuests = nrGuests;
    }

    public String getCity() { return city; }

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
