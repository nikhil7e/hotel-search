package hotelsearch.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UnsuccessfulDBSearchMock implements DatabaseService {

    @Override
    public List<Hotel> search(String nameOrLocation, Date checkInDate, Date checkOutDate, int nrGuests) throws SQLException {
        List<Hotel> list = new ArrayList<>();
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
