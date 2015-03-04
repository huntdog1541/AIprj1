
public class Movement {

	public BlindWalk walk;
	public DepthWalk walk1;
	public BreadthWalk walk2;
	public HillClimbingWalk walk3;
	public RandomRestartWalk walk4;
    private Log log;
    private gui gi;
	
	
	public Movement()
	{
		
	}

    public Movement(Log lg, gui tp)
    {
        log = lg;
        gi = tp;
    }
	
	//decides the movement path the robot uses
	public void setMovement(Robot robby, Map mps)
	{
		if(gi.isAllWalk())
            exeAllWalk(robby, mps);
        else
            selectiveWalk(robby, mps);
	}

    public void exeAllWalk(Robot robby, Map map)
    {
        walk = new BlindWalk(robby, map, log);
        walk1 = new DepthWalk(robby, map, log);
        walk2 = new BreadthWalk(robby, map, log);
        walk3 = new HillClimbingWalk(robby, map, log);
        walk4 = new RandomRestartWalk(robby, map, log);
    }

    public void selectiveWalk(Robot robby, Map map)
    {
        if(gi.isBlindWalk())
            walk = new BlindWalk(robby, map, log);
        if(gi.isDepthWalk())
            walk1 = new DepthWalk(robby, map, log);
        if(gi.isBreadthWalk())
            walk2 = new BreadthWalk(robby, map, log);
        if(gi.isHillClimbWalk())
            walk3 = new HillClimbingWalk(robby, map, log);
        if(gi.isRandomRestartWalk())
            walk4 = new RandomRestartWalk(robby, map, log);
    }
}
