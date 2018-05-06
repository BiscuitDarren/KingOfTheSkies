import processing.core.PApplet;
import processing.core.PImage;

public class Player extends PMovingImage {
	 // IN RADIANS
	public static final double MAX_SPEED = 10;

	public Player(PImage img, double x, double y, double w, double h) {
		super(img, x, y, w, h);
		setMag(0.1);
		setAngle(Math.toRadians(90));
		setVx(getMag() * Math.cos(getAngle()));
		setVy(getMag() * Math.sin(getAngle()));
	}

	public void act() {
		if(Math.abs(getMag()) < MAX_SPEED)
			setMag(getMag() * 1.1);
		else
			setMag(MAX_SPEED);
	
		setVx(getMag() * Math.cos(getAngle()));
		setVy(-1 *getMag() * Math.sin(getAngle()));
		super.moveByAmount(getVx(), getVy());
	}
	public void turnToward(PApplet p,int x, int y) {
		double cx = p.width;
		double cy = p.height;

		setAngle(Math.atan((cy - y) / (cx - x)));
		if (cx > x)
			setAngle(getAngle() + Math.PI);
	}

	public void draw(PApplet p) {
		p.pushMatrix();
		p.translate(p.width / 2, p.height / 2);
		p.rotate((float) getAngle() * -1 + p.PI/2);
		p.image(super.getImage(), (float) ( - getWidth() / 2), (float) ( - getHeight() / 2),(float) getWidth(),(float)getHeight());
		p.popMatrix();
	}

	@Override
	public void turnToward(int x, int y) {
		// TODO Auto-generated method stub
		System.out.println("USE OTHER OVERLOADED VERSION");
	}



}
