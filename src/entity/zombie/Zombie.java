package entity.zombie;

import entity.plants.EatFlower;
import entity.plants.Plant;
import entity.plants.PotatoMine;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.AudioPlayer;
import window.ChooseMap;
import window.NormalController;
import window.PrimaryCallBack;

import java.io.File;

import static java.lang.Thread.sleep;

public abstract class Zombie {
    protected int x;
    protected int y;
    int health;
    int row;
    int eatCd;
    boolean toWalk = true;
    Image walkImge;
    Image attackImge;
    Image dieImg;
    double velocity;//2

    public Image getWalkImge() {
        return walkImge;
    }

    public void setWalkImge(Image walkImge) {
        this.walkImge = walkImge;
    }

    public Image getAttackImge() {
        return attackImge;
    }

    public void setAttackImge(Image attackImge) {
        this.attackImge = attackImge;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public boolean isToWalk() {
        return toWalk;
    }

    public void setToWalk(boolean toWalk) {
        this.toWalk = toWalk;
    }

    public int getEatCd() {
        return eatCd;
    }

    public void setEatCd(int eatCd) {
        this.eatCd = eatCd;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public Zombie(ImageView imageView, int row) {
        this.imageView = imageView;
        this.row = row;
    }

    protected int damage;

    public void attack(Plant plant, PrimaryCallBack normalCallBack) {
        ImageView ZombieWalk = this.getImageView();
        if (ZombieWalk.getLayoutX() - plant.getX() <= 5
                && ZombieWalk.getLayoutX() - plant.getX() > -7 &&
                this.getRow() == plant.getRow() && plant.getHealth() > 0) {
            if (this.isToWalk()) {
                this.setToWalk(false);
                this.setImage(attackImge);
            }
            this.setEatCd(this.getEatCd() + 1);
            if (this.getEatCd() == 40) {
                plant.isAttacked(1);
                this.setEatCd(0);
                if (plant.getHealth() <= 0) {
                    this.setToWalk(true);
                    this.setImage(walkImge);
                }
            }
            if (plant.getName().equals("potatoMine")
                    && ((PotatoMine) plant).isAppear()) {
                ((PotatoMine) plant).explodePotatoMine();
                this.getAttacked(this.getHealth(), normalCallBack);
            }
            if (plant instanceof EatFlower && ((EatFlower) plant).getEatCd() == 0) {
                ((EatFlower) plant).eat();
                this.setHealth(0);
                this.getImageView().setImage(null);
            }
        }
    }

    protected ImageView imageView;

//    public abstract void setOriginImageview();

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setImage(Image image) {
        imageView.setImage(image);
    }

    public abstract void getAttacked(int damage, PrimaryCallBack normalCallBack);

    public void disappearZombie() {
        new Thread(() -> {
            try {
                sleep(1500);
                this.getImageView().setVisible(false);
                //zombies.remove(zombie);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void move(double x) {
        ImageView imageView = getImageView();
        imageView.setLayoutX(imageView.getLayoutX() - x);
    }
}
