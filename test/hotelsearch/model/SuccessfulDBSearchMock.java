package hotelsearch.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SuccessfulDBSearchMock implements DatabaseService {

    @Override
    public List<Hotel> search(String nameOrLocation, Date checkInDate, Date checkOutDate, int nrGuests) throws SQLException {
        List<Hotel> list = new ArrayList<>();
        list.add(new Hotel(1, "Reykjavík", 2, null, "", 1,
                1, 1));
        return list;
    }

    @Override
    public List<Booking> addBooking(Hotel hotel, String guestName, Date checkInDate, Date checkOutDate, int nrGuests) {
        return null;
    }

    @Override
    public void removeBooking(int bookingID) {

    }
}
