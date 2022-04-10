package hotelsearch.model;

import javafx.scene.image.Image;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SuccessfulDBSearchMock implements DatabaseService {

    @Override
    public List<Hotel> search(SearchOptions options) {
        List<Hotel> list = new ArrayList<>();
        list.add(new Hotel(1, options.getNameOrLocation(), 2,
                new Image(Objects.requireNonNull(HotelDB.class.getResourceAsStream("/images/hotel1.jpg"))),
                "Description", 1, 1, 1, true,
                true, true, true));
        return list;
    }

    @Override
    public List<Booking> addBooking(Hotel hotel, String guestEmail, String guestName, SearchOptions options) {
        return null;
    }

    @Override
    public void cancelBooking(Hotel hotel, int bookingID) {

    }
}
