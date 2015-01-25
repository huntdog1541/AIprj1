import java.util.Random;

import javax.swing.JPanel;


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
		pickEntry();
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
		pickEntry();
	}
	
	public void pickEntry()
	{
		boolean ans = false;
		int xpost = 0, ypost = 0, bord = rand.nextInt(4);
		System.out.println("bord: " + bord);
		while(ans == false)
		{
			if(bord == 0)
				ypost = rand.nextInt(yboundary);
			else if(bord == 1)
				xpost = rand.nextInt(xboundary);
			else if(bord == 2)
			{
				xpost = xboundary - 1;
				ypost = rand.nextInt(yboundary);
			}
			else if(bord == 3)
			{
				ypost = yboundary - 1;
				xpost = rand.nextInt(xboundary);
			}
			ans = notEntry(xpost, ypost);
		}
		System.out.println("X post: " + xpost + " Y post: " + ypost);
		arry[xpost][ypost].setEntry(true);
	}
	
	public boolean notEntry(int xpost, int ypost)
	{
		boolean ans = true;
		if((arry[xpost][ypost]).isAgent())
			ans = false;
		else if((arry[xpost][ypost]).isObstacle())
			ans = false;
		else if((arry[xpost][ypost]).isTreasure())
			ans = false;
		
		return ans;
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
	
	public boolean onObstacle(int xpost, int ypost)
	{
		boolean ans = false;
		if((arry[xpost][ypost]).isObstacle())
			ans = true;
		return ans;
	}
	
	public boolean onAgen(int xpost, int ypost)
	{
		boolean ans = false;
		if((arry[xpost][ypost]).isAgent())
			ans = true;
		return ans;
	}
	
	public boolean onTreasure(int xpost, int ypost)
	{
		boolean ans = false;
		if((arry[xpost][ypost]).isTreasure())
			ans = true;
		return ans;
	}
	
	public boolean onEntry(int xpost, int ypost)
	{
		boolean ans = false;
		if((arry[xpost][ypost]).isEntry())
			ans = true;
		return ans;
	}
	
	public boolean checkObstacle(int xpost, int ypost)
	{
		boolean ans = false;
		if(valid(xpost, ypost))
		{
			if((arry[xpost][ypost]).isObstacle())
				ans = true;
		}
		return ans;
	}
	
	public boolean checkAgent(int xpost, int ypost)
	{
		boolean ans = false;
		if(valid(xpost, ypost))
			
		if((arry[xpost][ypost]).isAgent())
			ans = true;
		return ans;
	}
	
	public boolean checkTreasure(int xpost, int ypost)
	{
		boolean ans =false;
		if((arry[xpost][ypost]).isTreasure)
			ans = true;
		return ans;
	}
	
	public boolean checkEntry(int xpost, int ypost)
	{
		boolean ans = false;
		if((arry[xpost][ypost]).isEntry())
			ans = true;
		return ans;
	}
	
	public JPanel getPaneling(int xpst, int ypst)
	{
		JPanel jp = (arry[xpst][ypst]).getPane();
		return jp;
	}
	
	public boolean valid(int xpost, int ypost)
	{
		boolean ans = false;
		if((xpost > xboundary) && (xpost < 0))
			ans = true;
		if((ypost > yboundary) && (ypost < 0))
			ans = true;
		return ans;
	}
}
