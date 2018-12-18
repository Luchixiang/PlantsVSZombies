package util;

import entity.bullet.PeaBullet;
import entity.plants.OnePeaShooter;
import entity.plants.SunFlower;
import entity.plants.SunShroom;
import entity.zombie.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import window.ChooseMap;
import window.PrimaryCallBack;
import window.Sun;

import java.io.File;
import java.util.Random;

//刷东西的工具类
public class AddUtil {
    private Image sunImg = new Image(new File("@../../images/mine/mysun.gif").toURI().toString());
    private Image ZombieWalkImage = new Image(new File("@../../images/Zombies/Zombie/Zombie2.gif").toURI().toString());
    private Image coneheadZombieImg = new Image(new File("@../../images/mine/Zombies/ConeheadZombie/ConeheadZombie.gif").toURI().toString());
    private Image bucketheadZombieImg = new Image(new File("@../../images/mine/Zombies/BucketheadZombie/BucketheadZombie.gif").toURI().toString());
    private Image footballZombieImg = new Image(new File("@../../images/mine/Zombies/FootballZombie/FootballZombie.gif").toURI().toString());
    private Image newspaperZombieImg = new Image(new File("@../../images/mine/Zombies/NewspaperZombie/HeadWalk1.gif").toURI().toString());
    private Image duckyNormalImg = new Image(new File("@../../images/Zombies/DuckyTubeZombie1/Walk2.gif").toURI().toString());
    private Image duckyConheadImg = new Image(new File("@../../images/Zombies/DuckyTubeZombie2/Walk2.gif").toURI().toString());
    private Image duckyBucketImg = new Image(new File("@../../images/Zombies/DuckyTubeZombie3/Walk2.gif").toURI().toString());
    private PrimaryCallBack primaryCallBack;
    private ChooseMap chooseMap = new ChooseMap();
    private int numberOfZombie = 0;

    public AddUtil(PrimaryCallBack primaryCallBack) {
        this.primaryCallBack = primaryCallBack;
    }

    //从天上掉太阳
    public void addSunFromSky(Random random) {
        ImageView imageView = new ImageView();
        imageView.setImage(sunImg);
        int multp = random.nextInt(5);
        int multp2 = random.nextInt(8);
        int x = 50 + 100 * multp2;
        imageView.setLayoutY(0);
        imageView.setLayoutX(x);
        int stopY = 75 + multp * 100;
        Sun sun = new Sun(imageView, stopY, 0);
        imageView.setOnMouseClicked(event -> {
            primaryCallBack.addSunNumber(1);
            disappearSun(sun, imageView);
        });
        imageView.setVisible(true);
        primaryCallBack.addSun(sun);
    }

    private void disappearSun(Sun sun, ImageView imageView) {
        sun.disappear();
        imageView = null;
        sun = null;
    }

    //向日葵刷阳光
    public void addSunFromFlower(SunFlower plant) {
        plant.setClientTime(plant.getClientTime() + 1);
        ImageView imageView = new ImageView();
        imageView.setImage(sunImg);
        imageView.setLayoutX(plant.getX());
        imageView.setLayoutY(plant.getY() + 20);
        Sun sun = new Sun(imageView, (int) plant.getY() + 20, 0);
        imageView.setOnMouseClicked(event -> {
            disappearSun(sun, imageView);
            primaryCallBack.addSunNumber(1);
        });
        plant.setClientTime(0);
        imageView.setVisible(true);
        primaryCallBack.addSun(sun);
    }

    public void addSunFromShroom(SunShroom plant) {
        plant.setClientTime(plant.getClientTime() + 1);
        ImageView imageView = new ImageView();
        imageView.setImage(sunImg);
        imageView.setLayoutX(plant.getX());
        imageView.setLayoutY(plant.getY() + 20);
        Sun sun = new Sun(imageView, (int) plant.getY() + 20, 0);
        imageView.setOnMouseClicked(event -> {
            disappearSun(sun, imageView);
            if (plant.getSmallSunTime() < 5)
                primaryCallBack.addSunNumber(2);
            else primaryCallBack.addSunNumber(1);
        });
        plant.setClientTime(0);
        imageView.setVisible(true);
        primaryCallBack.addSun(sun);
    }

    //产生子弹
    public void addBullet(OnePeaShooter shooter) {
        ImageView imageView = new ImageView();
        PeaBullet peaBullet = new PeaBullet(shooter.getX(), shooter.getY(), imageView);
        peaBullet.setRow(shooter.getRow());
        imageView.setImage(peaBullet.getImage());
        imageView.setLayoutX(peaBullet.getX());
        imageView.setLayoutY(peaBullet.getY());
        peaBullet.setImageView(imageView);
        shooter.setSleepTime(0);
        imageView.setVisible(true);
        primaryCallBack.addPeaBullet(peaBullet);
    }

