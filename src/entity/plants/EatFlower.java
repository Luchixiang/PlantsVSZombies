package entity.plants;

import javafx.scene.image.Image;
import util.AudioPlayer;

import java.io.File;

public class EatFlower extends Plant {
    private int eatCd;

    public int getEatCd() {

        return eatCd;
    }

    public void setEatCd(int eatCd) {
        this.eatCd = eatCd;
    }

    public EatFlower(double x, double y, String n) {
        super(x, y, n);
    }

    @Override
    public void setOriginHealth() {
        this.health = 7;
    }

    @Override
    public void setOriginImage() {
        this.image = new Image(new File("@../../images/mine/Plants/Chomper/Chomper.gif").toURI().toString());
    }

    @Override
    public void addSomeThing() {
    }

    //吃
    public void eat() {
        new Thread(() -> {
            this.getImageView().setImage(new Image(new File("@../../images/mine/Plants/Chomper/ChomperAttack.gif").toURI().toString()));
            try {
                AudioPlayer audioPlayer = new AudioPlayer(new File("@../../audio/chomp.mp3"));
                audioPlayer.start();
                Thread.sleep(800);
                this.getImageView().setImage(new Image(new File("@../../images/mine/Plants/Chomper/ChomperDigest.gif").toURI().toString()));
                this.eatCd = 400;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
    public void setToeat()
    {
        AudioPlayer audioPlayer = new AudioPlayer(new File("@../../audio/chompsoft.mp3"));
        audioPlayer.start();
        this.getImageView().setImage(new Image(new File("@../../images/mine/Plants/Chomper/Chomper.gif").toURI().toString()));
    }
}
