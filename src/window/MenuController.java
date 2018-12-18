package window;

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

public class MenuController implements Initializable {
    @FXML private ImageView menuImageView;
    @FXML private ImageView startImageView;
    @FXML private ImageView challengeImageView;
    @FXML private ImageView normalMap;
    @FXML private ImageView startHammerMode;
    @FXML private ImageView selectView;
    @FXML private ImageView sunSet;
    @FXML private AnchorPane selectPane;
    @FXML
    private javafx.scene.layout.AnchorPane menuPane;
    @FXML
    private AnchorPane gamePane;
    @FXML
    private AnchorPane smallGamePane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image menuImage = new Image(new File("@../../images/interface/Surface.jpg").toURI().toString());
        menuImageView.setImage(menuImage);
        Image startImage = new Image(new File("@../../images/mine/StartAdventure.png").toURI().toString());
        startImageView.setImage(startImage);
        Image startHammerImage = new Image(new File("@../../images/mine/StartHammerMode.png").toURI().toString());
        startHammerMode.setImage(startHammerImage);
        challengeImageView.setVisible(false);
       gamePane.setVisible(false);
       smallGamePane.setVisible(false);

    }

    @FXML
    private void startGame() throws IOException {
        Image challengeImage = new Image(new File("@../../images/mine/Challenge.png").toURI().toString());
        challengeImageView.setImage(challengeImage);
        challengeImageView.setVisible(true);
        gamePane.setVisible(true);
    }

    @FXML
    private void startHammerMode() throws IOException {
        Image challengeImage = new Image(new File("@../../images/mine/ChallengeSmallGame.png").toURI().toString());
        challengeImageView.setImage(challengeImage);
        challengeImageView.setVisible(true);
        smallGamePane.setVisible(true);
    }

    @FXML
    private void exit(){
        System.exit(1);
    }

    @FXML
    private void onSelect(){

    }

    @FXML
    private void showHand(){
        menuPane.setCursor(Cursor.HAND);
    }

    @FXML
    private void hideHand(){
        menuPane.setCursor(Cursor.DEFAULT);
    }

    @FXML
    private void setChallenge_1() throws IOException {
        ChooseMap chooseMap = new ChooseMap();
        chooseMap.setMapNumber(1);
        AnchorPane normalMapPane = FXMLLoader.load(getClass().getResource("normal_map.fxml"));
        menuPane.getChildren().setAll(normalMapPane);
    }

    @FXML
    private void setChallenge_2() throws IOException {
        ChooseMap chooseMap = new ChooseMap();
        chooseMap.setMapNumber(2);
        AnchorPane normalMapPane = FXMLLoader.load(getClass().getResource("normal_map.fxml"));
        menuPane.getChildren().setAll(normalMapPane);
    }

    @FXML
    private void setChallenge_3() throws IOException {
        ChooseMap chooseMap = new ChooseMap();
        chooseMap.setMapNumber(3);
        AnchorPane waterMapPane = FXMLLoader.load(getClass().getResource("water_map.fxml"));
        menuPane.getChildren().setAll(waterMapPane);
    }

    @FXML
    private void setChallenge_4() throws IOException {
        ChooseMap chooseMap = new ChooseMap();
        chooseMap.setMapNumber(4);
        AnchorPane waterMapPane = FXMLLoader.load(getClass().getResource("water_map.fxml"));
        menuPane.getChildren().setAll(waterMapPane);
    }

    @FXML
    private void setSamllChallenge_1() throws IOException {
        javafx.scene.layout.AnchorPane hammerModeMapPane = FXMLLoader.load(getClass().getResource("hammer_map.fxml"));
        menuPane.getChildren().setAll(hammerModeMapPane);
    }

    @FXML
    private void setSamllChallenge_2() throws IOException {
        javafx.scene.layout.AnchorPane ballMapPane = FXMLLoader.load(getClass().getResource("ball_map.fxml"));
        menuPane.getChildren().setAll(ballMapPane);
    }

}
