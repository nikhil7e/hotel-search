package hotelsearch.model;

import java.util.Comparator;

import javafx.scene.image.Image;

public class Hotel {
    private final int hotelID;
    private final String name;
    private final int numberOfStars;
    private Image image;
    private String description;
    private double startingRoomPrice;
    private final double distanceFromDowntown;
    private double distanceFromSupermarket;
    private boolean restaurant;
    private boolean breakfastIncluded;
    private boolean bar;
    private boolean freeWifi;

    /**
     * Constructor for the class Hotel.
     *
     * @param hotelID                 The ID of the hotel.
     * @param name                    The name of the hotel.
     * @param numberOfStars           The rating of the hotel.
     * @param image                   An image of the hotel.
     * @param description             A description of the hotel.
     * @param startingRoomPrice       The lowest room price for a night stay in the hotel.
     * @param distanceFromDowntown    The distance the hotel is from the downtown area in the city/town it is located.
     * @param distanceFromSupermarket The distance the hotel is from the nearest supermarket.
     * @param restaurant              Indicates whether the hotel contains a restaurant
     * @param breakfastIncluded       Indicates whether the breakfast cost is included for the hotel
     * @param bar                     Indicates whether the hotel contains a bar
     * @param freeWifi                Indicates whether the hotel offers Wi-Fi free of charge
     */
    public Hotel(int hotelID, String name,
                 int numberOfStars, Image image, String description,
                 double startingRoomPrice, double distanceFromDowntown,
                 double distanceFromSupermarket, boolean restaurant,
                 boolean breakfastIncluded, boolean bar, boolean freeWifi) {
        this.hotelID = hotelID;
        this.name = name;
        this.numberOfStars = numberOfStars;
        this.image = image;
        this.description = description;
        this.startingRoomPrice = startingRoomPrice;
        this.distanceFromDowntown = distanceFromDowntown;
        this.distanceFromSupermarket = distanceFromSupermarket;
        this.restaurant = restaurant;
        this.breakfastIncluded = breakfastIncluded;
        this.bar = bar;
        this.freeWifi = freeWifi;
    }

    public int getHotelID() {
        return hotelID;
    }

    public String getName() {
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

    public boolean getRestaurant() {
        return restaurant;
    }

    public boolean getBreakfastIncluded() {
        return breakfastIncluded;
    }

    public boolean getBar() {
        return bar;
    }

    public boolean getFreeWifi() {
        return freeWifi;
    }

    public static class priceAscending implements Comparator<Hotel> {
        @Override
        public int compare(Hotel o1, Hotel o2) {
            return (int) (o1.getStartingRoomPrice() - o2.getStartingRoomPrice());
        }
    }

    public static class priceDescending implements Comparator<Hotel> {
        @Override
        public int compare(Hotel o1, Hotel o2) {
            return (int) (o2.getStartingRoomPrice() - o1.getStartingRoomPrice());
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
        // ascending (the lowest diff on the top)
        @Override
        public int compare(Hotel o1, Hotel o2) {
            return (int) (o1.getDistanceFromDowntown() - o2.getDistanceFromDowntown());
        }
    }

}
