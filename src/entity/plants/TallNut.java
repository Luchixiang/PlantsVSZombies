package entity.plants;

import javafx.scene.image.Image;

import java.io.File;

public class TallNut extends WallNut {
    private Image image;

    public TallNut(double x, double y, String n) {
        super(x, y, n);
    }


    @Override
    public void setOriginHealth() {
        this.health = 20;
    }

    @Override
    public void setOriginImage() {
        this.image = new javafx.scene.image.Image(new File("@../../images/Plants/TallNut/0.gif").toURI().toString());
    }
}
