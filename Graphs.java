
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;

public class Graphs
{
	JFrame frame;			//JFrame that holds the JPanels
	Map map;				//Map that points to the Map function
	
	public Graphs(Map mps)
	{
		map = mps;
		frame = new JFrame();
		frame.setPreferredSize(new Dimension(500,500));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridLayout layout = new GridLayout(mps.getboundary(), mps.getboundary());
		frame.setLayout(layout);
		updateGraph();
		frame.pack();
		frame.setVisible(true);
	}
	
	public Graphs(Map mps, int xbound, int ybound)
	{
		map = mps;
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(ybound, xbound));
		updateGraph();
		frame.pack();
		frame.setVisible(true);
	}
	
	//grabs the JPanel() from the blocks
	public void updateGraph()
	{
		int xbound = map.getboundary(), ybound = map.getboundary(), i = 0, j = 0;
		for(i = 0; i < ybound; i++)
		{
			for(j = 0; j < xbound; j++)
			{
				frame.add(map.getPaneling(i, j));
			}
		}
	}
}
