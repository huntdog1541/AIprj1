import java.util.Random;

public class BlindWalk {

	private int xposition;
	private int yposition;
	private int xboundary;
	private int yboundary;
	private int direction;
	Robot rby;
	Map map;
	Random rand;
	
	public BlindWalk(Robot robby, Map mps)
	{
		rby = robby;
		map = mps;
		xposition = robby.getXposition();
		yposition = robby.getYposition();
		rand = new Random();
		xboundary = mps.getXboundary();
		yboundary = mps.getYboundary();
		direction = 0;
		rand = new Random();
		move();
	}
	
	public enum dirct
	{
		NORTH, SOUTH, EAST, WEST
	}
	
	public void moving()
	{
		
	}
	
	public void move()
	{
		updatePosition();
		dirct drt = dirct.NORTH;
		drt = getDirection();
		switch(drt)
		{
		case NORTH: moveNorth(); break;
		case SOUTH: moveSouth(); break;
		case EAST: moveEast(); break;
		case WEST: moveWest(); break;
		}
	}
	
	public dirct getDirection()
	{
		direction = rand.nextInt(4) + 1;
		dirct dt = dirct.NORTH;
		if(direction == 1)
			dt = dirct.NORTH;
		if(direction == 2)
			dt = dirct.SOUTH;
		if(direction == 3)
			dt = dirct.EAST;
		if(direction == 4)
			dt = dirct.WEST;
		return dt;
	}
	
	public void updatePosition()
	{
		xposition = rby.getXposition();
		yposition = rby.getYposition();
	}
	
	public void moveNorth()
	{
		if(xposition == xboundary)
			return;
		//else()
	}
	
	public void moveSouth()
	{
		
	}
	
	public void moveEast()
	{
		
	}
	
	public void moveWest()
	{
		
	}
}
