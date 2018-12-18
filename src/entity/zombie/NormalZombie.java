package entity.zombie;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import window.ChooseMap;
import window.NormalController;
import window.PrimaryCallBack;

import java.io.File;

public class NormalZombie extends Zombie {
    private Image halfImg;
    public NormalZombie(ImageView imageView, int row) {
        super(imageView, row);
        this.health = 70;
        this.velocity = 0.45;//2
        this.walkImge = new Image(new File("@../../images/Zombies/Zombie/Zombie2.gif").toURI().toString());
        this.attackImge = new Image(new File("@../../images/mine/Zombies/Zombie/ZombieAttack.gif").toURI().toString());
        this.dieImg = new Image(new File("@../../images/mine/Zombies/Zombie/ZombieDie.gif").toURI().toString());
        halfImg = new Image(new File("@../../images/Zombies/Zombie/Zombie3.gif").toURI().toString());
        ChooseMap chooseMap = new ChooseMap();
        if ((row == 2 || row == 3) && (chooseMap.getMapNumber() == 3 || chooseMap.getMapNumber() == 4)) {
            this.setWalkImge(imageView.getImage());
        }
    }


    @Override
    public void getAttacked(int damageFromPlant, PrimaryCallBack normalCallBack) {
        this.setHealth(this.getHealth() - damageFromPlant);
        if (this.getHealth() == 0) {
            this.setImage(dieImg);
            disappearZombie();
            normalCallBack.decreaseZombie(this.getRow());
        }
    }

    @Override
    public void setWalkImge(Image walkImge) {
        super.setWalkImge(walkImge);
        this.attackImge = new Image(new File("@../../images/Zombies/DuckyTubeZombie1/Attack.gif").toURI().toString());
        this.dieImg = new Image(new File("@../../images/Zombies/DuckyTubeZombie1/Die.gif").toURI().toString());
    }
}
