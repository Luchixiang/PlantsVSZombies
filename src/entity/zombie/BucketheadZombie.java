package entity.zombie;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import window.ChooseMap;
import window.NormalController;
import window.PrimaryCallBack;

import java.io.File;

public class BucketheadZombie extends Zombie {
private Image halfImg;
    @Override
    public void getAttacked(int damage, PrimaryCallBack normalCallBack) {
        this.setHealth(this.getHealth() - damage);
        if (this.getHealth() == 0) {
            this.setImage(dieImg);
            disappearZombie();
            normalCallBack.decreaseZombie(this.getRow());
        }else if (this.getHealth()==70)
        {
            this.setImage(halfImg);
        }
    }

    public BucketheadZombie(ImageView imageView, int row) {
        super(imageView, row);
        this.health = 210;
        this.velocity = 0.45;
        this.walkImge = new Image(new File("@../../images/mine/Zombies/BucketheadZombie/BucketheadZombie.gif").toURI().toString());
        this.attackImge = new Image(new File("@../../images/mine/Zombies/BucketheadZombie/BucketheadZombieAttack.gif").toURI().toString());
        this.dieImg = new Image(new File("@../../images/mine/Zombies/Zombie/ZombieDie.gif").toURI().toString());
        this.halfImg = new Image(new File("@../../images/mine/Zombies/Zombie/Zombie2.gif").toURI().toString());
        ChooseMap chooseMap = new ChooseMap();
        if ((row == 2 || row == 3) && (chooseMap.getMapNumber() == 3 || chooseMap.getMapNumber() == 4)) {
            this.setWalkImge(imageView.getImage());
        }
    }

    @Override
    public void setWalkImge(Image walkImge) {
        super.setWalkImge(walkImge);
        this.attackImge = new Image(new File("@../../images/Zombies/DuckyTubeZombie3/Attack.gif").toURI().toString());
        this.halfImg = new Image(new File("@../../images/Zombies/DuckyTubeZombie1/Walk2.gif").toURI().toString());
        this.dieImg = new Image(new File("@../../images/Zombies/DuckyTubeZombie1/Die.gif").toURI().toString());
    }
}
