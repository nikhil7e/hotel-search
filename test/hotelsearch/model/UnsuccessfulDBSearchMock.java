package hotelsearch.model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UnsuccessfulDBSearchMock implements DatabaseService {

    @Override
    public List<Hotel> search(SearchOptions options)
            throws SQLException {
        List<Hotel> list = new ArrayList<>();
        return list;
    }

    @Override
    public List<Booking> addBooking(Hotel hotel, String guestName, SearchOptions options) {
        return null;
    }

    @Override
    public void cancelBooking(Hotel hotel, int bookingID) {
    }

}
