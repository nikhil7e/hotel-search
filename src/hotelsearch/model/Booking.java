package hotelsearch.model;

import java.time.LocalDate;

public class Booking {

    private final int bookingID;
    private final int roomID;
    private final int hotelID;
    private final int bookingTransactionID;
    private final String guestEmail;
    private final String guestName;
    private final LocalDate checkInDate;
    private final LocalDate checkOutDate;

    public Booking(int hotelID, int roomID, int bookingID, int bookingProcessID, String guestEmail,
                   String guestName, LocalDate checkInDate, LocalDate checkOutDate) {
        this.bookingID = bookingID;
        this.hotelID = hotelID;
        this.roomID = roomID;
        this.bookingTransactionID = bookingProcessID;
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

    public int getBookingTransactionID() {
        return bookingTransactionID;
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