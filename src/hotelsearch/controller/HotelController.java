package hotelsearch.controller;

import hotelsearch.model.Booking;
import hotelsearch.model.DatabaseService;
import hotelsearch.model.Hotel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HotelController {

    private DatabaseService db;

    public HotelController(DatabaseService db) {
        this.db = db;
    }

    public List<Hotel> findHotels(String nameOrLocation, LocalDate checkInDate, LocalDate checkOutDate, int nrGuests)
            throws SQLException {
        return db.search(nameOrLocation, checkInDate, checkOutDate, nrGuests);
    }

    public List<Booking> bookRoom(Hotel hotel, String guestName, LocalDate checkInDate, LocalDate checkOutDate,
                                  int nrGuests) throws SQLException {
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
        list.sort(new Hotel.priceAscending());
        return list;
    }

    public List<Hotel> orderByPriceDescending(List<Hotel> list) {
        list.sort(new Hotel.priceDescending());
        return list;
    }

    public List<Hotel> orderByStarsDescending(List<Hotel> list) {
        list.sort(new Hotel.starsDescending());
        return list;
    }

    public List<Hotel> orderByStarsAscending(List<Hotel> list) {
        list.sort(new Hotel.starsAscending());
        return list;
    }

    public List<Hotel> orderByDistanceFromDowntown(List<Hotel> list) {
        list.sort(new Hotel.distanceFromDowntown());
        return list;
    }

    public List<Hotel> filterByStars(List<Hotel> list, int stars) {
        List<Hotel> newList = new ArrayList<>();

        for (Hotel hotel : list) {
            if (hotel.getNumberOfStars() == stars) {
                newList.add(hotel);
            }
        }
        return newList;
    }

    
}
