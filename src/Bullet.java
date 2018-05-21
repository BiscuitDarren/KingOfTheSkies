import processing.core.PImage;

/**
 * 
 * @author Eshan Jain
 * 
 *         This class represents a Bullet Object which is a PMovingImage. A
 *         bullet is shot from a player, and destroys things it collides with,
 *         incluing missiles and rocks or objects in its way.
 */
public class Bullet extends Missile {

	public Bullet(PImage img, PMovingImage player, int x, int y, int w, int h) {
		super(img, player, x, y, w, h, 12);

	}

	public void turnToward(int x, int y) {
		// TODO Auto-generated method stub

	}

}
