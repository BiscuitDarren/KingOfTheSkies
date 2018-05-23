import processing.core.PApplet;
import processing.core.PImage;

// Needs import statements

public class BackgroundController {

	// Fields
	private int actualX, actualY;
	private PImage bg;
	private int rate;
	private PMovingImage player;

	// Constructor
	public BackgroundController(PImage background, PMovingImage player, int rate) {
		this.bg = background;
		this.actualX = player.getX();
		this.actualY = player.getY();
		this.player = player;
		this.rate = rate;
		bg.loadPixels();
	}

	public void update(int w, int h) {

		// First update the X and Y Position of the "image"
		actualX -= player.getVx() / rate;
		actualY -= player.getVy() / rate;

		// Keep it always within adequate bounds. Possible for it to go from -width
		// to +width and same for height
		actualX = actualX % w;
		actualY = actualY % h;
	}

	public void draw(PApplet p) {
		// Loop through the PApplet's pixel array
		if (bg.width != p.width || bg.height != p.height) {
			bg.resize(p.width, p.height);
			bg.loadPixels();
		}
		update(p.width, p.height);
		p.loadPixels();

		if (p.pixels.length != bg.pixels.length)
			System.out.println("ERROR! PIXEL ARRAY NOT SAME LENGTH");
		
		for (int i = 0; i < bg.pixels.length - 1; i++) {
			int bgx = actualX + i % bg.width;
			int bgy = actualY + i / bg.width;

			int px = 0, py = 0;
			if (bgx > 0)
				px = bgx % p.width;
			else if (bgx < 0)
				px = p.width + bgx;

			if (bgy > 0)
				py = bgy % p.height;
			else if (bgy < 0)
				py = p.height + bgy;

			// Finally, set the pixels to be correctly equal
			int pval = py * p.width + px;
			int bgval = i / bg.width * bg.width + i % bg.width;
	
				p.pixels[py * p.width + px] = bg.pixels[i / bg.width * bg.width + i % bg.width];
			
		}

		// Real finally, update the PApplet's pixels
		p.updatePixels();
	}

	public void resetPlayer(PMovingImage player) {
		this.player = player;
		this.actualX = player.getX();
		this.actualY = player.getY();
	}
}
