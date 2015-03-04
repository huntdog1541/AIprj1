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
        frame.getContentPane().setSize(800, 400);
        gi = new gui(mn);
        frame.getContentPane().add(gi);
        frame.pack();
        log = new Log(gi);
        
    }



    public void startWalking()
    {
    	int rowSize = 0;
    	int columnSize = 0;
    	double percentageAgents = 0;
    	double percentageObstacles = 0;
    	boolean agentsPres = false;
    	
        
        map = new Map(5, .20, .10, false, log);
        robby = new Robot(map, log, gi);
        robby.setWalking();
        System.out.println("Done walking");
        gph = new Graphs(map);
        System.out.println("Creating Graphs");
        gi.setEnabledComponents(true);
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
