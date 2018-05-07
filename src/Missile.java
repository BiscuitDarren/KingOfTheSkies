import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * @author Darren Biskup
 *
 */
public class Missile extends PMovingImage {
	private PMovingImage centeredTarget;
	private int drawCount;

	public Missile(PImage img, PMovingImage centerGuy, double x, double y, double w, double h) {
		super(img, x, y, w, h, 10);
		this.centeredTarget = centerGuy;
		setMag(0.1);
		setMag(0);
		setAngle(Math.toRadians(90));
		setVx(getMag() * Math.cos(getAngle()));
		setVy(getMag() * Math.sin(getAngle()));
		drawCount = 0;
		
	}

	public void draw(PApplet p) {
		p.pushMatrix();
		double xDif = getX() - centeredTarget.getX();
		double yDif = getY() - centeredTarget.getY();
		//p.translate(, );
		p.image(super.getImage(), (float) ((p.width / 2 + xDif) - getWidth() / 2),
				(float) ((p.height / 2 + yDif) - getHeight() / 2), (float) getWidth(), (float) getHeight());
		p.popMatrix();
		drawCount++;
	}
	
	public void act() {
		//HOMING CODE
		
		super.act();
	}

	@Override
	public void turnToward(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
