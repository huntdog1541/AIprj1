import java.util.ArrayList;

/**
 * Created by David on 4/3/2015.
 */
public class HeuristicWalk {

    private Robot robby;
    private Map map;
    private ArrayList<Node> store;
    private ArrayList<Node> list;
    private ArrayList<Node> possibleMoves;
    private int pathCost;
    private int stepsToTreasure;
    private int stepsToHome;
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

    public HeuristicWalk()
    {

    }

    public HeuristicWalk(Robot roy, Map mps, Log lg) {
        robby = roy;
        map = mps;
        store = new ArrayList<Node>();
        list = new ArrayList<Node>();
        possibleMoves = new ArrayList<Node>();
        stepsToHome = 0;
        stepsToTreasure = 0;
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
        stat = new Stats("Heuristic Walk", map);
        lg.printResponse("Heuristic Walk\n");
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
        stepsToTreasure++;
        while(searchingTreasure(temp, running))
        {
            addNodes(temp);
            temp = getNextNode();
            robby.increaseStep(temp.getY(), temp.getX());
            stepsToTreasure++;
            findTreasure(temp);
            findAgent(temp);
        }
        reportTreasure();
        NodeNumber = 1;
        temp = new Node(map.getTreasureX(), map.getTreasureY());
        temp.reevaluateDistance(map);
        stepsToHome++;
        //then place possible nodes in queue
        //calculate the best possible movement
        while(searchingHome(temp, running))
        {
            addNodes(temp);
            temp = getNextHomeNode();
            robby.increaseStep(temp.getY(), temp.getX());
            stepsToHome++;
            findTreasure(temp);
            findAgent(temp);
        }
        reportHome();
    }

    public void findHome(Node temp)
    {
        if(temp.isEntry())
            foundHome = true;
    }

    public  void findTreasure(Node temp)
    {
        if(temp.isTreasure())
           foundTreasure = true;
    }

    public void findAgent(Node temp)
    {
        if(temp.isAgent())
           foundAgent = true;
    }

    public void addNodes(Node temp)
    {
        
        addNodes(direction.NORTH, temp);
        addNodes(direction.SOUTH, temp);
        addNodes(direction.EAST, temp);
        addNodes(direction.WEST, temp);
    }

    public Node getNextNode()
    {
        Node ans = null;
        int i = 0;
        for(Node tp : possibleMoves)
        {
            if(tp != null)
            {
                if(ans == null)
                    ans = possibleMoves.get(i);
                else if(ans.getConstraitsNumb() > possibleMoves.get(++i).getConstraitsNumb())
                {
                    ans = possibleMoves.get(i);
                }
            }
        }
        possibleMoves.clear();
        return ans;
    }

    public Node getNextHomeNode() {
        Node ans = null;
        int i = 0;
        for(Node tp : possibleMoves)
        {
            tp.reevaluateDistance(map);
            if(ans == null)
                ans = possibleMoves.get(i);
             else if(ans.getConstraitsNumb() > possibleMoves.get(++i).getConstraitsNumb())
             {
                ans = possibleMoves.get(i);
             }
        }
        possibleMoves.clear();
        return ans;
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
        if(temp == null)
            return false;
        if(running == false)
            return false;
        else if(temp.isAgent())
        {
            running = false;
            return false;
        }
        else if(temp.isTreasure())
        {
            running = false;
            foundTreasure = true;
            return false;
        }
        else if(robby.getSteps() > 10000)
        {
            running = false;
            return false;
        }
        else
            return true;
    }

    public boolean searchingHome(Node temp, boolean running)
    {
        if(running == false)
            return false;
        else if(temp.isAgent())
        {
            running = false;
            return false;
        }
        else if(temp.isEntry())
        {
            running = false;
            foundHome = true;
            return false;
        }
        else if(robby.getSteps() > 10000)
        {
            running = false;
            return false;
        }
        else
            return true;
    }

    public void addNodes(direction dirt, Node temp)
    {
        if(dirt == direction.NORTH)
        {
            addNextNode((temp.getX()), (temp.getY() - 1));
        }
        else if(dirt == direction.SOUTH)
        {
            addNextNode((temp.getX()), (temp.getY() + 1));
        }
        else if(dirt == direction.EAST)
        {
            addNextNode((temp.getX() + 1), (temp.getY()));
        }
        else if(dirt == direction.WEST)
        {
            addNextNode((temp.getX() - 1), (temp.getY()));
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
                tp.evaluateDistance(map);
                possibleMoves.add(tp);
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
                tp.evaluateDistance(map);
                possibleMoves.add(tp);
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
            stat.setStepsTreasure(stepsToTreasure);
            robby.resetSteps();
            log.printBoth("Robby found Treasure");
        }
        else if(foundAgent)
        {
            stat.setTotalSteps(stepsToTreasure);
            stat.setFails(true);
            robby.resetSteps();
            log.printBoth("Robby found Agent");
        }
        else if(running == false)
        {
            stat.setTotalSteps(stepsToTreasure);
            stat.setFails(true);
            robby.resetSteps();
            log.printBoth("Robby ran out of steps");
        }
        else if(robby.getSteps() >= 10000)
        {
            stat.setTotalSteps(stepsToTreasure);
            stat.setFails(true);
            robby.resetSteps();
            log.printBoth("Robby ran out of steps");
        }

    }

    public void reportHome()
    {
        if(foundHome)
        {
            stat.setStepsHome(stepsToTreasure);
            stat.setTotalSteps(robby.getSteps());
            stat.setSuccess(true);
            robby.resetSteps();
            log.printBoth("Robby found home");
        }
        else if(foundAgent)
        {
            stat.setStepsHome(stepsToHome);
            stat.setTotalSteps(robby.getSteps());
            stat.setFails(true);
            robby.resetSteps();
            log.printBoth("Robby found Agent");
        }
        else if(running == false)
        {
            stat.setStepsHome(stepsToHome);
            stat.setTotalSteps(robby.getSteps());
            stat.setFails(true);
            robby.resetSteps();
            log.printBoth("Robby ran out of steps");
        }

    }

}