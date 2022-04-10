package hotelsearch.controller;

import hotelsearch.model.Booking;
import hotelsearch.model.DatabaseService;
import hotelsearch.model.Hotel;
import hotelsearch.model.SearchOptions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HotelController {

    private final DatabaseService db;
    private SearchOptions options;

    public HotelController(DatabaseService db) {
        this.db = db;
    }

    public List<Hotel> findHotels(SearchOptions options) {
        this.options = options;
        return db.search(options);
    }

    public List<Booking> bookRoom(Hotel hotel, String guestEmail, String guestName) {
        return db.addBooking(hotel, guestEmail, guestName, options);
    }

    public List<Booking> modifyBooking(Hotel hotel, int bookingID, String guestEmail, String guestName,
                                       SearchOptions options) throws SQLException {
        cancelBooking(hotel.getHotelID(), bookingID);
        return db.addBooking(hotel, guestEmail, guestName, options);
    }

    public void cancelBooking(int hotelID, int bookingID) throws SQLException {
        db.cancelBooking(hotelID, bookingID);
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

    public List<Hotel> filterByPrice(List<Hotel> list, int minPrice, int maxPrice) {
        List<Hotel> newList = new ArrayList<>();

        for (Hotel hotel : list) {
            if (hotel.getStartingRoomPrice() <= maxPrice && hotel.getStartingRoomPrice() >= minPrice) {
                newList.add(hotel);
            }
        }
        return newList;
    }

    public List<Hotel> filterByRestaurant(List<Hotel> list) {
        List<Hotel> newList = new ArrayList<>();
        for (Hotel hotel : list) {
            if (hotel.getRestaurant()) {
                newList.add(hotel);
            }
        }
        return newList;
    }

    public List<Hotel> filterByBreakfast(List<Hotel> list) {
        List<Hotel> newList = new ArrayList<>();
        for (Hotel hotel : list) {
            if (hotel.getBreakfastIncluded()) {
                newList.add(hotel);
            }
        }
        return newList;
    }

    public List<Hotel> filterByBar(List<Hotel> list) {
        List<Hotel> newList = new ArrayList<>();
        for (Hotel hotel : list) {
            if (hotel.getBar()) {
                newList.add(hotel);
            }
        }
        return newList;
    }

    public List<Hotel> filterByWifi(List<Hotel> list) {
        List<Hotel> newList = new ArrayList<>();
        for (Hotel hotel : list) {
            if (hotel.getFreeWifi()) {
                newList.add(hotel);
            }
        }
        return newList;
    }
}
