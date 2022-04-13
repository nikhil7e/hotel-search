package hotelsearch.controller;

import hotelsearch.model.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HotelController {

    private final DatabaseService db;
    private SearchOptions options;

    public HotelController(DatabaseService db) {
        this.db = db;
    }

    /**
     * Searches and returns all hotels that satisfy the given conditions
     *
     * @param options a model class that contains a set of requirements
     *                that hotels must fulfill
     * @return a list of Hotel objects and an empty list if database
     * errors occur
     */
    public List<Hotel> search(SearchOptions options) {
        this.options = options;
        return db.search(options);
    }

    /**
     * Books an appropriate number of rooms in the given hotel sothat all guests can be accommodated. Uses the same
     * SearchOptionsobject that was passed into the last search method call
     *
     * @param hotel      the hotel which will contain the rooms to be booked
     * @param guestEmail the email of the guest that will create the booking
     * @param guestName  the name of the guest that will create the booking
     * @return a list of Booking objects and an empty list if database
     * errors occur. A booking object is created for each booked room if
     * multiple rooms must be booked to accommodate all guests
     */
    public List<Booking> book(Hotel hotel, String guestEmail, String guestName) {
        return db.book(hotel, guestEmail, guestName, options);
    }

    /**
     * Books an appropriate number of rooms in the given hotel so that all guests can be accommodated.
     *
     * @param hotel      the hotel which will contain the rooms to be booked
     * @param guestEmail the email of the guest that will create the booking
     * @param guestName  the name of the guest that will create the booking
     * @param options    a model class that contains a set of requirements
     *                   that hotels must fulfill
     * @return a list of Booking objects and an empty list if database
     * errors occur. A booking object is created for each booked room if
     * multiple rooms must be booked to accommodate all guests
     */
    public List<Booking> book(Hotel hotel, String guestEmail, String guestName, SearchOptions options) {
        return db.book(hotel, guestEmail, guestName, options);
    }

    /**
     * Cancels a booking
     *
     * @param hotelID   the ID of the hotel that was booked
     * @param bookingID the ID of the booking that will be canceled
     * @throws SQLException if database errors occur
     */
    public void cancelBooking(int hotelID, int bookingID) throws SQLException {
        db.cancelBooking(hotelID, bookingID);
    }

    /**
     * Removes the specified booking and adds a new one containing the given parameters
     *
     * @param hotel      the ID of the hotel that was booked
     * @param bookingID  the ID of the booking that will be canceled
     * @param guestEmail the email of the guest that will create the booking
     * @param guestName  the name of the guest that will create the booking
     * @param options    a model class that contains a set of requirements
     *                   that the new booking must fulfill
     * @return a list of Booking objects and an empty list if database
     * errors occur. A booking object is created for each booked room if
     * multiple rooms must be booked to accommodate all guests
     * @throws SQLException if database errors occur
     */
    public List<Booking> modifyBooking(Hotel hotel, int bookingID, String guestEmail, String guestName,
                                       SearchOptions options) throws SQLException {
        cancelBooking(hotel.getHotelID(), bookingID);
        return db.book(hotel, guestEmail, guestName, options);
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

    public static void main(String[] args) {
        HotelController hc = new HotelController(new HotelDB());

        SearchOptions options = new SearchOptions("Reykjavík", "Test",
                LocalDate.of(2023, 4, 16),
                LocalDate.of(2023, 4, 17), 1);

        List<Hotel> list = hc.search(options);
        System.out.println();

        try {
            hc.modifyBooking(list.get(0), 9063148, "Nejgluw", "Newkutf",
                    new SearchOptions("Reykjavík", "Test",
                            LocalDate.of(2023, 4, 16),
                            LocalDate.of(2023, 4, 17), 1));
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

}
