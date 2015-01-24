
public class Movement {
	
	public BlindWalk walk;
	
	public Movement(Robot robby, Map mps)
	{
		walk = new BlindWalk(robby, mps);
	}
	
}
