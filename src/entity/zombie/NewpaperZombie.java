package entity.zombie;

import entity.plants.Plant;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import window.NormalController;
import window.PrimaryCallBack;

import java.io.File;

public class NewpaperZombie extends Zombie {
    public NewpaperZombie(ImageView imageView, int row) {
        super(imageView, row);
        this.health = 140;
        this.velocity = 0.45;
        this.walkImge = new Image(new File("@../../images/mine/Zombies/NewspaperZombie/HeadWalk1.gif").toURI().toString());
        this.attackImge = new Image(new File("@../../images/mine/Zombies/NewspaperZombie/HeadAttack1.gif").toURI().toString());
    }

    @Override
    public void attack(Plant plant, PrimaryCallBack normalCallBack) {
        super.attack(plant, normalCallBack);
    }

    @Override
    public void getAttacked(int damage, PrimaryCallBack normalCallBack) {
        this.setHealth(this.getHealth() - damage);
        if (this.getHealth() == 0) {
            this.setImage(new Image(new File("@../../images/mine/Zombies/NewspaperZombie/Die.gif").toURI().toString()));
            disappearZombie();
            normalCallBack.decreaseZombie(this.getRow());
        } else if (this.health == 70) {
            this.setImage(new Image(new File("@../../images/mine/Zombies/NewspaperZombie/HeadWalk0.gif").toURI().toString()));
            this.walkImge = new Image(new File("@../../images/mine/Zombies/NewspaperZombie/HeadWalk0.gif").toURI().toString());
            this.attackImge = new Image(new File("@../../images/mine/Zombies/NewspaperZombie/HeadAttack0.gif").toURI().toString());
            this.velocity = 1;
        }
    }
}
