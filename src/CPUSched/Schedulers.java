package CPUSched;

import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.util.*;

public class Schedulers {

    //
    // Log
    //

    private final static boolean debug_fcfs = false;
    private final static boolean debug_sjf  = false;
    private final static boolean debug_rr   = true;

    //
    // rewrite output with pt_BR locale
    //

    private static String format_output(double output) {

        Locale fmtLocale = new Locale("pt", "BR");
        NumberFormat formatter = NumberFormat.getInstance(fmtLocale);

        formatter.setMaximumFractionDigits(1);
        formatter.setMinimumFractionDigits(1);

        return formatter.format(output);
    }

    //
    // FCFS Implementation - First Come, First Served
    //
    public static void runFCFS(ArrayList<Process> process_list) {

        int clock = -1;
        int count = 0;

        double fcfs_turnaround = 0;
        double fcfs_reply      = 0;
        double fcfs_awaiting   = 0;

        ListIterator<Process> iter = process_list.listIterator();
        Process process = iter.next();

        while (true) {

            clock++;
            if (debug_fcfs) { System.out.println("clock: " + clock + " count: " + count + "\nprocess_list: " + process_list.toString()); }

            // wait until clock reaches the first process in the list
            if (process.getSubmissionTimestamp() > clock) {
                if (debug_fcfs) { System.out.println("cpusched: waiting processes"); }
                continue;
            }

            process.setReplyTimestamp(clock);
            if (debug_fcfs) { System.out.println("cpusched: run process [" + process.getPID() + "] from [" + clock + "] until [" + (clock+process.getBurstDuration()-1) + "]"); }
            clock += process.getBurstDuration() - 1;

            // write process turnaround, reply and awaiting times
            fcfs_turnaround += clock - process.getSubmissionTimestamp() + 1;
            fcfs_reply      += process.getReplyTimestamp() - process.getSubmissionTimestamp();
            fcfs_awaiting   += process.getReplyTimestamp() - process.getSubmissionTimestamp();
            if (debug_fcfs) {
                System.out.println("\n\tprocess_submission: " + process.getSubmissionTimestamp() + "\t\t process_burst: " + process.getBurstDuration()
                        + "\n\ttotal_turnaround: " + fcfs_turnaround + "\t\t process_turnaround: " + (clock - process.getSubmissionTimestamp() + 1)
                        + "\n\ttotal_reply: " + fcfs_reply + "\t\t process_reply: " + (process.getReplyTimestamp() - process.getSubmissionTimestamp())
                        + "\n\ttotal_awaiting: " + fcfs_awaiting + "\t\t process_awaiting: " + (process.getReplyTimestamp() - process.getSubmissionTimestamp()));
            }
            count++;

            if (iter.hasNext())
                process = iter.next();
            else
                break;
        }

        System.out.println("FCFS " + format_output(fcfs_turnaround/count) + " " + format_output(fcfs_reply/count) + " " + format_output(fcfs_awaiting/count));
    }

    //
    // SJF Implementation - Shortest Job First
    //
    public static void runSJF(ArrayList<Process> process_list) {

        int clock = -1;
        int count =  0;

        double sjf_turnaround = 0;
        double sjf_reply      = 0;
        double sjf_awaiting   = 0;

        while (true) {

            clock++;

            if (debug_sjf) {
                System.out.println("\nclock: " + clock + " count: " + count);
            }

            Process process;
            ArrayList<Process> processListSorted = new ArrayList<Process>();
            ListIterator<Process> iter = process_list.listIterator();


            if (debug_sjf) {System.out.println("Begin Sort");}
            while (iter.hasNext()) {
                Process p;
                p = iter.next();
                if (p.getSubmissionTimestamp() <= clock && !p.getExecuted()) {
                    if (debug_sjf) {System.out.println("\tadding process: " + p.toString());}
                    processListSorted.add(p);
                }
            }

            Collections.sort(processListSorted);
            if (debug_sjf) { System.out.println("\tslist: " + processListSorted);
            System.out.println("End Sort"); }

            // wait until clock reaches the first process in the list
            if (processListSorted.isEmpty()) {
                if (debug_sjf) { System.out.println("cpusched: waiting processes"); }
                continue;
            } else {
                process = processListSorted.get(0);
            }

            process.setReplyTimestamp(clock);
            if (debug_sjf) { System.out.println("cpusched: run process [" + process.getPID() + "] from [" + clock + "] until [" + (clock + process.getBurstDuration() - 1) + "]"); }
            for (Process p : process_list) {
                if (p.getPID() == process.getPID()) {
                    if (debug_sjf) {  System.out.println("\tset executed: " + p.toString()); }
                    p.setExecuted(true);
                }
            }
            clock += process.getBurstDuration()-1;

            // write process turnaround, reply and awaiting times
            sjf_turnaround += clock - process.getSubmissionTimestamp() + 1;
            sjf_reply      += process.getReplyTimestamp() - process.getSubmissionTimestamp();
            sjf_awaiting   += process.getReplyTimestamp() - process.getSubmissionTimestamp();

            if (debug_sjf) {
                System.out.println("\tprocess_submission: " + process.getSubmissionTimestamp() + "\t\t process_burst: " + process.getBurstDuration()
                        + "\n\ttotal_turnaround: " + sjf_turnaround + "\t\t process_turnaround: " + (clock - process.getSubmissionTimestamp() + 1)
                        + "\n\ttotal_reply: " + sjf_reply + "\t\t process_reply: " + (process.getReplyTimestamp() - process.getSubmissionTimestamp())
                        + "\n\ttotal_awaiting: " + sjf_awaiting + "\t\t process_awaiting: " + (process.getReplyTimestamp() - process.getSubmissionTimestamp()));
            }

            count++;

            int total_list=0;
            for (Process p : process_list) {
                if (p.getExecuted())
                    total_list++;
            }

            if (total_list == process_list.size())
                break;

        }

        System.out.println("SJF " + format_output(sjf_turnaround/count) + " " + format_output(sjf_reply/count) + " " + format_output(sjf_awaiting/count));
    }

}