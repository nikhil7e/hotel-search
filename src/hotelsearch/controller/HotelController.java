package hotelsearch.controller;

import hotelsearch.model.Booking;
import hotelsearch.model.DatabaseService;
import hotelsearch.model.Hotel;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class HotelController {

    private DatabaseService db;

    public HotelController(DatabaseService db) {
        this.db = db;
    }

    public List<Hotel> findHotels(String nameOrLocation, Date checkInDate, Date checkOutDate, int nrGuests)
            throws SQLException {
        return db.search(nameOrLocation, checkInDate, checkOutDate, nrGuests);
    }

    public List<Booking> bookRoom(Hotel hotel, String guestName, Date checkInDate, Date checkOutDate, int nrGuests)
            throws SQLException {
        return db.addBooking(hotel, guestName, checkInDate, checkOutDate, nrGuests);
    }

    public List<Booking> modifyBooking(Hotel hotel, int bookingID, String guestName, Date newCheckInDate,
                                       Date newCheckOutDate, int newNrGuests) throws SQLException {
        cancelBooking(hotel, bookingID);
        return db.addBooking(hotel, guestName, newCheckInDate, newCheckOutDate, newNrGuests);
    }

    public void cancelBooking(Hotel hotel, int bookingID) throws SQLException {
        db.cancelBooking(hotel, bookingID);
    }

    public List<Hotel> orderByPriceAscending(List<Hotel> list) {
        return null;
    }

    public List<Hotel> orderByPriceDescending(List<Hotel> list) {
        return null;
    }

    public List<Hotel> orderByStarsDescending(List<Hotel> list) {
        return null;
    }

    public List<Hotel> orderByStarsAscending(List<Hotel> list) {
        return null;
    }

    public List<Hotel> orderByDistanceFromDowntown(List<Hotel> list) {
        return null;
    }

}
