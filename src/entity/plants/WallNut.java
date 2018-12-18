package entity.plants;

import javafx.scene.image.Image;

import java.io.File;

public class WallNut extends Plant {
    private Image image;
    private int clientTime;

    public WallNut(double x, double y, String n) {
        super(x, y, n);
    }


    @Override
    public void setOriginHealth() {
        this.health = 12;
    }

    @Override
    public void setOriginImage() {
        this.image = new javafx.scene.image.Image(new File("@../../images/Plants/WallNut/0.gif").toURI().toString());
    }

    @Override
    public void addSomeThing() {

    }
}
