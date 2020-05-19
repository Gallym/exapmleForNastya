package nastya.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import nastya.Threat;

import java.io.IOException;


public class СounteractionController  {

    @FXML
    VBox layout;
    
    @FXML
    VBox options;

    @FXML
    Label label;

    @FXML
    String chosenThreat;

    public void initData(Threat threat) {
        label.setText(threat.getName());

        for (int i = 0; i < threat.getReactions().size(); i++) {
            CheckBox option = new CheckBox();
            option.setId(Integer.toString(i));
            option.setText(threat.getReactions().get(i));

            options.getChildren().add(option);
        }
    }

    @FXML
    protected void initialize() {
        options.setSpacing(15);
        layout.setSpacing(20);
    }

    public void goBack(ActionEvent event) {
        Parent threatsRoot = null;
        try {
            threatsRoot = FXMLLoader.load(getClass().getResource("../../resources/threats.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("threat.ml not found");
            return;
        }

        Scene threatsScene = new Scene(threatsRoot);

        Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        mainStage.setScene(threatsScene);
        mainStage.setMaximized(true);
        mainStage.show();
    }

}
