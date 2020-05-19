package nastya;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.util.ArrayList;

public class NastyaApp extends Application {

    public ArrayList<RadioButton> options = new ArrayList<>();
    public ToggleGroup tg = new ToggleGroup();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent homeRoot = FXMLLoader.load(getClass().getResource("../resources/threats.fxml"));
        primaryStage.setTitle("My awesome application!");

        Scene home = new Scene(homeRoot);

        primaryStage.setScene(home);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
