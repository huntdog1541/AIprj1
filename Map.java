import java.util.Random;
import javax.swing.JPanel;


public class Map {
	
	int xboundary; 			//defines the x-boundary
	int yboundary;			//defines the y-boundary
	int numObstacle;		//the number of obstacles on the board
	int numTreasure;		//the number of treasures on the board
	int numAgents;			//the number of agents on the board
	int entryX;				//the x-location of the entry block
	int entryY;				//the y-location of the entry block
	Block[][] arry;			//the array that holds each individual square
	Random rand;			//Random to get random numbers
	
	public Map()
	{
		xboundary = 5;
		yboundary = 5;
		numObstacle = (int)Math.floor((xboundary * xboundary) * .20);
 		numTreasure = 1;
		numAgents   = (int)Math.floor((xboundary * xboundary) * .10);
		entryY 		= 0;
		entryX		= 0;
		arry = new Block[5][5];
		rand = new Random();
		this.initArray();
		this.setMapEntry();
		this.setMapObstacles();
		this.setMapAgent();
		this.setMapTreasure();
	}
	
	public Map(int boundary, double obsPer, double agPer)
	{
		xboundary = boundary;
		yboundary = boundary;
		numObstacle = (int)Math.floor((boundary * boundary) * obsPer);
 		numTreasure = 1;
		numAgents   = (int)Math.floor((boundary * boundary) * agPer);
		entryY		= 0;
		entryX		= 0;
		arry = new Block[boundary][boundary];
		rand = new Random();
		this.initArray();
		this.setMapEntry();
		this.setMapObstacles();
		this.setMapAgent();
		this.setMapTreasure();
    }
	
	//initializes the array
	public void initArray()
	{
		int i, j;
		for(i = 0; i < yboundary; i++)
		{
			for(j = 0; j < xboundary; j++)
			{
				arry[i][j] = new Block(i, j);
			}
		}
	}
	
	//sets the entry location
	public void setEntry()
	{
		arry[entryY][entryX].setEntry(true);
	}
	
	//defines the boundary
	public int getboundary()
	{
		return xboundary;
	}

	//gets the JPanel from the block
	public JPanel getPaneling(int i, int j) {
		return arry[i][j].getPane();
	}
	
	//looks for an empty square to place an obstacle on the board
	private void setMapObstacles()
	{
		boolean ans = false;
		int i, tempX = 0, tempY = 0;
		for(i = 0; i < numObstacle; i++)
		{
			while(ans == false)
			{
				tempY = rand.nextInt(yboundary);
				tempX = rand.nextInt(xboundary);
				if(arry[tempY][tempX].isEmpty())
					ans = true;
			}
			arry[tempY][tempX].setObstacle(true);
			System.out.println("Set Obstacle at " + tempX + " and " + tempY);
			//arry[tempX][tempY].printBlockInfo();
			ans = false;
		}
			
	}
	
	//looks for an empty location to place the treasure
	private void setMapTreasure()
	{
		boolean ans = false;
		int tempY = 0, tempX = 0;
		while(ans == false)
		{
			tempY = rand.nextInt(yboundary);
			tempX = rand.nextInt(xboundary);
			if(arry[tempY][tempX].isEmpty())
				ans = true;
		}
		arry[tempY][tempX].setTreasure(true);
		System.out.println("Set Treasure at " + tempY + " and " + tempX);
		//arry[tempX][tempY].printBlockInfo();
		
	}
	
	//looks for an empty spot on the board to place an agent
	private void setMapAgent()
	{
		boolean ans = false;
		int i, tempY = 0, tempX = 0;
		for(i = 0; i < numAgents; i++)
		{
			while(ans == false)
			{
				tempY = rand.nextInt(yboundary);
				tempX = rand.nextInt(xboundary);
				if(arry[tempY][tempX].isEmpty())
					ans = true;
			}
			arry[tempY][tempX].setAgent(true);
			System.out.println("Set Agent at " + tempX + " and " + tempY);
			//arry[tempX][tempY].printBlockInfo();
			ans = false;
		}
	}
	
	private void setMapEntry()
	{
		int ans1 = 0, ans2 = 0;
		ans1 = rand.nextInt(2);
		ans2 = rand.nextInt(2);
		if(ans1 == 0)
		{
			if(ans2 == 0)
				entryX = rand.nextInt(xboundary);
			else
				entryY = rand.nextInt(yboundary);
		}
		else
		{
			if(ans2 == 0)
			{
				entryX = rand.nextInt(xboundary);
				entryY = yboundary-1;
			}
			else
			{
				entryX = xboundary-1;
				entryY = rand.nextInt(yboundary);
			}
		}
		System.out.println("Entry Y is " + entryY + " entryx is "+ entryX);
		setEntry();
	}
	
	//only checks to see if the move is in the array
	public boolean isValidMove(int nextY, int nextX)
	{
		boolean ans = true;
		if((nextY < 0) || (nextY > (yboundary-1)))
			ans = false;
		if((nextX < 0) || (nextX > (xboundary-1)))
			ans = false;
		return ans;
	}
	
	//checks to see if the block that it wants to move to is an obstacle block, which goes back to its original location
	public boolean isMoveBlocked(int nextY ,int nextX)
	{
		boolean ans = false;
		if((arry[nextY][nextX].isObstacle))
			ans = true;
		return ans;
	}
	
	//checks to see if the robot is on the Agent square
	public boolean isStandingOnAgent(int tempY, int tempX)
	{
		boolean ans = false;
		if((arry[tempY][tempX].isAgent))
			ans = true;
		return ans;
	}
	
	//checks to see if the robot is on the Treasure square
	public boolean hasStepTreasure(int tempY, int tempX)
	{
		boolean ans = false;
		if((arry[tempY][tempX].isTreasure()))
			ans = true;
		return ans;
	}
	
	//adds one to the number of steps in the program
	public void accumulateSteps(int tempY, int tempX)
	{
		int step = arry[tempY][tempX].getTrans();
		arry[tempY][tempX].setTrans(++step);
	}

    public void decreaseSteps(int tempX, int tempY)
    {
        int stp = arry[tempY][tempX].getTrans();
        arry[tempY][tempX].setTrans(stp--);
    }
	
	//checks to see if the robot is on the Entry square
	public boolean steppingOnEntry(int tempY, int tempX)
	{
		boolean ans = false;
		if(arry[tempY][tempX].isEntry())
			ans = true;
		return ans;
	}

	public int getEntryX() {
		return entryX;
	}

	public void setEntryX(int entryX) {
		this.entryX = entryX;
	}

	public int getEntryY() {
		return entryY;
	}

	public void setEntryY(int entryY) {
		this.entryY = entryY;
	}
	
	
}
