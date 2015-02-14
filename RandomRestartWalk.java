
public class RandomRestartWalk {
	
	private Robot robby;
	private Map map;

	public RandomRestartWalk(Robot roy, Map mps) {
		robby = roy;
		map = mps;
	}

	public enum direction
	{
		NORTH, SOUTH, EAST, WEST
	}
}
