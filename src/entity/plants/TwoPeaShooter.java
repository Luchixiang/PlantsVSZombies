package entity.plants;

import javafx.scene.image.Image;

import java.io.File;

public class TwoPeaShooter extends OnePeaShooter {
    public TwoPeaShooter(double x, double y, String n) {
        super(x, y, n);
    }

    @Override
    public void setOriginImage() {
        this.image = new Image(new File("@../../images/mine/0.gif").toURI().toString());
    }
}
