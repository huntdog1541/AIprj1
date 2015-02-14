import java.awt.Color;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class Block {

	boolean isObstacle;				//if the block is an obstacle
	boolean isTreasure;				//if the block is the treasure square
	boolean isAgent;				//if the block is an agent square
	boolean isEntry;				//if the block is the entry block
	boolean hasTreasure;			//
	int trans;						//The number of steps that are taken on the square
	int Xpost;						//X-post is the x-location of the block
	int Ypost;						//Y-post is the y-location of the block
	Color clr;						//The color of the block
	myPanel jp;						//Custom JPanel that draws the information of the square
	
	public Block(int yposition, int xposition) 
	{
		isObstacle = false;
		isTreasure = false;
		isAgent    = false;
		isEntry    = false;
		hasTreasure= false;
		trans 	   = 0;
		jp		   = new myPanel();
		clr 	   = jp.getBackground();
		Ypost = yposition;
		Xpost = xposition;
	}
	
	public JPanel getPane()
	{
		setBlockColor();
		Border blackline = BorderFactory.createLineBorder(Color.black);
		jp.setBorder(blackline);
		jp.setNumber(trans);
		jp.setXandYPost(Ypost, Xpost);
		return jp;
	}
	
	//sets the color of the block
	public void setBlockColor()
	{
		if(isObstacle())
			clr = Color.BLACK;
		else if(isTreasure())
			clr = Color.ORANGE;
		else if(isAgent())
			clr = Color.RED;
		else if(isEntry())
			clr = Color.BLUE;
		jp.setBackground(clr);
	}
	
	//returns true if the square is empty
	public boolean isEmpty()
	{
		boolean ans = true;
		if(isObstacle)
			ans = false;
		else if(isTreasure)
			ans = false;
		else if(isAgent)
			ans = false;
		else if(isEntry)
			ans = false;
		return ans;
	}
	
	//checks to see if the block is an obstacle square
	public boolean isObstacle() {
		return isObstacle;
	}
	
	//sets the block to an obstacle square
	public void setObstacle(boolean isObstacle) {
		this.isObstacle = isObstacle;
	}
	
	//checks to see if the block is a treasure square
	public boolean isTreasure() {
		return isTreasure;
	}
	
	//set the block to a treasure square
	public void setTreasure(boolean isTreasure) {
		this.isTreasure = isTreasure;
	}

	//checks to see if the block is an agent square
	public boolean isAgent() {
		return isAgent;
	}

	//sets the block to an agent square
	public void setAgent(boolean isAgent) {
		this.isAgent = isAgent;
	}

	//checks to see if the block is an Entry square
	public boolean isEntry() {
		return isEntry;
	}

	//sets the block to an Entry square
	public void setEntry(boolean isEntry) {
		this.isEntry = isEntry;
		this.trans = 1;
	}

	//returns the number of steps take on that block
	public int getTrans() {
		return trans;
	}

	//sets the number of steps taken on that block
	public void setTrans(int trans) {
		this.trans = trans;
	}

	//returns the JPanel which is used in the graph class
	public JPanel getJp() {
		return jp;
	}

	/*public void setJp(JPanel jp) {
		this.jp = jp;
	}*/
	
	//helper fucntion that prints information about the block
	public void printBlockInfo()
	{
		System.out.println("Obstacle: "+ isObstacle);
		System.out.println("Treasure: "+ isTreasure);
		System.out.println("Agent: "+ isAgent);
		System.out.println("Entry: "+ isEntry);
		System.out.println("Steps: "+ trans);
	}
	
	//Custom JPanel class that adds extra information about the blocks on the screen
	class myPanel extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = -6055111815409971773L;
		private int number;			//number of steps take on the block
		private int xpost;			//x-position that is printed on the block
		private int ypost;			//y-position that is printed on the block
		
	    public myPanel() {
	  
	    }

		@Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); 
             //drawing(g);
            if(number > 0)
            	g.drawString(Integer.toString(number), 10, 20);
            
            String temp = "( ";
            temp = temp.concat(Integer.toString(ypost));
            temp = temp.concat(", ");
            temp = temp.concat(Integer.toString(xpost));
            temp = temp.concat(")");
            g.drawString(temp, 35, 50);
		}
		
		//sets the number of steps taken on that block
		public void setNumber(int numb)
		{
			number = numb;
		}
		
		//sets the x-position and y-position so it can be printed on the screen
		public void setXandYPost(int Yposition, int Xposition)
		{
			xpost = Xposition;
			ypost = Yposition;
		}
	    
	}
	
}
	
