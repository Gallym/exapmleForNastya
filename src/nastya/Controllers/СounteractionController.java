package nastya.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import nastya.Threat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class СounteractionController  {

    @FXML
    VBox layout;
    
    @FXML
    VBox options;

    @FXML
    Label label;

    @FXML
    Label recommendation;

    ArrayList<CheckBox> reactions = new ArrayList<>();

    public static final List<String> types = Collections.unmodifiableList(
            new ArrayList<String>() {{
                add("Административные");
                add("Организационные");
                add("Информационно-оперативные");
                add("Юридические");
                add("Социально-психологические");
                add("Охранные");
                add("IT-технологии безопасности");
            }});

    public void initData(Threat threat) {
        label.setText(threat.getName());

        for (int i = 0; i < threat.getReactions().size(); i++) {
            CheckBox option = new CheckBox();
            option.setId(threat.getReactions().get(i));
            option.setText(types.get(i));
            reactions.add(option);

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

    public void checkout(ActionEvent event) {
        Label headerLbl = new Label("ScrollPane");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < reactions.size(); i++) {
            if (reactions.get(i).isSelected()) {
                sb.append(types.get(i));
                sb.append('\n');
                sb.append(reactions.get(i).getId());
                sb.append("\n---------------------------------------------\n");
            }
        }
        recommendation.setText(sb.toString());

//        ScrollPane scrollPane = new ScrollPane(textLbl);
//        scrollPane.setPrefViewportHeight(150);
//        scrollPane.setPrefViewportWidth(200);

//        FlowPane root = new FlowPane(Orientation.VERTICAL, 10, 10, headerLbl, scrollPane);
//
//        layout.getChildren().add(root);


        Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        //mainStage.setScene(threatsScene);
        mainStage.setMaximized(true);
        mainStage.show();
    }
}
