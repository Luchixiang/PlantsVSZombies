package entity.bullet;

import entity.zombie.Zombie;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import window.PrimaryCallBack;

import java.util.Random;

public class Ball extends Bullet {
	public Ball(double x, double y, ImageView imageView) {
		super(x, y, imageView);
	}

	@Override
	public void setOriginDamage() {
		this.damage = 70;
	}

	@Override
	public void setOriginImage() {

	}

	@Override
	public void attack(Zombie zombie, PrimaryCallBack normalCallBack) {
		ImageView ZombieWalk = zombie.getImageView();
		//System.out.println(zombie.getRow()+"and"+this.getRow());
		if (zombie.getRow() == this.getRow()
				&& ZombieWalk.getLayoutX() - (this.getImageView().getLayoutX() + 69) <= 1
				&& ZombieWalk.getLayoutX() <= 830
				&& zombie.getHealth() > 0) {
			zombie.getAttacked(this.getDamage(), normalCallBack);
			Random random = new Random();
			int flag = random.nextInt(2);
			int row = this.getRow();
			System.out.println(row);

			TranslateTransition transition = new TranslateTransition();
			transition.setNode(imageView);
			transition.setDuration(Duration.millis(100));

			if (row == 0) {
				this.row = row + 1;
				transition.setToY(+95);
			} else if (row == 4) {
				this.row = row - 1;
				transition.setToY(-95);
			} else {
				if (flag ==  0) {
					this.row = row - 1;
					transition.setToY(-95);
				} else {
					this.row = row + 1;
					transition.setToY(+95);
				}
			}
			transition.play();
		}

	}
}
