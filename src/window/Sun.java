package window;

import javafx.application.Platform;
import javafx.scene.image.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class Sun implements Comparable<Sun> {
    private ImageView sunImage;
    private int stopY;
    private int stopTime = 0;

    public int getStopTime() {
        return stopTime;
    }

    public void setStopTime(int stopTime) {
        this.stopTime = stopTime;
    }


    public Sun(ImageView sunImage, int stopY, int stopTime) {
        this.sunImage = sunImage;
        this.stopY = stopY;
        this.stopTime = stopTime;
    }

    public ImageView getSunImage() {
        return sunImage;
    }

    public void setSunImage(ImageView sunImage) {
        this.sunImage = sunImage;
    }

    public int getStopY() {
        return stopY;
    }

    public void setStopY(int stopY) {
        this.stopY = stopY;
    }

    public void disappear() {
        double x = 0;
        double y = 0;
        x = (sunImage.getLayoutX() - 15) / 30;
        y = (sunImage.getLayoutY() - 10) / 30;
        Timer timer = new Timer();
        double finalX = x;
        double finalY = y;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (sunImage != null) {
                        if (sunImage.getLayoutX() > 15) {
                            sunImage.setLayoutX(sunImage.getLayoutX() - finalX);
                            sunImage.setLayoutY(sunImage.getLayoutY() - finalY);
                        } else {
                            sunImage.setVisible(false);
                            setSunImage(null);
                            timer.cancel();
                        }
                    }
                });
            }
        }, 0, 30);
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public int compareTo(Sun o) {
        return 1;
    }

    //阳光掉下来的过程
    public void drop(PrimaryCallBack normalCallBack) {
        ImageView dropSun = this.getSunImage();
        if (dropSun != null) {
            if (dropSun.getLayoutY() < this.getStopY()) {
                dropSun.setLayoutY(dropSun.getLayoutY() + 2);
            } else {
                if (this.getStopTime() < 250) {
                    this.setStopTime(this.getStopTime() + 1);
                } else {
                    this.disappear();
                    normalCallBack.addSunNumber(1);
                }
            }
        }
    }
}
