
public class Node {

	private int x;
	private int y;
	private boolean treasure;
	private boolean agent;
	private boolean obstacle;
	private boolean entry;
    private double eval;
	
	public Node(int numX, int numY)
	{
		x = numX;
		y = numY;
		treasure = false;
		agent = false;
		obstacle = false;
		entry = false;
        eval = 0;
	}
	
	public Node(int numX, int numY, Map map)
	{
		x = numX;
		y = numY;
        if(map.isValidMove(x, y))
        {
            treasure = map.hasStepTreasure(y, x);
            agent = map.isStandingOnAgent(y, x);
            obstacle = map.isMoveBlocked(y, x);
            entry = map.steppingOnEntry(y, x);
        }
        evaluateDistance(map);
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
			map.printInfo("The eval for Y: " + y + " X: " + x + " eval: " + eval);
			return;
		}
        int treX, treY;
        treX = map.getTreasureX();
        treY = map.getTreasureY();
        eval = Math.sqrt(Math.pow((x-treX), 2) + Math.pow((y-treY), 2));
        map.printInfo("The eval for Y: " + y + " X: " + x + " eval: " + eval);
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
}
