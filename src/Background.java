import processing.core.PApplet;
import processing.core.PImage;

/**
 * parallaxing background that follows the player
 * 
 * @author Darren Biskup
 *
 */
public class Background extends PMovingImage {
	private PMovingImage center;
	private boolean hasLeft, hasRight, hasTop, hasBottom;
	private int rate;

	public Background(PImage img, PMovingImage center, int x, int y, int w, int h,int rate, boolean hasLeft, boolean hasRight,
			boolean hasTop, boolean hasBottom) {
		super(img, x, y, w, h, 0);
		this.setHasLeft(hasLeft);
		this.setHasRight(hasRight);
		this.setHasTop(hasTop);
		this.setHasBottom(hasBottom);
		this.rate = rate;
		this.center = center;

	}

	public void act() {
		setVx(center.getVx() / rate);
		setVy(center.getVy() /	rate);
		moveByAmount(getVx(), getVy());
	}

	public void draw(PApplet p) {
		p.pushMatrix();
		double xDif = getX() - center.getX();
		double yDif = getY() - center.getY();
		p.translate((float) (460 + xDif), (float) (460 + yDif));

		p.image(getImage(), 0, 0, getWidth(), getHeight());
		p.popMatrix();
	}

	@Override
	public void turnToward(int x, int y) {
		// TODO Auto-generated method stub
		System.out.println("no effect");
	}

	public boolean hasLeft() {
		return hasLeft;
	}

	public void setHasLeft(boolean hasLeft) {
		this.hasLeft = hasLeft;
	}

	public boolean hasRight() {
		return hasRight;
	}

	public void setHasRight(boolean hasRight) {
		this.hasRight = hasRight;
	}

	public boolean hasTop() {
		return hasTop;
	}

	public void setHasTop(boolean hasTop) {
		this.hasTop = hasTop;
	}

	public boolean hasBottom() {
		return hasBottom;
	}

	public void setHasBottom(boolean hasBottom) {
		this.hasBottom = hasBottom;
	}

}
