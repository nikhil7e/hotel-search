package hotelsearch.model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface DatabaseService {

    List<Hotel> search(String nameOrLocation, LocalDate checkInDate, LocalDate checkOutDate, int nrGuests)
            throws SQLException;

    List<Booking> addBooking(Hotel hotel, String guestName, LocalDate checkInDate, LocalDate checkOutDate, int nrGuests)
            throws SQLException;

    void cancelBooking(Hotel hotel, int bookingID) throws SQLException;

}
