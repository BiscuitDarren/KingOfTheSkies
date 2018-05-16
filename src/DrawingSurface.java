import java.util.ArrayList;

import gifAnimation.Gif;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * DrawingSurface is the window that everything is drawn on. It extends PApplet,
 * and has all of the PMovingImages such as Player, Missile and Smoke as fields.
 * This class draws everything and sets up the window.
 * 
 * @author Darren Biskup & Eshan Jain
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
		// noCursor();
		cursor(CROSS);
		missiles = new ArrayList<Missile>();
		smokes = new ArrayList<Smoke>();
		player = new Player(this, 540, 540);
		missiles.add(new Missile(loadImage("missile.png"), player, 400, 700));
		missiles.add(new Missile(loadImage("missile.png"), player, 200, 400));
		drawCount = 0;
	}

	public void draw() {
		background(255);
		scale((float) width / 920, (float) height / 920);

		// DRAWING SMOKE
		for (int i = 0; i < smokes.size(); i++) {
			if (smokes.size() > 0)
				smokes.get(i).draw(this);
			if (!smokes.get(i).isPlaying()) {
				smokes.remove(i);
				i--;
			}

		}

		// DRAWING MISSILES
		for (int i = 0; i < missiles.size(); i++) {
			if (missiles.get(i).getDrawCount() < 600) {
				missiles.get(i).act();
				missiles.get(i).draw(this);
				if (frameCount % 5 == 0)
					smokes.add(new Smoke(this, player, missiles.get(i).getX(), missiles.get(i).getY()));

				// testing Collisions

				for (int c = i + 1; c < missiles.size(); c++) {
					if (missiles.get(i).collidesWith(missiles.get(c))) {
						smokes.add(new Explosion(this, player, missiles.get(c).getX(), missiles.get(c).getY()));
						smokes.add(new Explosion(this, player, missiles.get(i).getX(), missiles.get(i).getY()));

						missiles.remove(c);
						missiles.remove(i);
						i--;
						c--;
						drawCount += 12000;
						break;
					}
				}
			} else {
				smokes.add(new Explosion(this, player, missiles.get(i).getX(), missiles.get(i).getY()));
				missiles.remove(i);
				drawCount += 6000;
				i--;
			}

		}

		// DRAWING PLAYER
		player.turnToward(this, pmouseX, pmouseY);
		player.act();
		player.draw(this);

		// DRAWING LIVES
		for (Life l : player.getHealth()) {
			l.draw(this);
		}

		// MISC
		// drawScope();
		drawScore(100);

		drawCount++;
	}

	private void drawScope() {
		pushStyle();
		noFill();
		strokeWeight(1000);
		stroke(0);
		ellipse(width / 2, height / 2, 1750, 1750);
		popStyle();
	}

	private void drawScore(int alpha) {
		pushStyle();
		// textFont(loadFont());
		textSize(50);
		fill(0, 102, 153, alpha);
		text(drawCount / 60, 50, 100);
		popStyle();
	}

}
