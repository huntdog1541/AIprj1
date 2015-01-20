


public class Robot {
	
	int xposition;
	int yposition;
	int steps;
	Map map;
	
	public Robot()
	{
		map = new Map();
		xposition = 0;
		yposition = 0;
		steps = 0;
	}
	
	public Robot(int x, int y)
	{
		map = new Map(x, y);
		xposition = 0;
		yposition = 0;
		steps = 0;
	}
	
	public static void main(String[] args) {
		System.out.println("Hello Start");
		Robot robby = new Robot();
		//Graph gph = new Graph();
	}

}
