import processing.core.PApplet;
import processing.core.PImage;

public class DrawingSurface extends PApplet {
	private Player player;
	private Missile testMissile;

	public DrawingSurface() {

		runSketch();
	}

	// The statements in the setup() function
	// execute once when the program begins
	public void setup() {
		frameRate(60);
		background(255);
		player = new Player(loadImage("redBaron.png"),540, 540, 75, 100);
		testMissile = new Missile(loadImage("missile.png"),player,700, 700, 25,50);
	}

	public void draw() {
		background(255);
		scale((float) width / 1080, (float) height / 1080);
		
		testMissile.act();
		testMissile.draw(this);
		
		player.act();
		player.draw(this);
		
		//drawScope();
	}

	private void drawScope() {
		pushStyle();
		noFill();
		strokeWeight(2000);
		stroke(0);
		ellipse(width / 2, height / 2, 1500, 1500);
		popStyle();
	}

}
