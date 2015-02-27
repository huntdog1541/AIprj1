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
		
	}
	
	public void addNext()
	{
        int tempIterator = currentIterator, nwX, nwY, i;
        Node temp = list.get(currentIterator);
        for(i = 0; i < 4; i++)
        {

        }
        checkAllNodes();
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
                nwX = innerNode.getX();
                nwY = innerNode.getY();
                if((oldX == nwX) && (oldY == nwY)) {
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
