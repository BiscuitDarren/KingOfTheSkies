import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class PMovingImage {

	// FIELDS

	private double x, y;
	private double width, height;
	private double vx, vy, mag;
	private double angle;
	private PImage image;
	private boolean isVisible;
	public final double MAX_SPEED;

	// CONSTRUCTORS

	public PMovingImage(PImage img, double x, double y, double w, double h, double maxSpeed) {
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

	}

	// METHODS
	public void setImage(PImage img) {
		this.image = img;
	}

	public void act() {
		if (Math.abs(mag) < MAX_SPEED)
			mag *= 1.1;
		else
			mag = MAX_SPEED;

		vx = mag * Math.cos(angle);
		vy = -1 * mag * Math.sin(angle);
		moveByAmount(vx, vy);
	}

	public PImage getImage() {
		return image;
	}

	public void setVisibility(boolean visible) {
		isVisible = visible;
	}

	public void moveToLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void moveByAmount(double x, double y) {
		this.x += x;
		this.y += y;
	}

	public void applyWindowLimits(int windowWidth, int windowHeight) {
		x = Math.min(x, windowWidth - this.width);
		y = Math.min(y, windowHeight - this.height);
		x = Math.max(0, x);
		y = Math.max(0, y);
	}

	public boolean isPointInImage(double mouseX, double mouseY) {
		if (mouseX >= x && mouseY >= y && mouseX <= x + width && mouseY <= y + height) {
			return true;
		}
		return false;
	}

	public void resize(double w, double h) {
		width = w;
		height = h;
	}

	public void draw(PApplet p) {
		if (isVisible) {

			p.image(image, (float) (x - width / 2), (float) (y - height / 2), (int) width, (int) height);

		}
	}

	public abstract void turnToward(int x, int y);

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
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

	public double getCenterX() {
		return x + width / 2;
	}

	public double getCenterY() {
		return y + height / 2;
	}

}
