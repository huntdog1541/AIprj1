import java.util.Random;


public class BlindWalk {

	
	private int xposition;
	private int yposition;
	private int direction;
	Robot robby;
	Map map;
	Random rand;
    private Log log;
	
	public BlindWalk(Robot rby, Map mps, Log lg)
	{
		robby = rby;
		map = mps;
		xposition = map.getEntryX();
		robby.setXposition(xposition);
		yposition = map.getEntryY();
		robby.setYposition(yposition);
		direction = 0;
		rand = new Random();
        log = lg;
        lg.printResponse("Blind Walk");
		move();
	}
	
	//initializes the move loops that search for the treasure loop and entry loop
	public void move()
	{
		getTreasure();
		if(robby.isAlive())
			log.printBoth("The number of steps to find the treasure " + robby.getSteps());
		findEntry();
		log.printBoth("The number of steps is " + robby.getSteps());
	}
	
	//
	public void getTreasure()
	{
		int nextX = xposition, nextY = yposition;
		dirct nextDirection = dirct.EAST;
		while((robby.isAlive()) && (!robby.hasTreasure()) && (robby.getSteps() < 100))
		{
			nextX = xposition;
			nextY = yposition;
			nextDirection = getDirection();
			switch(nextDirection)
			{
				case NORTH :
					if(seekNorth(nextY, nextX))
						moveNorth(nextY, nextX);
					break;
				case SOUTH:
					if(seekSouth(nextY, nextX))
						moveSouth(nextY, nextX);
					break;
				case EAST: 
					if(seekEast(nextY, nextX))
						moveEast(nextY, nextX);
					break;
				case WEST: 
					if(seekWest(nextY, nextX))
						moveWest(nextY, nextX);
					break;
			}
			log.printBoth("Y: " + yposition + " X: " + xposition);
		}
	}
	
	private void updatePosition()
	{	
		robby.setXposition(xposition);
		robby.setYposition(yposition);
	}

	public void findEntry()
	{
		int nextX = xposition, nextY = yposition;
		dirct nextDirection = dirct.EAST;
		while((robby.isAlive()) && (!robby.atEntry()) && (robby.getSteps() < 100))
		{
			nextX = xposition;
			nextY = yposition;
			nextDirection = getDirection();
			switch(nextDirection)
			{
				case NORTH :
					if(seekNorth(nextY, nextX))
						moveNorth(nextY, nextX);
				case SOUTH:
					if(seekSouth(nextY, nextX))
						moveSouth(nextY, nextX);
					break;
				case EAST: 
					if(seekEast(nextY, nextX))
						moveEast(nextY, nextX);
					break; 
				case WEST: 
					if(seekWest(nextY, nextX))
						moveWest(nextY, nextX);
					break;
			}
			log.printBoth("Y: " + yposition + " X: " + xposition);
		}
	}
	
	
	public enum dirct
	{
		NORTH, SOUTH, EAST, WEST
	}
	
	public dirct getDirection()
	{
		direction = rand.nextInt(4) + 1;
		dirct dt = dirct.NORTH;
		if(direction == 1)
			dt = dirct.NORTH;
		else if(direction == 2)
			dt = dirct.SOUTH;
		else if(direction == 3)
			dt = dirct.EAST;
		else if(direction == 4)
			dt = dirct.WEST;
		return dt;
	}
	
	public boolean seekNorth(int currY, int currX)
	{
		int tempY = currY , tempX = currX - 1;
		boolean ans = true;
		if(!map.isValidMove(tempY, tempX))
			ans = false;		
		return ans;
	}
	
	public boolean seekSouth(int currY, int currX)
	{
		int tempY = currY , tempX = currX + 1;
		boolean ans = true;
		if(!map.isValidMove(tempY, tempX))
			ans = false;		
		return ans;
	}
	
	public boolean seekEast(int currY, int currX)
	{
		int tempY = currY + 1, tempX = currX;
		boolean ans = true;
		if(!map.isValidMove(tempY, tempX))
			ans = false;		
		return ans;
	}
	
	public boolean seekWest(int currY, int currX)
	{
		int tempY = currY - 1, tempX = currX;
		boolean ans = true;
		if(!map.isValidMove(tempY, tempX))
			ans = false;		
		return ans;
	}
	
	//function that lets the robot know if it can move there or not due to a obstacle
	public boolean canMove(int nextY, int nextX)
	{
		boolean ans = true;
		if(map.isValidMove(nextY, nextX))
		{
			if(map.isMoveBlocked(nextY, nextX))
				ans = false;
		}
		return ans;
	}
	
	public String getDirectionString(dirct direction)
	{
		String temp = null;
		if(direction == dirct.NORTH)
			temp = "North";
		else if(direction == dirct.SOUTH)
			temp = "South";
		else if(direction == dirct.EAST)
			temp = "East";
		else if(direction == dirct.WEST)
			temp = "West";
		return temp;
	}
	
	public void moveNorth(int nextY, int nextX)
	{
		int tempY = nextY, tempX = nextX - 1;
		moveSteps(tempY, tempX);
	}
	
	public void moveSouth(int nextY, int nextX)
	{
		int tempY = nextY, tempX = nextX + 1;
		moveSteps(tempY, tempX);
	}
	
	public void moveEast(int nextY, int nextX)
	{
		int tempY = nextY + 1, tempX = nextX;
		moveSteps(tempY, tempX);
	}
	
	public void moveWest(int nextY, int nextX)
	{
		int tempY = nextY - 1, tempX = nextX;
		moveSteps(tempY, tempX);
	}
	
	public void moveSteps(int tempY, int tempX) 
	{
		if(map.isValidMove(tempY, tempX))
		{
			if(canMove(tempY, tempX))
			{
				xposition = tempX;
				yposition = tempY;
				this.updatePosition();
				robby.checkAlive();
			}
			robby.increaseStep(tempY, tempX);
			robby.pickUpTreasure();
		}
	}
}
