import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * @author Eshan Jain
 * 
 *         This class represents a Bullet Object which is a PMovingImage. A
 *         bullet is shot from a player, and destroys things it collides with,
 *         incluing missiles and rocks or objects in its way.
 */
public class Bullet extends PMovingImage {

	public Bullet(PImage img, int x, int y, double angle, int speed) {
		super(img, x, y, 10, 30, speed);
		setAngle(angle);
		setMag(speed);
	}

	public void act() {
		setVx(getMag() * Math.cos(getAngle()));
		setVy(getMag() * Math.sin(getAngle()));
		moveByAmount(getVx(), getVy());
	}

	public void draw(PApplet p, PMovingImage center) {
		p.pushMatrix();
		double xDif = getX() - center.getX();
		double yDif = getY() - center.getY();
		p.translate((float) (460 + xDif), (float) (460 + yDif));
		p.rotate((float) getAngle() + p.PI / 2);
		p.image(getImage(), 0, 0, (float) getWidth(), (float) getHeight());
		p.popMatrix();
		incrementCount();
	}

	@Override
	public void turnToward(int x, int y) {
		// TODO Auto-generated method stub

	}


}
