import java.util.ArrayList;



public class BreadthWalk {

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

    public BreadthWalk(Robot roy, Map mps) {
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
        list.add(currentNode);
        currentIterator = 0;
    }

    public void walking()
    {
        Node temp = list.get(0);
        Node temp2;
        boolean running = true;
        while(searchingTreasure(temp, running))
        {
            System.out.println("At Y: " + temp.getY() + " and X: " + temp.getX());
            addNext();
            temp2 = list.remove(0);
            store.add(temp2);
            if(list.size() != 0)
                temp = list.get(0);
            else {
                System.out.println("Ran out of space in list");
                running = false;
            }
        }
        if(robby.getSteps() == 10000)
            System.out.println("out of steps");
        if(temp.isTreasure())
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

    public void addNext()
    {
        //int tempIterator = currentIterator, nwX, nwY, i;
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
        {
            //robby.increaseStep(temp.getX(), temp.getY());
        }
        else
        {
            //robby.increaseStep(temp.getX(), temp.getY());
            list.add(temp);
        }

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
