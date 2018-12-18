package entity.plants;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class OnePeaShooter extends Plant {
    private int sleepTime = 40;
    public OnePeaShooter(double x, double y, String n) {
        super(x, y, n);
        this.health = 6;
    }

    @Override
    public void setOriginImage() {
        this.image = new Image(new File("@../../images/Plants/Peashooter/0.gif").toURI().toString());

    }

    @Override
    public void setOriginHealth() {
        this.health = 6;
    }

    public int getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    @Override
    public void addSomeThing() {

    }
}
