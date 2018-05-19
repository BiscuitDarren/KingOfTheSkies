import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * a PMovingImage that represents the plane the user controls. This character
 * draws in the center of the screen, and missiles chase it
 * 
 * @author Darren Biskup
 *
 */
public class Player extends PMovingImage {
	// IN RADIANS

	private ArrayList<Life> health;

	public Player(PApplet p, int x, int y) {
		super(p.loadImage("redBaron.png"), x, y, 75, 75, 10);
		setBounds(10,75);
		health = new ArrayList<Life>();
		resetLife(p);
	}

	public void turnToward(PApplet p, int x, int y) {
		double cx = p.width / 2;
		double cy = p.height / 2;

		double targetAngle = Math.atan((cy - y) / (cx - x));
		setAngle(targetAngle);

		if (cx > x)
			setAngle(getAngle() + Math.PI);
	}

	public void draw(PApplet p) {
		p.pushMatrix();
		p.translate(460, 460);
		p.rotate((float) getAngle() + p.PI / 2);
		p.image(super.getImage(), 0, 0, (float) getWidth(), (float) getHeight());
		p.popMatrix();
	}

	@Override
	public void turnToward(int x, int y) {
		// TODO Auto-generated method stub
		System.out.println("USE OTHER OVERLOADED VERSION");
	}

	public void resetLife(PApplet p) {
		health.add(new Life(75, 75, p));
		health.add(new Life(130, 75, p));
		health.add(new Life(185, 75, p));

	}

	public void loseLife() {
		if (health.size() > 0)
			health.remove(health.size() - 1);
	}

	public void addLife(PApplet p) {
		if (health.size() != 3) {
			int mod = health.size() % 3;
			health.add(new Life(50 + 70 * mod, 50, p));

		}
	}

	public ArrayList<Life> getHealth() {
		return health;
	}

}
