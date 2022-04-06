package hotelsearch.model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UnsuccessfulDBSearchMock implements DatabaseService {

    @Override
    public List<Hotel> search(String nameOrLocation, LocalDate checkInDate, LocalDate checkOutDate, int nrGuests)
            throws SQLException {
        List<Hotel> list = new ArrayList<>();
        return list;
    }

    @Override
    public List<Booking> addBooking(Hotel hotel, String guestName, LocalDate checkInDate, LocalDate checkOutDate,
                                    int nrGuests) {
        return null;
    }

    @Override
    public void cancelBooking(Hotel hotel, int bookingID) {
    }

}
