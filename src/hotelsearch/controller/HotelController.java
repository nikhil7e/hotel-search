package hotelsearch.controller;

import hotelsearch.model.Booking;
import hotelsearch.model.DatabaseService;
import hotelsearch.model.Hotel;

import java.util.Date;
import java.util.List;

public class HotelController {

    private DatabaseService db;

    public HotelController(DatabaseService db) {
        this.db = db;
    }

    public List<Hotel> findHotels(String nameOrLocation, Date checkInDate, Date checkOutDate, int nrGuests) {
        return null;
    }

    public List<Booking> bookRoom(Hotel hotel, String guestName, Date checkInDate, Date checkOutDate, int nrGuests) {
        return null;
    }

    public List<Hotel> orderByPriceAscending(List<Hotel> list) {
        return null;
    }

}
