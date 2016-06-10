import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class springMvmt {
	public static void draw(Graphics g)
	{
		g.setColor(Color.GRAY);
		
		//Starting corner top left
		int x = 0;
		int y = 0;
		int axis = 200;
		
		double v = 0; //initial velocity 
		final double m = 1; //mass
		final double iDisplacement = .5; //initial displacement .5 meters
		final double k = 10; // constant depends on spring
		final double dt = .01; // time interval
		double d = iDisplacement; //x is the displacement
		double a; // acceleration
		
		for (int i = 0 ; i < 400; i++){
			for (int j = 1; j <= 10; j++){
				a = -k*d/m;
				v += a*dt;
				d += v*dt;
			}
			
			//prints displacement for every 10 iterations.
			
			int graphD = (int)(d*100)+axis; // 1 pixel = 1 cm.
			x = i;
			y = 0;
			g.fillRect(x, y, 1, graphD);
		}
		
	}
	
	
	public static void main(String[] args)
	{
		//Do not look at the code in the main method
		//Your code will go into the draw method above
		
		JFrame frame = new JFrame();
		
		final int FRAME_WIDTH = 400;
		final int FRAME_HEIGHT = 400;
		
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JComponent component = new JComponent()
		{
			public void paintComponent(Graphics graph)
			{
				draw(graph);
			}
		};
		
		frame.add(component);
		frame.setVisible(true);
		
	}
}


