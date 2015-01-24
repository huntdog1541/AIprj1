import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Graph {

	JFrame frame;
	
	public Graph()
	{
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(16, 16));
		frame.add(new Pane());
		frame.pack();
		frame.setVisible(true);
	}

	public class Pane extends JPanel
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public Pane(){
		
		}
		
		public Dimension getPreferredSize()
		{
			return new Dimension(500, 500);
		}
		
		
	}

}
