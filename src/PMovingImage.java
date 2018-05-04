import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

import processing.core.PApplet;
import processing.core.PImage;

public class PMovingImage {

	// FIELDS

	private double x, y;
	private double width, height;
	private PImage image;
	private boolean isVisible;

	// CONSTRUCTORS

	public PMovingImage(PImage img, double x, double y, double w, double h) {
		image = img;
		this.x = x;
		this.y = y;
		width = w;
		height = h;
		isVisible = true;
		
	}

	// METHODS
	public void setImage(PImage img) {
		this.image = img;
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
			p.image(image, (float)( x - width/2),(float)(y - height / 2), (int) width, (int) height);
		
		}
	}

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

}
