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
	private Rectangle2D boundingRect;

	// CONSTRUCTORS

	public PMovingImage(PImage img, int x, int y, int w, int h, double maxSpeed) {
		image = img;
		this.x = x;
		this.y = y;
		width = w;
		height = h;
		isVisible = true;
		angle = Math.toRadians(90);
		mag = 0.1;
		vx = mag * Math.cos(angle);
		vy = mag * Math.sin(angle);
		MAX_SPEED = maxSpeed;
		drawCount = 0;
		boundingRect = new Rectangle(x, y, width, height);
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
		boundingRect.setFrame(x, y, width, height);

	}

	public void moveByAmount(double vx, double vy) {
		this.x += vx;
		this.y += vy;
		boundingRect.setFrame(x, y, width, height);
	}

	public void applyWindowLimits(int windowWidth, int windowHeight) {
		x = Math.min(x, windowWidth - this.width);
		y = Math.min(y, windowHeight - this.height);
		x = Math.max(0, x);
		y = Math.max(0, y);
		boundingRect.setFrame(x, y, width, height);

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

	// public boolean collidesWith(PMovingImage other) {
	// for (int x = 0; x < getWidth(); x++) {
	// for (int y = 0; y < getHeight(); y++) {
	// if (image.pixels[y * getWidth() + x] >= 0x010000) {
	// int thisX = x + getX();
	// int thisY = y + getY();
	//
	// int xDif = thisX - other.getX();
	// int yDif = thisY - other.getY();
	// if (xDif >= 0 && xDif < other.getWidth() && yDif >= 0 && yDif <
	// other.getHeight())
	// if (other.getImage().pixels[yDif * other.getWidth() + xDif] >= 0x010000) {
	// return true;
	//
	// }
	//
	// }
	// }
	// }
	// return false;
	// }
	public boolean collidesWith(PMovingImage other) {
		return boundingRect.intersects(other.getBoundingRect());
	}

	public Rectangle2D getBoundingRect() {
		return boundingRect;
	}
	public void setBounds(int w, int h) {
		boundingRect.setFrame(x,y,w,h);
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
