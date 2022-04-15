package hotelsearch.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HotelGUIMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HotelGUI.fxml"));
        Parent root = loader.load();
        HotelGUIController sc = loader.getController();
        primaryStage.setTitle("HotelSearch");
        Scene scene = new Scene(root, 1150, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
