package window;

import entity.bullet.PeaBullet;
import entity.zombie.NormalZombie;
import entity.zombie.Zombie;
import entity.bullet.Ball;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.AddUtil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class BallMap implements Initializable {
	@FXML private AnchorPane ballMap;
	@FXML private AnchorPane mapPane;
	@FXML private ImageView mapImageView;
	@FXML private ImageView ball;
	@FXML private ImageView sunSet;
	@FXML private ImageView button;
	@FXML private Label sunLabel;

	private Image ballImage;
	private boolean selected;
	private List<Zombie> zombies = new ArrayList<>();
	private List<Ball> balls = new ArrayList<>();
	private List<Sun> suns = new ArrayList<>();

	private BallCallback ballCallback = new BallCallback();
	private AddUtil addUtil = new AddUtil(ballCallback);
	private int sunNum;

	private Timer timer = new Timer();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Image mapImage = new Image(new File("@../../images/interface/background1.jpg").toURI().toString());
		mapImageView.setImage(mapImage);
		Image setImage = new Image(new File("@../../images/mine/sunset1.png").toURI().toString());
		Image buttonImage = new Image(new File("@../../images/interface/Button.png").toURI().toString());
		button.setImage(buttonImage);
		sunSet.setImage(setImage);
		ballImage = new Image((new File("@../../images/Plants/WallNut/HugeWallNutRoll.gif").toURI().toString()));
		Image staticImage = new Image((new File("@../../images/mine/nut.png").toURI().toString()));
		ball.setImage(staticImage);
		for (Node node : mapPane.getChildren()) {
			node.addEventHandler(MouseEvent.MOUSE_ENTERED, new EnterHandler());
			node.addEventHandler(MouseEvent.MOUSE_EXITED, new ExitHandler());
			node.addEventHandler(MouseEvent.MOUSE_CLICKED, new ClickHandler());
		}
		add();
		zombieMove();
		roll();

	}

	private void roll() {
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				Platform.runLater(() -> {
					int flag = -1;
					for (int i = 0; i < balls.size(); i++) {
						ImageView ball = balls.get(i).getImageView();
						ball.setLayoutX(ball.getLayoutX() + 5);
						if (ball.getLayoutX() > 850) {
							flag = i;
						}
					}
					if (flag != -1) {
						balls.get(flag).setImage(null);
						mapPane.getChildren().remove(balls.get(flag).getImageView());
						balls.remove(flag);
					}
				});
			}
		}, 0 ,50);
	}

	private class BallCallback extends PrimaryCallBack {

		@Override
		public void addSun(Sun sun) {
			suns.add(sun);
			ballMap.getChildren().add(sun.getSunImage());
		}

		@Override
		public void addSunNumber(int n) {
			sunNum = sunNum + 25;
			sunLabel.setText(String.valueOf(sunNum));
		}

		@Override
		public void addZombie(Zombie zombie) {
			zombies.add(zombie);
			ballMap.getChildren().add(zombie.getImageView());
		}

		@Override
		public void addPeaBullet(PeaBullet peaBullet) {

		}

		@Override
		public void decreaseZombie(int row) {

		}
	}

	private void zombieMove() {
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				Platform.runLater(() -> {
					for (Zombie zombie : zombies){
						zombie.move(zombie.getVelocity());
						for (Ball ball : balls) {
							ball.attack(zombie, ballCallback);
						}
					}
					for (Sun sun : suns) {
						sun.drop(ballCallback);
					}
				});
			}
		}, 0, 50);
	}

	private void add() {
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				Platform.runLater(() -> addUtil.addZombie());
			}
		}, 0, 8000);
		Random random = new Random();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				Platform.runLater(() -> addUtil.addSunFromSky(random));
			}
		}, 0, 8000);
	}

	@FXML
	private void exit() throws IOException {
		AnchorPane menu = FXMLLoader.load(getClass().getResource("menu.fxml"));
		ballMap.getChildren().setAll(menu);
		timer.cancel();
	}

	@FXML
	private void onSelected() {
		if (sunNum >= 50)
		selected = true;
	}

	@FXML
	private void showHand() {
		ballMap.setCursor(Cursor.HAND);
	}

	@FXML
	private void hideHand() {
		ballMap.setCursor(Cursor.DEFAULT);
	}

	private class EnterHandler implements EventHandler<Event> {
		@Override
		public void handle(Event event) {
			ImageView source = (ImageView) event.getSource();
			if (selected && source.getImage() == null) {
				source.setImage(ballImage);
				source.setOpacity(0.75);
			}

		}
	}

	private class ExitHandler implements EventHandler<Event> {

		@Override
		public void handle(Event event) {
			ImageView source = (ImageView) event.getSource();
			if (selected && source.getOpacity() != 1) {
				source.setImage(null);
			}
		}
	}

	private class ClickHandler implements EventHandler<Event> {

		@Override
		public void handle(Event event) {
			if (selected) {
				ImageView source = (ImageView) event.getSource();
				source.setImage(null);
				ImageView ballImageView = new ImageView(ballImage);
				double x = source.getLayoutX();
				double y = source.getLayoutY();
				ballImageView.setLayoutX(x);
				ballImageView.setLayoutY(y);
				ballImageView.setPreserveRatio(true);
				ballImageView.setFitHeight(85);
				Ball ball = new Ball(x, y, ballImageView);
				switch ( (int) source.getLayoutY()) {
					case 0: ball.setRow(0);
						break;
					case 105: ball.setRow(1);
						break;
					case 197: ball.setRow(2);
						break;
					case 304: ball.setRow(3);
						break;
					case 401: ball.setRow(4);
						break;
				}
				sunNum = sunNum - 50;
				sunLabel.setText(String.valueOf(sunNum));
				mapPane.getChildren().add(ballImageView);
				balls.add(ball);
				selected = false;
			}

		}
	}



}
