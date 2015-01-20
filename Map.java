import java.util.Random;


public class Map {
	
	int xboundary;
	int yboundary;
	int numObstacle;
	int numTreasure;
	int numAgents;
	int entryX;
	int entryY;
	Block[][] arry;
	Random rand;
	
	public Map()
	{
		xboundary = 16;
		yboundary = 16;
		numObstacle = (int)Math.floor((16 * 16) * .20);
		numTreasure = 1;
		numAgents = (int)Math.floor((16 * 16) * .10);
		arry = new Block[16][16];
		initArray();
		System.out.println("Created array 16x16");
		System.out.println("The obstacles is " + numObstacle + " numAgents "+ numAgents);
		rand = new Random();
		placeTreasure();
		placeObstacles();
		placeAgents();
	}
	
	public Map(int x, int y)
	{
		xboundary = x;
		yboundary = y;
		numObstacle = (int)Math.floor((x * y) * .20);
		numTreasure = 1;
		numAgents = (int)Math.floor((x * y) * .10);
		arry = new Block[x][y];
		initArray();
		System.out.println("The obstacles is " + numObstacle + " numAgents "+ numAgents);
		rand = new Random();
		placeTreasure();
		placeObstacles();
		placeAgents();
	}
	
	public void pickEntry()
	{
		boolean ans = false;
		int xpost, ypost;
		ans = rand.nextBoolean();
		if(ans == false)
		{
			xpost = 0;
			ypost = rand.nextInt(yboundary);
		}
		else
		{
			xpost = rand.nextInt(xboundary);
			ypost = 0;
		}
		arry[xpost][ypost].setEntry(true);
	}
	
	
	public void initArray()
	{
		int i, j;
		for(i = 0; i < xboundary; i++)
		{
			for(j = 0; j < yboundary; j++)
			{
				arry[i][j] = new Block();
			}
		}
	}
	
	public void placeObstacles()
	{
		int i, xpost, ypost;
		boolean placed = false;
		for(i = 0; i < numObstacle; i++)
		{
			do
			{
				xpost = rand.nextInt(xboundary);
				ypost = rand.nextInt(yboundary);
				if((arry[xpost][ypost].isAgent()) == true)
					placed = false;
				if((arry[xpost][ypost].isTreasure()) == true)
					placed = false;
				if((arry[xpost][ypost].isObstacle()) == false)
				{
					placed = true;
					arry[xpost][ypost].setObstacle(true);
				}
			}while(placed == false);
		}
	}
	
	public void placeAgents()
	{
		int i, xpost, ypost;
		boolean placed = false;
		for(i = 0; i < numAgents; i++)
		{
			do
			{
				xpost = rand.nextInt(xboundary);
				ypost = rand.nextInt(yboundary);
				if((arry[xpost][ypost].isObstacle()) == true)
					placed = false;
				if((arry[xpost][ypost].isTreasure()) == true)
					placed = false;
				if((arry[xpost][ypost].isAgent()) == false)
				{
					placed = true;
					arry[xpost][ypost].setAgent(true);
				}
			}while(placed == false);
		}
	}
	
	public void placeTreasure()
	{
		boolean placed = false;
		int xpost, ypost;
		do
		{
			xpost = rand.nextInt(xboundary);
			ypost = rand.nextInt(yboundary);
			if((arry[xpost][ypost].isEntry) == false)
				placed = true;
		}while(placed == false);
		arry[xpost][ypost].setTreasure(true);
	}
	
	public int getXboundary()
	{
		return xboundary;
	}
	
	public int getYboundary()
	{
		return yboundary;
	}
	
}
