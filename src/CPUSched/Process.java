package CPUSched;

import java.lang.Comparable;

public class Process implements Comparable<Process> {

	private int pid;
	private int submissionTime;
	private int burstTime;
	private int replyTime;
	
	
	public Process (int pid, int submissionTime, int burstTime){
		
		this.pid = pid;
		this.submissionTime = submissionTime;
		this.burstTime = burstTime;
		
	}

    @Override
	public int compareTo(Process b) {
        if (burstTime > b.burstTime)
            return 1;

        if (burstTime == b.burstTime) {
            if (submissionTime > b.submissionTime)
                return 1;
            if (submissionTime == b.submissionTime)
                return 0;
            if (submissionTime < b.submissionTime)
                return -1;
        }

        return -1;
	}


	public int getPID() {
		return this.pid;
	}
	
	public int getSubmissionTimestamp(){
		return this.submissionTime;
	} 
	
	public int getBurstDuration(){
		return this.burstTime;
	}
	
	public void decreaseBurstDuration() {
		this.burstTime--;
	}

	public int getReplyTimestamp(){
		return this.replyTime;
	}
	
	public void setReplyTimestamp(int replyTime){
		this.replyTime = replyTime;
	}
	
	public String toString(){
		return "\npid[" + this.pid + "] submissionTime[" + this.submissionTime + "] burstTime[" + this.burstTime + "]";
	}

}
