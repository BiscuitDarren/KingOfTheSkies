import java.awt.Image;

import processing.core.PApplet;
import processing.core.PImage;
/**
 * 
 * @author Eshan Jain
 * This class makes a new Life. When the game starts, the player has 3 lifes. Every time a 
 * missile hits the player, the player looses a life. Once the player has lost all three lives,
 * the game is over.  
 */
public class Life extends PMovingImage {


	
	//private PImage heart;
	
	public Life(int xcor, int ycor, PApplet p) {
		super(p.loadImage("black-heart.png"), xcor, ycor, 50, 50, 0.0);
		
		//heart = loadImage("missile.png");
	}

	@Override
	public void turnToward(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
}
