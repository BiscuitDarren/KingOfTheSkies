import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;


public class OptionPanel extends JPanel implements ActionListener {
	
	private Main w;
	
	public OptionPanel(Main w) {
		this.w = w;
		JPanel p = new JPanel();
		p.setBackground(new Color(0,0,0,0));  // Panel is transparent
		
		p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));	
		p.add(Box.createVerticalStrut(300));   // Move down by 300 pixels  
		
		JButton play = new JButton("Play the Game!");
		JButton tutorial = new JButton("Tutorial");
		
		play.addActionListener(this);
		tutorial.addActionListener(this);
		
		
		p.add(play);
		p.add(tutorial);
		add(p);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		double ratioX = getWidth() / 800.0;
		double ratioY = getHeight() / 600.0;
		
		Graphics2D g2 = (Graphics2D)g;
		
		AffineTransform af = g2.getTransform();
		
		g2.scale(ratioX,ratioY);
		
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 45));
		g.drawString("King Of the Skies", 305, 200);
		
		g2.setTransform(af);
	}
	
	public void actionPerformed(ActionEvent e) {
		w.changePanel("2");
	}
	
}