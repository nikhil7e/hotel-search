package sql;

import hotelsearch.model.Booking;
import hotelsearch.model.Hotel;
import hotelsearch.model.HotelDB;
import hotelsearch.model.Room;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

public class PopulateDatabase {

    private int n;
    private HotelDB db;


    //    public String[] hotelNames;         // webScraping ! eða handavinna
    //    public String[] hotelAddressess;    // webScraping ! eða handavinna
    //    public String[] hotelDescriptions;  // webScraping ! eða handavinna


    public PopulateDatabase(int n, HotelDB db) {
        this.n = n;
        this.db = db;
    }

    public static void makeRooms(int n, HotelDB db, int hotelID) {
        Random rnd = new Random();
        //int uniqueID = UUID.randomUUID().hashCode();
        for(int i = 0; i < n; i++) {
            Room rm = new Room(
                    i,       // should just be = i
                    hotelID,
                    rnd.nextInt(5) + 1, // between 1 and 4 bedds
                    (rnd.nextInt(50) + 3) * 100, // between 3000 - 40000 isk
                    bools(),bools(),bools()
            );
            db.insertRoom(rm);
            if (bools25()) bookTheRoom(db,hotelID,i);    // 25% of rooms are booked, can be adjusted
        }
    }
    public static void bookTheRoom(HotelDB db, int hotelID, int roomID) {
        Random rnd = new Random();
        int uniqueID = UUID.randomUUID().hashCode();
        LocalDate loc = LocalDate.of(
                rnd.nextInt(6) + 2017, // between 2017 - 2022
                rnd.nextInt(12) + 1,        // between 1-12
                rnd.nextInt(28) + 1);       // between 1-28,
        Booking book = new Booking(
                hotelID,
                roomID,
                uniqueID,
                uniqueID,
                randomString(rnd.nextInt(13) + 7) + "@gmail.com",
                randomString(rnd.nextInt(12) + 5),
                loc,
                loc.plusDays(rnd.nextInt(10) + 1) // between 1-10
        );
        db.insertBooking(book);
    }
    public static void makeHotels(int n, HotelDB db) {
        Random rnd = new Random();
        for(int i = 0; i < n; i++) {
            Hotel ht = new Hotel(
                    i,
                    "hotelName" + i,
                    "address" + i,
                    "description" + i,
                    "src/images/hotel1.jpg",
                    rnd.nextInt(5) + 1, // between 1-5 int
                    ((rnd.nextDouble(50) + 3) * 100), // between 3000 - 50000 isk
                     rnd.nextDouble(3), // between 1 - 3 km?
                    rnd.nextDouble(3),
                    bools(),bools(),bools(),bools(),bools()
            );
            db.insertHotel(ht);
            makeRooms(rnd.nextInt(291)+10,db,i);  // make all the rooms corresponding to the hotelID, between 10 and 300 rooms per hotel
        }
    }
    private static boolean bools() {
        Random rnd = new Random();
        int var = rnd.nextInt(2);
        if (var == 1) return true;
        else return false;
    }

    private static boolean bools25() {
        Random rnd = new Random();
        int var = rnd.nextInt(4);
        if (var == 0) return true;
        else return false;
    }   // 25% chance we get true
    private static String randomString(int n) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < n; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }
        return sb + "@gmail.com";
    }
    public static void main(String[] args) {
        HotelDB db = new HotelDB();
        makeHotels(10,db);
    }




}
