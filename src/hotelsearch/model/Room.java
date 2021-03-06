package hotelsearch.model;

public class Room {

    private final int roomID;
    private final int hotelID;
    private final int nrBeds;
    private final int pricePerNight;
    private final boolean tv;
    private final boolean privateBathroom;
    private final boolean cityView;

    public Room(int roomID, int hotelID, int nrBeds, int pricePerNight, boolean tv, boolean cityView,
                boolean privateBathroom) {
        this.roomID = roomID;
        this.hotelID = hotelID;
        this.nrBeds = nrBeds;
        this.pricePerNight = pricePerNight;
        this.tv = tv;
        this.cityView = cityView;
        this.privateBathroom = privateBathroom;
    }

    public int getRoomID() {
        return roomID;
    }

    public int getHotelID() {
        return hotelID;
    }

    public int getNrBeds() {
        return nrBeds;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public boolean getTv() {
        return tv;
    }

    public boolean getCityView() {
        return cityView;
    }

    public boolean getPrivateBathroom() {
        return privateBathroom;
    }

}
