package entity.plants;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class SunFlower extends Plant {
    private Image image;
    private int clientTime;
    private boolean isSleep;

    public int getClientTime() {
        return clientTime;
    }

    public void setClientTime(int clientTime) {
        this.clientTime = clientTime;
    }

    public SunFlower(double x, double y, String n) {
        super(x, y, n);
        isSleep = false;
    }

    public boolean isSleep() {
        return isSleep;
    }

    public void setSleep(boolean sleep) {
        isSleep = sleep;
    }

    @Override
    public void setOriginImage() {
        this.image = new javafx.scene.image.Image(new File("@../../images/Plants/SunFlower/0.gif").toURI().toString());
    }
    @Override
    public void setOriginHealth() {
        this.health = 6;
    }

    public void getAmination(ImageView imageView) {
        float dx = 0;
        while (dx < 2) {
            imageView.setTranslateX(this.x + dx);
            imageView.setTranslateY(this.y + dx * dx);
            dx = dx + 0.2f;
        }
        imageView.setTranslateX(this.x);
        imageView.setTranslateY(this.y + 20);
        imageView.setLayoutX(this.getX());
        imageView.setLayoutY(this.getY() + 20);
    }

    @Override
    public void addSomeThing() {

    }
}
