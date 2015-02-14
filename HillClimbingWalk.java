
public class HillClimbingWalk {
	
	private Robot robby;
	private Map map;

	public HillClimbingWalk(Robot roy, Map mps) {
		robby = roy;
		map = mps;
	}

	public enum direction
	{
		NORTH, SOUTH, EAST, WEST
	}
}
