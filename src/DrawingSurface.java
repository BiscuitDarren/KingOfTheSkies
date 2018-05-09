import java.util.ArrayList;

import gifAnimation.Gif;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * the window on which the game is drawn on
 * 
 * @author Eshan Jain
 *
 */
public class DrawingSurface extends PApplet {
	private Player player;
	private ArrayList<Missile> missiles;
	private ArrayList<Smoke> smokes;
	private int drawCount = 0;

	public DrawingSurface() {
		runSketch();
	}

	// The statements in the setup() function
	// execute once when the program begins
	public void setup() {
		frameRate(60);
		background(255);
		imageMode(CENTER);
		missiles = new ArrayList<Missile>();
		smokes = new ArrayList<Smoke>();
		player = new Player(loadImage("redBaron.png"), 540, 540, 75, 100);
		missiles.add(new Missile(loadImage("missile.png"), player, 400, 700, 25, 50));
		missiles.add(new Missile(loadImage("missile.png"), player, 300, 200, 25, 50));
		drawCount = 0;
	}

	public void draw() {
		background(255);
		scale((float) width / 920, (float) height / 920);
		for (int i = 0; i < smokes.size(); i++) {
			if (smokes.size() > 0)
				smokes.get(i).draw(this);
			if (!smokes.get(i).isPlaying()) {
				smokes.remove(i);
				i--;
			}

		}

		for (Missile m : missiles) {
			m.act();
			m.draw(this);
			if (drawCount % 5 == 0)
				smokes.add(new Smoke(this, player, m.getX(), m.getY()));
		}

		player.turnToward(this, pmouseX, pmouseY);
		player.act();
		player.draw(this);

		// drawScope();
		drawCount++;
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
