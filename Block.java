
public class Block {
	
	boolean isObstacle;
	boolean isTreasure;
	boolean isAgent;
	boolean isEntry;
	
	public Block() 
	{
		isObstacle = false;
		isTreasure = false;
		isAgent    = false;
		isEntry    = false;
	}

	public boolean isObstacle() {
		return isObstacle;
	}

	public void setObstacle(boolean isObstacle) {
		this.isObstacle = isObstacle;
	}

	public boolean isTreasure() {
		return isTreasure;
	}

	public void setTreasure(boolean isTreasure) {
		this.isTreasure = isTreasure;
	}

	public boolean isAgent() {
		return isAgent;
	}

	public void setAgent(boolean isAgent) {
		this.isAgent = isAgent;
	}

	public boolean isEntry() {
		return isEntry;
	}

	public void setEntry(boolean isEntry) {
		this.isEntry = isEntry;
	}
	
}
