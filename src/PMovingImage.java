import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.*;
import javax.swing.*;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * @author Darren Biskup
 * 
 *         An abstract class which contains fields for physics-like movement
 *         images move based on the angle and magnitude
 *
 */
public abstract class PMovingImage {

	// FIELDS

	private int x, y;
	private int width, height;
	private double vx, vy, mag;
	private double angle;
	private PImage image;
	private boolean isVisible;
	public final double MAX_SPEED;
	private int drawCount;

	// CONSTRUCTORS

	public PMovingImage(PImage img, int x, int y, int w, int h, double maxSpeed) {
		image = img;
		this.x = x;
		this.y = y;
		width = w;
		height = h;
		isVisible = true;
		vx = 0;
		vy = 0;
		mag = 0;
		angle = Math.toRadians(90);
		MAX_SPEED = maxSpeed;
		drawCount = 0;
	}

	// METHODS
	public void setImage(PImage img) {
		this.image = img;
	}

	public void act() {
		if (Math.abs(mag) <= MAX_SPEED)
			mag *= 1.1;
		else
			mag = MAX_SPEED;

		vx = mag * Math.cos(angle);
		vy = mag * Math.sin(angle);
		moveByAmount(vx, vy);
	}

	public PImage getImage() {
		return image;
	}

	public void setVisibility(boolean visible) {
		isVisible = visible;
	}

	public void moveToLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void moveByAmount(double vx, double vy) {
		this.x += vx;
		this.y += vy;
	}

	public void applyWindowLimits(int windowWidth, int windowHeight) {
		x = Math.min(x, windowWidth - this.width);
		y = Math.min(y, windowHeight - this.height);
		x = Math.max(0, x);
		y = Math.max(0, y);
	}

	public boolean isPointInImage(int mouseX, int mouseY) {
		if (mouseX >= x && mouseY >= y && mouseX <= x + width && mouseY <= y + height) {
			return true;
		}
		return false;
	}

	public void resize(int w, int h) {
		width = w;
		height = h;
	}

	public void draw(PApplet p) {
		if (isVisible) {

			p.image(image, (float) (x - width / 2), (float) (y - height / 2), (int) width, (int) height);

		}
	}

	public boolean collidesWith(PMovingImage other) {
		for (int i = 0; i < image.pixels.length; i++) {
			int thisX = i % getWidth() + getX();
			int thisY = i / getWidth() + getY();

		}
		return false;
	}

	public abstract void turnToward(int x, int y);

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public double getVx() {
		return vx;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}

	public double getVy() {
		return vy;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}

	public double getMag() {
		return mag;
	}

	public void setMag(double mag) {
		this.mag = mag;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public int getCenterX() {
		return x + width / 2;
	}

	public int getCenterY() {
		return y + height / 2;
	}

	public int getDrawCount() {
		return drawCount;
	}

	public void setDrawCount(int drawCount) {
		this.drawCount = drawCount;
	}

	public void incrementCount() {
		drawCount++;
	}

}
