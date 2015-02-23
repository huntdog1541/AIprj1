import java.util.ArrayList;



public class DepthWalk {

	private Robot robby;
	private Map map;
	private ArrayList<Node> nodes;
	private int xpost;
	private int ypost;
	private Node currentNode;
	private int currentIterator;
	
	public DepthWalk(Robot roy, Map mps) {
		robby = roy;
		map = mps;
		nodes = new ArrayList<>();
		xpost = 0;
		ypost = 0;
		getHome();
		Node node = new Node(xpost, ypost);
		nodes.add(node);
		currentIterator = 0;
	}

	public enum direction
	{
		NORTH, SOUTH, EAST, WEST
	}
	
	public void getHome()
	{
		xpost = map.getEntryX();
		ypost = map.getEntryY();
	}
	
	public void walking()
	{
		
	}
	
	public void addNext()
	{
		currentNode = nodes.get(currentIterator);
		//currentIterator++;
		direction dirt = direction.WEST;
		int nextX = getNextX(currentNode, dirt);
		int nextY = getNextY(currentNode, dirt);
		//if()
		
	}
	
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
}
