import java.util.ArrayList;



public class DepthWalk {

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
	
	public DepthWalk(Robot roy, Map mps) {
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

	public enum direction
	{
		NORTH, SOUTH, EAST, WEST
	}
	
	public void getHome()
	{
		xpost = map.getEntryX();
		ypost = map.getEntryY();
        currentNode = new Node(xpost, ypost, map);
        currentIterator = 0;
	}
	
	public void walking()
	{
		Node temp = list.get(0);
        while(!temp.isTreasure())
        {
            addNext();
            list.remove(0);
            store.add(temp);
            temp = list.get(0);
        }
        
        /*
        while(!temp.isEntry())
        {
            addNext();
            list.remove(0);
            store.add(temp);
            temp = list.get(0);
        }*/
	}
	
	public void addNext()
	{
        int tempIterator = currentIterator, nwX, nwY, i;
        Node temp = list.get(currentIterator);
        //Add different Nodes
        Node node1 = new Node(getNextX(temp, direction.WEST), getNextY(temp, direction.WEST), map);
        if(okayMove(node1))
           possibleAdd(node1);
        Node node2 = new Node(getNextX(temp, direction.EAST), getNextY(temp, direction.EAST), map);
        if(okayMove(node2))
            possibleAdd(node2);
        Node node3 = new Node(getNextX(temp, direction.NORTH), getNextY(temp, direction.NORTH), map);
        if(okayMove(node3))
            possibleAdd(node3);
        Node node4 = new Node(getNextX(temp, direction.SOUTH), getNextY(temp, direction.SOUTH), map);
        if(okayMove(node4))
            possibleAdd(node4);


        checkAllNodes();
	}

    public boolean okayMove(Node temp)
    {
        boolean ans = true;
        if(!map.isValidMove(temp.getX(), temp.getY()))
            ans = false;
        return ans;
    }

    public void possibleAdd(Node temp)
    {
        if(map.isMoveBlocked(temp.getX(), temp.getY()))
            robby.increaseStep(temp.getX(), temp.getY());
        if(map.isStandingOnAgent(temp.getY(), temp.getY()))
            robby.checkAlive();
        if(map.hasStepTreasure(temp.getX(), temp.getY()))
            robby.hasTreasure();
        if(map.steppingOnEntry(temp.getX(), temp.getY()))
            robby.atEntry();
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
		return x;
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
		return y;
	}

    public void checkAllNodes()
    {
        int tempIterator1 = 0, tempIterator2 = 0;
        int oldX, oldY, nwX, nwY;
        Node outerNode = store.get(tempIterator1);
        Node innerNode;
        boolean exit = false;
        while((outerNode != null) || (tempIterator2 < store.size()) || (exit == false))
           {
             oldX = outerNode.getX();
             oldY = outerNode.getY();
             tempIterator2 = 0;
             innerNode = list.get(tempIterator2);
             while((innerNode != null) || (tempIterator2 < list.size()) || (exit == false))
             {
                if(outerNode.isMatch(innerNode.getX(), innerNode.getY())) {
                    list.remove(tempIterator2);
                    exit = true;
                 }
                tempIterator2++;
                innerNode = list.get(tempIterator2);
            }
            tempIterator1++;
            outerNode = store.get(tempIterator1);
            }
    }
}
