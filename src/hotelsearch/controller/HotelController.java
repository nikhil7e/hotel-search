package hotelsearch.controller;

import hotelsearch.model.Booking;
import hotelsearch.model.DatabaseService;
import hotelsearch.model.Hotel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class HotelController {

    private DatabaseService db;

    public HotelController(DatabaseService db) {
        this.db = db;
    }

    public List<Hotel> findHotels(String nameOrLocation, LocalDate checkInDate, LocalDate checkOutDate, int nrGuests)
            throws SQLException {
        return db.search(nameOrLocation, checkInDate, checkOutDate, nrGuests);
    }

    public List<Booking> bookRoom(Hotel hotel, String guestName, LocalDate checkInDate, LocalDate checkOutDate, int nrGuests)
            throws SQLException {
        return db.addBooking(hotel, guestName, checkInDate, checkOutDate, nrGuests);
    }

    public List<Booking> modifyBooking(Hotel hotel, int bookingID, String guestName, LocalDate newCheckInDate,
                                       LocalDate newCheckOutDate, int newNrGuests) throws SQLException {
        cancelBooking(hotel, bookingID);
        return db.addBooking(hotel, guestName, newCheckInDate, newCheckOutDate, newNrGuests);
    }

    public void cancelBooking(Hotel hotel, int bookingID) throws SQLException {
        db.cancelBooking(hotel, bookingID);
    }

    public List<Hotel> orderByPriceAscending(List<Hotel> list) {
        // dno why defining a new arrayList fixes everything?
        List<Hotel> orderedList = new ArrayList<Hotel>();
        Collections.sort(list, new Hotel.priceAscending());
        return orderedList;
    }
    // none of the following 4 have been tested ...
    public List<Hotel> orderByPriceDescending(List<Hotel> list) {
        List<Hotel> orderedList = new ArrayList<Hotel>();
        Collections.sort(list, new Hotel.priceDescending());
        return orderedList;
    }

    public List<Hotel> orderByStarsDescending(List<Hotel> list) {
        List<Hotel> orderedList = new ArrayList<Hotel>();
        Collections.sort(list, new Hotel.starsDescending());
        return orderedList;
    }

    public List<Hotel> orderByStarsAscending(List<Hotel> list) {
        List<Hotel> orderedList = new ArrayList<Hotel>();
        Collections.sort(list, new Hotel.starsAscending());
        return orderedList;
    }

    public List<Hotel> orderByDistanceFromDowntown(List<Hotel> list) {
        List<Hotel> orderedList = new ArrayList<Hotel>();
        Collections.sort(list, new Hotel.distanceFromDowntown());
        return orderedList;
    }
}
