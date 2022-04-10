package hotelsearch.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnsuccessfulDBSearchMock implements DatabaseService {

    @Override
    public List<Hotel> search(SearchOptions options) {
        return new ArrayList<>();
    }

    @Override
    public List<Booking> addBooking(Hotel hotel, String guestName, SearchOptions options) {
        return null;
    }

    @Override
    public void cancelBooking(Hotel hotel, int bookingID) {
    }

}
