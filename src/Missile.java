import java.awt.geom.Line2D;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * @author Darren Biskup This class represents a missile, whose purpose is to
 *         follow the player and destroy it. Missile is a PMovingImage, and the
 *         main enemy of the game. They are also drawn relative to the player.
 */
public class Missile extends PMovingImage {
	private PMovingImage centeredTarget;
	private final double MAX_dadt;

	public Missile(PImage img, PMovingImage centerGuy, int x, int y) {
		super(img, x, y, 20, 50, 11);
		this.centeredTarget = centerGuy;
		setMag(0.01);
		// setMag(0.0);
		setAngle(Math.toRadians(90));
		setVx(getMag() * Math.cos(getAngle()));
		setVy(getMag() * Math.sin(getAngle()));
		setDrawCount(0);
		MAX_dadt = Math.toRadians(2.5);
	}

	public void draw(PApplet p) {
		p.pushMatrix();
		double xDif = getX() - centeredTarget.getX();
		double yDif = getY() - centeredTarget.getY();
		p.translate((float) (p.width / 2 + xDif), (float) (p.height / 2 + yDif));
		p.rotate((float) getAngle() + p.PI / 2);
		p.image(super.getImage(), 0, 0, (float) getWidth(), (float) getHeight());
		p.popMatrix();
		incrementCount();
	}

	public void act() {
		turnToward((int) centeredTarget.getCenterX(), (int) centeredTarget.getCenterY());
		super.act();
	}

	@Override
	public void turnToward(int x, int y) {
		// TODO Auto-generated method stub
		double cx = getCenterX();
		double cy = getCenterY();

		double targetAngle = Math.atan((cy - y) / (cx - x));
		if (cx > x)
			targetAngle += Math.PI;
		
		if (targetAngle > Math.toRadians(270) && getAngle() < Math.toRadians(90))
			setAngle(getAngle() + Math.PI * 2);
		if (getAngle() > Math.toRadians(270) && targetAngle < Math.toRadians(90))
			targetAngle += Math.PI * 2;

		double angleDiff = targetAngle - getAngle();
		// pAngle = getAngle();

		//System.out.println(getAngle() + " " + targetAngle);

		setAngle(getAngle() + Math.signum(angleDiff) * MAX_dadt);
	}

}
