import processing.core.PApplet;
import processing.core.PImage;

public class Player extends PMovingImage {
	private double vx, vy,vf;
	private double angle; // IN RADIANS
	public static final double MAX_SPEED = 10;

	public Player(PImage img, double x, double y, double w, double h) {
		super(img, x, y, w, h);
		vf = -1 * MAX_SPEED;
		vx = 0;
		vy = vf;
		angle = Math.PI / 2;
	}

	public void act() {
		if(Math.abs(vf) < MAX_SPEED)
			vf *= 1.1;
		else
			vf = MAX_SPEED;
			
		vx = vf * Math.cos(angle);
		vy = vf * Math.cos(angle);

		
		
		
		
		
		
		
		super.moveByAmount(vx, vy);
	}

	public void draw(PApplet p) {
		super.draw(p);
		//p.image(super.getImage(), (float) (p.width / 2 - getWidth() / 2), (float) (p.height / 2 - getHeight() / 2), 75,100);
		
	}

}
