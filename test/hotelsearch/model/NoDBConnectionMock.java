package hotelsearch.model;

import java.sql.SQLException;
import java.util.Date;

public class NoDBConnectionMock implements DatabaseService {


    @Override
    public Hotel[] search(String nameOrLocation, Date checkInDate, Date checkOutDate, int nrGuests) throws SQLException {
        throw new SQLException();
    }

    @Override
    public Booking[] addBooking(Hotel hotel, String guestName, Date checkInDate, Date checkOutDate, int nrGuests) {
        return new Booking[0];
    }

    @Override
    public void removeBooking(int bookingID) {

    }
}
