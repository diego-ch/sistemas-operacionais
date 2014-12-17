package PageRep;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		// chamada incorreta
		if (args.length > 1) {
			System.err.println("pagerep: erro: número de parametros inválido");
			Tools.printUsage();
			return;
		}
		
		// lendo do stdin
		if (args.length == 0) {
			ArrayList<String> list = Tools.getInput(new Scanner(System.in));
			FIFO.run(list);
			LRU.run(list);
			OTM.run(list);
			return;
		}
		
		// lendo de um arquivo
		if (args.length > 0 && args.length < 2) {
			
			try {
				File input = new File(args[0]);
				ArrayList<String> list = Tools.getInput(new Scanner(input));
				FIFO.run(list);
				LRU.run(list);
				OTM.run(list);
				
			} catch (FileNotFoundException e) {
				System.err.println("pagerep: erro: arquivo não encontrado");
				Tools.printUsage();
				return;
			}
			
			return;
			
		}

	}
	
}