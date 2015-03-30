import java.util.ArrayList;

/**
 * Created by Admin on 3/22/2015.
 */
public class AStarWalk {

	private Robot robby;
    private Map map;
    private ArrayList<Node> store;
    private ArrayList<Node> list;
    private int pathCost;
    private int xpost;
    private int ypost;
    private Node currentNode;
    private int NodeNumber;
    private boolean foundTreasure;
    private boolean foundHome;
    private boolean hitWall;
    private boolean foundAgent;
    private boolean running;
    private Stats stat;
    private Log log;

     public AStarWalk(Robot roy, Map mps, Log lg) {
        robby = roy;
        map = mps;
        store = new ArrayList<Node>();
        list = new ArrayList<Node>();
        xpost = 0;
        ypost = 0;
        pathCost = 0;
        NodeNumber = 0;
        getHome();
        foundTreasure = false;
        hitWall = false;
        foundAgent = false;
         foundHome = false;
        log = lg;
         stat = new Stats("A* Walk");
        lg.printResponse("A* Walk\n");
        this.walking();
         stat.printstats();
    }

    public void walking()
    {
        log.printResponse("Walking started");
        Node temp = currentNode;
        NodeNumber++;
        double bstPt = 1000.0;
        running = true;
        robby.increaseStep(temp.getY(), temp.getX());
        //if current nodes does not have treasure or agent
        while(searchingTreasure(temp, running))
        {
            addNodes(direction.WEST, temp);
            addNodes(direction.EAST, temp);
            addNodes(direction.NORTH, temp);
            addNodes(direction.SOUTH, temp);
            updatePath();
            bstPt = findBestPath();
            temp = getNextBest(bstPt);
            if(temp == null)
            {
            	System.out.println("Exit loop");
            	return;
            }
            NodeNumber++;
            //robby.increaseStep(temp.getX(), temp.getY());
            
            //log.printResponse("Inside Loop");
            bstPt = 10000.0;
        }
        reportTreasure();
        NodeNumber = 1;
        list.clear();
        temp = new Node(map.getTreasureX(), map.getTreasureY());
        temp.reevaluateDistance(map);
        //then place possible nodes in queue
        //calculate the best possible movement
        while(searchingHome(temp, running))
        {
            addHomeNodes(direction.WEST, temp);
            addHomeNodes(direction.EAST, temp);
            addHomeNodes(direction.NORTH, temp);
            addHomeNodes(direction.SOUTH, temp);
            updatePath();
            bstPt = findBestPath();
            temp = getNextBest(bstPt);
            if(temp == null)
            {
                System.out.println("Exit loop");
                return;
            }
            NodeNumber++;
            //robby.increaseStep(temp.getX(), temp.getY());

            //log.printResponse("Inside Loop");
            bstPt = 10000.0;
        }
        reportHome();
    }

    public void updatePath()
    {
        for(Node temp : list)
        {
            temp.updatePathCost(NodeNumber);
        }
    }

    public double findBestPath()
    {
        double ans = 1000.0;
        for(Node temp : list)
        {
            if(temp.getBestPath() < ans)
                ans = temp.getBestPath();
        }
        return ans;
    }

    public Node getNextBest(double bstPath)
    {
        for(Node temp : list)
        {
            if(temp.getBestPath() == bstPath) {
                return list.remove(list.indexOf(temp));
            }
        }
        return null;
    }

    public boolean searchingTreasure(Node temp, boolean running)
    {
        if(running == false)
            return false;
        else if(temp.isAgent())
            return false;
        else if(temp.isTreasure())
            return false;
        else
            return true;
    }

    public boolean searchingHome(Node temp, boolean running)
    {
        if(running == false)
            return false;
        else if(temp.isAgent())
            return false;
        else if(temp.isEntry())
            return false;
        else
            return true;
    }

    public void addNodes(direction dirt, Node temp)
    {
        if(dirt == direction.NORTH)
        {
            addNextNode((temp.getX()), (temp.getY()-1));
        }
        else if(dirt == direction.SOUTH)
        {
            addNextNode((temp.getX()), (temp.getY()+1));
        }
        else if(dirt == direction.EAST)
        {
            addNextNode((temp.getX()+1), (temp.getY()));
        }
        else if(dirt == direction.WEST)
        {
            addNextNode((temp.getX()-1), (temp.getY()));
        }
    }

    public void addHomeNodes(direction dirt, Node temp)
    {
        if(dirt == direction.NORTH)
        {
            addNextHomeNode((temp.getX()), (temp.getY() - 1));
        }
        else if(dirt == direction.SOUTH)
        {
            addNextHomeNode((temp.getX()), (temp.getY() + 1));
        }
        else if(dirt == direction.EAST)
        {
            addNextHomeNode((temp.getX() + 1), (temp.getY()));
        }
        else if(dirt == direction.WEST)
        {
            addNextHomeNode((temp.getX() - 1), (temp.getY()));
        }
    }

    public void addNextHomeNode(int nwX, int nwY)
    {
        if(map.isValidMove(nwY, nwX))
        {
            if(!map.isMoveBlocked(nwY, nwX))
            {
                Node tp = new Node(nwX, nwY, map);
                tp.reevaluateDistance(map);
                list.add(tp);
                robby.increaseStep(tp.getY(), tp.getX());
            }
        }
    }

    public void addNextNode(int nwX, int nwY)
    {
        if(map.isValidMove(nwY, nwX))
        {
        	if(!map.isMoveBlocked(nwY, nwX))
        	{
        		Node tp = new Node(nwX, nwY, map);
                list.add(tp);
                robby.increaseStep(tp.getY(), tp.getX());
        	}
        }
    }


    public enum direction
    {
        NORTH, SOUTH, EAST, WEST
    }
    public void getHome()
    {
        xpost = map.getEntryX();
        ypost = map.getEntryY();
        currentNode = new Node(xpost, ypost);
        list.add(currentNode);
        NodeNumber = 0;
        System.out.println("The home is at " + xpost + " : " + ypost);
    }

    public void reportTreasure()
    {
        if(foundTreasure)
        {
            stat.setStepsTreasure(robby.getSteps());
            robby.resetSteps();
            log.printBoth("Robby found Treasure");
        }
        else if(foundAgent)
        {
            stat.setTotalSteps(robby.getSteps());
            stat.setFails(true);
            robby.resetSteps();
            log.printBoth("Robby found Agent");
        }
        else if(running == false)
        {
            stat.setTotalSteps(robby.getSteps());
            stat.setFails(true);
            robby.resetSteps();
            log.printBoth("Robby ran out of steps");
        }

    }

    public void reportHome()
    {
        if(foundHome)
        {
            stat.setStepsHome(robby.getSteps());
            robby.resetSteps();
            log.printBoth("Robby found home");
        }
        else if(foundAgent)
        {
            stat.setTotalSteps(robby.getSteps());
            stat.setFails(true);
            robby.resetSteps();
            log.printBoth("Robby found Agent");
        }
        else if(running == false)
        {
            stat.setTotalSteps(robby.getSteps());
            stat.setFails(true);
            robby.resetSteps();
            log.printBoth("Robby ran out of steps");
        }

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
