import processing.core.PApplet;
/**
 * 
 * @author Darren Biskup
 * Makes an explosion when missiles crash into each other
 */
public class Explosion extends Smoke{

	public Explosion(PApplet parent, PMovingImage center, int x, int y) {
		super(parent, "explosion.gif", center, x, y,200,200);
		
	}

}
