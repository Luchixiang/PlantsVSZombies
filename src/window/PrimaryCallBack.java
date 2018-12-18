package window;

import entity.bullet.PeaBullet;
import entity.zombie.Zombie;

public abstract class PrimaryCallBack {
    public abstract void addSun(Sun sun);


    public abstract void addSunNumber(int n);


    public abstract void addZombie(Zombie zombie);


    public abstract void addPeaBullet(PeaBullet peaBullet);


    public abstract void decreaseZombie(int row);

}
