import java.util.ArrayList;

import gifAnimation.Gif;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * @author Darren Biskup and Eshan Jain
 *
 */
public class DrawingSurface extends PApplet {
	private Player player;
	private ArrayList<Missile> missiles;

	public DrawingSurface() {
		runSketch();
	}

	// The statements in the setup() function
	// execute once when the program begins
	public void setup() {
		frameRate(120);
		background(255);
		player = new Player(loadImage("redBaron.png"),540, 540, 75, 100);
		missiles = new ArrayList<Missile>();
		missiles.add( new Missile(loadImage("missile.png"),player,700, 200, 25,50));
	}

	public void draw() {
		background(255);
		scale((float) width / 920, (float) height / 920);
		
		for(Missile m : missiles) {
			m.act();
			m.draw(this);
		}
				
		player.turnToward(this, pmouseX, pmouseY);
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
