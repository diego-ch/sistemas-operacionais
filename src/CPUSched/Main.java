/*
 * Diego Porto
 * Universidade Federal da Paraíba
 * 
 */

package CPUSched;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.*;

public class Main {

	private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

	public static void  printUsage() {
		System.out.println( "\nusage:\tjava CPUSched.Main [options] < process-list");
	}

	public static void main(String[] args) {

		LOGGER.setLevel(Level.SEVERE);

		int pid=0;
		int burstTime;
		int submissionTime;

		Scanner 	        input_list   = new Scanner(System.in);
		ArrayList<Process>  process_list = new ArrayList<Process>();

		LOGGER.log(Level.FINE,"cpusched: reading the process list");
		if (input_list.hasNextLine()) {
	        while (input_list.hasNext()) {
	            submissionTime = input_list.nextInt();
	            burstTime = input_list.nextInt();
	            process_list.add(new Process(pid, submissionTime, burstTime));
	            pid++;
	        }
			input_list.close();
		} else {
            LOGGER.log(Level.SEVERE, "error: empty process list");
			System.err.println("error: empty process list");
			input_list.close();
			System.exit(1);
		}

		// exibe a lista de processos dada como entrada
		for (int i = 0; i < process_list.size(); i++) {
            LOGGER.log(Level.FINE, process_list.get(i).toString());
        }

		// executa os métodos de escalonamento
		Schedulers.runFCFS(process_list);
        Schedulers.runSJF(process_list);

        LOGGER.log(Level.FINE, "cpusched: closing");
		System.exit(0);
	}

}
