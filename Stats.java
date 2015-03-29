import javax.swing.*;
import java.awt.*;

public class Stats {

	private int runs;
	private boolean success;
	private boolean fails;
	private int totalSteps;
	private boolean agentsPresent;
	public int stepsTreasure;
	public int stepsHome;
	private String walkType;
	private fieldresults fld;

	public Stats() {
		runs = 0;
		success = false;
		fails = false;
		totalSteps = 0;
	}

	public Stats(String type) {
		walkType = type;
		runs = 0;
		success = false;
		fails = false;
		totalSteps = 0;
		agentsPresent = false;
		stepsTreasure = 0;
		stepsHome = 0;
	}
	
	/*public Stats(boolean suc, boolean fal, int stp)
	{
		runs = 1;
        if(suc)
            success = 1;
        else
		    success = 0;
        if(fal)
		    fails = 1;
        else
            fails = 0;
		totalSteps = stp;
	}*/

	public void updateStat(boolean suc, boolean fal, int stp) {
		totalSteps = stp;
	}

	public void updateSteps() {
		totalSteps = stepsTreasure + stepsHome;
	}

	public boolean isAgentsPresent() {
		return agentsPresent;
	}

	public void setAgentsPresent(boolean agentsPresent) {
		this.agentsPresent = agentsPresent;
	}

	public int getRuns() {
		return runs;
	}

	public void setRuns(int runs) {
		this.runs = runs;
	}

	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean getFails() {
		return fails;
	}

	public void setFails(boolean fails) {
		this.fails = fails;
	}

	public int getTotalSteps() {
		totalSteps = stepsTreasure + stepsHome;
		return totalSteps;
	}

	public void setTotalSteps(int totalSteps) {
		this.totalSteps = totalSteps;
	}

	public int getStepsTreasure() {
		return stepsTreasure;
	}

	public void setStepsTreasure(int stepsTreasure) {
		this.stepsTreasure = stepsTreasure;
	}

	public int getStepsHome() {
		return stepsHome;
	}

	public void setStepsHome(int stepsHome) {
		this.stepsHome = stepsHome;
	}

	public void printstats() {
		fld = new fieldresults();
		updateSteps();
		fld.publish();
	}

	/*public void publish()
	{
		updateSteps();
		JFrame frame = new JFrame(walkType);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(500, 500));
		frame.add(new JTextField("Steps to Treasure " + stepsTreasure));
		frame.add(new JTextField("Steps to Home " + stepsHome));
		frame.add(new JTextField("Agents " + agentsPresent));
		frame.add(new JTextField("Success " + success));
		frame.add(new JTextField("Failed " + fails));
		frame.setVisible(true);
	}*/

	public class fieldresults extends JPanel {
		private JTextField treasureSteps;
		private JTextField homeSteps;
		private JTextField totalStps;
		private JTextField agentsPrest;
		private JTextField successt;
		private JTextField failt;


		public fieldresults() {
			//construct components
			treasureSteps = new JTextField(5);
			homeSteps = new JTextField(5);
			totalStps = new JTextField(5);
			agentsPrest = new JTextField(5);
			successt = new JTextField(5);
			failt = new JTextField(5);

			treasureSteps.setText("Steps to Treasure " + stepsTreasure);
			homeSteps.setText("Steps to Home " + stepsHome);
			totalStps.setText("Total steps is " + totalSteps);
			agentsPrest.setText("Agents present " + agentsPresent);
			successt.setText("Success is " + success);
			failt.setText("Failure is " + fails);


			//adjust size and set layout
			setPreferredSize(new Dimension(936, 499));
			setLayout(null);

			//add components
			add(treasureSteps);
			add(homeSteps);
			add(agentsPrest);
			add(successt);
			add(failt);
			add(totalStps);

			//set component bounds (only needed by Absolute Positioning)
			treasureSteps.setBounds(40, 25, 250, 25);
			homeSteps.setBounds(40, 60, 250, 25);
			agentsPrest.setBounds(40, 100, 250, 25);
			successt.setBounds(40, 135, 250, 25);
			failt.setBounds(40, 170, 250, 25);
			totalStps.setBounds(40, 210, 250, 25);
		}


		public void publish() {
			JFrame frame = new JFrame(walkType);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().add(new fieldresults());
			frame.pack();
			frame.setVisible(true);
		}
	}

}
