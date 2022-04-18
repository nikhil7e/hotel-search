package hotelsearch.gui;

import hotelsearch.controller.HotelController;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HotelGUIController implements Initializable {

    // FXML Objects for HotelGUI.fxml

    @FXML
    private Button fxSearchButton;
    @FXML
    private DatePicker fxDateIn;
    @FXML
    private DatePicker fxDateOut;
    @FXML
    private TextField fxNrOfGuests;
    @FXML
    private TextField fxCity;
    @FXML
    private TextField fxName;
    @FXML
    private TableView<Hotel> fxHotelTable;
    @FXML
    private javafx.scene.control.TableColumn<Hotel, Image> fxImageColumn;
    @FXML
    private javafx.scene.control.TableColumn<Hotel, Integer> fxIDColumn;
    @FXML
    private javafx.scene.control.TableColumn<Hotel, String> fxNameColumn;
    @FXML
    private javafx.scene.control.TableColumn<Hotel, Integer> fxStarsColumn;
    @FXML
    private javafx.scene.control.TableColumn<Hotel, String> fxAddressColumn;
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
    private javafx.scene.control.TableColumn<Hotel, Boolean> fxFeaturedColumn;

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

    // FXML Object for Booking.fxml

    @FXML
    private TextField fxBookingTextfieldEmail;

    @FXML
    private TextField fxBookingTextfieldName;

    @FXML
    private DatePicker fxBookingArrivalDate;

    @FXML
    private DatePicker fxBookingDepartureDate;


    @FXML
    private TextField fxBookingNumberGuestsTextfield;

    HotelDB test = new HotelDB();

    private List<Hotel> list;

    HotelController controller = new HotelController(new HotelDB());

    ObservableList<Hotel> hotelObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (fxHotelTable != null) {
            fxImageColumn.setCellValueFactory(new PropertyValueFactory<>("imageURL"));
            fxIDColumn.setCellValueFactory(new PropertyValueFactory<>("hotelID"));
            fxNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            fxAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
            fxStarsColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfStars"));
            fxDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            fxRoomPriceColumn.setCellValueFactory(new PropertyValueFactory<>("startingRoomPrice"));
            fxDFDowntownColumn.setCellValueFactory(new PropertyValueFactory<>("distanceFromDowntown"));
            fxDFSupermarketColumn.setCellValueFactory(new PropertyValueFactory<>("distanceFromSupermarket"));
            fxRestaurantColumn.setCellValueFactory(new PropertyValueFactory<>("restaurant"));
            fxBreakfastColumn.setCellValueFactory(new PropertyValueFactory<>("breakfastIncluded"));
            fxBarColumn.setCellValueFactory(new PropertyValueFactory<>("bar"));
            fxWifiColumn.setCellValueFactory(new PropertyValueFactory<>("freeWifi"));
            fxFeaturedColumn.setCellValueFactory(new PropertyValueFactory<>("featured"));
        }
        if (fxHotelTable != null) {
            fxHotelTable.setOnMouseClicked((MouseEvent event) -> {
                if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
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
    }

    // Handlers for BookingGUI.fxml

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

    // Handler for HotelGUI.fxml
    @FXML
    public void bookHandler() {
        Hotel hotel = fxHotelTable.getSelectionModel().getSelectedItem();
        /*
        LocalDate arrivalDate = fxDateIn.getValue();
        LocalDate departureDate = fxDateOut.getValue();
        String numberOfGuests = fxNrOfGuests.getText();
         */
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
        /*
        controller.fxBookingArraivalDate.setValue(arrivalDate);
        controller.fxBookingDepartureDate.setValue(departureDate);
        controller.fxBookingNumberGuestsTextfield.setText(numberOfGuests);
         */

        stage.showAndWait();


        if (!(controller.fxBookingTextfieldEmail.getText().equals("") || controller.fxBookingTextfieldName.getText().equals(""))) {
            test.book(
                    hotel,
                    controller.fxBookingTextfieldEmail.getText(),
                    controller.fxBookingTextfieldName.getText(),
                    new SearchOptions(hotel.getAddress(),
                    hotel.getName(),
                    controller.fxBookingArrivalDate.getValue(),
                    controller.fxBookingDepartureDate.getValue(),
                    Integer.parseInt(controller.fxBookingNumberGuestsTextfield.getText()))
            );
        }
        fxSearchButton.fire();
    }

    // Handlers for HotelGUI
    @FXML
    public void searchHandler(ActionEvent actionEvent) {
        LocalDate dateIn = fxDateIn.getValue();
        LocalDate dateOut = fxDateOut.getValue();
        String city = fxCity.getText();
        String name = fxName.getText();
        int nrOfGuests = Integer.parseInt(fxNrOfGuests.getText());
        list = controller.search(new SearchOptions(city, name, dateIn, dateOut, nrOfGuests));
        hotelObservableList.clear();
        hotelObservableList.addAll(list);
        fxHotelTable.setItems(hotelObservableList);
    }

    // Handler for HotelViewGUI
    @FXML
    public void backHandler(ActionEvent actionEvent) {
        fxHotelName.getScene().getWindow().hide();
    }

    // General methods

    public void displayHotel(Hotel hotel) {
        if (hotel.getName() != null) {
            fxHotelName.setText(hotel.getName());
        }
        if (hotel.getDescription() != null) {
            fxHotelReview.setText(hotel.getDescription());
        }
        if (hotel.getImageURL() != null) {
            fxHotelImage.setImage(new Image(hotel.getImageURL()));
            System.out.println(hotel.getImageURL());
        }
        if (hotel.getNumberOfStars() >= 1 && hotel.getNumberOfStars() <= 5) {
            switch (hotel.getNumberOfStars()) {
                case 1:
                    fxStars.setImage(new Image("images/OneStar.png"));
                    break;
                case 2:
                    fxStars.setImage(new Image("images/TwoStars.png"));
                    break;
                case 3:
                    fxStars.setImage(new Image("images/ThreeStars.png"));
                    break;
                case 4:
                    fxStars.setImage(new Image("images/FourStars.png"));
                    break;
                case 5:
                    fxStars.setImage(new Image("images/FiveStars.png"));
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
            fxStartingPrice.setText("Starting room price: " + hotel.getStartingRoomPrice() + " ISK");
        }
    }

    public List<Hotel> orderByPriceAscending(List<Hotel> list) {
        list.sort(new Hotel.priceAscending());
        return list;
    }

    public List<Hotel> orderByPriceDescending(List<Hotel> list) {
        list.sort(new Hotel.priceDescending());
        return list;
    }

    public List<Hotel> orderByStarsDescending(List<Hotel> list) {
        list.sort(new Hotel.starsDescending());
        return list;
    }

    public List<Hotel> orderByStarsAscending(List<Hotel> list) {
        list.sort(new Hotel.starsAscending());
        return list;
    }

    public List<Hotel> orderByDistanceFromDowntown(List<Hotel> list) {
        list.sort(new Hotel.distanceFromDowntown());
        return list;
    }

    public List<Hotel> filterByStars(List<Hotel> list, int stars) {
        List<Hotel> newList = new ArrayList<>();

        for (Hotel hotel : list) {
            if (hotel.getNumberOfStars() == stars) {
                newList.add(hotel);
            }
        }
        return newList;
    }

    public List<Hotel> filterByPrice(List<Hotel> list, int minPrice, int maxPrice) {
        List<Hotel> newList = new ArrayList<>();

        for (Hotel hotel : list) {
            if (hotel.getStartingRoomPrice() <= maxPrice && hotel.getStartingRoomPrice() >= minPrice) {
                newList.add(hotel);
            }
        }
        return newList;
    }

    public List<Hotel> filterByRestaurant(List<Hotel> list) {
        List<Hotel> newList = new ArrayList<>();
        for (Hotel hotel : list) {
            if (hotel.getRestaurant()) {
                newList.add(hotel);
            }
        }
        return newList;
    }

    public List<Hotel> filterByBreakfast(List<Hotel> list) {
        List<Hotel> newList = new ArrayList<>();
        for (Hotel hotel : list) {
            if (hotel.getBreakfastIncluded()) {
                newList.add(hotel);
            }
        }
        return newList;
    }

    public List<Hotel> filterByBar(List<Hotel> list) {
        List<Hotel> newList = new ArrayList<>();
        for (Hotel hotel : list) {
            if (hotel.getBar()) {
                newList.add(hotel);
            }
        }
        return newList;
    }

    public List<Hotel> filterByWifi(List<Hotel> list) {
        List<Hotel> newList = new ArrayList<>();
        for (Hotel hotel : list) {
            if (hotel.getFreeWifi()) {
                newList.add(hotel);
            }
        }
        return newList;
    }

    public List<Hotel> filterByFeatured(List<Hotel> list) {
        List<Hotel> newList = new ArrayList<>();
        for (Hotel hotel : list) {
            if (hotel.getFeatured()) {
                newList.add(hotel);
            }
        }
        return newList;
    }

    public void fx3StarHandler(ActionEvent actionEvent) {
        List<Hotel> newList = filterByStars(list, 3);
        list = newList;
        hotelObservableList.clear();
        hotelObservableList.addAll(newList);
        fxHotelTable.setItems(hotelObservableList);
    }

    public void fx4StarHandler(ActionEvent actionEvent) {
        List<Hotel> newList = filterByStars(list, 4);
        list = newList;
        hotelObservableList.clear();
        hotelObservableList.addAll(newList);
        fxHotelTable.setItems(hotelObservableList);
    }

    public void fx5StarHandler(ActionEvent actionEvent) {
        List<Hotel> newList = filterByStars(list, 5);
        list = newList;
        hotelObservableList.clear();
        hotelObservableList.addAll(newList);
        fxHotelTable.setItems(hotelObservableList);
    }

    public void fx5kPriceHandler(ActionEvent actionEvent) {
        List<Hotel> newList = filterByPrice(list, 5000, 10000);
        list = newList;
        hotelObservableList.clear();
        hotelObservableList.addAll(newList);
        fxHotelTable.setItems(hotelObservableList);
    }

    public void fx10kPriceHandler(ActionEvent actionEvent) {
        List<Hotel> newList = filterByPrice(list, 10000, 20000);
        list = newList;
        hotelObservableList.clear();
        hotelObservableList.addAll(newList);
        fxHotelTable.setItems(hotelObservableList);
    }

    public void fx20kPriceHandler(ActionEvent actionEvent) {
        List<Hotel> newList = filterByPrice(list, 20000, Integer.MAX_VALUE);
        list = newList;
        hotelObservableList.clear();
        hotelObservableList.addAll(newList);
        fxHotelTable.setItems(hotelObservableList);
    }

    public void fxRestaurantHandler(ActionEvent actionEvent) {
        List<Hotel> newList = filterByRestaurant(list);
        list = newList;
        hotelObservableList.clear();
        hotelObservableList.addAll(newList);
        fxHotelTable.setItems(hotelObservableList);
    }

    public void fxBreakfastHandler(ActionEvent actionEvent) {
        List<Hotel> newList = filterByBreakfast(list);
        list = newList;
        hotelObservableList.clear();
        hotelObservableList.addAll(newList);
        fxHotelTable.setItems(hotelObservableList);
    }

    public void fxBarHandler(ActionEvent actionEvent) {
        List<Hotel> newList = filterByBar(list);
        list = newList;
        hotelObservableList.clear();
        hotelObservableList.addAll(newList);
        fxHotelTable.setItems(hotelObservableList);
    }

    public void fxWifiHandler(ActionEvent actionEvent) {
        List<Hotel> newList = filterByWifi(list);
        list = newList;
        hotelObservableList.clear();
        hotelObservableList.addAll(newList);
        fxHotelTable.setItems(hotelObservableList);
    }

    public void fxFeaturedHandler(ActionEvent actionEvent) {
        List<Hotel> newList = filterByFeatured(list);
        list = newList;
        hotelObservableList.clear();
        hotelObservableList.addAll(newList);
        fxHotelTable.setItems(hotelObservableList);
    }

}
