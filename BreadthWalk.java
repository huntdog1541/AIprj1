
public class BreadthWalk {
	
	private Robot robby;
	private Map map;

	public BreadthWalk(Robot roy, Map mps) {
		robby = roy;
		map = mps;
	}

	public enum direction
	{
		NORTH, SOUTH, EAST, WEST
	}
}
