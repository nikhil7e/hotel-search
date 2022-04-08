package hotelsearch.model;

import java.time.LocalDate;
import java.util.Date;

public class Booking {

    private int bookingID;
    private int hotelID;
    private int roomID;
    private int guestID;
    private String guestName;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    // constructor
    public Booking (int bookingID, int hotelID, int roomID, int guestID, String guestName, LocalDate checkInDate,
                    LocalDate checkOutDate) {
        this.bookingID = bookingID;
        this.hotelID = hotelID;
        this.roomID = roomID;
        this.guestID = guestID;
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }
    
    // getters
    public int getBookingID() {
        return bookingID;
    }

    public int getHotelID() {
        return hotelID;
    }

    public int getRoomID() {
        return roomID;
    }

    public int getGuestID() {
        return guestID;
    }

    public String getGuestName() {
        return guestName;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public static void main(String[] args) {

    }

}