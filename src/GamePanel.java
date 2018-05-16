import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

import javax.swing.*;

import java.util.*;


public class GamePanel extends JPanel implements KeyListener
{

	private String message;
	private Main m;

	public GamePanel (Main m) {
		super();
		this.m = m;
		setBackground(Color.WHITE);
		System.out.println("");

		DrawingSurface s = new DrawingSurface();
		
		
		
	}


	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);  // Call JPanel's paintComponent method to paint the background

		Graphics2D g2 = (Graphics2D)g;

		int width = getWidth();
		int height = getHeight();

		double ratioX = (double)width/800.0;
		double ratioY = (double)height/600.0;

		AffineTransform at = g2.getTransform();
		g2.scale(ratioX, ratioY);

		g.setColor(Color.BLACK);
		g.setFont(new Font("SansSerif",Font.BOLD,28));
		int strWidth = g.getFontMetrics().stringWidth(message);
		g.drawString(message, 400-strWidth/2, 300);

		g2.setTransform(at);

		// TODO Add any custom drawings here
	}


	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {
			m.changePanel("1");
		}
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}


}
