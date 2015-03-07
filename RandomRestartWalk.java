import java.util.ArrayList;

public class RandomRestartWalk {
	
	private Robot robby;
	private Map map;
    private ArrayList<Node> previousEntries;
    private ArrayList<Node> store;
    private int xpost;
    private int ypost;
    private Node currentNode;
    private int currentIterator;
    private boolean foundTreasure;
    private boolean hitWall;
    private boolean foundAgent;
    private boolean foundLocalMinimum;
    private boolean success;
    private boolean running;
    private Log log;

	public RandomRestartWalk(Robot roy, Map mps, Log lg) {
		robby = roy;
		map = mps;
		previousEntries = new ArrayList<Node>();
        store = new ArrayList<Node>();
        xpost = 0;
        ypost = 0;
        getHome();
        foundTreasure = false;
        hitWall = false;
        foundAgent = false;
        log = lg;
        lg.printResponse("Random Restart\n");
        foundLocalMinimum = false;
        success = false;
        running = true;
        this.walking();
	}

	public enum direction
	{
		NORTH, SOUTH, EAST, WEST
	}


    public void walking()
    {
        long RandomRestartWalkTime = Runtime.getRuntime().totalMemory();
        log.printBoth("The start memory is " + RandomRestartWalkTime);


        walkingToTreasure();
        WalkingToHome();
        if(!foundLocalMinimum)
        {
            if(!running)
            {
                if(robby.isAlive())
                    success = true;
            }
        }
        log.printBoth("The number of total steps is " + robby.getSteps());

        long endWalkTime = Runtime.getRuntime().totalMemory();
        RandomRestartWalkTime = endWalkTime - RandomRestartWalkTime;
        log.printBoth("The end memory is " + endWalkTime);
        log.printBoth("The total memory for Random Restart Walk is " + RandomRestartWalkTime);

    }

    public void walkingToTreasure()
    {
        int steps = 1;
        while(searchingTreasure(currentNode))
        {
            log.printBoth("At Y: " + currentNode.getY() + " and X: " + currentNode.getX());
            store.add(currentNode);
            currentNode = this.getNextNode(currentNode);
            if(currentNode == null)
            {
                running = false;
                log.printBoth("Reached local maximum");
            }
            else if(currentNode.isAgent())
            {
                robby.increaseStep(currentNode.getY(), currentNode.getX());
                steps++;
                log.printBoth("Robby is dead");
                running = false;
            }
            else
            {
                robby.increaseStep(currentNode.getY(), currentNode.getX());
                steps++;
            }
        }
        if(robby.getSteps() == 10000)
            log.printResponse("out of steps");
        if(currentNode != null)
        {
            if(currentNode.isTreasure()) {
                log.printBoth("Robby has treasure");
                log.printBoth("The steps to treasure is " + steps);
            }
        }
        else if(isEntrySpaceAvailable())
        {
            restartHome();
            this.walkingToTreasure();
        }
        else
            return;

        previousEntries.clear();
        store.clear();
    }

    public void WalkingToHome()
    {
        int steps = 1;
        foundLocalMinimum = false;
        while(searchingHome(currentNode))
        {
            log.printBoth("At Y: " + currentNode.getY() + " and X: " + currentNode.getX());
            store.add(currentNode);
            currentNode = this.getNextNodeHome(currentNode);
            if(currentNode == null)
            {
                running = false;
                foundLocalMinimum = true;
                log.printBoth("Reached local maximum");
            }
            else if(currentNode.isAgent())
            {
                robby.increaseStep(currentNode.getY(), currentNode.getX());
                steps++;
                log.printBoth("Robby is dead");
                running = false;
            }
            else
            {
                robby.increaseStep(currentNode.getY(), currentNode.getX());
                steps++;
            }
        }
        if(robby.getSteps() >= 10000)
            log.printBoth("out of steps");
        else if(currentNode != null)
        {
            if(currentNode.isEntry())
            {
                log.printBoth("Robby is home");
                log.printBoth("Steps to home: " + steps);
            }  
        }

        previousEntries.clear();
        store.clear();
    }

    public boolean searchingTreasure(Node temp)
    {
        boolean ans = true;
        if(temp == null)
            return false;
        if(!running)
            ans = false;
        if(robby.getSteps() == 10000)
            ans = false;
        if(temp.isTreasure())
            ans = false;
        return ans;
    }

    public boolean searchingHome(Node temp)
    {
        boolean ans = true;
        if(temp == null)
            return false;
        if(!running)
            return false;
        if(robby.getSteps() == 10000)
            ans = false;
        if(temp.isEntry())
            ans = false;
        return ans;
    }

