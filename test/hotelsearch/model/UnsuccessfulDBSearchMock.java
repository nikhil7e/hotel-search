package hotelsearch.model;

import java.util.ArrayList;
import java.util.List;

public class UnsuccessfulDBSearchMock implements DatabaseService {

    @Override
    public List<Hotel> search(SearchOptions options) {
        return new ArrayList<>();
    }

    @Override
    public List<Booking> book(Hotel hotel, String guestEmail, String guestName, SearchOptions options) {
        return null;
    }

    @Override
    public void cancelBooking(int hotelID, int bookingID) {
    }

}
