package window;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeController implements Initializable{
    @FXML
    private ImageView welcomeImageView;
    @FXML
    private AnchorPane welcomePane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image welcomeImage = new Image(new File("@../../images/interface/Logo.jpg").toURI().toString());
        welcomeImageView.setImage(welcomeImage);
    }

    @FXML
    private void enterMenu(ActionEvent event) throws IOException {
        AnchorPane menuPane = FXMLLoader.load(getClass().getResource("menu.fxml"));
        welcomePane.getChildren().setAll(menuPane);
    }

    @FXML
    private void showHand(){
        welcomePane.setCursor(Cursor.HAND);
    }

    @FXML
    private void hideHand(){
        welcomePane.setCursor(Cursor.DEFAULT);
    }

}
