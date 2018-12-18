package entity.plants;

import javafx.scene.image.Image;

import java.io.File;

public class LilyPad extends Plant {
    private boolean isPlanted;

    public LilyPad(double x, double y, String n) {
        super(x, y, n);
        isPlanted = false;
    }

    public boolean isPlanted() {
        return isPlanted;
    }

    public void setPlanted(boolean planted) {
        isPlanted = planted;
    }

    @Override
    public void setOriginHealth() {
        this.health = 3;
    }

    @Override
    public void setOriginImage() {
        this.image = new Image(new File("@../../images/Plants/LilyPad/0.gif").toURI().toString());
    }

    @Override
    public void addSomeThing() {

    }
}
