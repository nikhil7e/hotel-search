package hotelsearch.model;

import java.time.LocalDate;

public class Booking {

    private final int bookingID;
    private final int roomID;
    private final int hotelID;
    private final int bookingProcessID;
    private final int guestID;
    private final String guestEmail;
    private final String guestName;
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;

    public Booking(int bookingID, int roomID, int hotelID, int bookingProcessID, int guestID, String guestEmail,
                   String guestName, LocalDate checkInDate, LocalDate checkOutDate) {
        this.bookingID = bookingID;
        this.hotelID = hotelID;
        this.roomID = roomID;
        this.bookingProcessID = bookingProcessID;
        this.guestID = guestID;
        this.guestEmail = guestEmail;
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public int getBookingID() {
        return bookingID;
    }

    public int getHotelID() {
        return hotelID;
    }

    public int getRoomID() {
        return roomID;
    }

    public int getBookingProcessID() {
        return bookingProcessID;
    }

    public int getGuestID() {
        return guestID;
    }

    public String getGuestEmail() {
        return guestEmail;
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