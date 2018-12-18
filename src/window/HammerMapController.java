package window;

import entity.bullet.PeaBullet;
import entity.zombie.ConeheadZombie;
import entity.zombie.NewpaperZombie;
import entity.zombie.NormalZombie;
import entity.zombie.Zombie;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.AddUtil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class HammerMapController implements Initializable {
    @FXML private AnchorPane zombiePane;
    @FXML private AnchorPane hammerMapPane;
    @FXML private ImageView hammerMap;
    @FXML private ImageView hammer;
	@FXML private ImageView button;

	private Timer timer = new Timer();

	private ClickHandler singleClickHandler = new ClickHandler(1);
    private ClickHandler doubleClickHandler = new ClickHandler(2);
    private ClickHandler tripleClickHandler = new ClickHandler(3);
    private HammerCallback hammerCallBack = new HammerCallback();
    private AddUtil addUtil = new AddUtil(hammerCallBack);


    private List<Zombie> zombies = new ArrayList<>();

    private double x;
    private double y;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image mapImage = new Image(new File("@../../images/interface/background1unsodded.jpg").toURI().toString());
        Image hammerImage = new Image(new File("@../../images/mine/hammer_46_7.png").toURI().toString());
        Image hammerDownImage = new Image(new File("@../../images/mine/hammer_down_small.png").toURI().toString());
	    Image buttonImage = new Image(new File("@../../images/interface/Button.png").toURI().toString());
	    button.setImage(buttonImage);

        hammerMap.setImage(mapImage);
        hammer.setImage(hammerImage);
        hammer.setMouseTransparent(true);
        x = hammer.getLayoutX();
        y = hammer.getLayoutY();
        hammerMapPane.setCursor(Cursor.NONE);
        hammerMapPane.setOnMousePressed(event -> hammer.setImage(hammerDownImage));
        hammerMapPane.setOnMouseReleased(event -> hammer.setImage(hammerImage));
        addZombie();
        zombieMove();
    }

    @FXML
    private void exit() throws IOException {
    	AnchorPane menu = FXMLLoader.load(getClass().getResource("menu.fxml"));
    	hammerMapPane.getChildren().setAll(menu);
    	timer.cancel();
    }

    private class HammerCallback extends PrimaryCallBack {

        @Override
        public void addSun(Sun sun) {

        }

        @Override
        public void addSunNumber(int n) {

        }

        @Override
        public void addZombie(Zombie zombie) {
            zombies.add(zombie);
            ImageView zombieImageView = zombie.getImageView();
            if (zombie instanceof NormalZombie) zombieImageView.addEventHandler(MouseEvent.MOUSE_CLICKED, singleClickHandler);
            else if (zombie instanceof ConeheadZombie) zombieImageView.addEventHandler(MouseEvent.MOUSE_CLICKED, doubleClickHandler);
            else if (zombie instanceof NewpaperZombie) zombieImageView.addEventHandler(MouseEvent.MOUSE_CLICKED, tripleClickHandler);

            zombiePane.getChildren().add(zombieImageView);
        }

        @Override
        public void addPeaBullet(PeaBullet peaBullet) {

        }

        @Override
        public void decreaseZombie(int row) {

        }
    }

    @FXML
    private void mouseMoved() {
        hammerMapPane.setOnMouseMoved(event -> {
            hammer.setX(event.getSceneX() - x - 15);
            hammer.setY(event.getSceneY() - y - 20);
        });
    }

    private class ClickHandler implements EventHandler<MouseEvent>{
        int count;
        private ClickHandler(int count) {
            this.count = count;
        }

        @Override
        public void handle(MouseEvent event) {
            ImageView target = (ImageView) event.getSource();
            if (event.getClickCount() >= count) {
                target.setImage(null);
            }
        }
    }

    private void zombieMove() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    for (Zombie zombie : zombies) {
                        zombie.move(zombie.getVelocity());
                    }
                });
            }
        }, 0, 50);
    }

    private void addZombie() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> addUtil.addZombie());
            }
        }, 0, 2000);
    }


}
