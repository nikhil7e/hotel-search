package sql;

import hotelsearch.model.*;

import java.awt.*;
import java.awt.print.Book;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class PopulateDatabase {

    //List<Hotel> hotels = new ArrayList<Hotel>();

    public static int n;
    private DatabaseService db;         // final?

    private static List<Hotel> hotels = new ArrayList<Hotel>();
    private List<Room> rooms = new ArrayList<Room>();
    private List<Booking> bookings = new ArrayList<Booking>();

    public PopulateDatabase(int n, DatabaseService db) {
        this.n = n;
        this.db = db;
        makeHotels();
        makeRooms();
        makeBookings();
    }

    public static void makeRooms() {

    }

    public static void makeBookings() {

    }
    public static void makeHotels() {

        for(int i = 0; i < n; i++) {
            Hotel ht = new Hotel(i, "hotelName" + i,"address" + i,"description" + i,
                    "src/images/hotel1.jpg",5,10.0, 100,100,
                    true,true,true,true,true);
            hotels.add(ht);
        }
        db.insertHotel()
    }

    public static void main(String[] args) {
        DatabaseService db = new HotelDB();
        PopulateDatabase dbb = new PopulateDatabase(5,db);
        //testing makeHotelObject
        for(int i  = 0; i < n; i++) System.out.println(hotels.get(i));
    }




}