    public Node getNextNode(Node temp)
    {
    	int i, nwX, nwY;
    	direction dt = direction.WEST;
    	Node ans = null;
    	for(i = 0; i < 4; i++)
    	{
    		nwX = getNextX(temp, dt);
            nwY = getNextY(temp, dt);
            if((nwX != temp.getX()) || (nwY != temp.getY()))
            {
         	   if(map.isValidMove(nwY, nwX))
         	   {
         		   Node temp2 = new Node(nwX, nwY, map);
                    if(!checkAdded(temp2))
                    {
                    	if(temp2.compareEval(temp))
                    	{
                    		if(ans == null)
                    			ans = temp2;
                    		else if(temp2.compareEval(ans))
                    			ans = temp2;
                    	}
                    }  
         	   }
            }
            dt = getNextDirect(dt);
    	}
    	return ans;
    }

    public Node getNextNodeHome(Node temp)
    {
        int i, nwX, nwY;
        temp.reevaluateDistance(map);
        direction dt = direction.WEST;
        Node ans = null;
        for(i = 0; i < 4; i++)
        {
            nwX = getNextX(temp, dt);
            nwY = getNextY(temp, dt);
            if((nwX != temp.getX()) || (nwY != temp.getY()))
            {
                if(map.isValidMove(nwY, nwX))
                {
                    Node temp2 = new Node(nwX, nwY, map);
                    temp2.reevaluateDistance(map);
                    if(!checkAdded(temp2))
                    {
                        if(temp2.compareEval(temp))
                        {
                            ans = temp2;
                        }
                    }
                }
            }
            dt = getNextDirect(dt);
        }
        return ans;
    }
    
    public direction getNextDirect(direction dirt)
    {
        direction dt2 = direction.NORTH;
        switch(dirt)
        {
            case NORTH: dt2 = direction.SOUTH; break;
            case SOUTH: dt2 = direction.WEST; break;
            case EAST: dt2 = direction.NORTH; break;
            case WEST: dt2 = direction.EAST; break;
        }
        return dt2;
    }
    
    public boolean checkAdded(Node temp)
    {
        boolean ans = false;
        int i = 0;
        Node temp2;
        if(store.size() > 0)
        {
            while((i < store.size()) && (ans == true))
            {
                temp2 = store.get(i);
                if(temp.isMatch(temp2.getX(), temp2.getY()))
                    ans = true;
                i++;
            }
        }
        return ans;
    }

    //gets the next X value for the next square
    public int getNextX(Node node, direction dirt)
    {
        int x = node.getX();
        switch(dirt)
        {
            case NORTH: x = x - 1; break;
            case SOUTH: x = x + 1; break;
            case EAST: break;
            case WEST: break;
        }
        if(map.isValidMove(x, node.getY()))
            return x;
        else
            return node.getX();
    }

    //gets the next Y value for the next square
    public int getNextY(Node node, direction dirt)
    {
        int y = node.getY();
        switch(dirt)
        {
            case NORTH: break;
            case SOUTH: break;
            case EAST: y = y + 1; break;
            case WEST: y = y - 1; break;
        }
        if(map.isValidMove(node.getX(), y))
            return y;
        else
            return node.getY();
    }


    public void getHome()
    {
        xpost = map.getEntryX();
        ypost = map.getEntryY();
        currentNode = new Node(xpost, ypost, map);
        previousEntries.add(currentNode);
        currentIterator = 0;
    }

    public void restartHome()
    {
    	log.printBoth("Started Over");
    	previousEntries.add(new Node(map.getEntryX(), map.getEntryY(), map));
        map.resetMapEntry();
        while(checkPreviousEntries(map.getEntryY(), map.getEntryX()))
        	map.resetMapEntry();
        store.clear();
        robby.resetSteps();
        currentNode = new Node(map.getEntryX(), map.getEntryY());
        store.add(currentNode);
    }
    
    public boolean checkPreviousEntries(int nwY, int nwX)
    {
    	int i;
    	boolean ans = true;
    	Node temp = null;
    	for(i = 0; i < previousEntries.size(); i++)
    	{
    		temp = previousEntries.get(i);
    		if(temp.isMatch(nwX, nwY))
    			ans = false;
    	}
    	return ans;
    }
    
    public boolean isEntrySpaceAvailable()
    {
    	boolean ans = false;
    	if(map.EntrySpace())
    		ans = true;
    	return ans;
    }
}
