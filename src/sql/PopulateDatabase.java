package sql;

import hotelsearch.model.Booking;
import hotelsearch.model.Hotel;
import hotelsearch.model.HotelDB;
import hotelsearch.model.Room;

import java.time.LocalDate;
import java.util.Random;

public class PopulateDatabase {

    private int n;
    private HotelDB db;


    public String[] hotelNames;     // webScraping !
    public String[] hotelAddressess;    // webScraping !
    public String[] hotelDescriptions;  // webScraping !
    public String[] hotelImageURLs;
    //public int[] hotelStars = {1,2,3,4,5};
    public double[] hotelStartingRoomPrice; // between 5k and 50k
    public double[] hotelDistFromDowntown;
    public double[] distFromSupermarket;
    public boolean[] bools = {true, false}; // feels dumb




    public PopulateDatabase(int n, HotelDB db) {
        this.n = n;
        this.db = db;
    }

    public static void makeRooms(int n, HotelDB db, int hotelID) {
        Random rnd = new Random();
        for(int i = 0; i < n; i++) {
            Room rm = new Room(
                    i,
                    hotelID,
                    rnd.nextInt(5) + 1, // between 1 and 4 bedds
                    (rnd.nextInt(50) + 3) * 100, // between 3000 - 40000 isk
                    bools(),bools(),bools()
            );
            db.insertRoom(rm);
        }
    }
    public static void makeBookings(int n, HotelDB db, int hotelID, int roomID) {
        Random rnd = new Random();
        LocalDate loc = LocalDate.of(
                rnd.nextInt(6)+2017, // between 2017 - 2022
                rnd.nextInt(12)+1,        // between 1-12
                rnd.nextInt(29)+1);       // between 1-28,
        for(int i = 0; i < n; i++) {
            Booking book = new Booking(
                    hotelID,
                    roomID,
                    i,
                    i,
                    randomString(rnd.nextInt(13)+7)+"@gmail.com",
                    randomString(rnd.nextInt(12)+5),
                    loc,
                    loc.plusDays(rnd.nextInt(10)+1) // between 1-10
            );
        }
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
                    rnd.nextInt(6), // between 1-5 int
                    ((rnd.nextDouble(50) + 3) * 100), // between 3000 - 50000 isk
                     rnd.nextDouble(3), // between 1 - 3 km?
                    rnd.nextDouble(3),
                    bools(),bools(),bools(),bools(),bools()
            );
            makeRooms(rnd.nextInt(300)+10,db,i);  // make all the rooms corresponding to the hotelID
            db.insertHotel(ht);
        }
    }

    private static boolean bools() {
        Random rnd = new Random();
        int var = rnd.nextInt(2);
        if (var == 1) return true;
        else return false;
    }
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

    }




}
