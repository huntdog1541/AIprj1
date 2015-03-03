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
    private Log log;

    public BreadthWalk(Robot roy, Map mps, Log lg) {
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
        log = lg;
        log.printResponse("Breadth Walk\n");
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
        int tempIterator = currentIterator, nwX, nwY, i;
        Node temp = list.get(currentIterator);
        direction dt = direction.WEST;
        //Add different Nodes
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
                	   list.add(temp2);
                	   robby.increaseStep(nwY, nwX);
                   }  
        	   }
           }
           dt = getNextDirect(dt);
       }
       //checkAllNodes();
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

        i = 0;
        if(list.size() > 0)
        {
            while ((i < list.size()) && (ans == true))
            {
                temp2 = list.get(i);
                if (temp.isMatch(temp2.getX(), temp2.getY()))
                    ans = true;
                i++;
            }
        }
        return ans;
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
