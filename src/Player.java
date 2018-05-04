import processing.core.PApplet;
import processing.core.PImage;

public class Player extends PMovingImage {
	private double vx, vy;

	public Player(PImage img, double x, double y, double w, double h) {
		super(img, x, y, w, h);
		vx = 0;
		vy = -10;
	}

	public void act() {
		super.moveByAmount(vx, vy);
	}

	public void draw(PApplet p) {
		p.image(super.getImage(), (float) (p.width / 2 - getWidth() / 2), (float) (p.height / 2 - getHeight() / 2), 75,
				100);
	}

}
