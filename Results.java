import java.util.ArrayList;


public class Results {

	private Stats st;
	private int currentRun;
	private double percentWin;
	private double percentLose;
	private int totalRuns;
	private ArrayList<Stats> list;
	
	
	public Results()
	{
		st = new Stats();
		currentRun = 1;
		percentWin = 0.0;
		percentLose = 0.0;
		totalRuns = 1;
		list = new ArrayList<Stats>();
	}
	
	public Results(int numOfRuns)
	{
		st = new Stats(numOfRuns);
		currentRun = 1;
		percentWin = 0.0;
		percentLose = 0.0;
		totalRuns = numOfRuns;
		list = new ArrayList<Stats>();
	}


	public Stats getSt() {
		return st;
	}

	public void setSt(Stats st) {
		this.st = st;
	}

	public int getCurrentRun() {
		return currentRun;
	}

	public void setCurrentRun(int currentRun) {
		this.currentRun = currentRun;
	}

	public double getPercentWin() {
		return percentWin;
	}

	public void setPercentWin(double percentWin) {
		this.percentWin = percentWin;
	}

	public double getPercentLose() {
		return percentLose;
	}

	public void setPercentLose(double percentLose) {
		this.percentLose = percentLose;
	}

	public int getTotalRuns() {
		return totalRuns;
	}

	public void setTotalRuns(int totalRuns) {
		this.totalRuns = totalRuns;
	}
	
}
