package hotelsearch.controller;

import hotelsearch.model.*;
import javafx.scene.image.Image;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

public class HotelControllerTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testFindHotelConnectionFail() {
        DatabaseService mockDatabaseService = new NoDBConnectionMock();
        HotelController hotelController = new HotelController(mockDatabaseService);
        List<Hotel> hotelList = hotelController.search(new SearchOptions("Reykjavík", "Name",
                LocalDate.of(2022, 4, 4),
                LocalDate.of(2022, 4, 14), 2));

        assertNotNull(hotelList);
        assertEquals(0, hotelList.size());
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

    @Test
    public void testBookRoomConnectionFail() {
        DatabaseService mockDatabaseService = new NoDBConnectionMock();
        HotelController hotelController = new HotelController(mockDatabaseService);

        /*
        List<Hotel> hotelList = hotelController.search(new SearchOptions("Reykjavík",
                LocalDate.of(2022, 4, 4), LocalDate.of(2022, 4, 14),
                2));
        hotelList.add(new Hotel(123, "Test", 5,
                new Image(Objects.requireNonNull(HotelDB.class.getResourceAsStream("/images/hotel1.jpg"))),
                "Description", 2, 1, 1,
                true, true, true, true));
         */

        List<Booking> bookingList = hotelController.book(new Hotel(123, "Test",
                        "1st street, 101 Reykjavík", "Description",
                        new Image(Objects.requireNonNull(HotelDB.class.getResourceAsStream("/images/hotel1.jpg"))),
                        5, 2, 1,
                        1, true, true, true, true, true),
                "Test", "Reykjavík", new SearchOptions("Reykjavík", "Reykjavík",
                        LocalDate.of(2022, 4, 4),
                        LocalDate.of(2022, 4, 14), 2));

        assertEquals(0, bookingList.size());
    }

    @Test
    public void testFindHotelsSuccess() {
        DatabaseService mockDatabaseService = new SuccessfulDBSearchMock();
        HotelController hotelController = new HotelController(mockDatabaseService);
        List<Hotel> hotelList = hotelController.search(new SearchOptions("Reykjavík", "Reykjavík",
                LocalDate.of(2022, 3, 10),
                LocalDate.of(2022, 4, 10), 2));
        assertNotNull(hotelList);

        for (Hotel hotel : hotelList) {
            assertEquals("Reykjavík", hotel.getName());
        }
    }

    @Test
    public void testFindHotelsFail() {
        DatabaseService mockDatabaseService = new UnsuccessfulDBSearchMock();
        HotelController hotelController = new HotelController(mockDatabaseService);
        List<Hotel> hotelList = hotelController.search(new SearchOptions("Reykjavík", "ARKGMRLKSGSKLN",
                LocalDate.of(2022, 3, 10),
                LocalDate.of(2022, 4, 10), 2));
        assertNotNull(hotelList);
        assertEquals(0, hotelList.size());
    }

    @Test
    public void testOrderByPriceAscending() {
        HotelController hotelController = new HotelController(null);
        List<Hotel> hotelList = new ArrayList<>();
        hotelList.add(new Hotel(123, "Test", "1st street, 101 Reykjavík", "D",
                new Image(Objects.requireNonNull(HotelDB.class.getResourceAsStream("/images/hotel1.jpg"))),
                5, 2, 1, 1,
                true, true, true, true, true));
        hotelList.add(new Hotel(12, "Test2", "1st street, 101 Reykjavík", "D",
                new Image(Objects.requireNonNull(HotelDB.class.getResourceAsStream("/images/hotel1.jpg"))),
                5, 1, 1, 1,
                true, true, true, true, true));
        hotelList.add(new Hotel(13, "Test3", "1st street, 101 Reykjavík", "D",
                new Image(Objects.requireNonNull(HotelDB.class.getResourceAsStream("/images/hotel1.jpg"))),
                5, 3, 1, 1,
                true, true, true, true, true));

        List<Hotel> orderedHotelList = hotelController.orderByPriceAscending(hotelList);
        assertNotNull(hotelList);
        for (int i = 1; i < orderedHotelList.size(); i++) {
            Hotel current = orderedHotelList.get(i);
            Hotel previous = orderedHotelList.get(i - 1);
            assertTrue(current.getStartingRoomPrice() >= previous.getStartingRoomPrice());
        }
    }

    @Test
    public void testFilterByStars() {
        HotelController hotelController = new HotelController(new SuccessfulDBSearchMock());
        List<Hotel> hotelList = new ArrayList<>();
        hotelList.add(new Hotel(123, "Test", "1st street, 101 Reykjavík", "D",
                new Image(Objects.requireNonNull(HotelDB.class.getResourceAsStream("/images/hotel1.jpg"))),
                3, 2, 1, 1,
                true, true, true, true, true));
        hotelList.add(new Hotel(123, "Test", "1st street, 101 Reykjavík", "D",
                new Image(Objects.requireNonNull(HotelDB.class.getResourceAsStream("/images/hotel1.jpg"))),
                4, 2, 1, 1,
                true, true, true, true, true));
        hotelList.add(new Hotel(123, "Test", "1st street, 101 Reykjavík", "D",
                new Image(Objects.requireNonNull(HotelDB.class.getResourceAsStream("/images/hotel1.jpg"))),
                5, 2, 1, 1,
                true, true, true, true, true));

        List<Hotel> filteredList = hotelController.filterByStars(hotelList, 5);
        assertNotNull(hotelList);
        assertEquals(1, filteredList.size());
        assertEquals(5, filteredList.get(0).getNumberOfStars());
    }

}