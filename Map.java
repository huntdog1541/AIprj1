
public class Map {
	
	public Map()
	{
		xboundary = 0;
		yboundary = 0;
		numObstacle = 0;
		numTreasure = 0;
		numAgents = 0;
		Block[][] arry = new Block[16][16];
		System.out.println("Created array 16x16");
	}
	
	public Map(int x, int y)
	{
		xboundary = 0;
		yboundary = 0;
		numObstacle = 0;
		numTreasure = 0;
		numAgents = 0;
		Block[][] arry = new Block[x][y];
	}
	
	int xboundary;
	int yboundary;
	int numObstacle;
	int numTreasure;
	int numAgents;
	
}
