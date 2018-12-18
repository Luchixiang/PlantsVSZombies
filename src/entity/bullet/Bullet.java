package entity.bullet;

import entity.zombie.Zombie;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import window.NormalController;
import window.PrimaryCallBack;

import java.io.File;

import static java.lang.Thread.sleep;

public abstract class Bullet {
    private double x;
    private double y;
    private Point2D point2D;
    int damage;
    Image image;
    ImageView imageView;
    boolean isShooted = false;
    int row;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    Bullet(double x, double y, ImageView imageView) {
        this.x = x;
        this.y = y;
        this.point2D = new Point2D(x, y);
        setOriginDamage();
        setOriginImage();
        this.imageView = imageView;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public boolean isShooted() {
        return isShooted;
    }

    public void setShooted(boolean shooted) {
        isShooted = shooted;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Point2D getPoint2D() {
        return point2D;
    }

    public void setPoint2D(Point2D point2D) {
        this.point2D = point2D;
    }

    public abstract void setOriginDamage();

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void move(int delatX) {
        this.x = this.x + delatX;
        imageView.setLayoutX(getX());
    }

    public abstract void setOriginImage();

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void attack(Zombie zombie, PrimaryCallBack normalCallBack) {
        ImageView ZombieWalk = zombie.getImageView();
        //System.out.println(zombie.getRow()+"and"+this.getRow());
        if (zombie.getRow() == this.getRow()
                && ZombieWalk.getLayoutX() - this.getX() <= 1
                && !this.isShooted()
                && ZombieWalk.getLayoutX() <= 830) {
            zombie.getAttacked(this.getDamage(), normalCallBack);
            this.setShooted(true);
            new Thread(() -> {
                try {
                    sleep(200);
                    this.getImageView().setImage(new Image(new File("@../../images/mine/Plants/PeaBulletHit.gif").toURI().toString()));
                    sleep(100);
                    this.getImageView().setVisible(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
