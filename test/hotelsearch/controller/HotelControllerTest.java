package hotelsearch.controller;

import hotelsearch.model.Hotel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
    public void testFindHotelConnectionFail() {
        DatabaseService mockDatabaseService = new NoConnectionMock();
        HotelController hotelController = new HotelController(mockDatabaseService);
        hotelController.findHotels();
    }

    @Test(expected = SQLException.class)
    public void testModifyBookingConnectionFail() {
        DatabaseService mockDatabaseService = new NoConnectionMock();
        HotelController hotelController = new HotelController(mockDatabaseService);
        hotelController.findHotels();
    }

    @Test(expected = SQLException.class)
    public void testCancelBookingConnectionFail() {
        DatabaseService mockDatabaseService = new NoConnectionMock();
        HotelController hotelController = new HotelController(mockDatabaseService);
        hotelController.findHotels();
    }

    @Test(expected = SQLException.class)
    public void testBookRoomConnectionFail() {
        DatabaseService mockDatabaseService = new NoConnectionMock();
        HotelController hotelController = new HotelController(mockDatabaseService);
        hotelController.findHotels();
    }

    @Test
    public void testFindHotelsSuccess() {
        DatabaseService mockDatabaseService = new SuccessfulSearchMock();
        HotelController hotelController = new HotelController(mockDatabaseService);
        List<Hotel> hotelList = hotelController.findHotels("Reykjavík", new Date(10), new Date(11), 2);
        assertNotNull(hotelList);
    }

    @Test
    public void testFindHotelsFail() {
        DatabaseService mockDatabaseService = new UnsuccessfulSearchMock();
        HotelController hotelController = new HotelController(mockDatabaseService);
        List<Hotel> hotelList = hotelController.findHotels("/", new Date(10), new Date(11), 2);
        assertNotNull(hotelList);
        assertEquals(0, hotelList.size());
    }

    @Test
    public void testOrderByPriceAscending() {
        DatabaseService databaseService = new DatabaseService();
        HotelController hotelController = new HotelController(databaseService);
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
            assertTrue(current.getRoomPrice() >= previous.getRoomPrice());
        }
    }

}