
public class Movement {

	public BlindWalk walk;
	public DepthWalk walk1;
	public BreadthWalk walk2;
	public HillClimbingWalk walk3;
	public RandomRestartWalk walk4;
    private Log log;
	
	
	public Movement()
	{
		
	}

    public Movement(Log lg)
    {
        log = lg;
    }
	
	//decides the movement path the robot uses
	public void setMovement(Robot robby, Map mps)
	{
		//walk = new BlindWalk(robby, mps, log);
		walk1 = new DepthWalk(robby, mps, log);
		//walk2 = new BreadthWalk(robby, mps, log);
		//walk3 = new HillClimbingWalk(robby, mps, log);
		//walk4 = new RandomRestartWalk(robby, mps, log);
	}
}
