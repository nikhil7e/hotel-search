package hotelsearch.model;

import java.util.Comparator;

public class Hotel {
    private final int hotelID;
    private final String name;
    private final String address;
    private final int numberOfStars;
    private final String imageURL;
    private final String description;
    private final double startingRoomPrice;
    private final double distanceFromDowntown;
    private final double distanceFromSupermarket;
    private final boolean restaurant;
    private final boolean breakfastIncluded;
    private final boolean bar;
    private final boolean freeWifi;
    private final boolean featured;

    /**
     * Constructor for the class Hotel.
     *
     * @param hotelID                 The ID of the hotel
     * @param name                    The name of the hotel
     * @param address                 The address of the hotel with format "street, ..., postal code  city",
     *                                i.e. "1st street, 101 Reykjavík"
     * @param description             A description of the hotel
     * @param imageURL                The path to the image of the hotel with format "images/x.imageType", where x is
     *                                any image and imageType is the image type of the image.
     * @param numberOfStars           The rating of the hotel
     * @param startingRoomPrice       The lowest room price for a night stay in the hotel.
     * @param distanceFromDowntown    The distance the hotel is from the downtown area in the city/town it is located.
     * @param distanceFromSupermarket The distance the hotel is from the nearest supermarket
     * @param restaurant              Indicates whether the hotel contains a restaurant
     * @param breakfastIncluded       Indicates whether the breakfast cost is included for the hotel
     * @param freeWifi                Indicates whether the hotel offers Wi-Fi free of charge
     * @param bar                     Indicates whether the hotel contains a bar
     * @param featured                Indicates whether the hotel is featured
     */
    public Hotel(int hotelID, String name,
                 String address, String description, String imageURL, int numberOfStars,
                 double startingRoomPrice, double distanceFromDowntown,
                 double distanceFromSupermarket, boolean restaurant,
                 boolean breakfastIncluded, boolean freeWifi, boolean bar, boolean featured) {
        this.hotelID = hotelID;
        this.name = name;
        this.address = address;
        this.numberOfStars = numberOfStars;
        this.imageURL = imageURL;
        this.description = description;
        this.startingRoomPrice = startingRoomPrice;
        this.distanceFromDowntown = distanceFromDowntown;
        this.distanceFromSupermarket = distanceFromSupermarket;
        this.restaurant = restaurant;
        this.breakfastIncluded = breakfastIncluded;
        this.bar = bar;
        this.freeWifi = freeWifi;
        this.featured = featured;
    }

    public int getHotelID() {
        return hotelID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getNumberOfStars() {
        return numberOfStars;
    }

    public String getImageURL() {
        return imageURL;
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

    public boolean getFeatured() {
        return featured;
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
