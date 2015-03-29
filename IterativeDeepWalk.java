import java.util.ArrayList;

/**
 * Created by Admin on 3/22/2015.
 */
public class IterativeDeepWalk {

	private Robot robby;
    private Map map;
    private ArrayList<Node> store;
    private ArrayList<Node> list;
    private ArrayList<Node> currentList;
    private int xpost;
    private int ypost;
    private Node currentNode;
    private int currentLimit;
    private int limitDepth;
    private int currentIterator;
    private boolean foundTreasure;
    private boolean hitWall;
    private boolean foundAgent;
    private boolean running;
    private Stats sta;
    private Log log;

     public IterativeDeepWalk(Robot roy, Map mps, Log lg) {
        robby = roy;
        map = mps;
        store = new ArrayList<Node>();
        list = new ArrayList<Node>();
        currentList = new ArrayList<Node>();
        xpost = 0;
        ypost = 0;
        currentLimit = 1;
        limitDepth = 1;
        running = false;
        foundTreasure = false;
        hitWall = false;
        foundAgent = false;
        log = lg;
        sta = new Stats("Iterative Deepening");
        sta.setAgentsPresent(map.isSetAgentsOn());
        getHome();
        lg.printResponse("Iterative Deep Walk\n");
        this.walking();
    }

    public void walking()
    {
        running = true;
        addHome();
        currentLimit++;
        limitDepth++;
        while(searchingTreasure(currentNode, running))
        {
            if(currentLimit == 1)
            {
                addHome();
                currentLimit++;
            }    
            while((currentLimit < limitDepth) && (running)) {
                for (Node temp : currentList) {
                    exploreNode(temp);
                }
                addNewNodes();
                checkList();
                currentLimit++;
            }
            searchNodes(limitDepth-1);
            checkList();
            currentList.clear();
            currentLimit = 1;
            limitDepth++;
            System.out.println("The limit depth is " + limitDepth);
        }
        report();
        sta.printstats();
    }

    public boolean searchingTreasure(Node temp, boolean running)
    {
        if(robby.getSteps() > 10000)
            return false;
        if(running == false)
            return false;
        else if(temp.isAgent())
            return false;
        else if(temp.isTreasure())
            return false;
        else
            return true;
    }

    public boolean alreadyAdded(int x, int y)
    {
        boolean ans = false;
        for(Node temp : currentList)
        {
            if((temp.getX() == x) && (temp.getY() == y))
                ans = true;
        }
        for(Node temp : store)
        {
            if((temp.getX() == x) && (temp.getY() == y))
                ans = true;
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
        currentNode = new Node(xpost, ypost, map, 1);
        list.add(currentNode);
        currentIterator = 0;
    }

    public void addHome()
    {
        int xpst = map.getEntryX();
        int ypst = map.getEntryY();
        currentNode = new Node(xpst, ypst, map, 1);
        currentList.add(currentNode);
    }

    public void addNodes(direction dirt, Node temp, int currentLimit)
    {
        if(dirt == direction.NORTH)
        {
            addNextNode((temp.getX()), (temp.getY()-1), currentLimit);
        }
        else if(dirt == direction.SOUTH)
        {
            addNextNode((temp.getX()), (temp.getY()+1), currentLimit);
        }
        else if(dirt == direction.EAST)
        {
            addNextNode((temp.getX()+1), (temp.getY()), currentLimit);
        }
        else if(dirt == direction.WEST)
        {
            addNextNode((temp.getX()-1), (temp.getY()), currentLimit);
        }
    }

    public void addNextNode(int nwX, int nwY, int currntLimit)
    {
        if(map.isValidMove(nwY, nwX))
        {
            if(!map.isMoveBlocked(nwY, nwX))
            {
                if(!alreadyAdded(nwX, nwY))
                {
                    Node tp = new Node(nwX, nwY, map, currntLimit);
                    store.add(tp);
                    log.printBoth("Adding Node at Y " + nwY + " and X: " + nwX + " at limit " + currntLimit);
                    robby.increaseStep(nwY, nwX);
                }
            }
        }
    }

    public void searchNodes(int limitDepth)
    {
        boolean ans = false;
        for(Node temp : currentList)
        {
            if(temp.getDepth() == limitDepth)
                checkNodeTreasure(temp);
        }
    }

    public void checkNodeTreasure(Node temp)
    {
        if(temp.isTreasure()) {
            foundTreasure = true;
            running = false;
        }
    }

    public void checkList()
    {
        for(Node temp : currentList)
        {
            if(temp.isAgent()) {
                foundAgent = true;
                running = false;
            }
        }
    }

    public void report()
    {
        if(foundAgent)
        {
            log.printBoth("Robby dead found agent");
            sta.setFails(true);
            if(foundTreasure)
            {
                sta.setStepsHome(robby.getSteps());
            }
            else
                sta.setStepsTreasure(robby.getSteps());
        }
        if(foundTreasure)
        {
            log.printBoth("Robby found treasure");
            sta.setStepsTreasure(robby.getSteps());
        }
    }

    public void exploreNode(Node temp)
    {
        if(!temp.isExplored())
        {
            addNodes(direction.EAST, temp, (temp.getDepth() + 1));
            addNodes(direction.WEST, temp, (temp.getDepth()+ 1));
            addNodes(direction.NORTH, temp, (temp.getDepth() + 1));
            addNodes(direction.SOUTH, temp, (temp.getDepth() + 1));
        }
        temp.setExplored(true);
    }

    public void addNewNodes()
    {
        for(Node temp : store)
        {
            currentList.add(temp);
        }
        store.clear();
    }

}
