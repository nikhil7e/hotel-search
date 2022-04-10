package hotelsearch.model;

import java.time.LocalDate;

public class SearchOptions {

    private String nameOrLocation;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int nrGuests;

    public SearchOptions(String nameOrLocation, LocalDate checkInDate, LocalDate checkOutDate, int nrGuests) {
        this.nameOrLocation = nameOrLocation;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.nrGuests = nrGuests;
    }

    public String getNameOrLocation() {
        return nameOrLocation;
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
