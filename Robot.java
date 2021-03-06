import java.util.Scanner;


public class Robot {
	
	int xposition;			//x-position of the robot
	int yposition;			//y-position of the robot
	int steps;				//the number of steps that the robot takes
	boolean alive;			//is the robot alive?
	boolean treasure;		//does the robot has the treasure?
	Map map;				//Map class that generates the map
	Movement move;			//move class that decides how the robot moves around the map
    private Log log;
    private gui gi;
	
	//constructor functions
	public Robot(Map mps, Log lg, gui tp)
	{
		xposition = 0;
		yposition = 0;
		steps = 1;
		alive = true;
		treasure = false;
		map = mps;
        log = lg;
        gi = tp;
		move = new Movement(log, gi);
	}

	
	public void running() {

		this.setWalking();
	}
	
	//setWalking() - initializes the blindWalk functions
	public void setWalking()
	{
		move.setMovement(this, map);
	}

	//return x-position of the robot
	public int getXposition() {
		return xposition;
	}

	//sets the x-position of the robot
	public void setXposition(int xposition) {
		this.xposition = xposition;
	}

	//returns the y-position of the robot
	public int getYposition() {
		return yposition;
	}

	//sets the y-position of the robot
	public void setYposition(int yposition) {
		this.yposition = yposition;
	}
	
	//checks the map to see if the robot is on a dangerous square
	public void checkAlive()
	{
		if(map.isStandingOnAgent(yposition, xposition))
		{
			alive = false;
			System.out.println("Robby is dead\n");
		}
	}
	
	//return if the robot is alive
	public boolean isAlive()
	{
		return alive;
	}
	
	//return the robot has the treasure
	public boolean hasTreasure()
	{
		return treasure;
	}
	
	//increases steps for the robot
	public void increaseStep(int tempY, int tempX)
	{
		steps++;
		System.out.println("Step: " + steps);
		map.accumulateSteps(tempY, tempX);
	}
	
	//checks to see if the robot is on the treasure square
	public void pickUpTreasure()
	{
		if(treasure == false)
		{
			if(map.hasStepTreasure(yposition, xposition))
			{
				treasure = true;
				System.out.println("Picked up Treasure\n");
			}	
		}		
	}
	
	//checks to see if the robot is on the entry square
	public boolean atEntry()
	{
		boolean ans = false;
		if(map.steppingOnEntry(yposition, xposition))
			ans = true;
		return ans;
	}
	
	//returns the number of steps
	public int getSteps() {
		return steps;
	}

    public void decreaseStep(int x, int y)
    {
        steps--;
        map.decreaseSteps(x, y);
    }
    
    public void resetSteps()
    {
    	steps = 0;
    }
}
