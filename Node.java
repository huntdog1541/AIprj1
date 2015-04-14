
public class Node {

	private int x;
	private int y;
	private boolean treasure;
	private boolean agent;
	private boolean obstacle;
	private boolean entry;
	private boolean added;
	private int depth;
	private boolean explored;
    private double eval;
	private double bestPath;
	private int interator;
	private int constraitsNumb;
	
	public Node(int numX, int numY)
	{
		x = numX;
		y = numY;
		treasure = false;
		agent = false;
		obstacle = false;
		entry = false;
        eval = 0;
		added = false;
		explored = false;
		constraitsNumb = 10;
	}
	
	public Node(int numX, int numY, Map map)
	{
		x = numX;
		y = numY;
		added = false;
		explored = false;
		constraitsNumb = 0;
        if(map.isValidMove(x, y))
        {
            treasure = map.hasStepTreasure(y, x);
            agent = map.isStandingOnAgent(y, x);
            obstacle = map.isMoveBlocked(y, x);
            entry = map.steppingOnEntry(y, x);
        }
        else
        {
            treasure = false;
            agent = false;
            obstacle = false;
            entry = false;
        }
        evaluateDistance(map);
		genConstraitsNumb();
	}


	public Node(int numX, int numY, Map map, int dpth)
	{
		x = numX;
		y = numY;
		added = false;
		depth = dpth;
		explored = false;
		constraitsNumb = 10;
		if(map.isValidMove(x, y))
		{
			treasure = map.hasStepTreasure(y, x);
			agent = map.isStandingOnAgent(y, x);
			obstacle = map.isMoveBlocked(y, x);
			entry = map.steppingOnEntry(y, x);
		}
		else
		{
			treasure = false;
			agent = false;
			obstacle = false;
			entry = false;
		}
		evaluateDistance(map);
		genConstraitsNumb();
	}

    public boolean isMatch(int OldX, int OldY){
        boolean ans = false;
        if((OldX == x) && (OldY == y))
            ans = true;
        return ans;
    }

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isTreasure() {
		return treasure;
	}

	public void setTreasure(boolean treasure) {
		this.treasure = treasure;
	}

	public boolean isAgent() {
		return agent;
	}

	public void setAgent(boolean agent) {
		this.agent = agent;
	}

	public boolean isObstacle() {
		return obstacle;
	}

	public void setObstacle(boolean obstacle) {
		this.obstacle = obstacle;
	}

	public boolean isEntry() {
		return entry;
	}

	public void setEntry(boolean entry) {
		this.entry = entry;
	}
	
    public double getEval() {
		return eval;
	}

	public void setEval(double eval) {
		this.eval = eval;
	}

	public void evaluateDistance(Map map)
    {
		if(evalObstacle())
		{
			//map.printInfo("The eval for Y: " + y + " X: " + x + " eval: " + eval);
			return;
		}
        int treX, treY;
        treX = map.getTreasureX();
        treY = map.getTreasureY();
        eval = Math.sqrt(Math.pow((x-treX), 2) + Math.pow((y-treY), 2));
        //map.printInfo("The eval for Y: " + y + " X: " + x + " eval: " + eval);
    }

    public void reevaluateDistance(Map map)
    {
        eval = 0;
        if(evalObstacle())
        {
            //map.printInfo("The eval for Y: " + y + " X: " + x + " eval: " + eval);
            return;
        }
        int treX, treY;
        treX = map.getEntryX();
        treY = map.getEntryY();
        eval = Math.sqrt(Math.pow((x-treX), 2) + Math.pow((y-treY), 2));
        //map.printInfo("The eval for Y: " + y + " X: " + x + " eval: " + eval);
    }

	public void updatePathCost(int pathNumber)
	{
		bestPath = eval + (double)pathNumber;
	}

	public double getBestPath()
	{
		return bestPath;
	}
	
	public boolean evalObstacle()
	{
		boolean ans =false;
		if(obstacle)
		{
			eval = 10000;
			ans = true;
		}
		return ans;
	}
	
    public boolean compareEval(Node tp)
    {
    	double temp = tp.getEval();
    	boolean ans = true;
    	if(eval > temp)
    		ans = false;
    	return ans;
    }

	public boolean isAdded() {
		return added;
	}

	public void setAdded(boolean added) {
		this.added = added;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public boolean isExplored() {
		return explored;
	}

	public void setExplored(boolean explored) {
		this.explored = explored;
	}

	public void genConstraitsNumb()
	{
		if(agent)
			constraitsNumb++;
		if(obstacle)
			constraitsNumb++;
	}

	public int getConstraitsNumb() {
		return constraitsNumb;
	}

	public void setConstraitsNumb(int constraitsNumb) {
		this.constraitsNumb = constraitsNumb;
	}
}
