package hotelsearch.model;

import java.util.Date;

public interface DatabaseService {

    Hotel[] search(String nameOrLocation, Date checkInDate, Date checkOutDate, int nrGuests);

    Booking[] addBooking(Hotel hotel, String guestName, Date checkInDate, Date checkOutDate, int nrGuests);

    void removeBooking(int bookingID);

}
