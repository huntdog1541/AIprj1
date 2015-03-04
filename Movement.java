
public class Movement {

	public BlindWalk walk;
	public DepthWalk walk1;
	public BreadthWalk walk2;
	public HillClimbingWalk walk3;
	public RandomRestartWalk walk4;
    public Graphs graph1;
    public Graphs graph2;
    public Graphs graph3;
    public Graphs graph4;
    public Graphs graph5;
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
        graph1 = new Graphs(map, "Blind Walk");
        map.resetMapSteps();
        walk1 = new DepthWalk(robby, map, log);
        graph2 = new Graphs(map, "Depth Walk");
        map.resetMapSteps();
        walk2 = new BreadthWalk(robby, map, log);
        graph3 = new Graphs(map, "Breadth Walk");
        map.resetMapSteps();
        walk3 = new HillClimbingWalk(robby, map, log);
        graph4 = new Graphs(map, "Hill-Climbing Walk");
        map.resetMapSteps();
        walk4 = new RandomRestartWalk(robby, map, log);
        graph5 = new Graphs(map, "Random Restart Hill-Climbing Walk");
        map.resetMapSteps();
    }

    public void selectiveWalk(Robot robby, Map map)
    {
        if(gi.isBlindWalk())
        {
            walk = new BlindWalk(robby, map, log);
            graph1 = new Graphs(map, "Blind Walk");
            map.resetMapSteps();
        }
        if(gi.isDepthWalk())
        {
            walk1 = new DepthWalk(robby, map, log);
            graph1 = new Graphs(map, "Depth Walk");
            map.resetMapSteps();
        }
        if(gi.isBreadthWalk())
        {
            walk2 = new BreadthWalk(robby, map, log);
            graph1 = new Graphs(map, "Breadth Walk");
            map.resetMapSteps();
        }
        if(gi.isHillClimbWalk())
        {
            walk3 = new HillClimbingWalk(robby, map, log);
            graph1 = new Graphs(map, "Hill-Climbing Walk");
            map.resetMapSteps();
        }
        if(gi.isRandomRestartWalk())
        {
            walk4 = new RandomRestartWalk(robby, map, log);
            graph1 = new Graphs(map, "Random Restart Hill-Climbing Walk");
            map.resetMapSteps();
        }
    }
}
