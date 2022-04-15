package hotelsearch.gui;

import hotelsearch.controller.HotelController;
import hotelsearch.model.Hotel;
import hotelsearch.model.HotelDB;
import hotelsearch.model.SearchOptions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HotelGUIController implements Initializable {
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
    private javafx.scene.control.TableColumn<Hotel, Boolean> fxFeaturedColumn;

    private List<Hotel> list;

    private boolean filterOn;

    HotelController controller = new HotelController(new HotelDB());

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
        fxRestaurantColumn.setCellValueFactory(new PropertyValueFactory<>("restaurant"));
        fxBreakfastColumn.setCellValueFactory(new PropertyValueFactory<>("breakfastIncluded"));
        fxBarColumn.setCellValueFactory(new PropertyValueFactory<>("bar"));
        fxWifiColumn.setCellValueFactory(new PropertyValueFactory<>("freeWifi"));
        fxFeaturedColumn.setCellValueFactory(new PropertyValueFactory<>("featured"));

        fxHotelTable.setOnMouseClicked((MouseEvent event) -> {
            if(event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2){
                //TODO - Láta þetta sýna viðeigandi Hotel Scene.
                System.out.println("Working");
            }
        });
    }

    public void searchHandler(ActionEvent actionEvent) throws SQLException {
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
