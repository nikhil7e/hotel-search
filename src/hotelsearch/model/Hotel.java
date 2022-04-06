package hotelsearch.model;

import java.awt.*;
import java.util.Comparator;

import javafx.scene.image.Image;

public class Hotel {
    private int hotelID;
    private String name;
    private int numberOfStars;
    private Image image;
    private String description;
    private double startingRoomPrice;
    private double distanceFromDowntown;
    private double distanceFromSupermarket;
    private boolean testBool;   // f.x restaurant

    /**
     * Constructor for the class Hotel.
     * @param hotelID The ID of the hotel.
     * @param name The name of the hotel.
     * @param numberOfStars The rating of the hotel.
     * @param image An image of the hotel.
     * @param description A description of the hotel.
     * @param startingRoomPrice The lowest room price for a night stay in the hotel.
     * @param distanceFromDowntown The distance the hotel is from the downtown area in the city/town it is located.
     * @param distanceFromSupermarket The distance the hotel is from the nearest supermarket.
     */
    public Hotel(int hotelID, String name,
                 int numberOfStars, Image image, String description,
                 double startingRoomPrice, double distanceFromDowntown,
                 double distanceFromSupermarket){
        this.hotelID = hotelID;
        this.name = name;
        this.numberOfStars = numberOfStars;
        this.image = image;
        this.description = description;
        this.startingRoomPrice = startingRoomPrice;
        this.distanceFromDowntown = distanceFromDowntown;
        this.distanceFromSupermarket = distanceFromSupermarket;
    }

    public int getHotelID(){
        return hotelID;
    }

    public String getName(){
        return name;
    }

    public int getNumberOfStars() {
        return numberOfStars;
    }

    public Image getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public double getStartingRoomPrice() {
        return startingRoomPrice;
    }

    public double getDistanceFromDowntown() {
        return distanceFromDowntown;
    }

    public double getDistanceFromSupermarket() {
        return distanceFromSupermarket;
    }

    public boolean getTestBool() {return testBool;}

    public static class priceAscending implements Comparator<Hotel> {
        @Override
        public int compare(Hotel o1, Hotel o2) {
            return (int)(o1.getStartingRoomPrice() - o2.getStartingRoomPrice());
        }
    }
    public static class priceDescending implements Comparator<Hotel> {
        @Override
        public int compare(Hotel o1, Hotel o2) {
            return (int)(o2.getStartingRoomPrice() - o1.getStartingRoomPrice());
        }
    }
    public static class starsDescending implements Comparator<Hotel> {
        @Override
        public int compare(Hotel o1, Hotel o2) {
            return o2.getNumberOfStars() - o1.getNumberOfStars();
        }
    }

    public static class starsAscending implements Comparator<Hotel> {
        @Override
        public int compare(Hotel o1, Hotel o2) {
            return o1.getNumberOfStars() - o2.getNumberOfStars();
        }
    }
    public static class distanceFromDowntown implements Comparator<Hotel> {
        // ascending (lowest diff on the top)
        @Override
        public int compare(Hotel o1, Hotel o2) {
            return (int)(o1.getDistanceFromDowntown() - o2.getDistanceFromDowntown());
        }
    }
}
