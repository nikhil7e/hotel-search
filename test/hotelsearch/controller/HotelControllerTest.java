package hotelsearch.controller;

import hotelsearch.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class HotelControllerTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test(expected = SQLException.class)
    public void testFindHotelConnectionFail() throws SQLException {
        DatabaseService mockDatabaseService = new NoDBConnectionMock();
        HotelController hotelController = new HotelController(mockDatabaseService);
        hotelController.findHotels("", null, null, 2);
    }

    /*
    @Test(expected = SQLException.class)
    public void testModifyBookingConnectionFail() {
        DatabaseService mockDatabaseService = new NoDBConnectionMock();
        HotelController hotelController = new HotelController(mockDatabaseService);
        hotelController.modifyBooking(123, null, null, 2);
    }

    @Test(expected = SQLException.class)
    public void testCancelBookingConnectionFail() {
        DatabaseService mockDatabaseService = new NoDBConnectionMock();
        HotelController hotelController = new HotelController(mockDatabaseService);
        hotelController.cancelBooking("", null, null, 2);
    }
     */

    @Test(expected = SQLException.class)
    public void testBookRoomConnectionFail() throws SQLException {
        DatabaseService mockDatabaseService = new NoDBConnectionMock();
        HotelController hotelController = new HotelController(mockDatabaseService);
        hotelController.bookRoom(null, "", null, null, 2);
    }

    @Test
    public void testFindHotelsSuccess() throws SQLException, ClassNotFoundException {
        DatabaseService mockDatabaseService = new SuccessfulDBSearchMock();
        HotelController hotelController = new HotelController(mockDatabaseService);
        List<Hotel> hotelList = hotelController.findHotels("Reykjavík",
                LocalDate.of(2022, 3, 10),
                LocalDate.of(2022, 4, 10), 2);
        assertNotNull(hotelList);
    }

    @Test
    public void testFindHotelsFail() throws SQLException, ClassNotFoundException {
        DatabaseService mockDatabaseService = new UnsuccessfulDBSearchMock();
        HotelController hotelController = new HotelController(mockDatabaseService);
        List<Hotel> hotelList = hotelController.findHotels("/",
                LocalDate.of(2022, 3, 10),
                LocalDate.of(2022, 4, 10), 2);
        assertNotNull(hotelList);
        assertEquals(0, hotelList.size());
    }

    @Test
    public void testOrderByPriceAscending() {
        HotelController hotelController = new HotelController(null);
        List<Hotel> hotelList = new ArrayList<>();
        hotelList.add(new Hotel(123, "Test", 5, null, "",
                1, 1, 1));
        hotelList.add(new Hotel(12, "Test2", 5, null, "",
                2, 1, 1));
        hotelList.add(new Hotel(13, "Test3", 5, null, "",
                3, 1, 1));

        List<Hotel> orderedHotelList = hotelController.orderByPriceAscending(hotelList);
        assertNotNull(hotelList);
        for (int i = 1; i < orderedHotelList.size(); i++) {
            Hotel current = orderedHotelList.get(i);
            Hotel previous = orderedHotelList.get(i - 1);
            assertTrue(current.getStartingRoomPrice() >= previous.getStartingRoomPrice());
        }
    }

}