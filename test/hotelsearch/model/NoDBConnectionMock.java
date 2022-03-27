package hotelsearch.model;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class NoDBConnectionMock implements DatabaseService {


    @Override
    public List<Hotel> search(String nameOrLocation, Date checkInDate, Date checkOutDate, int nrGuests) throws SQLException {
        throw new SQLException();
    }

    @Override
    public List<Booking>  addBooking(Hotel hotel, String guestName, Date checkInDate, Date checkOutDate, int nrGuests) {
        return null;
    }

    @Override
    public void removeBooking(int bookingID) {

    }
}
