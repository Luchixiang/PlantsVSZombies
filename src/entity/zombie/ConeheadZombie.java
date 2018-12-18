package entity.zombie;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import window.ChooseMap;
import window.NormalController;
import window.PrimaryCallBack;

import java.io.File;

public class ConeheadZombie extends Zombie {
    private Image halfImage;
    public ConeheadZombie(ImageView imageView, int row) {
        super(imageView, row);
        this.health = 140;
        this.velocity = 0.45;
        this.walkImge = new Image(new File("@../../images/mine/Zombies/ConeheadZombie/ConeheadZombie.gif").toURI().toString());
        this.dieImg = new Image(new File("@../../images/Zombies/Zombie/ZombieDie.gif").toURI().toString());
        this.attackImge = new Image(new File("@../../images/mine/Zombies/ConeheadZombie/ConeheadZombieAttack.gif").toURI().toString());
        this.halfImage = new Image(new File("@../../images/Zombies/Zombie/Zombie2.gif").toURI().toString());
        ChooseMap chooseMap = new ChooseMap();
        if ((row == 2 || row == 3) && (chooseMap.getMapNumber() == 3 || chooseMap.getMapNumber() == 4)) {
            this.setWalkImge(imageView.getImage());
        }
    }



    @Override
    public void getAttacked(int damage, PrimaryCallBack normalCallBack) {
        this.setHealth(this.getHealth() - damage);
        if (this.getHealth() == 0) {
            this.setImage(dieImg);
            disappearZombie();
            normalCallBack.decreaseZombie(this.getRow());
        } else if (this.getHealth()==70)
        {
            this.setImage(halfImage);
        }
    }

    @Override
    public void setWalkImge(Image walkImge) {
        super.setWalkImge(walkImge);
        this.attackImge = new Image(new File("@../../images/Zombies/DuckyTubeZombie2/Attack.gif").toURI().toString());
        this.halfImage = new Image(new File("@../../images/Zombies/DuckyTubeZombie1/Walk2.gif").toURI().toString());
        this.dieImg = new Image(new File("@../../images/Zombies/DuckyTubeZombie1/Die.gif").toURI().toString());
    }
}
