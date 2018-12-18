package window;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewController implements Initializable {

    @FXML
    private AnchorPane newPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AnchorPane normalMapPane = null;
        AnchorPane waterMapPane = null;
        ChooseMap chooseMap = new ChooseMap();
        try {
            switch (chooseMap.getMapNumber()) {
                case 1:
                case 2:
                    normalMapPane = FXMLLoader.load(getClass().getResource("normal_map.fxml"));
                break;
                case 3:
                case 4:
                    waterMapPane = FXMLLoader.load(getClass().getResource("water_map.fxml"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        newPane.getChildren().setAll(normalMapPane);
    }
}
