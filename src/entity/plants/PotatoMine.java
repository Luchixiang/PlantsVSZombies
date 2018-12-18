package entity.plants;

import javafx.scene.image.Image;

import java.io.File;

import static java.lang.Thread.sleep;

public class PotatoMine extends Plant {
    private Image image;
    private boolean isAppear;

    public PotatoMine(double x, double y, String n) {
        super(x, y, n);
        isAppear = false;
    }

    public void setAppear(boolean appear) {
        isAppear = appear;
    }

    public boolean isAppear() {
        return isAppear;
    }

    @Override
    public void setOriginHealth() {
        this.health = 6;
    }

    @Override
    public void setOriginImage() {
        this.image = new javafx.scene.image.Image(new File("@../../images/Plants/PotatoMine/0.gif").toURI().toString());
    }

    @Override
    public void addSomeThing() {

    }
    public void explodePotatoMine()
    {
        new Thread(() -> {
            this.getImageView().setImage(new Image(new File("@../../images/mine/Plants/PotatoMine/PotatoMine_mashed.gif").toURI().toString()));
            try {
                sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           this.getImageView().setImage(null);
           this.setHealth(0);
        }).start();
    }

}
