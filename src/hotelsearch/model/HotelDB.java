package hotelsearch.model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
To compile and run this file on the command line:
Windows:
  javac Sample.java
  java -cp .;sqlite-jdbc-....jar HotelDB
Unix:
  javac Sample.java
  java -cp .:sqlite-jdbc-....jar HotelDB
 */
public class HotelDB implements DatabaseService {

    /**
     * Searches and returns all hotels that satisfy the given conditions
     *
     * @param options an object that contains a set of conditions that searched hotels must fulfill
     * @return a list of Hotel objects that fulfill the conditions and an empty list if database errors occur
     */
    @Override
    public List<Hotel> search(SearchOptions options) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

        Connection connection;
        List<Hotel> hotelList = new ArrayList<>();
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:src/sql/hotel-search.db");
            // find hotels that contain enough available rooms to accommodate all guests
            PreparedStatement statement = connection.prepareStatement(
                    "select * from Hotel where Hotel.address like ? and Hotel.name like ? and (" +
                            "select SUM(nrBeds) from (" +
                            "select * from Room where Room.hotelID = Hotel.hotelID and not exists(" +
                            "select * from Booking where Booking.hotelID = Hotel.hotelID and " +
                            "Booking.roomID = Room.roomID and (Booking.checkInDate between ? and ? or " +
                            "Booking.checkOutDate between ? and ? or Booking.checkInDate < ? and " +
                            "Booking.checkOutDate > ?)))) >= ? order by Hotel.featured desc");
            statement.clearParameters();

            // use "% " instead of "%", since we use address format street, postal code city (notice the space)
            statement.setString(1, "% " + options.getCity());
            // allow the user to submit an empty string
            if (options.getName() == null || options.getName().equals("")) {
                statement.setString(2, "%");
            } else {
                statement.setString(2, options.getName() + "%");
            }
            statement.setString(3, java.sql.Date.valueOf(options.getCheckInDate()).toString());
            statement.setString(4, java.sql.Date.valueOf(options.getCheckOutDate()).toString());
            statement.setString(5, java.sql.Date.valueOf(options.getCheckInDate()).toString());
            statement.setString(6, java.sql.Date.valueOf(options.getCheckOutDate()).toString());
            statement.setString(7, java.sql.Date.valueOf(options.getCheckInDate()).toString());
            statement.setString(8, java.sql.Date.valueOf(options.getCheckOutDate()).toString());
            statement.setInt(9, options.getNrGuests());
            ResultSet rs = statement.executeQuery();

            // TODO remove once testing is no longer needed
            // System.out.println("Hotel search results:");

            while (rs.next()) {
                Hotel hotel = new Hotel(rs.getInt("hotelID"),
                        rs.getString("name"), rs.getString("address"), rs.getString("description"),
                        rs.getString("imageURL"), rs.getInt("numberOfStars"), rs.getDouble("startingRoomPrice"),
                        rs.getDouble("distanceFromDowntown"), rs.getDouble("distanceFromSupermarket"),
                        rs.getBoolean("restaurant"), rs.getBoolean("breakfastIncluded"),
                        rs.getBoolean("freeWifi"), rs.getBoolean("bar"), rs.getBoolean("featured"));
                hotelList.add(hotel);

                // TODO remove once testing is no longer needed
                // System.out.println("Name: " + rs.getString("name"));
            }

            rs.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

