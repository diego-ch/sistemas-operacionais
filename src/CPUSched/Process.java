package CPUSched;

import java.lang.Comparable;

public class Process implements Comparable<Process> {

    private int pid;                // process id
    private int submissionTime;        // process submission time
    private int burstDuration;        // process burst duration
    private int replyTime;            // process reply time
    private boolean executed;


    // Class Constructor
    public Process(int pid, int submissionTime, int burstTime) {
        this.pid = pid;
        this.submissionTime = submissionTime;
        this.burstDuration = burstTime;
        this.executed = false;
    }


    //
    // Implement the Comparable interface so that we can compare processes
    // and determine priorities for the SJF and RR algorithms
    //
    @Override
    public int compareTo(Process b) {

        if (burstDuration > b.burstDuration)
            return 1;

        if (burstDuration == b.burstDuration) {
            if (submissionTime > b.submissionTime)
                return 1;
            if (submissionTime == b.submissionTime)
                return 0;
            if (submissionTime < b.submissionTime)
                return -1;
        }

        return -1;
    }


    //
    // get and set methods for each process variables
    //

    public boolean getExecuted() {
        return this.executed;
    }

    public void setExecuted(boolean executed) {
        this.executed = executed;
    }

    public int getPID() {
        return this.pid;
    }

    public int getSubmissionTimestamp() {
        return this.submissionTime;
    }

    public int getBurstDuration() {
        return this.burstDuration;
    }

    public void decreaseBurstDuration() {
        this.burstDuration--;
    }

    public int getReplyTimestamp() {
        return this.replyTime;
    }

    public void setReplyTimestamp(int replyTime) {
        this.replyTime = replyTime;
    }


    //
    // outputs the process variables as string
    //

    public String toString() {
        return "pid(" + this.pid + ") submissionTime(" + this.submissionTime + ") burstDuration(" + this.burstDuration + ") executed(" + this.executed + ")";
    }

}
