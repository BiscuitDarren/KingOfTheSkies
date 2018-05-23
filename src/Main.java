import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import processing.awt.PSurfaceAWT;
/**
 * 
 * @author Eshan Jain
 * 
 * Starts the game
 *
 */
public class Main extends JFrame {
	
	private JPanel cardPanel;
	
	
	public Main(String title) {
		super(title);
		setBounds(100, 100, 800, 600);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    cardPanel = new JPanel();
	    CardLayout cl = new CardLayout();
	    cardPanel.setLayout(cl);
	    
		OptionPanel panel1 = new OptionPanel(this);    
	    GamePanel panel2 = new GamePanel(this);
	    cardPanel.add(panel1,"1");
	    cardPanel.add(panel2,"2"); 
	    
	    add(cardPanel);
	    addKeyListener(panel2);
	    addMouseListener(panel1);
	    addMouseMotionListener(panel1);
	
	    setVisible(true);
		

	}

	public static void main(String args[]) {
		
		Main w = new Main("AP Animation Demo");
		//DrawingSurface s = new DrawingSurface();
		 
	}
	
	
	public void changePanel(String name) {
		((CardLayout)cardPanel.getLayout()).show(cardPanel,name);
		requestFocus();
	}
	
	public void start(int x) {
		DrawingSurface drawing = new DrawingSurface(x);

		PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		JFrame window = (JFrame) canvas.getFrame();

		window.setBounds(300, 150, 920, 920);
		window.setMinimumSize(new Dimension(100, 100));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);

		window.setVisible(true);
	}
	
	
	
	
	
}
