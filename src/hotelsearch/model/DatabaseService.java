package hotelsearch.model;

import java.util.List;

public interface DatabaseService {

    List<Hotel> search(SearchOptions options);

    List<Booking> book(Hotel hotel, String guestEmail, String guestName, SearchOptions options);

    boolean cancelBooking(int bookingID);

    List<Booking> findBookings(String guestEmail);

}
