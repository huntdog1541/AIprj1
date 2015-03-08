import javax.swing.*;

/**
 * Created by David on 3/3/2015.
 */
public class Main {
    private static gui gi;
    private static Robot robby;
    private static Map map;
    private static Log log;
    private static Graphs gph;

    public Main()
    {

    }

    public static void main(String[] args) {
        Main mn = new Main();
        System.out.println("Hello World!");
        JFrame frame = new JFrame("AI GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.getContentPane().setSize(600, 400);
        gi = new gui(mn);
        log = new Log(gi);
        frame.getContentPane().add(gi);
        frame.pack();

        
    }



    public void startWalking()
    {
    	int rowSize = gi.getRowNumber();
    	int columnSize = gi.getRowNumber();
    	double percentageAgents = ((double)gi.getNumberOfAgents()/100);
    	double percentageObstacles = ((double)gi.getNumberOfObstacles()/100);
    	boolean agentsPres = gi.isAgentsPresent();
    	
    	log.printBoth("The number of agents is " + percentageAgents + " and obstacles " + percentageObstacles);
        
        map = new Map(rowSize, columnSize, percentageObstacles, percentageAgents, agentsPres, log);
        robby = new Robot(map, log, gi);
        robby.setWalking();
        System.out.println("Done walking");
        System.out.println("Creating Graphs");

    }

    public void addToLog(String msg)
    {
        log.addString(msg);
    }

	public static gui getGi() {
		return gi;
	}

	public static void setGi(gui gi) {
		Main.gi = gi;
	}

	public static Robot getRobby() {
		return robby;
	}

	public static void setRobby(Robot robby) {
		Main.robby = robby;
	}

	public static Map getMap() {
		return map;
	}

	public static void setMap(Map map) {
		Main.map = map;
	}

	public static Log getLog() {
		return log;
	}

	public static void setLog(Log log) {
		Main.log = log;
	}

	public static Graphs getGph() {
		return gph;
	}

	public static void setGph(Graphs gph) {
		Main.gph = gph;
	}
    
    
}
