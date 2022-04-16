package hotelsearch.model;

import java.util.ArrayList;
import java.util.List;

public class NoDBConnectionMock implements DatabaseService {


    @Override
    public List<Hotel> search(SearchOptions options) {
        return new ArrayList<>();
    }

    @Override
    public List<Booking> book(Hotel hotel, String guestEmail, String guestName, SearchOptions options) {
        return new ArrayList<>();
    }

    @Override
    public boolean cancelBooking(int bookingID) {
        return false;
    }

    @Override
    public List<Booking> findBookings(String guestEmail) {
        return new ArrayList<>();
    }

}
