
public class Movement {

	public BlindWalk walk;
	public DepthWalk walk1;
	public BreadthWalk walk2;
	public HillClimbingWalk walk3;
	public RandomRestartWalk walk4;
    public AStarWalk walk5;
    public IterativeDeepWalk walk6;
    public HeuristicWalk walk7;
    public Graphs graph1;
    public Graphs graph2;
    public Graphs graph3;
    public Graphs graph4;
    public Graphs graph5;
    public Graphs graph6;
    public Graphs graph7;
    public Graphs graph8;
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
        long blindWalkTime = Runtime.getRuntime().totalMemory();
        walk = new BlindWalk(robby, map, log);
        blindWalkTime = Runtime.getRuntime().totalMemory() - blindWalkTime;
        log.printBoth("The total memory for Blind Walk is " + blindWalkTime);
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
        walk5 = new AStarWalk(robby, map, log);
        graph6 = new Graphs(map, "AStar Walk");
        map.resetMapSteps();
        walk6 = new IterativeDeepWalk(robby, map, log);
        graph7 = new Graphs(map, "Iterative Deepening Walk");
        map.resetMapSteps();
        walk7 = new HeuristicWalk(robby, map, log);
        graph8 = new Graphs(map, "Heuristic Walk");
        map.resetMapSteps();
    }

    public void selectiveWalk(Robot robby, Map map)
    {
        if(gi.isBlindWalk())
        {
            long blindWalkTime = Runtime.getRuntime().totalMemory();
            walk = new BlindWalk(robby, map, log);
            blindWalkTime = Runtime.getRuntime().totalMemory() - blindWalkTime;
            log.printBoth("The total memory for Blind Walk is " + blindWalkTime);
            graph1 = new Graphs(map, "Blind Walk");
            map.resetMapSteps();
        }
        if(gi.isDepthWalk())
        {
            long DepthWalkTime = Runtime.getRuntime().totalMemory();
            walk1 = new DepthWalk(robby, map, log);
            DepthWalkTime = Runtime.getRuntime().totalMemory() - DepthWalkTime;
            log.printBoth("The total memory for Depth Walk is " + DepthWalkTime);
            graph2 = new Graphs(map, "Depth Walk");
            map.resetMapSteps();
        }
        if(gi.isBreadthWalk())
        {
            long BreadthWalkTime = Runtime.getRuntime().totalMemory();
            walk2 = new BreadthWalk(robby, map, log);
            BreadthWalkTime = Runtime.getRuntime().totalMemory() - BreadthWalkTime;
            log.printBoth("The total memory for Breadth Walk is " + BreadthWalkTime);
            graph3 = new Graphs(map, "Breadth Walk");
            map.resetMapSteps();
        }
        if(gi.isHillClimbWalk())
        {
            long HillClimbWalkTime = Runtime.getRuntime().totalMemory();
            walk3 = new HillClimbingWalk(robby, map, log);
            HillClimbWalkTime = Runtime.getRuntime().totalMemory() - HillClimbWalkTime;
            log.printBoth("The total memory for Hill Climbing Walk is " + HillClimbWalkTime);
            graph4 = new Graphs(map, "Hill-Climbing Walk");
            map.resetMapSteps();
        }
        if(gi.isRandomRestartWalk())
        {
            long RandomRestartWalkTime = Runtime.getRuntime().totalMemory();
            log.printBoth("The start memory is " + RandomRestartWalkTime);
            walk4 = new RandomRestartWalk(robby, map, log);
            long endWalkTime = Runtime.getRuntime().totalMemory();
            RandomRestartWalkTime = endWalkTime - RandomRestartWalkTime;
            log.printBoth("The end memory is " + endWalkTime);
            log.printBoth("The total memory for Random Restart Walk is " + RandomRestartWalkTime);
            graph5 = new Graphs(map, "Random Restart Hill-Climbing Walk");
            map.resetMapSteps();
        }
        if(gi.isAstarWalk())
        {
            long AStarWalkTime = Runtime.getRuntime().totalMemory();
            log.printBoth("The start memory is " + AStarWalkTime);
            walk5 = new AStarWalk(robby, map, log);
            long endWalkTime = Runtime.getRuntime().totalMemory();
            AStarWalkTime = endWalkTime - AStarWalkTime;
            log.printBoth("The end memory is " + endWalkTime);
            log.printBoth("The total memory for A * Walk is " + AStarWalkTime);
            graph6 = new Graphs(map, "AStar Walk");
            map.resetMapSteps();
        }
        if(gi.isIterativeDeepingWalk())
        {
            long IterativeDeepWalkTime = Runtime.getRuntime().totalMemory();
            log.printBoth("The start memory is " + IterativeDeepWalkTime);
            walk6 = new IterativeDeepWalk(robby, map, log);
            long endWalkTime = Runtime.getRuntime().totalMemory();
            IterativeDeepWalkTime = endWalkTime - IterativeDeepWalkTime;
            log.printBoth("The end memory is " + endWalkTime);
            log.printBoth("The total memory for Iterative Deepening Walk is " + IterativeDeepWalkTime);
            graph7 = new Graphs(map, "Iterative Deepening Walk");
            map.resetMapSteps();
        }
        if(gi.isHeuristicWalk())
        {
            long IterativeDeepWalkTime = Runtime.getRuntime().totalMemory();
            log.printBoth("The start memory is " + IterativeDeepWalkTime);
            walk7 = new HeuristicWalk(robby, map, log);
            long endWalkTime = Runtime.getRuntime().totalMemory();
            IterativeDeepWalkTime = endWalkTime - IterativeDeepWalkTime;
            log.printBoth("The end memory is " + endWalkTime);
            log.printBoth("The total memory for Iterative Deepening Walk is " + IterativeDeepWalkTime);
            graph8 = new Graphs(map, "Iterative Deepening Walk");
            map.resetMapSteps();
        }
    }
}
