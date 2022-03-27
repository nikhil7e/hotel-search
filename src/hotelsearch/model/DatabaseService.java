package hotelsearch.model;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface DatabaseService {

    List<Hotel> search(String nameOrLocation, Date checkInDate, Date checkOutDate, int nrGuests) throws SQLException;

    List<Booking> addBooking(Hotel hotel, String guestName, Date checkInDate, Date checkOutDate, int nrGuests);

    void removeBooking(int bookingID);

}
