import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

/**
 * 
 * @author Eshan Jain. 
 *  This makes an menu screen that prompts the user with different options, including Play game 
 *  and a tutorial screen that guides the user on how to play the game. 
 *
 */
public class OptionPanel extends JPanel implements MouseListener, MouseMotionListener {

	private Main w;

	private Image background;
	private boolean isPlay, isTut, drawTut;
	private boolean bigChoice;
	private int mouseX, mouseY;
	private boolean isClassic, isMidnight;

	public OptionPanel(Main w) {
		this.w = w;
		JPanel p = new JPanel();

		background = (new ImageIcon("back3.png")).getImage();
		p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));	
		p.add(Box.createVerticalStrut(300));   

		isPlay = isTut = drawTut = false;
		mouseX = mouseY = 0;
		bigChoice = false;

		isClassic = isMidnight = false;

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		double ratioX = getWidth() / 800.0;
		double ratioY = getHeight() / 600.0;

		Graphics2D g2 = (Graphics2D)g;

		AffineTransform af = g2.getTransform();

		g2.scale(ratioX,ratioY);


		g.drawImage(background, 0, 0, 800, 600,this);


		if(drawTut) {
			g.drawImage((new ImageIcon("wood.png")).getImage(), 0, 0, 800, 600, this);

			g.setColor(Color.WHITE);
			g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));

			g.drawImage((new ImageIcon("redBaron.png")).getImage(), 75, 75, 100, 100, this);
			g.drawString("Player", 200, 140);

			g.drawImage((new ImageIcon("missile.png")).getImage(), 100, 200, 55, 100, this);
			g.drawString("Missiles", 200, 250);

			g.drawImage((new ImageIcon("bullet.png")).getImage(), 100, 330, 50, 100, this);
			g.drawString("Bullets", 200, 380);

			g.drawImage((new ImageIcon("life.png")).getImage(), 90, 460, 70, 70, this);
			g.drawString("Life", 200, 495);

			g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 15));

			g.drawString("Use your mouse to control the direction", 400, 130);
			g.drawString("and avoid being hit by missiles" , 400, 150);

			g.drawString("Homing missiles, if they hit you, you die", 400, 250);

			g.drawString("Bullets blow up missiles. Press mouse to", 400, 370);
			g.drawString("generate", 400, 390);

			g.drawString("You have 3 lives at the start. Every time", 400, 480);
			g.drawString("a missile hits you, you lose a life", 400, 500);


		}
		else if(bigChoice) {
			g.drawImage((new ImageIcon("wood.png")).getImage(), 0, 0, 800, 600, this);


			if(isClassic) {
				g.setColor(new Color(173, 161, 161));
				g.fillRect(50, 100, 300, 400);
				g.setColor(new Color(79, 74, 74));
				g.fillRect(450, 100, 300, 400);





			}else if(isMidnight) {
				g.setColor(new Color(173, 161, 161));
				g.fillRect(450, 100, 300, 400);
				g.setColor(new Color(79, 74, 74));
				g.fillRect(50, 100, 300, 400);
			}
			else {
				g.setColor(new Color(79, 74, 74));
				g.fillRect(50, 100, 300, 400);

				g.fillRect(450, 100, 300, 400);

			}

			g.setColor(Color.WHITE);
			g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 45));
			g.drawString("CLASSIC", 100 , 300);
			g.drawString("MIDNIGHT", 495, 300);
		}

		else {
			if(isPlay) {
				g.setColor(new Color(173, 161, 161));
			}
			else {
				g.setColor(new Color(79, 74, 74));
			}
			g.fillRect(320, 270, 150, 50);


			if(isTut) {
				g.setColor(new Color(173, 161, 161));

			}
			else {
				g.setColor(new Color(79, 74, 74));

			}

			g.fillRect(320, 330, 150, 50);

			g.setColor(new Color(79, 74, 74));
			g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 45));
			g.drawString("King Of the Skies", 170, 200);



			g.setColor(Color.white);
			g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 25));
			g.drawString("Play", 364, 303);
			g.drawString("Tutorial", 335, 360);

		}


		g2.setTransform(af);
	}

	public void actionPerformed(ActionEvent e) {
		w.changePanel("2");
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {


	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(!drawTut) {
			if(mouseX >= 320 && mouseX <= 470 && mouseY >=280 && mouseY <=330) {
				bigChoice = true;
				System.out.println(bigChoice);
			}else bigChoice = false;
			if(mouseX>= 320 && mouseX <= 470 && mouseY >=340 && mouseY <=390) {
				drawTut = true;
			}
		}
		else drawTut = false;

		if(isClassic) {
			isClassic = false;
			bigChoice = false;
			w.start(0);
		}
		if(isMidnight) {
			isClassic = false;
			bigChoice = false;
			w.start(1);
		}


		repaint();

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();

		if(!drawTut) {
			if(mouseX>= 320 && mouseX <= 470 && mouseY >=280 && mouseY <=330) {
				isPlay = true;
			}else isPlay = false;

			if(mouseX>= 320 && mouseX <= 470 && mouseY >=340 && mouseY <=390) {
				isTut = true;
			}else isTut = false;
			repaint();

		}

		if(bigChoice) {
			if(mouseX>=50 && mouseX<=350 && mouseY >= 103 && mouseY <=504) {
				isClassic = true;
			}else isClassic = false;
			if(mouseX >= 450 && mouseX <=750 && mouseY>= 103 && mouseY <= 504) {
				isMidnight = true;
			}else isMidnight = false;
		}

		repaint();

	}

}