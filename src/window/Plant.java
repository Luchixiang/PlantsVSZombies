package window;

import javafx.scene.image.Image;

public class Plant {
    private Image plant;
    private double prx;
    private double pry;

    public Plant(Image p, double x, double y){
        plant = p;
        prx = x;
        pry = y;
    }

    public Image getPlant() {
        return plant;
    }

    public double getPrx() {
        return prx;
    }

    public double getPry() {
        return pry;
    }

    public void setPlant(Image plant) {
        this.plant = plant;
    }

    public void setPrx(int prx) {
        this.prx = prx;
    }

    public void setPry(int pry) {
        this.pry = pry;
    }
}
