import java.util.ArrayList;

/**
 * Created by Admin on 3/22/2015.
 */
public class AStartWalk {

	private Robot robby;
    private Map map;
    private ArrayList<Node> store;
    private ArrayList<Node> list;
    private int xpost;
    private int ypost;
    private Node currentNode;
    private int currentIterator;
    private boolean foundTreasure;
    private boolean hitWall;
    private boolean foundAgent;
    private Log log;

     public AStarWalk(Robot roy, Map mps, Log lg) {
        robby = roy;
        map = mps;
        store = new ArrayList<Node>();
        list = new ArrayList<Node>();
        xpost = 0;
        ypost = 0;
        getHome();
        foundTreasure = false;
        hitWall = false;
        foundAgent = false;
        log = lg;
        lg.printResponse("A* Walk\n");
        //this.walking();
    }

    public void walking()
    {

    }

    public void getHome()
    {
        xpost = map.getEntryX();
        ypost = map.getEntryY();
        currentNode = new Node(xpost, ypost);
        list.add(currentNode);
        currentIterator = 0;
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
