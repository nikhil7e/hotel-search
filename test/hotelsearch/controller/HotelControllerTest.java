package hotelsearch.controller;

import hotelsearch.model.Booking;
import hotelsearch.model.Hotel;
import org.junit.*;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

public class HotelControllerTest {

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test(expected = SomeException)
    public void testFindHotelConnectionFail() {
        DatabaseService mockDatabaseService = new NoConnectionMock();
        HotelController hotelController = new HotelController(mockDatabaseService);
        hotelController.findHotels();
    }

    @Test(expected = SomeException)
    public void testModifyBookingConnectionFail() {
        DatabaseService mockDatabaseService = new NoConnectionMock();
        HotelController hotelController = new HotelController(mockDatabaseService);
        hotelController.findHotels();
    }

    @Test(expected = SomeException)
    public void testCancelBookingConnectionFail() {
        DatabaseService mockDatabaseService = new NoConnectionMock();
        HotelController hotelController = new HotelController(mockDatabaseService);
        hotelController.findHotels();
    }

    @Test(expected = SomeException)
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
        List<Hotel> hotelList = hotelController.findHotels("", new Date(10), new Date(11), 2);
        assertNotNull(hotelList);
        assertEquals(0, hotelList.size());
    }

    @Test
    public void testModifyBookingSuccess() {
        DatabaseService mockDatabaseService = new UnsuccessfulSearchMock();
        HotelController hotelController = new HotelController(mockDatabaseService);
        Booking originalBooking = hotelController.bookRoom("Hilton Hotel", new Date(9), new Date(11), 2);
        Booking modifiedBooking = hotelController.modifyBooking(originalBooking.getBookingID(), new Date(10), originalBooking.getCheckOutDate());
        assertEquals(modifiedBooking.getCheckInDate(), new Date(10));
    }

}