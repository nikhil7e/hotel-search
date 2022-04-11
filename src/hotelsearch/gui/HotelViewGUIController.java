package hotelsearch.gui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import hotelsearch.model.Hotel;
import javafx.stage.Stage;

public class HotelViewGUIController{



    @FXML
    private Label fxHotelName;

    @FXML
    private Label fxHotelReview;

    @FXML
    private ImageView fxHotelImage;

    @FXML
    private ImageView fxStars;

    public void displayHotel(Hotel hotel) {
        fxHotelName.setText(hotel.getName());
        fxHotelReview.setText(hotel.getDescription());

        Image image = new Image("/images/HiltonImage.jpg");


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



    }
}
