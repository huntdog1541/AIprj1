import java.util.ArrayList;


public class HillClimbingWalk {
	
	private Robot robby;
	private Map map;
    private ArrayList<Node> list;
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

	public HillClimbingWalk(Robot roy, Map mps, Log lg) {
		robby = roy;
		map = mps;
        list = new ArrayList<Node>();
        store = new ArrayList<Node>();
        xpost = 0;
        ypost = 0;
        getHome();
        foundTreasure = false;
        hitWall = false;
        foundAgent = false;
        log = lg;
        foundLocalMinimum = true;
        running = true;
        success = false;
        log.printResponse("Hill-Climbing Walk\n");
        this.walking();
	}

    public void walking()
    {
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
    }

    public boolean getSuccess()
    {
        return success;
    }

    public void walkingToTreasure()
    {
        boolean running = true;
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
        list.clear();
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
            currentNode = this.getNextNodeHome(currentNode);;
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
        list.clear();
        store.clear();
    }

    public boolean searchingTreasure(Node temp)
    {
        boolean ans = true;
        if(temp == null)
            return false;
        if(!running)
            return false;
        else if(robby.getSteps() == 10000)
            ans = false;
        else if(temp.isTreasure())
            ans = false;
        return ans;
    }

    public boolean searchingHome(Node temp)
    {
        boolean ans = true;
        if(temp == null)
            return false;
        if(!running)
            ans = false;
        if(robby.getSteps() == 10000)
            ans = false;
        if(temp.isEntry())
            ans = false;
        return ans;
    }

    public void homeEvaluate()
    {
        for(Node n : store)
        {
            n.reevaluateDistance(map);
        }
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
    
	public enum direction
	{
		NORTH, SOUTH, EAST, WEST
	}

    public void getHome()
    {
        xpost = map.getEntryX();
        ypost = map.getEntryY();
        currentNode = new Node(xpost, ypost, map);
        store.add(currentNode);
        currentIterator = 0;
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

    
    public void checkAllNodes()
    {
        int tempIterator1 = 0, tempIterator2 = 0;
        int oldX, oldY, nwX, nwY;
        if((store.size() == 0) || (list.size() == 0))
            return;
        Node outerNode = store.get(tempIterator1);
        Node innerNode;
        Node temp;
        boolean exit = false;
        while((outerNode != null) && (tempIterator2 < store.size()))
        {
            oldX = outerNode.getX();
            oldY = outerNode.getY();
            tempIterator2 = 0;
            innerNode = list.get(tempIterator2);
            while((innerNode != null) && (tempIterator2 < list.size()))
            {
                if((outerNode.isMatch(innerNode.getX(), innerNode.getY())) && (tempIterator2 <= list.size())) {
                    temp = list.remove(tempIterator2);
                    robby.decreaseStep(temp.getX(), temp.getY());
                }
                tempIterator2++;
                if(tempIterator2 < list.size())
                    innerNode = list.get(tempIterator2);
            }
            tempIterator1++;
            if(tempIterator1 < store.size())
                outerNode = store.get(tempIterator1);
        }
    }
}
