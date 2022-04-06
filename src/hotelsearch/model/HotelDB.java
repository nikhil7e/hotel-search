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
    public List<Hotel> search(String nameOrLocation, LocalDate checkInDate, LocalDate checkOutDate, int nrGuests)
            throws SQLException {
        // load the sqlite-JDBC driver using the current class loader
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println(e);
            throw new SQLException(e);
        }

        // create a database connection
        Connection connection = DriverManager.getConnection("jdbc:sqlite:src/sql/hotel-search.db");
        List<Hotel> hotelList = new ArrayList<>();

        // TODO only show hotels that have a sufficient amount of available rooms for the number of guests
        PreparedStatement statement = connection.prepareStatement(
                "select * from Hotel where Hotel.nameOrLocation like ? and exists(" +
                        "select * from Room where Room.hotelID = Hotel.hotelID and not exists(" +
                        "select * from Booking where Booking.hotelID = Hotel.hotelID and " +
                        "Booking.roomID = Room.roomID and (Booking.checkInDate between ? and ? or " +
                        "Booking.checkOutDate between ? and ? or Booking.checkInDate < ? and " +
                        "Booking.checkOutDate > ?)))");

        statement.clearParameters();
        statement.setString(1, nameOrLocation + "%");
        statement.setString(2, java.sql.Date.valueOf(checkInDate).toString());
        statement.setString(3, java.sql.Date.valueOf(checkOutDate).toString());
        statement.setString(4, java.sql.Date.valueOf(checkInDate).toString());
        statement.setString(5, java.sql.Date.valueOf(checkOutDate).toString());
        statement.setString(6, java.sql.Date.valueOf(checkInDate).toString());
        statement.setString(7, java.sql.Date.valueOf(checkOutDate).toString());
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            // read the result set
            Hotel hotel = new Hotel(rs.getInt("hotelID"),
                    rs.getString("nameOrLocation"), rs.getInt("numberOfStars"),
                    new Image(Objects.requireNonNull(HotelDB.class.getResourceAsStream(rs.getString("image")))),
                    rs.getString("description"), rs.getDouble("startingRoomPrice"),
                    rs.getDouble("distanceFromDowntown"), rs.getDouble("distanceFromSupermarket"),rs.getBoolean("testBool"));
            hotelList.add(hotel);
            // TODO remove once testing is no longer needed
            System.out.println("Hotel: " + rs.getString("nameOrLocation"));
        }

        rs.close();
        connection.close();
        return hotelList;
    }

    @Override
    public List<Booking> addBooking(Hotel hotel, String guestName, LocalDate checkInDate,
                                    LocalDate checkOutDate, int nrGuests) throws SQLException {
        return null;
    }

    @Override
    public void cancelBooking(Hotel hotel, int bookingID) throws SQLException {
    }

    public static void main(String[] args) throws SQLException {
        HotelDB db = new HotelDB();
        db.search("Test", LocalDate.of(2022, 4, 16),
                LocalDate.of(2022, 4, 17), 4);
    }

}






