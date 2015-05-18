/*
 * Diego Porto
 * Universidade Federal da Paraíba
 * 
 */

package CPUSched;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int pid = 0;                // process id
        int burstTime;            // process burst duration
        int submissionTime;        // process submission time

        // use stdin as source
        Scanner input_list = new Scanner(System.in);

        // process lists
        ArrayList<Process> list_fcfs = new ArrayList<Process>();
        ArrayList<Process> list_sjf = new ArrayList<Process>();
        //ArrayList<Process>  list_rr  = new ArrayList<Process>();

        // check if we've got a valid process list
        // then add them to the lists used by each algorithm
        if (input_list.hasNextLine()) {

            while (input_list.hasNext()) {
                submissionTime = input_list.nextInt();
                burstTime = input_list.nextInt();
                list_fcfs.add(new Process(pid, submissionTime, burstTime));
                list_sjf.add(new Process(pid, submissionTime, burstTime));
                //list_rr.add(new Process(pid, submissionTime, burstTime));
                pid++;
            }

            input_list.close();

        } else {

            System.err.println("error: empty process list");
            input_list.close();
            System.exit(1);

        }

        // run schedulers
        Schedulers.runFCFS(list_fcfs);
        Schedulers.runSJF(list_sjf);
        // Schedulers.runRR(list_rr);


        System.exit(0);
    }

}
