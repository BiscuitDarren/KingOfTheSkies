import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * a PMovingImage that represents the plane the user controls. This character draws in the center
 * of the screen, and missiles chase it
 * 
 * @author Darren Biskup
 *
 */
public class Player extends PMovingImage {
	 // IN RADIANS
	
	private ArrayList<Life> health;

	public Player(PImage img, int x, int y) {
		super(img, x, y,100,100,10);
		setMag(0.01);
		setAngle(Math.toRadians(90));
		setVx(getMag() * Math.cos(getAngle()));
		setVy(getMag() * Math.sin(getAngle()));
		
		health = new ArrayList<Life>();
		resetLife();
	}

	
	
	public void turnToward(PApplet p,int x, int y) {
		double cx = p.width/2;
		double cy = p.height/2;
				
		double targetAngle = Math.atan((cy - y) / (cx - x));
		setAngle(targetAngle);

		if (cx > x)
			setAngle(getAngle() + Math.PI);
	}

	public void draw(PApplet p) {
		p.pushMatrix();
		p.translate(p.width / 2, p.height / 2);
		p.rotate((float) getAngle()  + p.PI/2);
		p.image(super.getImage(), 0, 0,(float) getWidth(),(float)getHeight());
		p.popMatrix();
	}

	@Override
	public void turnToward(int x, int y) {
		// TODO Auto-generated method stub
		System.out.println("USE OTHER OVERLOADED VERSION");
	}
	

	public void resetLife() {
		health.add(new Life(50, 50));
		health.add(new Life(120, 50));
		health.add(new Life(170, 50));
		
	}
	
	public void looseLife() {
		health.remove(health.size()-1);
	}
	
	public void addLife() {
		if(health.size() != 3) {
			int mod = health.size()%3;
			health.add(new Life(50+70*mod, 50));
			
		}
	}

	public ArrayList<Life> getHealth(){
		return health;
	}


}
