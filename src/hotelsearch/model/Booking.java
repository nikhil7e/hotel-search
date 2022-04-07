package hotelsearch.model;

import java.util.Date;

public class Booking {

    int bookingID;
    int hotelID;
    int roomID;
    int guestID;
    String guestName;
    Date checkInDate;
    Date checkOutDate;

    // constructor
    public Booking (int bookid, int hotelid, int roomid, int guestid, String guestname, Date checkin, Date checkout) {
        bookingID = bookid;
        hotelID = hotelid;
        roomID = roomid;
        guestID = guestid;
        guestName = guestname;
        checkInDate = checkin;
        checkOutDate = checkout;
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

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public static void main(String[] args) {

    }

}