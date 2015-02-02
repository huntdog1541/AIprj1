
public class Movement {

	public BlindWalk walk;
	
	public Movement()
	{
		
	}
	
	//decides the movement path the robot uses
	public void setMovement(Robot robby, Map mps)
	{
		walk = new BlindWalk(robby, mps);
	}
}
