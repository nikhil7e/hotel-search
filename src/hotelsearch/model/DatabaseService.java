package hotelsearch.model;

import java.sql.SQLException;
import java.util.List;

public interface DatabaseService {

    List<Hotel> search(SearchOptions options);

    List<Booking> book(Hotel hotel, String guestEmail, String guestName, SearchOptions options);

    void cancelBooking(int hotelID, int bookingID) throws SQLException;

}
