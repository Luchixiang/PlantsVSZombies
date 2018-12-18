package window;

import entity.bullet.Bullet;
import entity.bullet.PeaBullet;
import entity.plants.*;
import entity.plants.Plant;
import entity.zombie.Zombie;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.AddUtil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import static java.lang.Thread.sleep;

public class WaterController implements Initializable {
    public ImageView sunSet;
    @FXML
    private AnchorPane waterMapPane;
    @FXML
    private AnchorPane mapPane;
    @FXML
    private ImageView NormalMap;
    @FXML
    private ImageView Zombie;
    @FXML
    private ImageView test;
    @FXML
    private ImageView test1;
    @FXML
    private ImageView test2;
    @FXML
    private ImageView test3;
    @FXML
    private ImageView test4;
    @FXML
    private ImageView test5;
    @FXML
    private ImageView test6;
    @FXML
    private ImageView test7;
    @FXML
    private ImageView plant;
    @FXML
    private ImageView wantedPlantImageView;
    @FXML
    private ImageView diedImageView;
    @FXML
    private ImageView checkBoxView;
    @FXML
    private Button playAgainButton;
    @FXML
    private Button backMenuButton;
    @FXML
    private Button anyButton;
    @FXML
    private ImageView mouseShovel;
    @FXML
    private ImageView shovelView;
    @FXML
    private ImageView gameWinner;
    @FXML
    private ImageView selectView;
    @FXML
    private AnchorPane selectPane;
    @FXML
    private ImageView backView;

    private AddUtil addUtil;
    private Image ZombieWalkImage;
    private Plant wantedPlant;
    private Image wantedPlantImage;
    private Image sunFlowerImage;
    private Image sunShroomImage;
    private Image onePeaShooterImage;
    private Image wallNutImage;
    private Image potatoMineImage;
    private Image lilyPadImage;
    private Image twoPeaShooterImage;
    private Image eatFlowerImage;
    private Image tallNut;
    private Image diedImage;
    private Image checkBoxImage;
    private int zombieNumber = 0;
    private Image shovelImage;
    private Image grayImag;
    private volatile List<Zombie> zombies = new ArrayList<>();
    private volatile List<Sun> sunes = new ArrayList<>();
    private volatile List<Plant> allPlants = new ArrayList<>();
    private volatile List<Bullet> allBullets = new ArrayList<>();
    private List<ImageView> grayImageviews = new ArrayList<>();
    private List<String> plantList = new ArrayList<>();
    private List<Integer> plantCd = new ArrayList<>();
    private List<ImageView> tests = new ArrayList<>();
    private int theNumberOfSun = 0;
    private int subSun = 0;
    private WaterCallBack waterCallBack;
    private volatile int[] isHaveZombie = {0, 0, 0, 0, 0, 0};
    private boolean gameover;
    private boolean isShovel;
    private int mapNumber;
    private int testNumber = 0;
    private Image sunImg;
    @FXML
    private Label sunNumber;

    private boolean cardSelected = false;
    private double cardX;
    private double cardY;
    private Timer timer = new Timer();
    private ChooseMap chooseMap = new ChooseMap();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectPane.setVisible(true);
        Image selectPlants = new Image(new File("@../../images/mine/selectPlants.png").toURI().toString());
        selectView.setImage(selectPlants);
        waterCallBack = new WaterCallBack();
        addUtil = new AddUtil(waterCallBack);
        switch (chooseMap.getMapNumber()) {
            case 3:
                setMapWater();
                break;
            case 4:
                setMapWaterNight();
                break;
        }

        sunImg = new Image(new File("@../../images/mine/mysun.gif").toURI().toString());
        Image ZombieImage = new Image(new File("@../../images/Zombies/Zombie/0.gif").toURI().toString());
        Zombie.setImage(ZombieImage);
        Image sunsetImag = new Image(new File("@../../images/mine/SeedBank.png").toURI().toString());
        sunSet.setImage(sunsetImag);
        grayImag = new Image(new File("@../../images/mine/Gray.png").toURI().toString());
        ZombieWalkImage = new Image(new File("@../../images/Zombies/Zombie/Zombie2.gif").toURI().toString());
        diedImage = new Image(new File("@../../images/mine/ZombiesWon.png").toURI().toString());
        shovelImage = new Image(new File("@../../images/mine/Shovel.png").toURI().toString());
        shovelView.setImage(shovelImage);
        mouseShovel.setImage(shovelImage);
        Image gameWinnerImage = new Image(new File("@../../images/mine/Sunflower_trophy8.png").toURI().toString());
        gameWinner.setImage(gameWinnerImage);
        backView.setImage(new Image(new File("@../../images/mine/back.png").toURI().toString()));


