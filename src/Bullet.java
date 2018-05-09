import processing.core.PImage;
/**
 * 
 * @author Eshan Jain 
 *  
 *  This class represents a Bullet Object which is a PMovingImage. A bullet is shot from a player, and destroys things it collides with,
 *  incluing missiles and rocks or objects in its way.  
 */
public class Bullet extends PMovingImage{

	public Bullet(PImage img, double x, double y, double w, double h, double maxSpeed) {
		super(img, x, y, w, h, maxSpeed);
		
	}
	public void turnToward(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	


	
	
}
