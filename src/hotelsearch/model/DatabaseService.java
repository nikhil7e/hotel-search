package hotelsearch.model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface DatabaseService {

    List<Hotel> search(SearchOptions options)
            throws SQLException;

    List<Booking> addBooking(Hotel hotel, String guestName, SearchOptions options)
            throws SQLException;

    void cancelBooking(Hotel hotel, int bookingID) throws SQLException;

}
