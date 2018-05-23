import processing.core.PApplet;
/**
 * 
 * @author Darren Biskup
 * Makes an explosion when missiles crash into each other
 */
public class Explosion extends Smoke{

	public Explosion(PApplet parent, int x, int y) {
		super(parent, "explosion.gif", x, y,200,200);
		
	}

}
