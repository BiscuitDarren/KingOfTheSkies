import processing.core.PApplet;
import processing.core.PImage;

public class Background {

	private int rate;
	private PImage background;
	private PMovingImage center;
	private int centerX, centerY;

	public Background(PImage background, PMovingImage center, int rate) {
		this.background = background;
		this.rate = rate;
		this.center = center;
		this.centerX = center.getX();
		this.centerY = center.getY();
		background.resize(1920, 1080);
	} 

	public void draw(PApplet p) {

		try {
			p.set((int) (centerX - background.width), (int) (centerY - background.height), background);
			p.set((int) (centerX - background.width), (int) (centerY), background);
			p.set((int) (centerX), (int) (centerY), background);
			p.set((int) (centerX), (int) (centerY - background.height), background);
		} catch (ArrayIndexOutOfBoundsException e) {
			return;
		}
	}

	public void act(PApplet p) {
		centerX -= center.getVx() / rate;
		centerY -= center.getVy() / rate;
		if (centerX < 0)
			centerX = p.width - 1;
		else if (centerX >= p.width)
			centerX = 1;
		if (centerY < 0)
			centerY = p.height - 1;
		else if (centerY >= p.height)
			centerY = 1;
	}
	
	public void newPlayer(PMovingImage center) {
		this.center = center;
		this.centerX = center.getX();
		this.centerY = center.getY();
	}

	/*
	 * private Background[] backgrounds; private PMovingImage center;
	 * 
	 * public BackgroundList(PImage background, PMovingImage center, int rate) {
	 * backgrounds = new Background[4]; backgrounds[0] = new Background(background,
	 * center, 690, 230, 920, 920, rate, true, false, false, true); backgrounds[1] =
	 * new Background(background, center, 230, 230, 920, 920, rate, false, true,
	 * false, true); backgrounds[2] = new Background(background, center, 230, 690,
	 * 920, 920, rate, false, true, true, false); backgrounds[3] = new
	 * Background(background, center, 690, 690, 920, 920, rate, true, false, true,
	 * false); this.center = center; }
	 * 
	 * public void draw(PApplet p) { for (int i = 0; i < backgrounds.length; i++) {
	 * //backgrounds[i].act(); int xDif = backgrounds[i].getX() - center.getX(); int
	 * yDif = backgrounds[i].getY() - center.getY(); if (Math.abs(xDif) >
	 * backgrounds[i].getWidth() || Math.abs(yDif) > backgrounds[i].getHeight()) for
	 * (int j = 0; j < backgrounds.length; j++) { if (j != i) { if (center.getVy() >
	 * 0 && !backgrounds[j].hasBottom()) {
	 * backgrounds[i].moveToLocation(backgrounds[j].getX(), backgrounds[j].getY() +
	 * backgrounds[j].getHeight()); backgrounds[j].setHasBottom(true); break; } else
	 * if (center.getVy() < 0 && !backgrounds[j].hasTop()) {
	 * backgrounds[i].moveToLocation(backgrounds[j].getX(), backgrounds[j].getY() -
	 * backgrounds[j].getHeight()); backgrounds[j].setHasTop(true); break; } if
	 * (center.getVx() > 0 && !backgrounds[j].hasRight()) {
	 * backgrounds[i].moveToLocation(backgrounds[j].getX() +
	 * backgrounds[j].getWidth(), backgrounds[j].getY());
	 * backgrounds[j].setHasRight(true); break; } else if (center.getVx() < 0 &&
	 * !backgrounds[j].hasLeft()) {
	 * backgrounds[i].moveToLocation(backgrounds[j].getX() -
	 * backgrounds[j].getWidth(), backgrounds[j].getY());
	 * backgrounds[j].setHasLeft(true); break; }
	 * 
	 * } } backgrounds[i].draw(p); } updateFlags(); }
	 * 
	 * private void updateFlags() {
	 * 
	 * }
	 */
}
