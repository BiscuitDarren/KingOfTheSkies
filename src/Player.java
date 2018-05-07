import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * @author Darren Biskup
 *
 */
public class Player extends PMovingImage {
	 // IN RADIANS

	public Player(PImage img, double x, double y, double w, double h) {
		super(img, x, y, w, h,10);
		setMag(0.01);
		setAngle(Math.toRadians(90));
		setVx(getMag() * Math.cos(getAngle()));
		setVy(getMag() * Math.sin(getAngle()));
	}

	
	
	public void turnToward(PApplet p,int x, int y) {
		double cx = p.width/2;
		double cy = p.height/2;
				
		double targetAngle = -1*Math.atan((cy - y) / (cx - x));
//		double angleDiff = getAngle() - targetAngle;
//		double dTheta = angleDiff / 1.1;
		
		setAngle(targetAngle);

		
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
