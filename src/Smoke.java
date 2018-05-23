import gifAnimation.Gif;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * 
 * 
 * @author Darren Biskup
 * 
 *         This class represents the smoke that trails a missile. Smoke extends
 *         PMovingImage and is a gif and uses a gif library.
 *
 */
public class Smoke extends PMovingImage {
	private Gif smokeAnimation;
	

	public Smoke(PApplet parent, int x, int y) {
		this(parent, "smoke.gif", x, y, 70, 70);
	}

	public Smoke(PApplet parent, String filename, int x, int y, int w, int h) {
		super(parent.loadImage(filename), x, y, w, h, 0);
		// TODO Auto-generated constructor stub
		smokeAnimation = new Gif(parent, filename);
		smokeAnimation.play();
	}

	public void draw(PApplet p, PMovingImage center) {
		p.pushMatrix();
		double xDif = getX() - center.getX();
		double yDif = getY() - center.getY();
		p.translate((float) (460 + xDif), (float) (460 + yDif));
		p.rotate((float) getAngle() * -1 + p.PI / 2);

		p.image(smokeAnimation, 0,0, getWidth(), getHeight());
		p.popMatrix();
	}

	public boolean isPlaying() {
		return smokeAnimation.isPlaying();
	}

	@Override
	public void turnToward(int x, int y) {
		// TODO Auto-generated method stub
	}

}