        return hotelList;
    }

    /**
     * Books an appropriate number of rooms in the given hotel so that all guests can be accommodated
     *
     * @param hotel      the hotel which will contain the rooms to be booked
     * @param guestEmail the email of the guest that will create the booking
     * @param guestName  the name of the guest that will create the booking
     * @param options    an object that contains a set of conditions that hotels must fulfill. The city and name
     *                   tied to the object have no effect on the booking
     * @return a list of Booking objects and an empty list if database
     * errors occur. A booking object is created for each booked room if multiple rooms must be booked to accommodate
     * all guests
     */
    public List<Booking> book(Hotel hotel, String guestEmail, String guestName, SearchOptions options) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

        Connection connection;
        List<Booking> bookingList = new ArrayList<>();
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:src/sql/hotel-search.db");
            // find rooms that don't contain a conflicting booking, making sure they have enough beds for all guests
            // TODO order by nrBeds desc rather? Maybe even find a better solution?
            PreparedStatement statement = connection.prepareStatement(
                    "select * from (" +
                            "select *, sum(nrBeds) over() as summa from Room where Room.hotelID = ? and not exists(" +
                            "select * from Booking where Booking.hotelID = ? and " +
                            "Booking.roomID = Room.roomID and (Booking.checkInDate between ? and ? or " +
                            "Booking.checkOutDate between ? and ? or Booking.checkInDate < ? and " +
                            "Booking.checkOutDate > ?)) " +
                            "order by nrBeds) " +
                            "where summa >= ?");

            statement.clearParameters();
            statement.setInt(1, hotel.getHotelID());
            statement.setInt(2, hotel.getHotelID());
            statement.setString(3, java.sql.Date.valueOf(options.getCheckInDate()).toString());
            statement.setString(4, java.sql.Date.valueOf(options.getCheckOutDate()).toString());
            statement.setString(5, java.sql.Date.valueOf(options.getCheckInDate()).toString());
            statement.setString(6, java.sql.Date.valueOf(options.getCheckOutDate()).toString());
            statement.setString(7, java.sql.Date.valueOf(options.getCheckInDate()).toString());
            statement.setString(8, java.sql.Date.valueOf(options.getCheckOutDate()).toString());
            statement.setInt(9, options.getNrGuests());
            ResultSet rs = statement.executeQuery();

            // TODO remove once testing is no longer needed
            // System.out.println("Booking results for hotel " + hotel.getName() + ":");

            // get largest bookingID and bookingTransactionID to create new unique ones
            PreparedStatement idStatement = connection.prepareStatement(
                    "select max(bookingID) as maxBookingID, max(bookingTransactionID) as " +
                            "maxBookingTransactionID from Booking");
            ResultSet idRs = idStatement.executeQuery();
            int guestsRemaining = options.getNrGuests();
            int bookingID = idRs.getInt("maxBookingID") + 1;
            int bookingTransactionID = idRs.getInt("maxBookingTransactionID") + 1;

            while (rs.next() && guestsRemaining > 0) {
                // insert the bookings into the DB
                PreparedStatement update = connection.prepareStatement("insert into Booking values " +
                        "(?, ?, ?, ?, ?, ?, ?, ?)");
                update.clearParameters();
                update.setInt(1, hotel.getHotelID());
                update.setInt(2, rs.getInt("roomID"));
                update.setInt(3, bookingID);
                update.setInt(4, bookingTransactionID);
                update.setString(5, guestEmail);
                update.setString(6, guestName);
                update.setString(7, java.sql.Date.valueOf(options.getCheckInDate()).toString());
                update.setString(8, java.sql.Date.valueOf(options.getCheckOutDate()).toString());
                update.executeUpdate();

                bookingList.add(new Booking(hotel.getHotelID(), rs.getInt("roomID"), bookingID, bookingTransactionID,
                        guestEmail, guestName, options.getCheckInDate(), options.getCheckOutDate()));
                bookingID++;
                guestsRemaining -= rs.getInt("nrBeds");

                // TODO remove once testing is no longer needed
                /*
                System.out.println("Booking added: HotelID " + hotel.getHotelID() + ", name " + hotel.getName()
                        + ", roomID " + rs.getInt("roomID") + ", bookingID " + (bookingID - 1) +
                        ", bookingTransactionID " + bookingTransactionID + ", nrBeds " + rs.getInt("nrBeds") +
                        ", total nrGuests to book " + options.getNrGuests());
                 */
            }

            rs.close();
            idRs.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

        return bookingList;
    }

    /**
     * Cancels a booking
     *
     * @param bookingID the ID of the booking that will be canceled
     * @return true if the booking was cancelled, else false
     */
    @Override
    public boolean cancelBooking(int bookingID) {
        // load the sqlite-JDBC driver using the current class loader
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        // create a database connection
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:src/sql/hotel-search.db");
            PreparedStatement updateStatement =
                    connection.prepareStatement("delete from Booking where bookingID = ?");
            updateStatement.clearParameters();
            updateStatement.setInt(1, bookingID);
            int rowsAffected = updateStatement.executeUpdate();

            if (rowsAffected == 0) {
                // TODO remove once testing is no longer needed
                // System.out.println("Booking cancellation failed, invalid ID/s");
                return false;
            }

            // TODO remove once testing is no longer needed
            // System.out.println("Booking " + bookingID + " cancelled");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Finds all bookings that were booked with the given information
     *
     * @param guestEmail the email that is associated with the bookings
     * @return a list of Booking objects and an empty list if database errors occur
     */
    @Override
    public List<Booking> findBookings(String guestEmail) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

        Connection connection;
        List<Booking> bookingList = new ArrayList<>();
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:src/sql/hotel-search.db");
            PreparedStatement statement = connection.prepareStatement(
                    "select * from Booking where Booking.guestEmail = ?");
            statement.clearParameters();
            statement.setString(1, guestEmail);
            ResultSet rs = statement.executeQuery();

            // TODO remove once testing is no longer needed
            // System.out.println("Bookings found for email " + guestEmail + ":");

            while (rs.next()) {
                bookingList.add(new Booking(rs.getInt("hotelID"), rs.getInt("roomID"), rs.getInt("bookingID"),
                        rs.getInt("bookingTransactionID"), guestEmail, rs.getString("guestName"),
                        LocalDate.parse(rs.getString("checkInDate")),
                        LocalDate.parse(rs.getString("checkOutDate"))));

                // TODO remove once testing is no longer needed
                // System.out.println("HotelID " + rs.getInt("hotelID") + ", bookingID " + rs.getInt("bookingID"));
            }

            rs.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

        return bookingList;
    }

    // no modifier so package visible
    public boolean insertHotel(Hotel hotel) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:src/sql/hotel-search.db");
            // insert the bookings into the DB
            PreparedStatement update = connection.prepareStatement("insert into Hotel values " +
                    "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            update.clearParameters();
            update.setInt(1, hotel.getHotelID());
            update.setString(2, hotel.getName());
            update.setString(3, hotel.getAddress());
            update.setString(4, hotel.getDescription());
            update.setString(5, hotel.getImageURL());
            update.setInt(6, hotel.getNumberOfStars());
            update.setDouble(7, hotel.getStartingRoomPrice());
            update.setDouble(8, hotel.getDistanceFromDowntown());
            update.setDouble(9, hotel.getDistanceFromSupermarket());
            update.setBoolean(10, hotel.getRestaurant());
            update.setBoolean(11, hotel.getBreakfastIncluded());
            update.setBoolean(12, hotel.getBar());
            update.setBoolean(13, hotel.getFreeWifi());
            update.setBoolean(14, hotel.getFeatured());
            update.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean insertRoom(Room room) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:src/sql/hotel-search.db");
            // insert the bookings into the DB
            PreparedStatement update = connection.prepareStatement("insert into Room values " +
                    "(?, ?, ?, ?, ?, ?, ?)");
            update.clearParameters();
            update.setInt(1, room.getHotelID());
            update.setInt(2, room.getRoomID());
            update.setInt(3, room.getNrBeds());
            update.setDouble(4, room.getPricePerNight());
            update.setBoolean(5, room.getTv());
            update.setBoolean(6, room.getCityView());
            update.setBoolean(7, room.getPrivateBathroom());
            update.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    // no modifier so package visible
    public boolean insertBooking(Booking booking) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:src/sql/hotel-search.db");
            // insert the bookings into the DB
            PreparedStatement update = connection.prepareStatement("insert into Booking values " +
                    "(?, ?, ?, ?, ?, ?, ?, ?)");
            update.clearParameters();
            update.setInt(1, booking.getHotelID());
            update.setInt(2, booking.getRoomID());
            update.setInt(3, booking.getBookingID());
            update.setInt(4, booking.getBookingTransactionID());
            update.setString(5, booking.getGuestEmail());
            update.setString(6, booking.getGuestName());
            update.setString(7, java.sql.Date.valueOf(booking.getCheckInDate()).toString());
            update.setString(8, java.sql.Date.valueOf(booking.getCheckOutDate()).toString());
            update.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


    public static void main(String[] args) {
        HotelDB db = new HotelDB();
        SearchOptions options = new SearchOptions("Reykjavík", "",
                LocalDate.of(2023, 4, 16),
                LocalDate.of(2023, 5, 17), 6);
        SearchOptions options2 = new SearchOptions("Reykjavík", "",
                LocalDate.of(2023, 4, 16),
                LocalDate.of(2023, 4, 17), 4);
        SearchOptions options3 = new SearchOptions("Reykjavík", "",
                LocalDate.of(2023, 4, 16),
                LocalDate.of(2023, 4, 17), 8);

        db.findBookings("email");
        System.out.println();
        List<Hotel> list = db.search(options);
        System.out.println();

        if (list.size() != 0) {
            List<Booking> bookingList = db.book(list.get(0), "email", "name", options);
            System.out.println();
            db.book(list.get(0), "email", "name", options2);
            System.out.println();
            db.book(list.get(0), "email", "name", options3);
            System.out.println();
        }

        db.cancelBooking(1);
        System.out.println();
        db.findBookings("email");
        System.out.println();


        Hotel test = new Hotel(123, "Tesvwegt", "1st street, 101 Reykjavík",
                "Description", "images/hotel1.jpg", 5, 2,
                1, 1, true, true, true, true,
                true);
        Room room = new Room(135, 123, 6, 1, true, true,
                true);
        db.insertHotel(test);
        db.insertRoom(room);
        db.book(test, "seah@seg.is", "aseg", options);
    }

}






