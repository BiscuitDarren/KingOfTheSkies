import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

/**
 * 
 * @author Eshan Jain. Code taken from Shelby Menu Demo
 *  This makes an option panel that prompts the user with different options. 
 *
 */
public class OptionPanel extends JPanel implements MouseListener, MouseMotionListener {
	
	private Main w;
	private Image background;
	
	private boolean isPlay, isTut, drawTut;
	
	private int mouseX, mouseY;
	
	public OptionPanel(Main w) {
		this.w = w;
		JPanel p = new JPanel();
		p.setBackground(new Color(0,0,0,0));  // Panel is transparent

		background = (new ImageIcon("back3.png")).getImage();
		p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));	
		p.add(Box.createVerticalStrut(300));   // Move down by 300 pixels  
	    
		JButton play = new JButton("Play the Game!");
		JButton tutorial = new JButton("Tutorial");
		
		//play.addActionListener(this);
		//tutorial.addActionListener(this);

		 isPlay = isTut = drawTut = false;
		 mouseX = mouseY = 0;
		
		
		//p.add(play);
		//p.add(tutorial);
		//add(p);
		
		
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
	    	g.drawLine(12, 23, 243, 244);
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
	
		
		//System.out.println(mouseX + " " + mouseY);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	if(!drawTut) {
		if(mouseX >= 320 && mouseX <= 470 && mouseY >=280 && mouseY <=330) {
			w.start();
		}
		if(mouseX>= 320 && mouseX <= 470 && mouseY >=340 && mouseY <=390) {
			System.out.println("h");
			drawTut = true;
		}
	}
	else drawTut = false;
		
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
		
		//g.fillRect(320, 330, 150, 50);
		if(!drawTut) {
			if(mouseX>= 320 && mouseX <= 470 && mouseY >=280 && mouseY <=330) {
			isPlay = true;
		}else isPlay = false;
		
		if(mouseX>= 320 && mouseX <= 470 && mouseY >=340 && mouseY <=390) {
			isTut = true;
		}else isTut = false;
		repaint();
		
		}
		
		

		
		
		
	}
	
}