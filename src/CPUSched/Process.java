package CPUSched;

public class Process {

	private int pid;
	private int submissionTime;
	private int burstTime;
	
	
	public Process (int pid, int submissionTime, int burstTime){
		
		this.pid = pid;
		this.submissionTime = submissionTime;
		this.burstTime = burstTime;
		
	} 
	
	public int getPID() {
		return this.pid;
	}
	
	public int getSubmissionTime(){
		return this.submissionTime;
	} 
	
	public int getBurstTime(){
		return this.burstTime;
	}
	
	public void decreseBurstTime() {
		this.burstTime--;
	}
	
	public String toString(){
		return "pid[" + this.pid + "] submissionTime[" + this.submissionTime + "] burstTime[" + this.burstTime + "]";
	}
}
