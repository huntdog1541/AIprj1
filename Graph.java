import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Graph {

	JFrame frame;
	Map map;
	
	public Graph(Map mps)
	{
		map = mps;
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(16, 16));
		updateGraph();
		frame.pack();
		frame.setVisible(true);
	}
	
	public Graph(Map mps, int xbound, int ybound)
	{
		map = mps;
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(xbound, ybound));
		updateGraph();
		frame.pack();
		frame.setVisible(true);
	}
	
	public void updateGraph()
	{
		int xbound = map.getXboundary(), ybound = map.getYboundary(), i = 0, j = 0;
		for(i = 0; i < xbound; i++)
		{
			for(j = 0; j < ybound; j++)
			{
				frame.add(map.getPaneling(i, j));
			}
		}
	}

	public class Pane extends JPanel
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public Pane(){
			this.setPreferredSize(getPreferredSize());
		}
		
		public Dimension getPreferredSize()
		{
			return new Dimension(500, 100);
		}
		
		
	}

}
