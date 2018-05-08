import java.awt.Dimension;

import javax.swing.JFrame;

import processing.awt.PSurfaceAWT;

public class Main {
	public static void main(String args[]) {
		DrawingSurface drawing = new DrawingSurface();
		
		PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		JFrame window = (JFrame)canvas.getFrame();

		window.setBounds(300, 150,1080 ,1080);
		window.setMinimumSize(new Dimension(100,100));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);

		window.setVisible(true);
	}
}