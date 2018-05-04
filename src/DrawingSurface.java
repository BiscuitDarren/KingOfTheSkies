import processing.core.PApplet;
import processing.core.PImage;

public class DrawingSurface extends PApplet {
	private Player player;

	public DrawingSurface() {

		runSketch();
	}

	// The statements in the setup() function
	// execute once when the program begins
	public void setup() {
		frameRate(60);
		background(255);
		player = new Player(loadImage("redBaron.png"),540, 540, 75, 100);
	}

	public void draw() {
		background(255);
		scale((float) width / 1080, (float) height / 1080);
		
		player.act();
		player.draw(this);
		
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
