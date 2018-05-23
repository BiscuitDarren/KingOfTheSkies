import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import gifAnimation.Gif;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * DrawingSurface is the window that everything is drawn on. It extends PApplet,
 * and has all of the PMovingImages such as Player, Missile and Smoke as fields.
 * This class draws everything and sets up the window.
 * 
 * @author Darren Biskup
 *
 */
public class DrawingSurface extends PApplet {
	private Player player;
	private ArrayList<Missile> missiles;
	private ArrayList<Smoke> smokes;
	private ArrayList<Bullet> bullets;
	private int score = 0;
	private PImage missileImg, restartButton, frontBackground, bulletImg;
	private Background front;
	private Gif gameOverImg;
	private int gameMode, highScore;
	private boolean gameOver;

	public DrawingSurface(int gameMode) {
		runSketch();
		missileImg = loadImage("missile.png");
		frontBackground = loadImage("front.jpg");
		restartButton = loadImage("restartButton.png");
		bulletImg = loadImage("bullet.png");
		gameOverImg = new Gif(this, "gameOver.gif");
		gameOver = false;
		this.gameMode = gameMode;
		score = 0;
		highScore = 0;
	}

	public void settings() {
		size(920, 920);
	}

	public void setup() {
		frameRate(62);
		imageMode(CENTER);
		cursor(CROSS);
		missiles = new ArrayList<Missile>();
		smokes = new ArrayList<Smoke>();
		bullets = new ArrayList<Bullet>();
		player = new Player(this, 460, 460);
		missiles.add(new Missile(missileImg, player, 100, 100));
		front = new Background(frontBackground, player, 2);
	}

	public void draw() {
		background(150, 175, 255);
		// image(frontBackground, 460,460);
		scale((float) width / 920, (float) height / 920);

		if (!gameOver) {
			front.act(this);
			front.draw(this);
			if (player.getHealth().isEmpty()) { // UPON DEATH
				gameOver = true;
				explodeAll();
				gameOverImg.play();
				if (score > highScore)
					highScore = score;
			}
			drawSmokes();
			processBullets();
			drawPlayer();
			processMissiles();
			// MISC

			if (gameMode == 1)
				drawScope();
			drawLives();
			drawScore();
			spawnMissiles();
			score++;
		} else {
			front.draw(this);
			// MISC
			drawSmokes();

			try {
				drawScore();
				filter(GRAY);
			} catch (ArrayIndexOutOfBoundsException e) {
				return;
			}
			drawHighScore();
			drawRestart();
			drawMenuButton();
			drawGameOver();

		}

	}

