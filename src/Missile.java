import processing.core.PImage;

public class Missile extends PMovingImage{
	private double vx, vy,vf;
	private double angle; // IN RADIANS
	public static final double MAX_SPEED = 10;
	
	public Missile(PImage img, double x, double y, double w, double h) {
		super(img, x, y, w, h);
		
	}

}
