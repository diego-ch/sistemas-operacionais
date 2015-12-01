package PageRep;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		int frame 		= 0;
		int frame_size	= 0;

		// use stdin as source
		Scanner input_list = new Scanner(System.in);

		// lists
		ArrayList<Integer> list_fifo = new ArrayList<Integer>();
		ArrayList<Integer> list_lru  = new ArrayList<Integer>();
		ArrayList<Integer> list_otm  = new ArrayList<Integer>();

		// import file list to individual arrays
		if (input_list.hasNextLine()) {

			frame_size = input_list.nextInt();

			while (input_list.hasNext()) {
				frame = input_list.nextInt();
				list_fifo.add(new Integer(frame));
				list_lru.add(new Integer(frame));
				list_otm.add(new Integer(frame));
			}

			input_list.close();

		} else {

			System.err.println("error: empty list");
			input_list.close();
			System.exit(1);

		}

		// run algorithms
		FIFO.run(frame_size, list_fifo);
		OTM.run(frame_size, list_otm);
		LRU.run(frame_size, list_lru);
		

		System.exit(0);
	}

}