	private void processBullets() {
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).act();
			bullets.get(i).draw(this, player);
			if (bullets.get(i).getDrawCount() < 180) {
				for (int j = missiles.size() - 1; j >= 0; j--) {
					if (i < bullets.size() && bullets.get(i).collidesWith(missiles.get(j))) {
						smokes.add(new Explosion(this, missiles.get(j).getX(), missiles.get(j).getY()));
						bullets.remove(i);
						missiles.remove(j);
						score += 6000;
					}
				}
			} else {
				bullets.remove(i);
				break;
			}

		}
	}

	private void drawMenuButton() {
		pushStyle();
		strokeWeight(7);
		int x = (int) (mouseX / (width / 920.0));
		int y = (int) (mouseY / (height / 920.0));

		if (mousePressed && x < 610 && x > 310 && y < 837 && y > 738) {
			stroke(255, 204, 0, 100);
			fill(255, 255, 102, 100);
		} else if (x < 610 && x > 310 && y < 837 && y > 738) {
			stroke(51, 150, 200, 100);
			fill(150, 200, 225, 100);
		} else {
			stroke(51, 204, 255, 100);
			fill(204, 255, 255, 100);
		}
		rectMode(CENTER);
		rect(460, 787, 275, 100, 25, 25, 25, 25);
		textAlign(CENTER);
		fill(0);
		stroke(0);
		if (gameMode == 0)
			text("DAY", 460, 805);
		else if (gameMode == 1)
			text("MIDNIGHT", 460, 805);
		popStyle();
	}

	private void drawHighScore() {
		int alpha = 100;
		pushStyle();
		textSize(75);
		fill(0, 102, 153, alpha);
		textAlign(CENTER);
		text("High Score: " + highScore / 60, 460, 460);
		popStyle();
	}

	public void mousePressed() {
		if (!gameOver) {
			if (player.getMag() > 4) {
				player.slowBy(4);
				bullets.add(new Bullet(bulletImg, player.getX(), player.getY(), player.getAngle(), 20));
			}
		}
	}

	public void mouseReleased() {
		int x = (int) (mouseX / (width / 920.0));
		int y = (int) (mouseY / (height / 920.0));

		if (dist(x, y, 450, 600) < 125 / 2 && gameOver) // restart button
			reset(gameMode);
		if (gameOver && x < 610 && x > 310 && y < 837 && y > 738) {// main menu button
			if (gameMode == 1)
				gameMode = 0;
			else if (gameMode == 0)
				gameMode = 1;
		}
	}

	private void drawRestart() {
		pushStyle();
		strokeWeight(7);
		int x = (int) (mouseX / (width / 920.0));
		int y = (int) (mouseY / (height / 920.0));

		if (mousePressed && dist(x, y, 450, 600) < 125 / 2) {
			stroke(255, 204, 0, 100);
			fill(255, 255, 102, 100);
		} else if (dist(x, y, 450, 600) < 125 / 2) {
			stroke(51, 150, 200, 100);
			fill(150, 200, 225, 100);
		} else {
			stroke(51, 204, 255, 100);
			fill(204, 255, 255, 100);
		}

		ellipse(460, 600, 125, 125);
		image(restartButton, 460, 600, 100, 100);
		popStyle();

	}

	private void explodeAll() {
		for (int i = missiles.size() - 1; i >= 0; i--) {
			Missile m = missiles.remove(i);
			smokes.add(new Explosion(this, m.getX(), m.getY()));
			score += 6000;
		}
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
		if (frameCount % 5 == 0) {
			if (player.getHealth().size() > 2)
				smokes.add(new Smoke(this, "smoke.gif", player.getX(), player.getY(), 30, 30));
			else if (player.getHealth().size() > 1)
				smokes.add(new Smoke(this, "smoke.gif", player.getX(), player.getY(), 50, 50));
			else
				smokes.add(new Smoke(this, "smoke.gif", player.getX(), player.getY(), 75, 75));
		}
	}

	private void drawGameOver() {
		image(gameOverImg, 450, 250, 700, 125);
	}

	private void spawnMissiles() {
		if ((frameCount % 300 == 0 || missiles.size() <= 1) && frameRate > 50) {
			int seconds = frameCount / 60;
			int missilesToSpawn = (int) (seconds / 5.0 * 1.25);
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
		ellipse(460, 460, 1750, 1750);
		popStyle();
	}

	private void drawSmokes() {
		for (int i = smokes.size() - 1; i >= 0; i--) {
			if (smokes.size() > 0)
				smokes.get(i).draw(this, player);
			if (!smokes.get(i).isPlaying()) {
				smokes.remove(i);
			}

		}
	}

	private void processMissiles() {
		if (missiles.size() > 0)
			for (int i = missiles.size() - 1; i >= 0 && i < missiles.size(); i--) {
				if (missiles.get(i).getDrawCount() < 600) {
					missiles.get(i).act();
					missiles.get(i).draw(this);
					if (frameCount % 5 == 0)
						smokes.add(new Smoke(this, missiles.get(i).getX(), missiles.get(i).getY()));

					// testing Collisions
					if (player.collidesWith(missiles.get(i))) {
						missiles.remove(i);
						player.loseLife();
						smokes.add(new Explosion(this, player.getX(), player.getY()));
						// i++;
						score += 6000;
						continue;
					}

					for (int c = i - 1; c >= 0 && c < missiles.size(); c--) {
						if (missiles.get(i).collidesWith(missiles.get(c))) {
							smokes.add(new Explosion(this, missiles.get(c).getX(), missiles.get(c).getY()));
							smokes.add(new Explosion(this, missiles.get(i).getX(), missiles.get(i).getY()));

							missiles.remove(i);
							missiles.remove(c);
							// i++;
							// c++;
							score += 12000;
							break;
						}
					}
				} else {// missile ran out of fuel
					smokes.add(new Explosion(this, missiles.get(i).getX(), missiles.get(i).getY()));
					missiles.remove(i);
					score += 6000;
				}

			}
	}

	private void drawScore() {
		int alpha = 100;
		if (!gameOver && gameMode == 1)
			alpha = 10000;
		pushStyle();
		// textFont(loadFont());
		textSize(50);
		stroke(0);
		fill(0, 75, 125, alpha);
		text(score / 60, 75, 125);
		popStyle();
	}

	public void reset(int gameMode) {
		player.resetLife(this);
		score = 0;
		frameCount = 0;
		gameOver = false;
		this.gameMode = gameMode;

		missiles = new ArrayList<Missile>();
		smokes = new ArrayList<Smoke>();
		bullets = new ArrayList<Bullet>();
		player = new Player(this, 460, 460);
		front.newPlayer(player);
		missiles.add(new Missile(missileImg, player, 100, 100));
	}

}
