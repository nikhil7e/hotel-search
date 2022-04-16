package hotelsearch.gui;

import hotelsearch.model.Hotel;
import hotelsearch.model.HotelDB;
import hotelsearch.model.SearchOptions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.w3c.dom.html.HTMLObjectElement;

import java.io.IOException;
import java.sql.*;

import javax.swing.table.TableColumn;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.input.MouseEvent;

public class HotelGUIController implements Initializable {

    // FXML Objects for HotelGUI.fxml

    @FXML
    private DatePicker fxDateIn;
    @FXML
    private DatePicker fxDateOut;
    @FXML
    private TextField fxNrOfGuests;
    @FXML
    private TextField fxLocation;
    @FXML
    private TableView<Hotel> fxHotelTable;
    @FXML
    private Button fxSearchButton;
    @FXML
    private javafx.scene.control.TableColumn<Hotel, Image> fxImageColumn;
    @FXML
    private javafx.scene.control.TableColumn<Hotel, Integer> fxIDColumn;
    @FXML
    private javafx.scene.control.TableColumn<Hotel, String> fxLocationColumn;
    @FXML
    private javafx.scene.control.TableColumn<Hotel, Integer> fxStarsColumn;
    @FXML
    private javafx.scene.control.TableColumn<Hotel, String> fxDescriptionColumn;
    @FXML
    private javafx.scene.control.TableColumn<Hotel, Double> fxRoomPriceColumn;
    @FXML
    private javafx.scene.control.TableColumn<Hotel, String> fxDFDowntownColumn;
    @FXML
    private javafx.scene.control.TableColumn<Hotel, String> fxDFSupermarketColumn;
    @FXML
    private javafx.scene.control.TableColumn<Hotel, String> fxRestaurantColumn;
    @FXML
    private javafx.scene.control.TableColumn<Hotel, Boolean> fxBreakfastColumn;
    @FXML
    private javafx.scene.control.TableColumn<Hotel, Boolean> fxBarColumn;
    @FXML
    private javafx.scene.control.TableColumn<Hotel, Boolean> fxWifiColumn;

    @FXML
    public void bookHandler() {
        Hotel hotel = fxHotelTable.getSelectionModel().getSelectedItem();

        LocalDate arraivalDate = fxDateIn.getValue();

        LocalDate departureDate = fxDateOut.getValue();

        String numberOfGuests = fxNrOfGuests.getText();





        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("BookingGUI.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();

        Scene scene = new Scene(root, 500, 300);
        stage.setScene(scene);

        HotelGUIController controller = loader.getController();

        controller.fxBookingArraivalDate.setValue(arraivalDate);
        controller.fxBookingDepartureDate.setValue(departureDate);
        controller.fxBookingNumberGuestsTextfield.setText(numberOfGuests);

        stage.showAndWait();


        String[] info = controller.getUserInfo();
        if (!(info[0].equals("") || info[1].equals(""))) {
            test.book(hotel, info[0], info[1],new SearchOptions(hotel.getName(), arraivalDate, departureDate,Integer.parseInt(numberOfGuests)));
        }
    }

    // FXML Objects for HotelViewGUI.fxml

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

    @FXML
    private Button fxBack;


    // FXML Object for Booking.fxml

    @FXML
    private TextField fxBookingTextfieldEmail;

    @FXML
    private TextField fxBookingTextfieldName;

    @FXML
    private Label fxBookingLabelWarningEmail;

    @FXML
    private Label fxBookingLabelWarningName;

    @FXML
    private Label fxBookingLabelEmail;

    @FXML
    private Label fxBookingLabelName;

    @FXML
    private Button fxBookingButton;

    @FXML
    private DatePicker fxBookingArraivalDate;

    @FXML
    private DatePicker fxBookingDepartureDate;

    @FXML
    private Button fxBookingBack;

    @FXML
    private TextField fxBookingNumberGuestsTextfield;

    @FXML
    void bookingBookHandler(ActionEvent event) {
        fxBookingTextfieldName.getScene().getWindow().hide();
    }

    @FXML
    void bookingBackHandler(ActionEvent actionEvent) {
        fxBookingTextfieldEmail.setText("");
        fxBookingTextfieldName.setText("");
        fxBookingTextfieldName.getScene().getWindow().hide();
    }


    HotelDB test = new HotelDB();




    ObservableList<Hotel> hotelObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fxImageColumn.setCellValueFactory(new PropertyValueFactory<>("image"));
        fxIDColumn.setCellValueFactory(new PropertyValueFactory<>("hotelID"));
        fxLocationColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        fxStarsColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfStars"));
        fxDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        fxRoomPriceColumn.setCellValueFactory(new PropertyValueFactory<>("startingRoomPrice"));
        fxDFDowntownColumn.setCellValueFactory(new PropertyValueFactory<>("distanceFromDowntown"));
        fxDFSupermarketColumn.setCellValueFactory(new PropertyValueFactory<>("distanceFromSupermarket"));

        fxHotelTable.setOnMouseClicked((MouseEvent event) -> {
            if(event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2){
                Stage stage = new Stage();

                Hotel hotel = fxHotelTable.getSelectionModel().getSelectedItem();



                FXMLLoader loader = new FXMLLoader(getClass().getResource("HotelViewGUI.fxml"));


                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                HotelGUIController controller = loader.getController();
                Parent root = loader.getRoot();

                controller.displayHotel(hotel);

                Scene scene = new Scene(root, 550, 500);
                stage.setScene(scene);
                stage.showAndWait();
            }
        });

    }





