import java.util.ArrayList;

/**
 * Created by David on 4/3/2015.
 */
public class HeuristicWalk {

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

    public HeuristicWalk()
    {

    }

    public HeuristicWalk(Robot roy, Map mps, Log lg) {
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
        stat = new Stats("Heuristic Walk");
        lg.printResponse("Heuristic Walk\n");
        this.walking();
        stat.printstats();
    }

    public void walking()
    {

    }

    public void getHome()
    {

    }

}
