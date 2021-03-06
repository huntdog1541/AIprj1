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
    int treasureX;
    int treasureY;
	Block[][] arry;			//the array that holds each individual square
	Random rand;			//Random to get random numbers
    boolean setAgentsOn;
    Log log;
	
	public Map(int xbdary, int ybdary, double obsPer, double agPer, boolean agtOn, Log lg)
	{
		xboundary = xbdary;
		yboundary = ybdary;
		numObstacle = (int)Math.floor((xbdary * ybdary) * obsPer);
 		numTreasure = 1;
		numAgents   = (int)Math.floor((xbdary * ybdary) * agPer);
		entryY		= 0;
		entryX		= 0;
		arry = new Block[xbdary][ybdary];
		rand = new Random();
        setAgentsOn = agtOn;
        log = lg;
		this.initArray();
		this.setMapEntry();
		this.setMapObstacles();
        if(setAgentsOn)
		    this.setMapAgent();
		this.setMapTreasure();
    }
	
	//initializes the array
	public void initArray()
	{
		int i, j;
		for(i = 0; i < xboundary; i++)
		{
			for(j = 0; j < yboundary; j++)
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


	public boolean isSetAgentsOn() {
		return setAgentsOn;
	}

	public void setSetAgentsOn(boolean setAgentsOn) {
		this.setAgentsOn = setAgentsOn;
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
			log.printBoth("Set Obstacle at " + tempY + " and " + tempX);
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

		log.printBoth("Set Treasure at Y " + tempY + " and X " + tempX);
        treasureX = tempX;
        treasureY = tempY;
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
			log.printBoth("Set Agent at " + tempY + " and " + tempX);
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
		log.printBoth("Entry Y is " + entryY + " entryx is "+ entryX);
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
	
	public int getTreasureX() {return this.treasureX; }

    public int getTreasureY() {return this.treasureY; }
    
    //resets the map for random restart
    public void resetMapEntry()
    {
        int ans1 = 0, ans2 = 0, entX = 0, entY = 0;
        arry[entryY][entryX].setEntry(false);
        boolean ans = true;
        while(ans)
        {
            ans1 = rand.nextInt(2);
            ans2 = rand.nextInt(2);
            if(ans1 == 0)
            {
                if(ans2 == 0)
                    entX = rand.nextInt(xboundary);
                else
                    entY = rand.nextInt(yboundary);
            }
            else
            {
                if(ans2 == 0)
                {
                    entX = rand.nextInt(xboundary);
                    entY = yboundary-1;
                }
                else
                {
                    entX = xboundary-1;
                    entY = rand.nextInt(yboundary);
                }
            }
            if((entX != entryX) || (entY != entryY))
            {
                if(isEmpty(entX, entY))
                    ans = false;
            }
        }
        log.printBoth("Entry X is " + entryX + " Entry Y is "+ entryY);
        setEntry();
    }
    
    //checks to see if there is space for a new entry position
    public boolean EntrySpace()
    {
    	boolean ans = false;
    	int tempX = 0, tempY = 0;
    	if(!entryRunThroughY(0, 0))
    		ans = true;
    	if(!entryRunThroughX(0, 0))
    		ans = true;
    	if(!entryRunThroughY((yboundary-1), 0))
    		ans = true;
    	if(!entryRunThroughX(0, (xboundary-1)))
    		ans = true;
    	return ans;
    }
    
    //runs through the map and checks to see if there is a new space for an entry insertion
    public boolean entryRunThroughY(int tempY, int tempX)
    {
    	boolean ans = false;
    	for(; tempY < yboundary; tempY++)
    	{
    		if(arry[tempY][tempX].isEmpty())
    			ans = true;
    	}
    	return ans;
    }
    
    
    public boolean entryRunThroughX(int tempY, int tempX)
    {
    	boolean ans = false;
    	for(; tempX < xboundary; tempX++)
    	{
    		if(arry[tempY][tempX].isEmpty())
    			ans = true;
    	}
    	return ans;
    }
    
    public boolean isEmpty(int x, int y)
    {
        boolean ans = true;
        if(arry[y][x].isAgent())
            ans = false;
        if(arry[y][x].isObstacle())
            ans = false;
        if(arry[y][x].isTreasure())
            ans = false;
        if(arry[y][x].isEntry())
            ans = false;
        return ans;
    }

    public void resetMapSteps()
    {
        int i, j;
        for(i = 0; i < xboundary; i++)
        {
            for(j = 0; j < yboundary; j++)
            {
                arry[i][j].setTrans(0);
            }
        }
    }

    public void printInfo(String... args)
    {
        log.printBoth(args);
    }
}