    public void searchHandler(ActionEvent actionEvent) throws SQLException {
        LocalDate dateIn = fxDateIn.getValue();
        LocalDate dateOut = fxDateOut.getValue();
        String location = fxLocation.getText();
        int nrOfGuests = Integer.parseInt(fxNrOfGuests.getText());
        List<Hotel> list = test.search(new SearchOptions(location, dateIn, dateOut, nrOfGuests));
        hotelObservableList.addAll(list);
        fxHotelTable.setItems(hotelObservableList);
    }



    public void backHandler(ActionEvent actionEvent) {
        fxHotelName.getScene().getWindow().hide();
    }

    public String[] getUserInfo() {
        String[] string = {fxBookingTextfieldEmail.getText(), fxBookingTextfieldName.getText() };
        return string;
    }

    public void displayHotel(Hotel hotel) {
        if (hotel.getName() != null) {
            fxHotelName.setText(hotel.getName());
        }
        if (hotel.getDescription() != null) {
            fxHotelReview.setText(hotel.getDescription());
        }
        if (hotel.getImage() != null) {
            fxHotelImage.setImage(hotel.getImage());
        }
        if (hotel.getNumberOfStars() >= 1 && hotel.getNumberOfStars() <= 5) {
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
        if (hotel.getRestaurant()) {
            fxRestaurant.setSelected(true);
        }
        fxRestaurant.setDisable(true);
        if (hotel.getBreakfastIncluded()) {
            fxBreakfast.setSelected(true);
        }
        fxBreakfast.setDisable(true);
        if (hotel.getBar()) {
            fxBar.setSelected(true);
        }
        fxBar.setDisable(true);
        if (hotel.getFreeWifi()) {
            fxFreeWifi.setSelected(true);
        }
        fxFreeWifi.setDisable(true);


        if (hotel.getDistanceFromDowntown() >= 0) {
            fxDownTown.setText("Distance from down town is " + hotel.getDistanceFromDowntown() + " Km");
        }
        if (hotel.getDistanceFromSupermarket() >= 0) {
            fxSuperMarket.setText("Distance from the supermarket is " + hotel.getDistanceFromSupermarket() + " Km");
        }
        if (hotel.getStartingRoomPrice() > 0) {
            fxStartingPrice.setText("The starting price for a room here is " + hotel.getStartingRoomPrice() + " ISK");
        }
    }

}
