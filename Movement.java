
public class Movement {

	public BlindWalk walk;
	public DepthWalk walk1;
	public BreadthWalk walk2;
	public HillClimbingWalk walk3;
	public RandomRestartWalk walk4;
	
	
	public Movement()
	{
		
	}
	
	//decides the movement path the robot uses
	public void setMovement(Robot robby, Map mps)
	{
		//walk = new BlindWalk(robby, mps);
		walk1 = new DepthWalk(robby, mps);
//		walk2 = new BreadthWalk(robby, mps);
//		walk3 = new HillClimbingWalk(robby, mps);
//		walk4 = new RandomRestartWalk(robby, mps);
	}
}
