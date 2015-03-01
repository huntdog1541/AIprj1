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

	public HillClimbingWalk(Robot roy, Map mps) {
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
        this.walking();
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
        if(robby.getSteps() == 10000)
            System.out.println("out of steps");
        if(currentNode != null)
        	if(currentNode.isTreasure())
        		System.out.println("has treasure");
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
