package nastya.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import nastya.Threat;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ThreatsController  {
    @FXML
    VBox threatsView;

    @FXML
    Button nextBtn;

    public ArrayList<RadioButton> options = new ArrayList<>();
    public ToggleGroup tg = new ToggleGroup();
    private ArrayList<Threat> threatsList = new ArrayList<>();

    @FXML
    protected void initialize() {
        readThreats();

        for (int i = 0; i < this.threatsList.size(); i++) {
            RadioButton rb = new RadioButton();
            rb.setId(Integer.toString(i));
            rb.setText(threatsList.get(i).getName());
            rb.setPrefWidth(400);
            rb.setToggleGroup(tg);
            rb.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE );
            options.add(rb);
            threatsView.getChildren().add(rb);
        }
        threatsView.setSpacing(10);
        threatsView.setFillWidth(true);

    }

    public void onClickEvent(ActionEvent event) {
        System.out.println("Button click");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../../resources/reactions.fxml"));

        Parent reactionsRoot = null;
        try {
            reactionsRoot = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("threat.xml not found");
            return;
        }

        Scene reactionsScene = new Scene(reactionsRoot);

        СounteractionController сounteractionController = loader.getController();
        сounteractionController.initData(threatsList.get(Integer.parseInt(((RadioButton)tg.getSelectedToggle()).getId())));

        Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        mainStage.setScene(reactionsScene);
        mainStage.setMaximized(true);
        mainStage.show();
    }

    private void readThreats() {
        File inputFile = new File("src/resources/info.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("Threat");
            for (int i = 0; i < nList.getLength(); i++) {
                org.w3c.dom.Node threatXml = nList.item(i);

                if (threatXml.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    Element threatElem = (Element) threatXml;

                    Threat threat = new Threat();
                    threat.setId(threatElem.getAttribute("id"));
                    threat.setName(threatElem.getAttribute("name"));
                    System.out.println("Threat id: " + threatElem.getAttribute("id"));
                    System.out.println("Threat name : " + threatElem.getAttribute("name"));

                    ArrayList<String> reactions = new ArrayList<>();
                    NodeList reactionsXml = threatElem.getElementsByTagName("step");

                    for (int j = 0; j < reactionsXml.getLength(); j++) {
                        org.w3c.dom.Node reactionXml = reactionsXml.item(j);

                        if (threatXml.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                            Element reactionElem = (Element) reactionXml;

                            reactions.add(reactionElem.getTextContent());
                            System.out.println("Reaction " +  reactionElem.getAttribute("id") + ": " + reactionElem.getTextContent());
                        }
                    }
                    threat.setReactions(reactions);
                    this.threatsList.add(threat);
                }
            }

        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

}
