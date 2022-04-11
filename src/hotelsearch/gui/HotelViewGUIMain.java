package hotelsearch.gui;

import hotelsearch.model.Hotel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HotelViewGUIMain extends Application {

    private Hotel hotel;

    public void showHotel(Hotel hotel) {
        this.hotel = hotel;
        launch();
    }

    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HotelViewGUI.fxml"));
        Parent root = loader.load();
        HotelViewGUIController sc = loader.getController();

        sc.displayHotel(hotel);

        stage.setTitle(hotel.getName());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

