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
	private PMovingImage centeredTarget;

	public Smoke(PApplet parent, PMovingImage center, int x, int y) {
		super(parent.loadImage("smoke.gif"), x, y, 10, 10, 0);
		// TODO Auto-generated constructor stub
		centeredTarget = center;
		smokeAnimation = new Gif(parent, "smoke.gif");
		smokeAnimation.play();

	}

	public void draw(PApplet p) {
		p.pushMatrix();
		double xDif = getX() - centeredTarget.getX();
		double yDif = getY() - centeredTarget.getY();
		p.translate((float) ((p.width / 2 + xDif) - getWidth() / 2), (float) ((p.height / 2 + yDif) - getHeight() / 2));
		p.rotate((float) getAngle() * -1 + p.PI / 2);

		p.image(smokeAnimation, 0, 0, 70, 70);
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
