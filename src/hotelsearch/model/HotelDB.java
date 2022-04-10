package hotelsearch.model;

import javafx.scene.image.Image;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
To compile and run this file:
Windows:
  javac Sample.java
  java -cp .;sqlite-jdbc-....jar HotelDB
Unix:
  javac Sample.java
  java -cp .:sqlite-jdbc-....jar HotelDB
 */
public class HotelDB implements DatabaseService {

    @Override
    public List<Hotel> search(SearchOptions options) {
        // load the sqlite-JDBC driver using the current class loader
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println(e);
            return new ArrayList<>();
        }

        // create a database connection
        Connection connection;
        List<Hotel> hotelList = new ArrayList<>();
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:src/sql/hotel-search.db");
            PreparedStatement statement = connection.prepareStatement(
                    "select * from Hotel where Hotel.nameOrLocation like ? and (" +
                            "select SUM(nrBeds) from (" +
                            "select * from Room where Room.hotelID = Hotel.hotelID and not exists(" +
                            "select * from Booking where Booking.hotelID = Hotel.hotelID and " +
                            "Booking.roomID = Room.roomID and (Booking.checkInDate between ? and ? or " +
                            "Booking.checkOutDate between ? and ? or Booking.checkInDate < ? and " +
                            "Booking.checkOutDate > ?)))) >= ?");

            statement.clearParameters();
            statement.setString(1, options.getNameOrLocation() + "%");
            statement.setString(2, java.sql.Date.valueOf(options.getCheckInDate()).toString());
            statement.setString(3, java.sql.Date.valueOf(options.getCheckOutDate()).toString());
            statement.setString(4, java.sql.Date.valueOf(options.getCheckInDate()).toString());
            statement.setString(5, java.sql.Date.valueOf(options.getCheckOutDate()).toString());
            statement.setString(6, java.sql.Date.valueOf(options.getCheckInDate()).toString());
            statement.setString(7, java.sql.Date.valueOf(options.getCheckOutDate()).toString());
            statement.setInt(8, options.getNrGuests());
            ResultSet rs = statement.executeQuery();

            // TODO remove once testing is no longer needed
            System.out.println("Hotel search results:");

            while (rs.next()) {
                // read the result set
                Hotel hotel = new Hotel(rs.getInt("hotelID"),
                        rs.getString("nameOrLocation"), rs.getInt("numberOfStars"),
                        new Image(Objects.requireNonNull(HotelDB.class.getResourceAsStream(rs.getString("image")))),
                        rs.getString("description"), rs.getDouble("startingRoomPrice"),
                        rs.getDouble("distanceFromDowntown"), rs.getDouble("distanceFromSupermarket"),
                        rs.getBoolean("restaurant"), rs.getBoolean("breakfastIncluded"), rs.getBoolean("bar"),
                        rs.getBoolean("freeWifi"));
                hotelList.add(hotel);

                // TODO remove once testing is no longer needed
                System.out.println("Name/location: " + rs.getString("nameOrLocation"));
            }

            rs.close();
            connection.close();
        } catch (SQLException e) {
            System.err.println(e);
            return new ArrayList<>();
        }

        return hotelList;
    }

    public List<Booking> addBooking(Hotel hotel, String guestEmail, String guestName, SearchOptions options)
            throws SQLException {
        // load the sqlite-JDBC driver using the current class loader
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println(e);
            return new ArrayList<>();
        }

        // create a database connection
        Connection connection;
        List<Booking> bookingList = new ArrayList<>();
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:src/sql/hotel-search.db");
            // TODO find rooms that fit, then add bookings to each of them
            // find available rooms
            PreparedStatement statement = connection.prepareStatement(
                    "select * from Room where Room.hotelID = ? and not exists(" +
                            "select * from Booking where Booking.hotelID = ? and " +
                            "Booking.roomID = Room.roomID and (Booking.checkInDate between ? and ? or " +
                            "Booking.checkOutDate between ? and ? or Booking.checkInDate < ? and " +
                            "Booking.checkOutDate > ?)) order by nrBeds desc");

            statement.clearParameters();
            statement.setInt(1, hotel.getHotelID());
            statement.setInt(2, hotel.getHotelID());
            statement.setString(3, java.sql.Date.valueOf(options.getCheckInDate()).toString());
            statement.setString(4, java.sql.Date.valueOf(options.getCheckOutDate()).toString());
            statement.setString(5, java.sql.Date.valueOf(options.getCheckInDate()).toString());
            statement.setString(6, java.sql.Date.valueOf(options.getCheckOutDate()).toString());
            statement.setString(7, java.sql.Date.valueOf(options.getCheckInDate()).toString());
            statement.setString(8, java.sql.Date.valueOf(options.getCheckOutDate()).toString());
            ResultSet rs = statement.executeQuery();

            // TODO remove once testing is no longer needed
            System.out.println("Booking results:");

            int guests = options.getNrGuests();
            int guestID = (int) (Math.random() * 10000000);
            int bookingProcessID = (int) (Math.random() * 10000000);
            while (rs.next() && guests > 0) {
                // read the result set
                PreparedStatement update = connection.prepareStatement("insert into Booking values " +
                        "(?, ?, ?, ?, ?, ?, ?, ?, ?)");

                update.clearParameters();
                update.setInt(1, hotel.getHotelID());
                update.setInt(2, rs.getInt("roomID"));
                // TODO figure out how to keep IDs unique
                update.setInt(3, (int) (Math.random() * 10000000));
                update.setInt(4, bookingProcessID);
                update.setInt(5, guestID);
                update.setString(6, guestEmail);
                update.setString(7, guestName);
                update.setString(8, java.sql.Date.valueOf(options.getCheckInDate()).toString());
                update.setString(9, java.sql.Date.valueOf(options.getCheckOutDate()).toString());
                update.executeUpdate();

                guests -= rs.getInt("nrBeds");

                // TODO remove once testing is no longer needed
                System.out.println("Booking added: HotelID " + hotel.getHotelID() + ", roomID " +
                        rs.getInt("roomID"));
            }

            rs.close();
            connection.close();
        } catch (SQLException e) {
            System.err.println(e);
            return new ArrayList<>();
        }

        return bookingList;
    }

    @Override
    public void cancelBooking(Hotel hotel, int bookingID) throws SQLException {
    }

    public static void main(String[] args) throws SQLException {
        HotelDB db = new HotelDB();
        SearchOptions options = new SearchOptions("Test",
                LocalDate.of(2023, 4, 16),
                LocalDate.of(2023, 4, 17), 3);

        List<Hotel> list = db.search(options);
        if(list.size() != 0) {
            db.addBooking(list.get(0), "email", "name", options);
        }
    }

}






