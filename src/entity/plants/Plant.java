package entity.plants;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Plant {
    double x;
    double y;
    String name;
    int health;
    Image image;
    ImageView imageView;
    int row;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
//        imageView.setLayoutY(this.y);
//        imageView.setLayoutX(this.x);
    }

    Plant(double x, double y,String n) {
        this.x = x;
        this.y = y;
        this.name = n;
        setOriginImage();
        setOriginHealth();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }


    public abstract void setOriginHealth();

    public abstract void setOriginImage();

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void isAttacked(int damage) {
        this.health = health - damage;
    }
    public abstract void addSomeThing();
}
