package hotelsearch.model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class NoDBConnectionMock implements DatabaseService {


    @Override
    public List<Hotel> search(String nameOrLocation, LocalDate checkInDate, LocalDate checkOutDate, int nrGuests)
            throws SQLException {
        throw new SQLException();
    }

    @Override
    public List<Booking> addBooking(Hotel hotel, String guestName, LocalDate checkInDate, LocalDate checkOutDate,
                                    int nrGuests) throws SQLException {
        throw new SQLException();
    }

    @Override
    public void cancelBooking(Hotel hotel, int bookingID) throws SQLException {
        throw new SQLException();
    }
}
