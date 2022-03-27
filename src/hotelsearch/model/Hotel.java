package hotelsearch.model;

import java.awt.*;
import javafx.scene.image.Image;

public class Hotel {
    private int hotelID;
    private String name;
    private int numberOfStars;
    private Image image;
    private String description;
    private double roomPrice;
    private double distanceFromDowntown;
    private double distanceFromSupermarket;

    /**
     * Constructor for the class Hotel.
     * @param hotelID The ID of the hotel.
     * @param name The name of the hotel.
     * @param numberOfStars The rating of the hotel.
     * @param image An image of the hotel.
     * @param description A description of the hotel.
     * @param roomPrice The room price for a night stay in the hotel.
     * @param distanceFromDowntown The distance the hotel is from the downtown area in the city/town it is located.
     * @param distanceFromSupermarket The distance the hotel is from the nearest supermarket.
     */
    public Hotel(int hotelID, String name,
                 int numberOfStars, Image image, String description,
                 double roomPrice, double distanceFromDowntown,
                 double distanceFromSupermarket){
        this.hotelID = hotelID;
        this.name = name;
        this.numberOfStars = numberOfStars;
        this.image = image;
        this.description = description;
        this.roomPrice = roomPrice;
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

    public double getRoomPrice() {
        return roomPrice;
    }

    public double getDistanceFromDowntown() {
        return distanceFromDowntown;
    }

    public double getDistanceFromSupermarket() {
        return distanceFromSupermarket;
    }
}
