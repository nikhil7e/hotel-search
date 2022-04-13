package hotelsearch.gui;

import hotelsearch.model.Hotel;
import hotelsearch.model.HotelDB;
import hotelsearch.model.SearchOptions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
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
    }

    public void searchHandler(ActionEvent actionEvent) throws SQLException {
        LocalDate dateIn = fxDateIn.getValue();
        LocalDate dateOut = fxDateOut.getValue();
        String location = fxLocation.getText();
        int nrOfGuests = Integer.parseInt(fxNrOfGuests.getText());
        List<Hotel> list = test.search(new SearchOptions("Reykjavík", location, dateIn, dateOut, nrOfGuests));
        hotelObservableList.addAll(list);
        fxHotelTable.setItems(hotelObservableList);
    }
}
