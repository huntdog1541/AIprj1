
public class Stats {

	private int runs;
	private int success;
	private int fails;
	private int totalSteps;
	
	public Stats()
	{
		runs = 0;
		success = 0;
		fails = 0;
		totalSteps = 0;
	}

	public int getRuns() {
		return runs;
	}

	public void setRuns(int runs) {
		this.runs = runs;
	}

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}

	public int getFails() {
		return fails;
	}

	public void setFails(int fails) {
		this.fails = fails;
	}

	public int getTotalSteps() {
		return totalSteps;
	}

	public void setTotalSteps(int totalSteps) {
		this.totalSteps = totalSteps;
	}
	
}