        tests.add(test);
        tests.add(test1);
        tests.add(test2);
        tests.add(test3);
        tests.add(test4);
        tests.add(test5);
        tests.add(test6);
        tests.add(test7);

        anyButton.setVisible(false);
        checkBoxView.setVisible(false);
        playAgainButton.setVisible(false);
        backMenuButton.setVisible(false);
        diedImageView.setVisible(false);
        mouseShovel.setVisible(false);
        gameWinner.setVisible(false);

        theNumberOfSun = 500;
        sunNumber.setText(String.valueOf(theNumberOfSun));

        gameover = false;
        isShovel = false;


        for (Node node : mapPane.getChildren()) {

            node.addEventHandler(MouseEvent.MOUSE_ENTERED, new EnterHandler());
            node.addEventHandler(MouseEvent.MOUSE_EXITED, new ExitHandler());
            node.addEventHandler(MouseEvent.MOUSE_CLICKED, new ClickHandler());
        }
    }

    @FXML
    private void set1_1() {
        if (testNumber < 8) {
            onePeaShooterImage = new Image(new File("@../../images/mine/OnePeashooter.jpg").toURI().toString());
            tests.get(testNumber).setImage(onePeaShooterImage);
            testNumber = testNumber + 1;
            plantList.add("onePeaShooter");
        }
    }

    @FXML
    private void set1_2() {
        if (testNumber < 8) {
            sunFlowerImage = new Image(new File("@../../images/mine/SunFlower.jpg").toURI().toString());
            tests.get(testNumber).setImage(sunFlowerImage);
            testNumber = testNumber + 1;
            plantList.add("sunFlower");
        }
    }

    @FXML
    private void set1_3() {
        if (testNumber < 8) {
            lilyPadImage = new Image(new File("@../../images/mine/lilyPad.png").toURI().toString());
            tests.get(testNumber).setImage(lilyPadImage);
            testNumber = testNumber + 1;
            plantList.add("lilyPad");
        }
    }

    @FXML
    private void set1_4() {
        if (testNumber < 8) {
            wallNutImage = new Image(new File("@../../images/mine/nut.png").toURI().toString());
            tests.get(testNumber).setImage(wallNutImage);
            testNumber = testNumber + 1;
            plantList.add("wallNut");
        }
    }

    @FXML
    private void set1_5() {
        if (testNumber < 8) {
            potatoMineImage = new Image(new File("@../../images/mine/potato.png").toURI().toString());
            tests.get(testNumber).setImage(potatoMineImage);
            testNumber = testNumber + 1;
            plantList.add("potatoMine");
        }
    }

    @FXML
    private void set1_6() {
        if (testNumber < 8) {
            twoPeaShooterImage = new Image(new File("@../../images/mine/twoPeaShooter.jpg").toURI().toString());
            tests.get(testNumber).setImage(twoPeaShooterImage);
            testNumber = testNumber + 1;
            plantList.add("twoPeaShooter");
        }
    }

    @FXML
    private void set1_7() {
        if (testNumber < 8) {
            eatFlowerImage = new Image(new File("@../../images/mine/eatFlower.jpg").toURI().toString());
            tests.get(testNumber).setImage(eatFlowerImage);
            testNumber = testNumber + 1;
            plantList.add("eatFlower");
        }
    }

    @FXML
    private void set1_8() {
        if (testNumber < 8) {
            tallNut = new Image(new File("@../../images/mine/tallNut.jpg").toURI().toString());
            tests.get(testNumber).setImage(tallNut);
            testNumber = testNumber + 1;
            plantList.add("tallNut");
        }
    }

    @FXML
    private void set2_1() {
        if (testNumber < 8) {
            sunShroomImage = new Image(new File("@../../images/mine/SunMushroom.png").toURI().toString());
            tests.get(testNumber).setImage(sunShroomImage);
            testNumber = testNumber + 1;
            plantList.add("sunShroom");
        }
    }

    @FXML
    private void onStart() {
        if (plantList.size() > 0) {
            selectPane.setVisible(false);
            for (int i = 0; i < plantList.size(); i++) {
                plantCd.add(0);
            }
            if (chooseMap.getMapNumber() == 3) {
                addSun();
            }
            cardX = plant.getLayoutX();
            cardY = plant.getLayoutY();
            zombieMove();
            addZombie();
            dropSun();
        }
    }


    private void setMapWater() {
        Image normalMapImage = new Image(new File("@../../images/interface/background3.jpg").toURI().toString());
        NormalMap.setImage(normalMapImage);

    }

    private void setMapWaterNight() {
        Image normalMapImage = new Image(new File("@../../images/interface/background4.jpg").toURI().toString());
        NormalMap.setImage(normalMapImage);
    }

    @FXML
    private void showHand() {
        waterMapPane.setCursor(Cursor.HAND);
    }

    @FXML
    private void hideHand() {
        waterMapPane.setCursor(Cursor.DEFAULT);
    }

    @FXML
    private void onCardSelect() {
        cardSelected = true;
    }

    @FXML
    private void mouseMoved() {
        for (Node node : waterMapPane.getChildren()) {
            node.setOnMouseMoved(event -> {
                if (cardSelected) {
                    plant.setOpacity(1);
                    plant.toFront();
                    plant.setX(event.getSceneX() - cardX + 30);
                    plant.setY(event.getSceneY() - cardY - 30);
                }
            });
        }
    }

    private class EnterHandler implements EventHandler<Event> {
        @Override
        public void handle(Event event) {
            ImageView source = (ImageView) event.getSource();
            if (cardSelected && source.getImage() == null && wantedPlantImage != null) {
                source.setImage(wantedPlantImage);
                source.setFitHeight(wantedPlantImage.getHeight());
                source.setFitWidth(wantedPlantImage.getWidth());
                if (wantedPlant instanceof TallNut || wantedPlant instanceof EatFlower) {
                    source.setY(source.getY() - 20);
                }
                source.setOpacity(0.75);
            }
        }
    }

    private class ExitHandler implements EventHandler<Event> {
        @Override
        public void handle(Event event) {
            ImageView source = (ImageView) event.getSource();
            if (cardSelected && source.getOpacity() != 1) {
                source.setImage(null);
            }
        }
    }

    private class ClickHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            if (!gameover) {
                if (cardSelected && wantedPlant != null) {
                    if (event.getSceneY() > 100 && (!(wantedPlant instanceof LilyPad)
                            || (wantedPlant instanceof LilyPad && event.getSceneY() >= 287 && event.getSceneY() <= 452))) {
                        ImageView source = (ImageView) event.getSource();
                        if (event.getButton() == MouseButton.PRIMARY) {
                            if (wantedPlant instanceof SunShroom && chooseMap.getMapNumber() == 3) {
                                ((SunShroom) wantedPlant).setSleep(true);
                            }
                            if (wantedPlant instanceof SunFlower && chooseMap.getMapNumber() == 4) {
                                ((SunFlower) wantedPlant).setSleep(true);
                            }
                            source.setOpacity(1);
                            source.toFront();
                            wantedPlant.setImageView(source);
                            wantedPlant.getImageView().setImage(wantedPlantImageView.getImage());
                            wantedPlant.setX((int) source.getLayoutX());
                            wantedPlant.setY((int) source.getLayoutY());

                            if (wantedPlant instanceof LilyPad) {
                                ((LilyPad) wantedPlant).setPlanted(true);
                                source.toBack();
                            }

                            theNumberOfSun = theNumberOfSun - subSun;
                            sunNumber.setText(String.valueOf(theNumberOfSun));
                            wantedPlant.setRow((int) wantedPlant.getY() / 100);
                            allPlants.add(wantedPlant);
                            if (wantedPlant.getName().equals("potatoMine")) {
                                appearPotatoMine((PotatoMine) wantedPlant);
                            }

                        } else {
                            source.setOpacity(0);
                        }
                        plant.setOpacity(0);
                        cardSelected = false;
                        wantedPlant = null;
                        wantedPlantImage = null;
                        wantedPlantImageView.setVisible(false);
                        wantedPlantImageView.setImage(null);
                        plant.setImage(null);
                        subSun = 0;
                    }
                } else if (event.getSceneY() < 120 && event.getSceneY() < 100) {
                    double x = event.getSceneX();
                    int plantNumber = (int) (x - 75) / 50;
                    if (plantNumber < 8) {
                        switch (plantList.get(plantNumber)) {
                            case "lilyPad": {
                                if (theNumberOfSun >= 25) {
                                    subSun = 25;
                                    plant.setImage(new Image(new File("@../../images/Plants/LilyPad/0.gif").toURI().toString()));
                                    plant.toFront();
                                    Image lilyPadGif = new Image(new File("@../../images/Plants/LilyPad/LilyPad.gif").toURI().toString());
                                    wantedPlantImageView.setImage(lilyPadGif);
                                    wantedPlantImageView.setVisible(true);
                                    wantedPlantImage = lilyPadGif;
                                    wantedPlant = new LilyPad(event.getSceneX(), event.getSceneY(), "lilyPad");
                                    beGray(tests.get(plantNumber));
                                }
                            }
                            break;
                            case "sunFlower": {
                                if (theNumberOfSun >= 50) {
                                    subSun = 50;
                                    plant.setImage(new Image(new File("@../../images/Plants/SunFlower/0.gif").toURI().toString()));
                                    plant.toFront();
                                    Image sunFlowerGif = new Image(new File("@../../images/Plants/SunFlower/SunFlower1.gif").toURI().toString());
                                    wantedPlantImageView.setImage(sunFlowerGif);
                                    wantedPlantImageView.setVisible(true);
                                    wantedPlantImage = sunFlowerGif;
                                    wantedPlant = new SunFlower(event.getSceneX(), event.getSceneY(), "sunFlower");
                                    beGray(tests.get(plantNumber));
                                }
                            }
                            break;
                            case "onePeaShooter": {
                                if (theNumberOfSun >= 100) {
                                    subSun = 100;
                                    plant.setImage(new Image(new File("@../../images/Plants/Peashooter/0.gif").toURI().toString()));
                                    plant.toFront();
                                    Image onePeashooterGif = new Image(new File("@../../images/mine/Plants/Peashooter/Peashooter.gif").toURI().toString());
                                    wantedPlantImageView.setImage(onePeashooterGif);
                                    wantedPlantImageView.setVisible(true);
                                    wantedPlantImage = onePeashooterGif;
                                    wantedPlant = new OnePeaShooter(event.getX(), event.getY(), "onePeaShooter");
                                    beGray(tests.get(plantNumber));
                                }
                            }
                            break;
                            case "wallNut": {
                                if (theNumberOfSun >= 50) {
                                    subSun = 50;
                                    plant.setImage(new Image(new File("@../../images/Plants/WallNut/0.gif").toURI().toString()));
                                    plant.toFront();
                                    Image wallNutGif = new Image(new File("@../../images/mine/Plants/WallNut/WallNut.gif").toURI().toString());
                                    wantedPlantImageView.setImage(wallNutGif);
                                    wantedPlantImageView.setVisible(true);
                                    wantedPlantImage = wallNutGif;
                                    wantedPlant = new WallNut(event.getX(), event.getY(), "wallNut");
                                    beGray(tests.get(plantNumber));
                                }
                            }
                            break;
                            case "potatoMine": {
                                if (theNumberOfSun >= 25) {
                                    subSun = 25;
                                    plant.setImage(new Image(new File("@../../images/Plants/PotatoMine/0.gif").toURI().toString()));
                                    plant.toFront();
                                    Image potatoMineGif = new Image(new File("@../../images/mine/Plants/PotatoMine/PotatoMineNotReady.gif").toURI().toString());
                                    wantedPlantImageView.setImage(potatoMineGif);
                                    wantedPlantImageView.setVisible(true);
                                    wantedPlantImage = potatoMineGif;
                                    wantedPlant = new PotatoMine(event.getX(), event.getY(), "potatoMine");
                                    beGray(tests.get(plantNumber));
                                }
                            }
                            break;
                            case "twoPeaShooter": {
                                if (theNumberOfSun >= 200) {
                                    subSun = 200;
                                    plant.setImage(new Image(new File("@../../images/mine/Plants/Repeater/0.gif").toURI().toString()));
                                    plant.toFront();
                                    Image twoPeaShooterGif = new Image(new File("@../../images/mine/Plants/Repeater/Repeater.gif").toURI().toString());
                                    wantedPlantImageView.setImage(twoPeaShooterGif);
                                    wantedPlantImageView.setVisible(true);
                                    wantedPlantImage = twoPeaShooterGif;
                                    wantedPlant = new TwoPeaShooter(event.getSceneX(), event.getSceneY(), "twoPeaShooter");
                                    beGray(tests.get(plantNumber));
                                }
                            }
                            break;
                            case "eatFlower": {
                                if (theNumberOfSun >= 150) {
                                    subSun = 150;
                                    plant.setImage(new Image(new File("@../../images/mine/Plants/Chomper/0.gif").toURI().toString()));
                                    plant.toFront();
                                    Image eatFlowerGif = new Image(new File("@../../images/mine/Plants/Chomper/Chomper.gif").toURI().toString());
                                    wantedPlantImageView.setImage(eatFlowerGif);
                                    wantedPlantImageView.setVisible(true);
                                    wantedPlantImage = eatFlowerGif;
                                    wantedPlant = new EatFlower(event.getSceneX(), event.getSceneY(), "eatFlower");
                                    beGray(tests.get(plantNumber));
                                }
                            }
                            break;
                            case "tallNut": {
                                if (theNumberOfSun >= 50) {
                                    subSun = 50;
                                    plant.setImage(new Image(new File("@../../images/Plants/TallNut/0.gif").toURI().toString()));
                                    plant.toFront();
                                    Image tallNutGif = new Image(new File("@../../images/mine/Plants/TallNut/TallNut.gif").toURI().toString());
                                    wantedPlantImageView.setImage(tallNutGif);
                                    wantedPlantImageView.setVisible(true);
                                    wantedPlantImage = tallNutGif;
                                    wantedPlant = new TallNut(event.getX(), event.getY(), "tallNut");
                                    beGray(tests.get(plantNumber));
                                }
                            }
                            break;
                            case "sunShroom": {
                                subSun = 0;
                                plant.setImage(new Image(new File("@../../images/Plants/SunShroom/0.gif").toURI().toString()));
                                plant.toFront();
                                Image sunShroomGif;
                                if (chooseMap.getMapNumber() == 3) {
                                    sunShroomGif = new Image(new File("@../../images/mine/Plants/SunShroom/SunShroomSleep.gif").toURI().toString());
                                } else
                                    sunShroomGif = new Image(new File("@../../images/Plants/SunShroom/SunShroom2.gif").toURI().toString());
                                wantedPlantImageView.setImage(sunShroomGif);
                                wantedPlantImageView.setVisible(true);
                                wantedPlantImage = sunShroomGif;
                                wantedPlant = new SunShroom(event.getSceneX(), event.getSceneY(), "sunShroom");
                                beGray(tests.get(plantNumber));
                            }
                            break;
                        }
                    }
                    cardSelected = true;
                    if (event.getX() < 500) {
                        plantCd.set(plantNumber, 350);
                    }
                } else if (isShovel) {
                    isShovel = false;
                    mouseShovel.setVisible(false);
                    ImageView source = (ImageView) event.getSource();
                    allPlants.remove(source);
                    source.setImage(null);
                }
            }
        }
    }

    //僵尸移动
    private void zombieMove() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (!gameover) {
                        for (entity.zombie.Zombie zombie : zombies) {
                            if (zombie.getHealth() > 0) {
                                ImageView ZombieWalk = zombie.getImageView();
                                if (allPlants.size() != 0) {
                                    //僵尸吃植物
                                    for (Plant plant : allPlants) {
                                        if (plant.getImageView().getImage() != null) {
                                            if (plant.getHealth() > 0) {
                                                zombie.attack(plant, waterCallBack);
                                            }
                                            if (plant.getName().equals("wallNut")) {
                                                if (plant.getHealth() == 8) {
                                                    plant.getImageView().setImage(new Image(new File("@../../images/mine/Plants/WallNut/Wallnut_cracked1.gif").toURI().toString()));
                                                }
                                                if (plant.getHealth() == 4) {
                                                    plant.getImageView().setImage(new Image(new File("@../../images/mine/Plants/WallNut/Wallnut_cracked2.gif").toURI().toString()));
                                                }
                                            } else if (plant.getName().equals("tallNut")) {
                                                if (plant.getHealth() == 12) {
                                                    plant.getImageView().setImage(new Image(new File("@../../images/mine/Plants/TallNut/TallnutCracked1.gif").toURI().toString()));
                                                }
                                                if (plant.getHealth() == 6) {
                                                    plant.getImageView().setImage(new Image(new File("@../../images/mine/Plants/TallNut/WTallnutCracked2.gif").toURI().toString()));
                                                }
                                            }
                                        } else plant.setHealth(0);
                                    }
                                    //植物消失
                                    for (Plant plant : allPlants) {
                                        if (plant.getHealth() <= 0)
                                            disappearPlant(plant);
                                        if (allPlants.size() <= 0)
                                            break;
                                    }
                                }

                                //僵尸移动
                                if (zombie.isToWalk()) {
                                    zombie.move(0.45);
                                }

                                //判断是否有子弹打到
                                for (Bullet bullet : allBullets) {
                                    bullet.attack(zombie, waterCallBack);
                                }

                                //游戏结束
                                if (ZombieWalk.getLayoutX() < -40) {
                                    diedImageView.toFront();
                                    diedImageView.setVisible(true);
                                    diedImageView.setImage(diedImage);
                                    gameover = true;
                                }
                            }
                        }
                        for (int i = 0; i < plantCd.size(); i++) {
                            if (plantCd.get(i) > 0) {
                                plantCd.set(i, plantCd.get(i) - 1);
                            }
                        }
                        for (ImageView imageView : grayImageviews) {
                            imageView.setLayoutY(imageView.getLayoutY() - 0.2);
                            if (imageView.getLayoutY() <= -68.5) {
                                imageView.setVisible(false);
                                waterMapPane.getChildren().remove(imageView);
                            }
                        }
                    } else {
                        timer.cancel();
                        anyButton.setVisible(true);
                        anyButton.toFront();
                    }

                    for (Bullet bullet : allBullets) {
                        bullet.move(9);
                    }
                });
            }
        }, 0, 30);
    }

    //刷僵尸
    private void addZombie() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    for (int i = 0; i < zombieNumber / 10 + 1; i++) {
                        addUtil.addZombie();
                    }
                });
            }
        }, 25000, 20000);
    }

    //阳光的掉落
    private void dropSun() {
        ImageView imageView = new ImageView(sunImg);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (!gameover) {
                        for (Sun sun : sunes) {
                            sun.drop(waterCallBack);
                        }
                        for (Plant plant : allPlants) {
                            //是向日葵的话就产生阳关
                            if (plant instanceof SunFlower && !(((SunFlower) plant).isSleep())) {
                                ((SunFlower) plant).setClientTime(((SunFlower) plant).getClientTime() + 1);
                                if (((SunFlower) plant).getClientTime() == 200) {
                                    addUtil.addSunFromFlower((SunFlower) plant);
                                }
                                //射手产生子弹
                            } else if (plant instanceof TwoPeaShooter) {
                                addBullets((TwoPeaShooter) plant);
                                addBullets((TwoPeaShooter) plant);
                                //食人花减CD
                            } else if (plant instanceof EatFlower && ((EatFlower) plant).getEatCd() > 0) {
                                ((EatFlower) plant).setEatCd(((EatFlower) plant).getEatCd() - 1);
                                if (((EatFlower) plant).getEatCd() == 0) {
                                    ((EatFlower) plant).setToeat();
                                }
                            } else if (plant instanceof OnePeaShooter) {
                                addBullets((OnePeaShooter) plant);
                            } else if (plant instanceof SunShroom) {
                                if (!((SunShroom) plant).isSleep()) {
                                    ((SunShroom) plant).setClientTime(((SunShroom) plant).getClientTime() + 1);
                                    if (((SunShroom) plant).getClientTime() == 200) {
                                        ((SunShroom) plant).setSmallSunTime(((SunShroom) plant).getSmallSunTime() + 1);
                                        addUtil.addSunFromShroom((SunShroom) plant);
                                    }
                                    if (((SunShroom) plant).getSmallSunTime() == 3) {
                                        plant.getImageView().setImage(new Image(new File("@../../images/Plants/SunShroom/SunShroom.gif").toURI().toString()));
                                        ((SunShroom) plant).setSmallSunTime(((SunShroom) plant).getSmallSunTime() + 1);
                                    }
                                }
                            }
                        }
                    } else timer.cancel();
                });
            }
        }, 0, 75);
    }

    //产生子弹
    private void addBullets(OnePeaShooter shooter) {
        if (shooter.getHealth() > 0) {
            int Y = (int) shooter.getY();
            int mul = Y / 100;
            if (isHaveZombie[mul] != 0) {
                if (shooter.getSleepTime() == 40) {
                    addUtil.addBullet(shooter);
                }
                shooter.setSleepTime(shooter.getSleepTime() + 1);
            }
        }
    }

    //随机刷阳光
    private void addSun() {
        Random random = new Random();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (!gameover) {
                        addUtil.addSunFromSky(random);
                    }
                });
            }
        }, 0, 15000);
    }

    //土豆出现
    private void appearPotatoMine(PotatoMine potatoMine) {
        new Thread(() -> {
            try {
                sleep(10000);
                if (potatoMine.getHealth() > 0) {
                    potatoMine.getImageView().setImage(new Image(new File("@../../images/mine/Plants/PotatoMine/PotatoMine.gif").toURI().toString()));
                    potatoMine.setAppear(true);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    //植物消失
    private void disappearPlant(Plant plant) {
        plant.getImageView().setImage(null);
        allPlants.remove(plant);
    }

    @FXML
    private void displayOptions() {
        diedImageView.setVisible(false);
        checkBoxImage = new Image(new File("@../../images/mine/Challenge_Background.png").toURI().toString());
        checkBoxView.setImage(checkBoxImage);
        checkBoxView.toFront();
        checkBoxView.setVisible(true);
        playAgainButton.toFront();
        playAgainButton.setVisible(true);
        backMenuButton.toFront();
        backMenuButton.setVisible(true);
    }

    @FXML
    private void playAgain() throws IOException {
        AnchorPane newPane = FXMLLoader.load(getClass().getResource("new.fxml"));
        waterMapPane.getChildren().setAll(newPane);
    }

    @FXML
    private void backMenu() throws IOException {
        AnchorPane menuPane = FXMLLoader.load(getClass().getResource("menu.fxml"));
        waterMapPane.getChildren().setAll(menuPane);
        timer.cancel();
    }

    //回调内部类
    public class WaterCallBack extends PrimaryCallBack {
        public void addSun(Sun sun) {
            sunes.add(sun);
            waterMapPane.getChildren().add(sun.getSunImage());
        }

        public void addSunNumber(int n) {
            if (n == 1)
                theNumberOfSun = theNumberOfSun + 25;
            else theNumberOfSun = theNumberOfSun + 15;
            sunNumber.setText(String.valueOf(theNumberOfSun));
        }

        public void addZombie(Zombie zombie) {
            isHaveZombie[zombie.getRow()] = isHaveZombie[zombie.getRow()] + 1;
            zombies.add(zombie);
            waterMapPane.getChildren().add(zombie.getImageView());
            zombieNumber += 1;
        }

        public void addPeaBullet(PeaBullet peaBullet) {
            allBullets.add(peaBullet);
            waterMapPane.getChildren().add(peaBullet.getImageView());
        }

        public void decreaseZombie(int row) {
            if (isHaveZombie[row] > 0)
                isHaveZombie[row] = isHaveZombie[row] - 1;
        }
    }

    @FXML
    private void shovelMouseMoved() {
        mouseShovel.setVisible(true);
        mouseShovel.toBack();
        isShovel = true;
        mouseShovel.setImage(shovelImage);
        waterMapPane.setOnMouseMoved(event -> {
            mouseShovel.setX(event.getSceneX() - mouseShovel.getLayoutX() - 25);
            mouseShovel.setY(event.getSceneY() - mouseShovel.getLayoutY() - 40);
        });
    }


    @FXML
    private void gameWin() throws IOException {

        if (chooseMap.getMapNumber() == 1)
            chooseMap.setMapNumber(2);
        else if (chooseMap.getMapNumber() == 2)
            chooseMap.setMapNumber(3);
        else if (chooseMap.getMapNumber() == 3)
            chooseMap.setMapNumber(4);
        else if (chooseMap.getMapNumber() == 4) {
            AnchorPane menuPane = FXMLLoader.load(getClass().getResource("menu.fxml"));
            waterMapPane.getChildren().setAll(menuPane);
        }

        AnchorPane newPane = FXMLLoader.load(getClass().getResource("new.fxml"));
        waterMapPane.getChildren().setAll(newPane);
    }

    public void beGray(ImageView imageView) {
        ImageView grayImageView = new ImageView();
        grayImageView.setImage(grayImag);
        grayImageView.setFitWidth(50);
        grayImageView.setLayoutX(imageView.getLayoutX());
        grayImageView.setLayoutY(imageView.getLayoutY());
        grayImageView.setFitHeight(imageView.getFitHeight());
        grayImageView.setVisible(true);
        grayImageView.toFront();
        grayImageviews.add(grayImageView);
        waterMapPane.getChildren().add(grayImageView);
    }

}

