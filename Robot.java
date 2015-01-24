


public class Robot {
	
	int xposition;
	int yposition;
	int steps;
	Map map;
	static Movement move;
	
	public Robot()
	{
		map = new Map();
		move = new Movement(this, map);
		xposition = 0;
		yposition = 0;
		steps = 0;
	}
	
	public Robot(int x, int y)
	{
		map = new Map(x, y);
		move = new Movement(this, map);
		xposition = 0;
		yposition = 0;
		steps = 0;
	}
	
	public static void main(String[] args) {
		System.out.println("Hello Start");
		Robot robby = new Robot();
		Graph gph = new Graph();
		
		
	}

	public int getXposition()
	{
		return xposition;
	}
	
	public int getYposition()
	{
		return yposition;
	}

	public void setXposition(int xposition) {
		this.xposition = xposition;
	}

	public void setYposition(int yposition) {
		this.yposition = yposition;
	}
	
	
	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public boolean checkAlive()
	{
		boolean ans = true;
		if(map.checkAgent(xposition, yposition))
			ans = false;
		return ans;
	}
	
}
