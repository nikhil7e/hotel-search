package hotelsearch.model;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SuccessfulDBSearchMock implements DatabaseService {

    @Override
    public List<Hotel> search(SearchOptions options) {
        List<Hotel> list = new ArrayList<>();
        list.add(new Hotel(1, options.getName(), "Reykjavík", "Description",
                "/images/hotel1.jpg", 2, 1, 1,
                1, true, true, true, true, false));
        return list;
    }

    @Override
    public List<Booking> book(Hotel hotel, String guestEmail, String guestName, SearchOptions options) {
        return null;
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
