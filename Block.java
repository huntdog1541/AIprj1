import java.awt.Color;

import javax.swing.JPanel;


public class Block {
	
	boolean isObstacle;
	boolean isTreasure;
	boolean isAgent;
	boolean isEntry;
	int trans;
	JPanel jp;
	
	public Block() 
	{
		isObstacle = false;
		isTreasure = false;
		isAgent    = false;
		isEntry    = false;
		trans 	   = 0;
		jp		   = new JPanel();
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

	public int getTrans() {
		return trans;
	}

	public void setTrans(int trans) {
		this.trans = trans;
	}
	
	public JPanel getPane()
	{
		if(isObstacle)
			jp.setBackground(Color.BLACK);
		else if(isTreasure)
			jp.setBackground(Color.ORANGE);
		else if(isAgent)
			jp.setBackground(Color.RED);
		else if(isEntry)
			jp.setBackground(Color.BLUE);

		return jp;
	}
}
