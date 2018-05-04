import processing.core.PApplet;
import processing.core.PImage;

public class Missile extends PMovingImage{
	private double vx, vy,mag;
	private double angle; // IN RADIANS
	private PMovingImage centeredTarget;
	public static final double MAX_SPEED = 15;
	
	public Missile(PImage img, PMovingImage centerGuy, double x, double y, double w, double h) {
		super(img, x, y, w, h);
		this.centeredTarget = centerGuy;
		mag = 0.1;
		angle = Math.toRadians(90);
		vx = mag * Math.cos(angle);
		vy = mag * Math.sin(angle);
		
	}
	public void act() {
		if(Math.abs(mag) < MAX_SPEED)
			mag *= 1.1;
		else
			mag = MAX_SPEED;
		
		//ROTATING CODE
	
			
		
		vx = mag * Math.cos(angle);
		vy = -1 *mag * Math.sin(angle);
		super.moveByAmount(vx, vy);
	}

	public void draw(PApplet p) {
		
		double xDif = getX() - centeredTarget.getX();
		double yDif = getY() - centeredTarget.getY();
		
		p.image(super.getImage(), (float) ((p.width / 2 + xDif )- getWidth() / 2), (float) ((p.height / 2 + yDif) - getHeight() / 2), (float)getWidth(),(float)getHeight());
		
		
	}

}