    //刷僵尸
    public void addZombie() {
        ImageView imageView = new ImageView();
        int row;
        imageView.setLayoutX(862.0);
        Random random = new Random();
        if ((chooseMap.getMapNumber()==1||chooseMap.getMapNumber()==2)) {
            row = random.nextInt(5);
        } else row = random.nextInt(6);
        int Y ;
        if ((chooseMap.getMapNumber()==1||chooseMap.getMapNumber()==2))
        {
            Y=100*row;
        }else Y = 80*row+30;
        imageView.setLayoutY(Y);
        if ((row == 2 || row == 3)&&(chooseMap.getMapNumber()==3||chooseMap.getMapNumber()==4)) {
            imageView.setImage(duckyNormalImg);
        } else {
            imageView.setImage(ZombieWalkImage);
        }
        Zombie zombie = new NormalZombie(imageView, row);
        primaryCallBack.addZombie(zombie);
        imageView.setVisible(true);
        numberOfZombie++;
        if (numberOfZombie % 2 == 0) {
            addConeheadZombie();
        }
        if (numberOfZombie % 2 == 0) {
            addBucketheadZombie();
        }
        if (numberOfZombie % 2 == 0) {
            addFootballZombie();
        }
        if (numberOfZombie % 2 == 0) {
            addNewspaperZombie();
        }
    }

    //刷路障僵尸
    private void addConeheadZombie() {
        ImageView imageView = new ImageView();
        imageView.setLayoutX(862.0);
        int row;
        Random random = new Random();
        if (chooseMap.getMapNumber() != 3) {
            row = random.nextInt(5);
        } else row = random.nextInt(6);
        int Y ;
        if ((chooseMap.getMapNumber()==1||chooseMap.getMapNumber()==2))
        {
            Y=100*row;
        }else Y = 80*row+30;
        imageView.setLayoutY(Y);
        if ((row == 2 || row == 3)&&(chooseMap.getMapNumber()==3||chooseMap.getMapNumber()==4)) {
            imageView.setImage(duckyConheadImg);
        } else {
            imageView.setImage(coneheadZombieImg);
        }
        Zombie zombie = new ConeheadZombie(imageView, row);
        primaryCallBack.addZombie(zombie);
        imageView.setVisible(true);
        numberOfZombie++;
    }

    //刷铁通僵尸
    private void addBucketheadZombie() {
        ImageView imageView = new ImageView();
        imageView.setLayoutX(862.0);
        Random random = new Random();
        int row;
        if (chooseMap.getMapNumber() != 3) {
            row = random.nextInt(5);
        } else row = random.nextInt(6);
        int Y ;
        if ((chooseMap.getMapNumber()==1||chooseMap.getMapNumber()==2))
        {
            Y=100*row;
        }else Y = 80*row+30;
        imageView.setLayoutY(Y);
        if ((row == 2 || row == 3)&&(chooseMap.getMapNumber()==3||chooseMap.getMapNumber()==4)) {
            imageView.setImage(duckyBucketImg);
        } else {
            imageView.setImage(bucketheadZombieImg);
        }
        Zombie zombie = new BucketheadZombie(imageView, row);
        primaryCallBack.addZombie(zombie);
        imageView.setVisible(true);
        numberOfZombie++;
    }

    //刷橄榄球僵尸
    private void addFootballZombie() {
        ImageView imageView = new ImageView();
        imageView.setImage(footballZombieImg);
        imageView.setLayoutX(862.0);
        Random random = new Random();
        int row;
        if (chooseMap.getMapNumber() != 3) {
            row = random.nextInt(5);
        } else {
            do {
                row = random.nextInt(6);
            } while (row == 2 || row == 3);
        }
        int Y ;
        if ((chooseMap.getMapNumber()==1||chooseMap.getMapNumber()==2))
        {
            Y=100*row;
        }else Y = 80*row+30;
        imageView.setLayoutY(Y);
        Zombie zombie = new FootbalZombie(imageView, row);
        primaryCallBack.addZombie(zombie);
        imageView.setVisible(true);
        numberOfZombie++;
    }

    private void addNewspaperZombie() {
        ImageView imageView = new ImageView();
        imageView.setImage(newspaperZombieImg);
        imageView.setLayoutX(862.0);
        Random random = new Random();
        int row;
        if (chooseMap.getMapNumber() != 3) {
            row = random.nextInt(5);
        } else {
            do {
                row = random.nextInt(6);
            } while (row == 2 || row == 3);
        }
        int Y ;
        if ((chooseMap.getMapNumber()==1||chooseMap.getMapNumber()==2))
        {
            Y=100*row;
        }else Y = 80*row+30;
        imageView.setLayoutY(Y);
        Zombie zombie = new NewpaperZombie(imageView, row);
        primaryCallBack.addZombie(zombie);
        imageView.setVisible(true);
        numberOfZombie++;
    }

}
