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

	public RandomRestartWalk(Robot roy, Map mps) {
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
        this.walking();
	}

	public enum direction
	{
		NORTH, SOUTH, EAST, WEST
	}
	

    public void walking()
    {
        boolean running = true;
        while(searchingTreasure(currentNode, running))
        {
            System.out.println("At Y: " + currentNode.getY() + " and X: " + currentNode.getX());
            store.add(currentNode);
            currentNode = this.getNextNode(currentNode);
            if(currentNode == null)
            {
            	running = false;
            	System.out.println("Reached local maximum");
            }
        }
        if(robby.getSteps() >= 10000)
            System.out.println("out of steps");
        else if(currentNode != null)
        {
        	if(currentNode.isTreasure())
        		System.out.println("has treasure");
	
        }
    	else if(isEntrySpaceAvailable())
    	{
    		restartHome();
    		this.walking();
    	}
    	else
    		return;		
    
        	
        System.out.println("Entry X : " + map.getEntryX() + " and Y: " + map.getEntryY());
        /*
        while(!temp.isEntry())
        {
            addNext();
            list.remove(0);
            store.add(temp);
            temp = list.get(0);
        }*/
    }

    public boolean searchingTreasure(Node temp, boolean running)
    {
        boolean ans = true;
        if(running == false)
            ans = false;
        else if(robby.getSteps() == 10000)
            ans = false;
        else if(temp.isTreasure())
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
    	System.out.println("Started OVer");
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
    	boolean ans = false;
    	Node temp = null;
    	for(i = 0; i < previousEntries.size(); i++)
    	{
    		temp = previousEntries.get(i);
    		if(temp.isMatch(nwX, nwY))
    			ans = true;
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
