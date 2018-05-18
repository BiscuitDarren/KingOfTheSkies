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
	private int score = 0;
	private PImage missileImg;
	private int gameMode;
	private boolean gameOver;

	public DrawingSurface() {
		runSketch();
		missileImg = loadImage("missile.png");
		gameOver = false;
		gameMode = 0;
		score = 0;
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
		missiles.add(new Missile(missileImg, player, 100, 100));

	}

	public void draw() {
		background(255);
		scale((float) width / 920, (float) height / 920);
		if (!gameOver) {
			if (player.getHealth().isEmpty())
				gameOver = true;

			drawSmokes();
			drawPlayer();
			processMissiles();
		}
		
		// MISC
		if (gameMode == 1)
			drawScope();
		drawLives();
		drawScore(100);
		spawnMissiles();

		score++;
	}

	private void drawLives() {
		for (Life l : player.getHealth()) {
			l.draw(this);
		}
	}

	private void drawPlayer() {
		player.turnToward(this, pmouseX, pmouseY);
		player.act();
		player.draw(this);

	}

	private void drawGameOver() {

	}

	private void spawnMissiles() {
		if (frameCount % 300 == 0) {
			int seconds = frameCount / 60;
			int missilesToSpawn = (int) (seconds / 5 * 1.25);
			if (missilesToSpawn > 20)
				missilesToSpawn = 20;
			for (int i = 0; i < missilesToSpawn; i++) {
				int rand = (int) random(10);
				int x, y;
				if (rand >= 5) {
					x = player.getX() + (int) random(-width, width);
					rand = (int) random(10);
					if (rand >= 5)
						y = player.getY() - height;
					else
						y = player.getY() + height;
				} else {
					y = player.getY() + (int) random(-height, height);
					rand = (int) random(10);
					if (rand >= 5)
						x = player.getX() - width;
					else
						x = player.getX() + width;
				}
				missiles.add(new Missile(missileImg, player, x, y));
			}

		}
	}

	private void drawScope() {
		pushStyle();
		noFill();
		strokeWeight(1000);
		stroke(0);
		ellipse(width / 2, height / 2, 1750, 1750);
		popStyle();
	}

	private void drawSmokes() {
		for (int i = 0; i < smokes.size(); i++) {
			if (smokes.size() > 0)
				smokes.get(i).draw(this);
			if (!smokes.get(i).isPlaying()) {
				smokes.remove(i);
				i--;
			}

		}
	}

	
	private void processMissiles() {
		for (int i = 0; i < missiles.size(); i++) {
			if (missiles.get(i).getDrawCount() < 600) {
				missiles.get(i).act();
				missiles.get(i).draw(this);
				if (frameCount % 5 == 0)
					smokes.add(new Smoke(this, player, missiles.get(i).getX(), missiles.get(i).getY()));

				// testing Collisions
				if (player.collidesWith(missiles.get(i))) {
					missiles.remove(i);
					player.loseLife();
					smokes.add(new Explosion(this, player, player.getX(), player.getY()));
					i--;
					continue;
				}

				for (int c = i + 1; c < missiles.size(); c++) {
					if (missiles.size() > 0 && missiles.get(i).collidesWith(missiles.get(c))) {
						smokes.add(new Explosion(this, player, missiles.get(c).getX(), missiles.get(c).getY()));
						smokes.add(new Explosion(this, player, missiles.get(i).getX(), missiles.get(i).getY()));

						missiles.remove(c);
						missiles.remove(i);
						i--;
						c--;
						score += 12000;
						break;
					}
				}
			} else {// missile ran out of fuel
				smokes.add(new Explosion(this, player, missiles.get(i).getX(), missiles.get(i).getY()));
				missiles.remove(i);
				score += 6000;
				i--;
			}

		}
	}

	private void drawScore(int alpha) {
		pushStyle();
		// textFont(loadFont());
		textSize(50);
		fill(0, 102, 153, alpha);
		text(score / 60, 75, 125);
		popStyle();
	}

}
