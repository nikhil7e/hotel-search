package hotelsearch.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NoDBConnectionMock implements DatabaseService {


    @Override
    public List<Hotel> search(SearchOptions options) {
        return new ArrayList<>();
    }

    @Override
    public List<Booking> addBooking(Hotel hotel, String guestName, SearchOptions options) throws SQLException {
        throw new SQLException();
    }

    @Override
    public void cancelBooking(Hotel hotel, int bookingID) throws SQLException {
        throw new SQLException();
    }
}
