import processing.core.PApplet;
import processing.core.PImage;

public class Player extends PMovingImage {
	private double vx, vy,mag;
	private double angle; // IN RADIANS
	public static final double MAX_SPEED = 10;

	public Player(PImage img, double x, double y, double w, double h) {
		super(img, x, y, w, h);
		mag = 0.1;
		angle = Math.toRadians(45);
		vx = mag * Math.cos(angle);
		vy = mag * Math.sin(angle);
	}

	public void act() {
		if(Math.abs(mag) < MAX_SPEED)
			mag *= 1.1;
		else
			mag = MAX_SPEED;
	
		vx = mag * Math.cos(angle);
		vy = -1 *mag * Math.sin(angle);
		super.moveByAmount(vx, vy);
	}

	public void draw(PApplet p) {
		p.image(super.getImage(), (float) (p.width / 2 - getWidth() / 2), (float) (p.height / 2 - getHeight() / 2),(float) getWidth(),(float)getHeight());
	}

}
