package hotelsearch.gui;

import hotelsearch.model.Hotel;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HotelViewGUIController {

    @FXML
    private Label fxHotelName;

    @FXML
    private Label fxHotelReview;

    @FXML
    private ImageView fxHotelImage;

    @FXML
    private ImageView fxStars;

    @FXML
    private Label fxDownTown;

    @FXML
    private Label fxSuperMarket;

    @FXML
    private CheckBox fxRestaurant;

    @FXML
    private CheckBox fxBreakfast;

    @FXML
    private CheckBox fxBar;

    @FXML
    private CheckBox fxFreeWifi;

    @FXML
    private Label fxStartingPrice;


    public void displayHotel(Hotel hotel) {
        fxHotelName.setText(hotel.getName());
        fxHotelReview.setText(hotel.getDescription());
        fxHotelImage.setImage(hotel.getImage());
        switch (hotel.getNumberOfStars()) {
            case 1:
                fxStars.setImage(new Image("/images/OneStar.png"));
                break;
            case 2:
                fxStars.setImage(new Image("/images/TwoStars.png"));
                break;
            case 3:
                fxStars.setImage(new Image("/images/ThreeStars.png"));
                break;
            case 4:
                fxStars.setImage(new Image("/images/FourStars.png"));
                break;
            case 5:
                fxStars.setImage(new Image("/images/FiveStars.png"));
                break;
        }

        /*
        fxRestaurant.setSelected(hotel.getRestaurant());
        fxRestaurant.setDisable(true);
        fxBreakfast.setSelected(hotel.getBreakfastIncluded());
        fxBreakfast.setDisable(true);
        fxBar.setSelected(hotel.getBar());
        fxBar.setDisable(true);
        fxFreeWifi.setSelected(hotel.getFreeWifi());
        fxFreeWifi.setDisable(true);
         */


        fxDownTown.setText("Distance from down town is " + hotel.getDistanceFromDowntown() + "Km");
        fxSuperMarket.setText("Distance from the supermarket is " + hotel.getDistanceFromSupermarket() + "Km");

        // fxStartingPrice.setText("The starting price for a room here is " + hotel.getStartingRoomPrice());
    }

}
