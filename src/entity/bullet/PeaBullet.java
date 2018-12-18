package entity.bullet;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class PeaBullet extends Bullet {
    public PeaBullet(double x, double y, ImageView imageView) {
        super(x, y,imageView);
    }

    @Override
    public void setOriginDamage() {
        this.damage = 10;
    }

    @Override
    public void setOriginImage() {
        this.image = new Image(new File("@../../images/mine/PeaBullet.gif").toURI().toString());
    }
}
