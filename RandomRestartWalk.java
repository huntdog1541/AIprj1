import java.util.ArrayList;

public class RandomRestartWalk {
	
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

	public RandomRestartWalk(Robot roy, Map mps) {
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
