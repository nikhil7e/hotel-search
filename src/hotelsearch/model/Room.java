package hotelsearch.model;

public class Room {

    // Variables
    private int roomID;
    private int hotelID;
    private int nrBeds;
    private int pricePerNight;

    // Getters
    public int getRoomID() {
        return roomID;
    }

    public int gethotelID() {
        return hotelID;
    }

    public int getNrBeds() {
        return nrBeds;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    // Constructor
    public Room(int roomid, int hotelid, int nrbeds, int pricepernight) {
        roomID = roomid;
        hotelID = hotelid;
        nrBeds = nrbeds;
        pricePerNight = pricepernight;
    }
}
