package CPUSched;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Schedulers {

    //
    // Log
    //

    private final static Logger LOGGER = Logger.getLogger(Schedulers.class.getName());

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

        LOGGER.setLevel(Level.ALL);

        int clock = -1;
        int count = 0;

        double fcfs_turnaround = 0;
        double fcfs_reply      = 0;
        double fcfs_awaiting   = 0;

        ListIterator<Process> iter = process_list.listIterator();
        Process process = iter.next();

        while (true) {

            clock++;
            //System.out.println("clock: " + clock + " count: " + count);

            // wait until clock reaches the first process in the list
            if (process.getSubmissionTimestamp() > clock) {
                //LOGGER.log(Level.FINE, "cpusched: waiting processes");
                continue;
            }

            process.setReplyTimestamp(clock);
            //System.out.println("cpusched: run process [" + process.getPID() + "] from [" + clock + "] until [" + (clock+process.getBurstDuration()-1) + "]");
            clock += process.getBurstDuration() - 1;

            // write process turnaround, reply and awaiting times
            fcfs_turnaround += clock - process.getSubmissionTimestamp() + 1;
            fcfs_reply      += process.getReplyTimestamp() - process.getSubmissionTimestamp();
            fcfs_awaiting   += process.getReplyTimestamp() - process.getSubmissionTimestamp();
            //System.out.println(fcfs_turnaround +" "+ fcfs_reply+" "+fcfs_awaiting);

            count++;

            if (iter.hasNext())
                process = iter.next();
            else
                break;
        }

        System.out.println("FCFS " + format_output(fcfs_turnaround/count) + " " + format_output(fcfs_reply/count) + " " + format_output(fcfs_awaiting/count));
    }

    public static void runSJF(ArrayList<Process> process_list) {


        LOGGER.setLevel(Level.ALL);

        int clock = -1;
        int count = 0;

        double sjf_turnaround = 0;
        double sjf_reply      = 0;
        double sjf_awaiting   = 0;

        ArrayList<Process> processesSortedByDuration = new ArrayList<Process>();
        processesSortedByDuration.addAll(process_list);
        Process process = processesSortedByDuration.get(1);

        while (true) {

            clock++;
            System.out.println("\nclock: " + clock + " count: " + count);
            System.out.println("ordered_list " + processesSortedByDuration.toString());

            // wait until clock reaches the first process in the list
            if (process.getSubmissionTimestamp() > clock) {
                System.out.println("cpusched: waiting processes");
                continue;
            }

            process.setReplyTimestamp(clock);
            System.out.println("cpusched: run process [" + process.getPID() + "] from [" + clock + "] until [" + (clock + process.getBurstDuration() - 1) + "]");
            clock += process.getBurstDuration()-1;

            // write process turnaround, reply and awaiting times
            sjf_turnaround += clock - process.getSubmissionTimestamp()+1;
            sjf_reply += process.getReplyTimestamp() - process.getSubmissionTimestamp();
            sjf_awaiting += process.getReplyTimestamp() - process.getSubmissionTimestamp();
            System.out.println("\n\tprocess_submission: " + process.getSubmissionTimestamp() + "\t\t process_burst: " + process.getBurstDuration()
                      + "\n\ttotal_turnaround: " + sjf_turnaround + "\t\t process_turnaround: " + (clock - process.getSubmissionTimestamp()+1)
                      + "\n\ttotal_reply: "      + sjf_reply      + "\t\t process_reply: "      + (process.getReplyTimestamp() - process.getSubmissionTimestamp())
                      + "\n\ttotal_awaiting: "   + sjf_awaiting   + "\t\t process_awaiting: "   + (process.getReplyTimestamp() - process.getSubmissionTimestamp()));

            count++;

            processesSortedByDuration.remove(0);
            if (processesSortedByDuration.isEmpty())
                break;

            Collections.sort(processesSortedByDuration);
            process = processesSortedByDuration.get(0);
        }

        System.out.println("SJF " + format_output(sjf_turnaround/count) + " " + format_output(sjf_reply/count) + " " + format_output(sjf_awaiting/count));

    }

}